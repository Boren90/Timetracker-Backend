package BackEndTimeTracker.Service;

import java.io.Console;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import BackEndTimeTracker.DTO.ChangeTimeEntryCategoryRequest;
import BackEndTimeTracker.DTO.StartTimeEntryRequest;
import BackEndTimeTracker.DTO.TimeEntryResponse;
import BackEndTimeTracker.Model.Category;
import BackEndTimeTracker.Model.TimeEntry;
import BackEndTimeTracker.Repository.CategoryRepository;
import BackEndTimeTracker.Repository.TimeEntryRepository;

@Service
public class TimeEntryService {

    private final TimeEntryRepository timeEntryRepository;
    private final CategoryRepository categoryRepository;

    public TimeEntryService(TimeEntryRepository timeEntryRepository, CategoryRepository categoryRepository) {
        this.timeEntryRepository = timeEntryRepository;
        this.categoryRepository = categoryRepository;
    }

    public TimeEntry startTimeEntry(StartTimeEntryRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));

        TimeEntry newTimeEntry = new TimeEntry();
        newTimeEntry.setCategory(category);
        newTimeEntry.setStartTime(LocalDateTime.now());

        return timeEntryRepository.save(newTimeEntry);
    }

    public TimeEntry stopTimeEntry(String id) {
        TimeEntry existingTimeEntry = timeEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TimeEntry not found with id: " + id));
        existingTimeEntry.setEndTime(LocalDateTime.now());
        return timeEntryRepository.save(existingTimeEntry);
    }



    public Optional<TimeEntry> getActiveTimeEntry() {
        return timeEntryRepository.findByEndTimeIsNull();
    }


    
    
    public List<TimeEntryResponse> getAllTimeEntries() {

    List<TimeEntry> timeEntries = timeEntryRepository.findAll();

    return timeEntries.stream()
            .map(this::mapToResponse)
            .toList();
}


    private TimeEntryResponse mapToResponse(TimeEntry timeEntry) {

    TimeEntryResponse response = new TimeEntryResponse();

    response.setId(timeEntry.getId());
    response.setCategory(timeEntry.getCategory());
    response.setStartTime(timeEntry.getStartTime());
    response.setEndTime(timeEntry.getEndTime());
    
    
    Duration duration;
    if (timeEntry.getEndTime() != null) {
            duration = Duration.between(
                    timeEntry.getStartTime(),
                    timeEntry.getEndTime());
                } else {//failsafe för om getEndTime är null
                    duration = Duration.between(
                            timeEntry.getStartTime(),
                            LocalDateTime.now());
                }
                    
        response.setDuration(duration.toSeconds());

    return response;
}

public TimeEntryResponse changeCategory(String id, ChangeTimeEntryCategoryRequest request) {

    TimeEntry timeEntry = timeEntryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("TimeEntry not found with id: " + id));
            
    Category newCategory = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new RuntimeException("TimeEntry not found with id: " + request));
    timeEntry.setCategory(newCategory);

    timeEntryRepository.save(timeEntry);


    return mapToResponseCategory(timeEntry);
}

private TimeEntryResponse mapToResponseCategory(TimeEntry timeEntry) {
    TimeEntryResponse response = new TimeEntryResponse();

    response.setId(timeEntry.getId());
    response.setCategory(timeEntry.getCategory());
    response.setStartTime(timeEntry.getStartTime());
    response.setEndTime(timeEntry.getEndTime());
    
    Duration duration;
    if (timeEntry.getEndTime() != null) {
            duration = Duration.between(
                    timeEntry.getStartTime(),
                    timeEntry.getEndTime());
                } else {//failsafe för om getEndTime är null
                    duration = Duration.between(
                            timeEntry.getStartTime(),
                            LocalDateTime.now());
                }
                    
        response.setDuration(duration.toSeconds());

    return response;
}

public void deleteTimeEntry(String id) {
    TimeEntry timeEntry = timeEntryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("TimeEntry not found with id: " + id));
    timeEntryRepository.delete(timeEntry);
}

}

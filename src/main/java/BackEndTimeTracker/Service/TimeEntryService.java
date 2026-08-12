package BackEndTimeTracker.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import BackEndTimeTracker.DTO.StartTimeEntryRequest;
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
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found with id: " + request.getCategoryId()));


        TimeEntry newTimeEntry = new TimeEntry();
        newTimeEntry.setCategory(category);
        newTimeEntry.setStartTime(LocalDateTime.now());

        //findByEndTimeIsNull() returnerar en Optional som innehåller en TimeEntry om det finns en pågående timer, annars returnerar den en tom Optional. Om det finns en pågående timer kastas ett undantag med ett meddelande som informerar användaren om att de måste stoppa den pågående timern innan de kan starta en ny.
        timeEntryRepository.findByEndTimeIsNull().ifPresent(timeEntry -> {throw new RuntimeException("There is already an ongoing time entry. Please stop it before starting a new one.");});
        return timeEntryRepository.save(newTimeEntry);
    }

    public TimeEntry stopTimeEntry(String id) {
        TimeEntry existingTimeEntry = timeEntryRepository.findById(id).orElseThrow(() -> new RuntimeException("TimeEntry not found with id: " + id));
        existingTimeEntry.setEndTime(LocalDateTime.now());
        return timeEntryRepository.save(existingTimeEntry);
    }

    public List <TimeEntry> getAllTimeEntries() {
        
        return timeEntryRepository.findAll();
    }
    
}

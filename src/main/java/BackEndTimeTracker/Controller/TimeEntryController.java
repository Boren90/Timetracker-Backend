package BackEndTimeTracker.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackEndTimeTracker.DTO.StartTimeEntryRequest;
import BackEndTimeTracker.DTO.TimeEntryResponse;
import BackEndTimeTracker.Model.TimeEntry;
import BackEndTimeTracker.Service.TimeEntryService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class TimeEntryController {

    
    private final TimeEntryService timeEntryService;

    public TimeEntryController(TimeEntryService timeEntryService) {
        this.timeEntryService = timeEntryService;
    }

    @PostMapping("/timeentries")
    public ResponseEntity<TimeEntry> createTimeEntry(@RequestBody StartTimeEntryRequest request) {

        return ResponseEntity.ok(timeEntryService.startTimeEntry(request));
    }

    @PutMapping("/timeentries/{id}")
    public ResponseEntity <TimeEntry> stopTimeEntry(@PathVariable String id) {
        TimeEntry response = timeEntryService.stopTimeEntry(id);
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/timeentries")
    public ResponseEntity <List<TimeEntryResponse>> getAllTimeEntries() {
        List <TimeEntryResponse> response = timeEntryService.getAllTimeEntries();
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/timeentries/active")
    public ResponseEntity<TimeEntry> getActiveTimeEntry() {

    Optional<TimeEntry> activeTimeEntry = timeEntryService.getActiveTimeEntry();

    if (activeTimeEntry.isPresent()) {
        return ResponseEntity.ok(activeTimeEntry.get());
    }

    return ResponseEntity.noContent().build();
}
    
}

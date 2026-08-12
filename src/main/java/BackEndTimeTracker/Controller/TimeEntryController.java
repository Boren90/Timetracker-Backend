package BackEndTimeTracker.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackEndTimeTracker.DTO.StartTimeEntryRequest;
import BackEndTimeTracker.Model.TimeEntry;
import BackEndTimeTracker.Service.TimeEntryService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
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
    public ResponseEntity <List<TimeEntry>> getAllTimeEntries() {
        List <TimeEntry> response = timeEntryService.getAllTimeEntries();
        
        return ResponseEntity.ok(response);
    }
    
}

package BackEndTimeTracker.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "timeEntries")
public class TimeEntry {

    public TimeEntry() {}

    @Id
    private String id;

    private Category category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    
    
}

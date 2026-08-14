package BackEndTimeTracker.DTO;

import java.time.LocalDateTime;

import BackEndTimeTracker.Model.Category;

public class TimeEntryResponse {

    private String id;
    private Category category;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private long duration;

    public TimeEntryResponse(){
        
    }

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
    public long getDuration() {
        return duration;
    }
    public void setDuration(long duration) {
        this.duration = duration;
    }

    
    
}

package BackEndTimeTracker.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import BackEndTimeTracker.Model.TimeEntry;

public interface TimeEntryRepository extends MongoRepository<TimeEntry, String> {
    
}

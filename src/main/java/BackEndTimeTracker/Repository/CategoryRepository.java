package BackEndTimeTracker.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import BackEndTimeTracker.Model.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    
}

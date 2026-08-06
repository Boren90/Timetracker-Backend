package BackEndTimeTracker.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import BackEndTimeTracker.Model.Category;

public interface CategoryRepository extends MongoRepository<Category, String> {
    
}

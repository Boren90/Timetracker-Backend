package BackEndTimeTracker.Service;

import org.springframework.stereotype.Service;

import BackEndTimeTracker.Model.Category;
import BackEndTimeTracker.Repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    
    public Category createCategory(Category newCategory){

        categoryRepository.save(newCategory);

        return newCategory;
    }

}

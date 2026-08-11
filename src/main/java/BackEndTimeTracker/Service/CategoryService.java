package BackEndTimeTracker.Service;

import java.util.List;
import org.springframework.stereotype.Service;

import BackEndTimeTracker.Model.Category;
import BackEndTimeTracker.Repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category newCategory) {

        categoryRepository.save(newCategory);

        return newCategory;
    }

    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    public Category getCategoryById(String id) {

        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

    }

    public Category updateCategoryById(String id , Category updatedCategory) {

        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));

        existingCategory.setCategoryName(updatedCategory.getCategoryName());

        categoryRepository.save(existingCategory);

        return existingCategory;

    }

    public void deleteCategoryById(String id) {
        
        //La till denna för att varna de blev något fel. iom att existById returnerar void tänker jag.
        if (!categoryRepository.existsById(id)) {
    throw new RuntimeException("Category not found");
}
        categoryRepository.deleteById(id);
        
    }

}

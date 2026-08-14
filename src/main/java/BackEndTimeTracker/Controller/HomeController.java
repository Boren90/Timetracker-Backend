package BackEndTimeTracker.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackEndTimeTracker.Model.Category;
import BackEndTimeTracker.Repository.CategoryRepository;
import BackEndTimeTracker.Service.CategoryService;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public HomeController(CategoryRepository categoryRepository, CategoryService categoryService){
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }
    
    
    @GetMapping("")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Time Tracker API!");
    }

    @GetMapping("/test")
    public ResponseEntity<Long> test() {
        return ResponseEntity.ok(categoryRepository.count());
    
    }

    @PostMapping("/categories")
    public ResponseEntity<Category> newCategory(@RequestBody Category newCategory) {
        categoryService.createCategory(newCategory);
        System.out.println(newCategory);
        return ResponseEntity.ok(newCategory);
    }

    @GetMapping("/categories")
    public ResponseEntity <List<Category>> getAllCategories() {

        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity <Category> getCategoryById(@PathVariable String id) {

        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity <String> deleteCategoryById(@PathVariable String id) {
        categoryService.deleteCategoryById(id);
        return ResponseEntity.ok("Category with id: " + id + " has been deleted.");
    }

    @PutMapping("categories/{id}")
    public ResponseEntity <Category> putCategoryById(@PathVariable String id, @RequestBody Category entity) {
        
        
        
        return ResponseEntity.ok(categoryService.updateCategoryById(id, entity));
    }

    
}

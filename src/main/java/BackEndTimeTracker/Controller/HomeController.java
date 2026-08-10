package BackEndTimeTracker.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackEndTimeTracker.Model.Category;
import BackEndTimeTracker.Repository.CategoryRepository;
import BackEndTimeTracker.Service.CategoryService;

@RestController
@RequestMapping("/api")
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

    
}

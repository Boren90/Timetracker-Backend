package BackEndTimeTracker.Artifact;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BackEndTimeTracker.Model.Category;
import BackEndTimeTracker.Repository.CategoryRepository;

@RestController
@RequestMapping("/api")
public class HomeController {

    CategoryRepository categoryRepository;

    public HomeController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    
    
    @GetMapping("")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Time Tracker API!");
    }

    @GetMapping("/test")
    public ResponseEntity<Long> test() {
        return ResponseEntity.ok(categoryRepository.count());
    
    }

    @PostMapping("/save")
    public ResponseEntity<Category> save(@RequestBody Category newCategory) {
        categoryRepository.save(newCategory);
        System.out.println(newCategory);
        return ResponseEntity.ok(newCategory);
    }

    
}

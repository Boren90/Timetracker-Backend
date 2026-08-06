package BackEndTimeTracker.Model;

import org.springframework.data.annotation.Id;

public class Category {

    @Id
    private String id;

    private String categoryName;

    public Category() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    
    
}

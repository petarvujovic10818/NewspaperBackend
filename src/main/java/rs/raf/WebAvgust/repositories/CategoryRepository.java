package rs.raf.WebAvgust.repositories;

import rs.raf.WebAvgust.entities.Category;
import rs.raf.WebAvgust.entities.News;
import rs.raf.WebAvgust.entities.User;

import java.util.List;

public interface CategoryRepository {

    public Category addCategory(Category category);
    public List<Category> allCategories();
    public Category findCategory(int id);
    public void deleteCategory(int id);
    public void updateCategory(Category category);

}

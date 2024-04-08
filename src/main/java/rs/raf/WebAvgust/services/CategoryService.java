package rs.raf.WebAvgust.services;


import rs.raf.WebAvgust.entities.Category;
import rs.raf.WebAvgust.entities.News;
import rs.raf.WebAvgust.repositories.CategoryRepository;
import rs.raf.WebAvgust.repositories.NewsRepository;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    public CategoryService(){

    }

    @Inject
    private CategoryRepository categoryRepository;

    public Category addCategory(Category category){
        return this.categoryRepository.addCategory(category);
    }

    public List<Category> allCategories() {
        return this.categoryRepository.allCategories();
    }

    public void deleteCategory(int id){this.categoryRepository.deleteCategory(id);}

    public void updateCategory(Category category){
        this.categoryRepository.updateCategory(category);
    }

    public Category findCategory(int id){
        return this.categoryRepository.findCategory(id);
    }

}

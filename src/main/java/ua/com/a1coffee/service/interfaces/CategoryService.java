package ua.com.a1coffee.service.interfaces;

import ua.com.a1coffee.model.category.Category;


public interface CategoryService extends MainService<Category> {
   
    Category get(String url);

   
    void remove(String url);
}

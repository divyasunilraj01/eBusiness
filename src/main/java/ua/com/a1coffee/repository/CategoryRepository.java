package ua.com.a1coffee.repository;

import ua.com.a1coffee.model.category.Category;


public interface CategoryRepository extends MainRepository<Category> {
   
    Category findByUrl(String url);

   
    void deleteByUrl(String url);
}

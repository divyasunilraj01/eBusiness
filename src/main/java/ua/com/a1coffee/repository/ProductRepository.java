package ua.com.a1coffee.repository;

import ua.com.a1coffee.model.product.Product;

import java.util.Collection;
import java.util.List;


public interface ProductRepository extends MainRepository<Product> {
    
    Product findByUrl(String url);

    
    Product findByArticle(int article);

   
    void deleteByUrl(String url);

   
    void deleteByArticle(int article);

   
    void deleteByCategoryUrl(String url);

    
    void deleteByCategoryId(long id);

   
    Collection<Product> findByCategoryId(long id);

   
    Collection<Product> findByCategoryUrl(String url);
}

package ua.com.a1coffee.service.interfaces;

import ua.com.a1coffee.model.product.Product;

import java.util.Collection;
import java.util.List;


public interface ProductService extends MainService<Product> {
   
    Product getByUrl(String url);

   
    Product getByArticle(int article);

   
    Collection<Product> getByCategoryUrl(String url);

    
    Collection<Product> getByCategoryId(long id);

   
    Collection<Product> getRandomByCategoryId(
            int size,
            long categoryId,
            long differentProductId
    );

   
    Collection<Product> getRandomByCategoryId(int size, long id);

   
    Collection<Product> getRandom(int size);

   
    void removeByUrl(String url);

    void removeByArticle(int article);

   
    void removeByCategoryUrl(String url);

   
    void removeByCategoryId(long id);
}

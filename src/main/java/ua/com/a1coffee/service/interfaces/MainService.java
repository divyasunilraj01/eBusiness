package ua.com.a1coffee.service.interfaces;

import ua.com.a1coffee.model.model.Model;
import ua.com.a1coffee.service.impl.MainServiceImpl;

import java.util.Collection;
import java.util.List;

public interface MainService<T extends Model> {
   
    void add(T model);

   
    void add(Collection<T> models);

   
    void update(T model);

    
    T get(long id);

   
    Collection<T> getAll();

   
    void remove(T model);

   
    void remove(long id);

   
    void remove(Collection<T> models);

   
    void removeAll();
}

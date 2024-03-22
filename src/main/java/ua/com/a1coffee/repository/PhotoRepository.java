package ua.com.a1coffee.repository;

import ua.com.a1coffee.model.photo.Photo;


public interface PhotoRepository extends MainRepository<Photo> {
   
    Photo findByTitle(String title);

   
    void deleteByTitle(String title);
}

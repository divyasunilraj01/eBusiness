package ua.com.a1coffee.service.interfaces;

import ua.com.a1coffee.model.photo.Photo;
import org.springframework.web.multipart.MultipartFile;


public interface PhotoService extends MainService<Photo> {
   
    Photo get(String title);

    
    void remove(String title);

   
    void saveFile(MultipartFile photo);

   
    void deleteFile(String url);
}

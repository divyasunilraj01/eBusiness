package ua.com.a1coffee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ua.com.a1coffee.model.photo.Photo;
import ua.com.a1coffee.repository.PhotoRepository;
import ua.com.a1coffee.service.interfaces.PhotoService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static ua.com.a1coffee.util.validator.ObjectValidator.*;


@Service
@ComponentScan(basePackages = "ua.com.alexcoffee.repository")
public final class PhotoServiceImpl extends MainServiceImpl<Photo> implements PhotoService {

   
    private final PhotoRepository repository;

  
    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public PhotoServiceImpl(final PhotoRepository repository) {
        super(repository);
        this.repository = repository;
    }

   
    @Override
    @Transactional(readOnly = true)
    public Photo get(final String title) throws IllegalArgumentException, NullPointerException {
        if (isEmpty(title)) {
            throw new IllegalArgumentException("No photo title!");
        }
        final Photo photo = this.repository.findByTitle(title);
        if (isNull(photo)) {
            throw new NullPointerException("Can't find photo by title " + title + "!");
        }
        return photo;
    }

   
    @Override
    @Transactional
    public void remove(final String title) {
        if (isNotEmpty(title)) {
            this.repository.deleteByTitle(title);
        }
    }

   
    @Override
    @Transactional
    public void saveFile(final MultipartFile photo) {
        if (isNotEmpty(photo)) {
            final String filePath = Photo.PATH + photo.getOriginalFilename();
            try (OutputStream stream = new FileOutputStream(filePath)) {
                stream.write(photo.getBytes());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

   
    @Override
    @Transactional
    public void deleteFile(final String url) {
        if (isNotEmpty(url)) {
            final File file = new File(Photo.PATH + url);
            if (file.exists() && file.isFile()) {
                file.delete();
            }
        }
    }
}

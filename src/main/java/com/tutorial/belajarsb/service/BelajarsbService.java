package com.tutorial.belajarsb.service;

import com.tutorial.belajarsb.model.Photo;
import com.tutorial.belajarsb.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BelajarsbService {

    private final PhotoRepository photoRepository;

    public BelajarsbService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }


    public Iterable<Photo> get() {
        return photoRepository.findAll();
    }

    public Photo get(Integer id) {
        return photoRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        photoRepository.deleteById(id);
    }


    public Photo save(String fileName, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setContentType(contentType);
        photo.setFileName(fileName);
        photo.setData(data);
        photoRepository.save(photo);
        return photo;
    }
}

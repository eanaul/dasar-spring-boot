package com.tutorial.belajarsb.web;

import com.tutorial.belajarsb.service.BelajarsbService;
import com.tutorial.belajarsb.model.Photo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;



@RestController
public class BelajarsbController {

    private final BelajarsbService belajarsbService;

    public BelajarsbController(BelajarsbService belajarsbService) {
        this.belajarsbService = belajarsbService;
    }


    @GetMapping("/")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/photo")
    public Iterable<Photo> get(){
        return belajarsbService.get();
    }

    @GetMapping("/photo/{id}")
    public Photo get(@PathVariable Integer id){
        Photo photo = belajarsbService.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photo/{id}")
    public void delete(@PathVariable Integer id){
        belajarsbService.remove(id);
    }

    @PostMapping("/photo")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {
        return belajarsbService.save(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }

}

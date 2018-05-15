package com.bezama.portalserver.controllers;


import com.bezama.portalserver.models.Category;
import com.bezama.portalserver.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/category"})
public class CategoryController {
    @Autowired
    protected CategoryRepository categoryRepository;
    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category category, UriComponentsBuilder ucBuilder){
        categoryRepository.save(category);
        return new ResponseEntity<Category>(category, HttpStatus.CREATED);
    }


    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Category> findOne(@PathVariable("id") Long id){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent())
            return ResponseEntity.notFound().build();
        return new ResponseEntity<Category>(categoryOptional.get(),HttpStatus.OK);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable long id){
        Optional<Category>  categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent())
            return ResponseEntity.notFound().build();
        category.setId(id);
        categoryRepository.save(category);
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<Category> delete(@PathVariable("id") Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (!categoryOptional.isPresent())
            return ResponseEntity.notFound().build();
        categoryRepository.delete(categoryOptional.get());
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Category>>findAll(){

        List<Category> categories = categoryRepository.findAll();
        if( categories.isEmpty()){
            return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
    }
}

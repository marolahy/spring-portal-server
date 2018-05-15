package com.bezama.portalserver.controllers;


import com.bezama.portalserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bezama.portalserver.models.User;
import java.util.List;
import java.util.Optional;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping({"/user"})
public class UserController {

    @Autowired
    protected UserRepository userRepository;
    @PostMapping
    public ResponseEntity<User>  create(@RequestBody User user, UriComponentsBuilder ucBuilder){
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }


    @GetMapping(path = {"/{id}"})
    public ResponseEntity<User> findOne(@PathVariable("id") Long id){
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
            return ResponseEntity.notFound().build();
        return new ResponseEntity<User>(userOptional.get(),HttpStatus.OK);
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable long id){
        Optional<User>  userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
            return ResponseEntity.notFound().build();
        user.setId(id);
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<User> delete(@PathVariable("id") Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent())
            return ResponseEntity.notFound().build();
        userRepository.delete(userOptional.get());
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<User>>findAll(){

        List<User> users = userRepository.findAll();
        if( users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);

    }
}

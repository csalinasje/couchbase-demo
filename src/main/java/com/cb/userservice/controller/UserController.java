package com.cb.userservice.controller;

import com.cb.userservice.model.User;
import com.cb.userservice.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserServiceImpl service;
    UserController(UserServiceImpl service){
        this.service= service;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> save(@RequestBody User user) {
        //generates an id and save the user
        try {
            service.create(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<User>> list (){
        List<User> userList= service.findAll();
        return new ResponseEntity<>(userList,HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getById (@PathVariable("id") String id){
       Optional<User> userOpt= service.findById(id);
       User user = userOpt.isPresent() ? userOpt.get() : null;
        return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
    }
}

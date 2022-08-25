package com.cb.userservice.controller;

import com.cb.userservice.model.User;
import com.cb.userservice.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {
    private UserServiceImpl service;
    public UserController(UserServiceImpl service){
        this.service= service;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> save(@Valid @RequestBody User user) throws Exception {
        log.info("#Starting save user");
        try {
            service.create(user);
            log.info("#Ending save user");
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<User>> list (){
        log.info("#Starting list user");
        List<User> userList= service.findAll();
        log.info("#Ending list user");
        return new ResponseEntity<>(userList,HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<User> getById (@PathVariable("id") String id){
        log.info("#Starting get user by ID: {}", id);
       Optional<User> userOpt= service.findById(id);
       User user = userOpt.orElse(null);
        log.info("#Ending get user by ID");
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }
}

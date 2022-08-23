package com.cb.userservice.service;

import com.cb.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(String id);
    void create (User user);
}

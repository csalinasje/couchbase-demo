package com.cb.userservice.service.impl;

import com.cb.userservice.model.User;
import com.cb.userservice.repository.UserRepository;
import com.cb.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;
    @Autowired
    ServiceServiceImpl serviceService;

    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public void create(User user) {
        if(user.getService() !=null) {
            user.getService().forEach(s->
                serviceService.create(s));
        }
        repository.save(user);
    }
}

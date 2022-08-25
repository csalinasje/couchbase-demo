package com.cb.userservice.service.impl;

import com.cb.userservice.enums.UserTypes;
import com.cb.userservice.exceptions.UniqueIdException;
import com.cb.userservice.model.User;
import com.cb.userservice.repository.UserRepository;
import com.cb.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
    public void create(User user) throws UniqueIdException {
        log.info("# Create a user: {}",user);
        // check unique id
        if (isUniqueId(user.getId())) {
            // save services
            if (user.getService() != null) {
                user.getService().forEach(s ->
                        serviceService.create(s));
            }
            // set user type
            user.setType(setUserType(user.getId()));
            repository.save(user);
        } else {
            log.error("The id must be unique: {} already exist", user.getId());
            throw new UniqueIdException("The id must be unique");
        }
    }

    public boolean isUniqueId(String id) {
        Optional<User> user = repository.findById(id) ;
        return !user.isPresent();
    }

    public UserTypes setUserType(String userId){
        char lastCharacter = userId.charAt(userId.length()-1);
        if (Character.isLetter(lastCharacter)) {
            return UserTypes.NORMAL;
        } else {
            return UserTypes.ANONYMOUS;
        }
    }
}

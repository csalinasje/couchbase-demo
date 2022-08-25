package com.cb.userservice.repository;

import com.cb.userservice.model.Services;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ServiceRepository extends CrudRepository<Services, UUID>{
    Optional<Services> findByVendor (String vendor);
}

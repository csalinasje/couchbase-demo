package com.cb.userservice.service.impl;

import com.cb.userservice.model.Services;
import com.cb.userservice.repository.ServiceRepository;
import com.cb.userservice.service.ServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
@Service
@Slf4j
public class ServiceServiceImpl implements ServiceService {
    private ServiceRepository repository;
    public ServiceServiceImpl (ServiceRepository repository){
        this.repository = repository;
    }
    @Override
    public void create(Services service) {
        if (!existServices(service)) {
            log.info("# Create a service: {}",service);
            service.setId(UUID.randomUUID().toString());
            service.setCreated(new Date());
            repository.save(service);
        }
    }

    public boolean existServices (Services service) {
        try {
            Optional<Services>services= repository.findByVendor(service.getVendor());
            log.info("# Exist service: {}",services);
            return services.isPresent();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex.getCause());
            return false;
        }
    }
}

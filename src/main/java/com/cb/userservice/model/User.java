package com.cb.userservice.model;

import com.cb.userservice.enums.UserTypes;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.List;


@Document
@Data
@Builder
public class User {
    @Id
    private String id;

    private String name;
    @Size(min = 9, max = 9, message
            = "must be 9 characters")
    private String phone;

    private UserTypes type;
    @Size(min = 0, max = 10, message
            = "must be less than 10 services")
    private List<Services> service;
}
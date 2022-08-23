package com.cb.userservice.model;

import com.cb.userservice.enums.UserTypes;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.List;


@Document
@Getter
@Builder
public class User {
    @Id
    private String id;

    private String name;

    private String phone;

    private UserTypes type;

    private List<Service> service;


}
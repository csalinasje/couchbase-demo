package com.cb.userservice.model;


import com.cb.userservice.enums.ServiceStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.Date;

@Document
@Getter
@Builder
public class Service {
    @Id
    private String id;

    private String vendor;

    private Date created;

    private ServiceStatus status;
}

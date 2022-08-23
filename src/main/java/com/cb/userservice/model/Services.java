package com.cb.userservice.model;


import com.cb.userservice.enums.ServiceStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document
@Getter
@Setter
@Builder
public class Services {
    @Id
    private String id;

    private String vendor;

    private Date created;

    private ServiceStatus status;
}

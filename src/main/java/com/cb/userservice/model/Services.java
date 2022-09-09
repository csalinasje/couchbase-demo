package com.cb.userservice.model;


import com.cb.userservice.enums.ServiceStatus;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Document
@Data
@Builder
public class Services implements Serializable {
    private static final long serialVersionUID = 8799656478674716638L;
    @Id
    private String id;

    private String vendor;

    private Date created;

    private ServiceStatus status;
}

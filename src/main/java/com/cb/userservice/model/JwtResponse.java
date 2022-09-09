package com.cb.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
/*
This is class is required for creating a response containing the JWT to be returned to the user.
 */
@Getter
@AllArgsConstructor
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

}

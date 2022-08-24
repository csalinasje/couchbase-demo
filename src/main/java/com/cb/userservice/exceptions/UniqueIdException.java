package com.cb.userservice.exceptions;

public class UniqueIdException extends Exception{
    public UniqueIdException(String errorMessage) {
        super(errorMessage);
    }
}

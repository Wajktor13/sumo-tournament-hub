package com.sumotournamenthub.backend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ExceptionUtils {

    public static ResponseStatusException notExist(String message) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
    }

}

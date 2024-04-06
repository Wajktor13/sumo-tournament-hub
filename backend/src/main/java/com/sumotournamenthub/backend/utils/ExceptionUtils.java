package com.sumotournamenthub.backend.utils;

import jakarta.persistence.EntityNotFoundException;

public class ExceptionUtils {

    public static EntityNotFoundException entityNotFound(String entityName, int entityId) {
        return new EntityNotFoundException(String.format(entityName + " with id %d does not exist", entityId));
    }

}

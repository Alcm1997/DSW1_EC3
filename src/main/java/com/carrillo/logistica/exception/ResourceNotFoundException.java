package com.carrillo.logistica.exception;

/**
 * Excepción personalizada para recursos no encontrados (404 Not Found)
 * Se lanza cuando se busca un recurso por ID y no existe en la base de datos
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

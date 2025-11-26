package com.carrillo.logistica.exception;

/**
 * Excepción personalizada para recursos duplicados (409 Conflict)
 * Se lanza cuando se intenta crear un recurso que ya existe (ej: RUC duplicado)
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}

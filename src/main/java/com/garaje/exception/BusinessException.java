package com.garaje.exception;

/**
 * Excepción personalizada para manejar errores de lógica de negocio.
 */
public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }
}

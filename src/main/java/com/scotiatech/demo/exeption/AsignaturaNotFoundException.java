package com.scotiatech.demo.exeption;


public class AsignaturaNotFoundException extends RuntimeException {
    public AsignaturaNotFoundException(String message) {
        super(message);
    }
}


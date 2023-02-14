package com.cgr.App.exception;

public class BadRequestCustom extends Exception{

    public BadRequestCustom(String mensaje){
        super(mensaje);
    }
}

package com.cgr.App.validator;

import com.cgr.App.exception.BadRequestCustom;
import com.cgr.App.exception.ConflictException;

public class Control {

    public static void validateStringSize(String string, Integer size, String messageError) throws BadRequestCustom{
        if(string.length() > size){
            throw new BadRequestCustom(messageError);
        }
    }

    public static void validateEmail(String email) throws BadRequestCustom {
        if (!email.contains("@") || email.contains(" ") || !email.contains(".com")){
            throw new BadRequestCustom("El correo no es valido");
        }
    }
    public static void validateEmailUser(String email) throws ConflictException {
        if (!email.contains("@") || email.contains(" ") || email.contains(".com")){
            throw new ConflictException("");
        }
    }

    public static void validateAttributePresent(Object attribute, String messageError) throws BadRequestCustom {
        if(attribute == null){
            throw new BadRequestCustom(messageError);
        }
    }

    public static void validateEmptyField(String string, String messageError) throws BadRequestCustom{
        if(string.trim() == "" || string.equals("") || string.isEmpty() || string.isBlank()){
            throw new BadRequestCustom(messageError);
        }
    }
}

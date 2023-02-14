package com.cgr.App.validator;

import com.cgr.App.entity.User;
import com.cgr.App.exception.BadRequestCustom;

public class UserValidator {

    public static void validateEntity(User user) throws BadRequestCustom {
        Control.validateAttributePresent(user.getRol().getId(),"El id del rol es requerido");
        Control.validateAttributePresent(user.getName(),"El nombre del usuario es requerido");
        Control.validateAttributePresent(user.getPassword(),"La contraseña del usuario es requerida");
        Control.validateAttributePresent(user.getEmail(),"El correo del usuario es requerida");

    }
    public static void validateStringSize(User user) throws BadRequestCustom{
        Control.validateStringSize(user.getName(),60,"El nombre del usuario supera la longitud establecida");
        Control.validateStringSize(user.getPassword(),255,"La contraseña del usuario supera la longitud establecida");
        Control.validateStringSize(user.getEmail(),255,"El correo del usuario supera la longitud establecida");
    }
    public static void validateEmptyField(User user) throws BadRequestCustom{
        Control.validateEmptyField(user.getName(),"El campo nombre del usuario no puede ser vacio");
        Control.validateEmptyField(user.getPassword(),"El campo contraseña del usuario no puede ser vacio");
        Control.validateEmptyField(user.getEmail(),"El campo correo del usuario no puede ser vacio");
    }

    public static User trimAttributes(User user){
        user.setName(user.getName().trim());
        user.setPassword(user.getPassword().trim());
        user.setEmail(user.getEmail().trim());
        return user;
    }
}

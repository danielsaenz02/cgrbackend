package com.cgr.App.validator;

import com.cgr.App.entity.Ticket;
import com.cgr.App.entity.User;
import com.cgr.App.exception.BadRequestCustom;

public class TicketValidator {

    public static void validateEntity(Ticket ticket) throws BadRequestCustom {
        Control.validateAttributePresent(ticket.getUser().getId(),"El id del usuario es requerido");
        Control.validateAttributePresent(ticket.getTitle(),"El título del ticket es requerido");
        Control.validateAttributePresent(ticket.getDescription(),"La descrición del ticket es requerida");


    }
    public static void validateStringSize(Ticket ticket) throws BadRequestCustom{
        Control.validateStringSize(ticket.getTitle(),60,"El título del ticket  supera la longitud establecida");
        Control.validateStringSize(ticket.getDescription(),1024,"La descrición del ticke supera la longitud establecida");

    }
    public static void validateEmptyField(Ticket ticket) throws BadRequestCustom{
        Control.validateEmptyField(ticket.getTitle(),"El campo título del ticket no puede ser vacio");
        Control.validateEmptyField(ticket.getDescription(),"El campo descrición del usuario no puede ser vacio");

    }

    public static Ticket trimAttributes(Ticket ticket){
        ticket.setTitle(ticket.getTitle().trim());
        ticket.setDescription(ticket.getDescription().trim());
        return ticket;
    }
}

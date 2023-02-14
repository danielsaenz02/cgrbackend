package com.cgr.App.controller;

import com.cgr.App.entity.Ticket;
import com.cgr.App.exception.BadRequestCustom;
import com.cgr.App.service.TicketService;
import com.cgr.App.validator.Control;
import com.cgr.App.validator.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@CrossOrigin({"*"})
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/save")
    public ResponseEntity<?> saveTicket(@RequestBody Ticket ticket) throws Exception {
        try {
            TicketValidator.validateEntity(ticket);
            TicketValidator.validateEmptyField(ticket);
            TicketValidator.validateStringSize(ticket);
            Ticket newTicket = TicketValidator.trimAttributes(ticket);
            newTicket.setStatus("n");
            ticketService.save(newTicket);
            return new ResponseEntity<>(newTicket, HttpStatus.OK);
        }catch (BadRequestCustom badMessage){
            return new ResponseEntity<>(badMessage.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/listaruser/{idUser}")
    public ResponseEntity<List<Ticket>> getTicketByUser(@PathVariable Long idUser){
        List<Ticket> ticketList = ticketService.findByIdUser(idUser);
        return new ResponseEntity<List<Ticket>>(ticketList,HttpStatus.OK);
    }

}

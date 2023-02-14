package com.cgr.App.service;

import com.cgr.App.entity.Ticket;
import com.cgr.App.repository.ITicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private ITicketRepository iTicketRepository;

    public List<Ticket> getAll(){
        return iTicketRepository.findAll();
    }

    public Ticket save(Ticket ticket){
        return iTicketRepository.save(ticket);
    }

    public Ticket findById(Long id){
        return iTicketRepository.findById(id).orElse(null);
    }

    public List<Ticket> findByIdUser(Long IdUser){
        return iTicketRepository.findByIdUser(IdUser);
    }

}

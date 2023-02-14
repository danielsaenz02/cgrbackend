package com.cgr.App.repository;

import com.cgr.App.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITicketRepository extends JpaRepository<Ticket,Long> {
    @Query(nativeQuery = true, value = "SELECT * From Tickets where User_id = ? and status != 'I'")
    List<Ticket> findByIdUser(Long IdUser);
}

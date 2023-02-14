package com.cgr.App.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name="Complaints")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String facts;



    @ManyToOne(fetch = FetchType.LAZY) // Relación con la clase Rol
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "Municipality_Id", referencedColumnName = "id")
    private Municipality municipality;

    private String people_involved;
    @ManyToOne(fetch = FetchType.LAZY) // Relación con la clase Rol
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @JoinColumn(name = "Entity_Id", referencedColumnName = "id")
    private Entidad entidad;

    private String name_othr_entity;

    private String email_user;

    private Integer verification_code;

    private String status;
}

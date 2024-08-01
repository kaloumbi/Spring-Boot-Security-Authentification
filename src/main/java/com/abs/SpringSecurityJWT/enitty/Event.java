package com.abs.SpringSecurityJWT.enitty;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "date")
    private Date date;

    @Column(name = "lieu")
    private String lieu;

    @Column(name = "description")
    private String description;

    @Column(name = "etat")
    private String etat;
    

}

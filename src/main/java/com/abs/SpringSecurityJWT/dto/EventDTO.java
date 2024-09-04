package com.abs.SpringSecurityJWT.dto;

import com.abs.SpringSecurityJWT.enitty.User;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class EventDTO {

    private Long id;

    private String nom;

    private Date date;

    private String lieu;

    private String description;

    private String etat;

}

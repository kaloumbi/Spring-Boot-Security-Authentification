package com.abs.SpringSecurityJWT.dto;

import com.abs.SpringSecurityJWT.enitty.Association;
import com.abs.SpringSecurityJWT.enitty.User;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AssociationDTO {

    private Long id;

    private String nom;

    private String description;

    private String etat;

}

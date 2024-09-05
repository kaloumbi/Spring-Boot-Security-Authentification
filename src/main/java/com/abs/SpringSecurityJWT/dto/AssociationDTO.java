package com.abs.SpringSecurityJWT.dto;

import com.abs.SpringSecurityJWT.enitty.Association;
import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AssociationDTO {

    private Long id;

    private String nom;

    private String description;

    private String etat;

}

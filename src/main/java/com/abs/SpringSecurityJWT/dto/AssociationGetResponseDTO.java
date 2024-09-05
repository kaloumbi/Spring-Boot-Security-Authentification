package com.abs.SpringSecurityJWT.dto;

import com.abs.SpringSecurityJWT.enitty.Cotisation;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AssociationGetResponseDTO {

    private Long id;

    private String nom;

    private String description;

    private String etat;

    private List<CotisationGetResponseDTO> cotisations = new ArrayList<>();

}

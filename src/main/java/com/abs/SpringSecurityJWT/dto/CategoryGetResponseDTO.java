package com.abs.SpringSecurityJWT.dto;

import com.abs.SpringSecurityJWT.enitty.Cotisation;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryGetResponseDTO {

    private Long id;
    private String nom;
    private String description;
    private String etat;

    private List<CotisationGetResponseDTO> cotisations = new ArrayList<>() ;

}

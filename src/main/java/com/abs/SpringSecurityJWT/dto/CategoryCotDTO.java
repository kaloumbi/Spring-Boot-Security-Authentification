package com.abs.SpringSecurityJWT.dto;

import com.abs.SpringSecurityJWT.enitty.Cotisation;
import lombok.Data;

import java.util.List;


@Data
public class CategoryCotDTO {

    private Long id;
    private String nom;
    private String description;
    private String etat;
}

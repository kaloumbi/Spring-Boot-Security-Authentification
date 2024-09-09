package com.abs.SpringSecurityJWT.dto;

import com.abs.SpringSecurityJWT.enitty.CategoryCot;
import com.abs.SpringSecurityJWT.enitty.User;
import com.abs.SpringSecurityJWT.enums.ModeDePaiement;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CotisationDTO {

    private Long id;

    private BigDecimal montant;

    private Date dateCotisation;

    private ModeDePaiement modeDePaiement;

    private String etat;

    private CategoryCotDTO categoryCot;

    private AssociationDTO association;

    private UserReqResDTO user;

}

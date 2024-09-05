package com.abs.SpringSecurityJWT.dto;

import com.abs.SpringSecurityJWT.enums.ModeDePaiement;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CotisationGetResponseDTO {
    private Long id;

    private BigDecimal montant;

    private Date dateCotisation;

    private ModeDePaiement modeDePaiement;

    private String etat;
}

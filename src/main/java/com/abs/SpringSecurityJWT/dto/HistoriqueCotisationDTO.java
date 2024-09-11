package com.abs.SpringSecurityJWT.dto;

import com.abs.SpringSecurityJWT.enums.ETAT_COTISATION;
import com.abs.SpringSecurityJWT.enums.ModeDePaiement;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class HistoriqueCotisationDTO {

    private ModeDePaiement modeDePaiement;

    private BigDecimal montant;

    private Date dateCotisation;

    private ETAT_COTISATION etat;
}

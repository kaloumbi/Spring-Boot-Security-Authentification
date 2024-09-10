package com.abs.SpringSecurityJWT.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class StatistiqueCotisationDTO {

    private BigDecimal totalMontantCotisation;

    private BigDecimal totalCotisationValidee;

    private BigDecimal totalCotisationNonValidee;
}

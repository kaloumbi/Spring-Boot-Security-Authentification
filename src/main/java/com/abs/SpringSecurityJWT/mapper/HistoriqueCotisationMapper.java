package com.abs.SpringSecurityJWT.mapper;

import com.abs.SpringSecurityJWT.dto.HistoriqueCotisationDTO;
import com.abs.SpringSecurityJWT.enitty.Cotisation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoriqueCotisationMapper extends MyEntityManager<HistoriqueCotisationDTO, Cotisation> {
}

package com.abs.SpringSecurityJWT.mapper;

import com.abs.SpringSecurityJWT.dto.CotisationDTO;
import com.abs.SpringSecurityJWT.dto.CotisationGetResponseDTO;
import com.abs.SpringSecurityJWT.enitty.Cotisation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CotisationGetResponseMapper extends MyEntityManager<CotisationGetResponseDTO, Cotisation> {

}

package com.abs.SpringSecurityJWT.mapper;

import com.abs.SpringSecurityJWT.dto.AssociationGetResponseDTO;
import com.abs.SpringSecurityJWT.enitty.Association;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssociationGetResponseMapper extends MyEntityManager<AssociationGetResponseDTO, Association> {
}

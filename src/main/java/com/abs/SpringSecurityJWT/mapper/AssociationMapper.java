package com.abs.SpringSecurityJWT.mapper;

import com.abs.SpringSecurityJWT.dto.AssociationDTO;
import com.abs.SpringSecurityJWT.enitty.Association;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssociationMapper extends MyEntityManager<AssociationDTO, Association> {
}

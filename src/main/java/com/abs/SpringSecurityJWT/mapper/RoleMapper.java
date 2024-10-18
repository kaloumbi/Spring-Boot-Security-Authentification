package com.abs.SpringSecurityJWT.mapper;

import com.abs.SpringSecurityJWT.dto.RoleDTO;
import com.abs.SpringSecurityJWT.enitty.Role;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RoleMapper extends MyEntityManager<RoleDTO, Role> {

}

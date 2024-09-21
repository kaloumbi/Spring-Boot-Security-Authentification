package com.abs.SpringSecurityJWT.mapper;

import com.abs.SpringSecurityJWT.dto.UserGetDTO;
import com.abs.SpringSecurityJWT.enitty.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserGetMapper extends MyEntityManager<UserGetDTO, User> {
}

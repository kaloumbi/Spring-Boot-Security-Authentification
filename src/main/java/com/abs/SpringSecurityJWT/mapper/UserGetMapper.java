package com.abs.SpringSecurityJWT.mapper;

import com.abs.SpringSecurityJWT.dto.UserGetDTO;
import com.abs.SpringSecurityJWT.dto.UserReqResDTO;
import com.abs.SpringSecurityJWT.enitty.User;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Bean;

@Mapper(componentModel = "spring")
public interface UserGetMapper extends MyEntityManager<UserGetDTO, User> {

}

package com.abs.SpringSecurityJWT.mapper;

import com.abs.SpringSecurityJWT.dto.UserReqResDTO;
import com.abs.SpringSecurityJWT.enitty.User;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends MyEntityManager<UserReqResDTO, User> {

}

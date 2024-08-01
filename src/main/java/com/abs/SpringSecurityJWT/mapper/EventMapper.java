package com.abs.SpringSecurityJWT.mapper;

import com.abs.SpringSecurityJWT.dto.EventDTO;
import com.abs.SpringSecurityJWT.dto.UserReqResDTO;
import com.abs.SpringSecurityJWT.enitty.Event;
import com.abs.SpringSecurityJWT.enitty.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper extends MyEntityManager<EventDTO, Event> {

}

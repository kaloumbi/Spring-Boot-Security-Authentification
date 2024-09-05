package com.abs.SpringSecurityJWT.mapper;

import com.abs.SpringSecurityJWT.dto.CategoryGetResponseDTO;
import com.abs.SpringSecurityJWT.enitty.CategoryCot;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoryGetResponseMapper extends MyEntityManager<CategoryGetResponseDTO, CategoryCot> {

}

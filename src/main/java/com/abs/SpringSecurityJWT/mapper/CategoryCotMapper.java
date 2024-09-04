package com.abs.SpringSecurityJWT.mapper;


import com.abs.SpringSecurityJWT.dto.CategoryCotDTO;
import com.abs.SpringSecurityJWT.enitty.CategoryCot;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoryCotMapper extends MyEntityManager<CategoryCotDTO, CategoryCot> {

}

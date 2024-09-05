package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.CategoryCotDTO;
import com.abs.SpringSecurityJWT.dto.CategoryGetResponseDTO;

import java.util.List;

public interface CategoryCotService {

    CategoryGetResponseDTO addCategoryCot(CategoryCotDTO categoryCotDTO);

    List<CategoryGetResponseDTO> listeCategories();

    CategoryGetResponseDTO getCategory(Long id);

    CategoryCotDTO updateCategory(Long id, CategoryCotDTO categoryCotDTO);

    void deleteCategory(Long id);

    List<CategoryGetResponseDTO> searchCategoryByName(String nom);
}

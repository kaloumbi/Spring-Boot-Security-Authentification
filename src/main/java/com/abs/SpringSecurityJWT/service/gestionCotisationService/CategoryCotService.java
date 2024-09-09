package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.CategoryCotDTO;

import java.util.List;

public interface CategoryCotService {

    CategoryCotDTO addCategoryCot(CategoryCotDTO categoryCotDTO);

    List<CategoryCotDTO> listeCategories();

    CategoryCotDTO getCategory(Long id);

    CategoryCotDTO updateCategory(Long id, CategoryCotDTO categoryCotDTO);

    void deleteCategory(Long id);

    List<CategoryCotDTO> searchCategoryByName(String nom);
}

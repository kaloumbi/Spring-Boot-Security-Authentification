package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.CategoryCotDTO;
import com.abs.SpringSecurityJWT.dto.CategoryGetResponseDTO;
import com.abs.SpringSecurityJWT.enitty.CategoryCot;
import com.abs.SpringSecurityJWT.enums.ETAT_COTISATION;
import com.abs.SpringSecurityJWT.enums.ETAT_SHARED;
import com.abs.SpringSecurityJWT.mapper.CategoryCotMapper;
import com.abs.SpringSecurityJWT.mapper.CategoryGetResponseMapper;
import com.abs.SpringSecurityJWT.myExeptions.MyNotFoundExceptionClass;
import com.abs.SpringSecurityJWT.repository.CategoryCotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryCotServiceImpl implements CategoryCotService {

    @Autowired
    private CategoryCotRepo categoryCotRepo;

    @Autowired
    private CategoryCotMapper categoryCotMapper;

    @Autowired
    private CategoryGetResponseMapper categoryGetResponseMapper;

    @Override
    public CategoryGetResponseDTO addCategoryCot(CategoryCotDTO categoryCotDTO) {

        CategoryCot catAdded = categoryCotMapper.toEntity(categoryCotDTO);

        catAdded.setEtat(ETAT_COTISATION.ACTIF.toString());
        catAdded = categoryCotRepo.save(catAdded);

        return categoryGetResponseMapper.toDto(catAdded);

    }

    @Override
    public List<CategoryGetResponseDTO> listeCategories() {
        List<CategoryCot> categoryCotList = categoryCotRepo.findAll();

        return categoryGetResponseMapper.toDto(categoryCotList);
    }

    @Override
    public CategoryGetResponseDTO getCategory(Long id) {
        Optional<CategoryCot> detailCatCot = categoryCotRepo.findById(id);

        if (detailCatCot.isEmpty()){
            return null;
        }

        CategoryCot catDetail = detailCatCot.get();
        return categoryGetResponseMapper.toDto(catDetail);
    }

    @Override
    public CategoryCotDTO updateCategory(Long id, CategoryCotDTO categoryCotDTO) {
        Optional<CategoryCot> catSearchUp = categoryCotRepo.findById(id);

        if (catSearchUp.isEmpty()){
            return null;
        }
        CategoryCot catFound = catSearchUp.get();

        //si id dto est null prends l'id l'objet trouvé
        if (categoryCotDTO.getId() == null){
            categoryCotDTO.setId(catFound.getId());
        }

        catFound = categoryCotMapper.toEntity(categoryCotDTO);

        categoryCotRepo.save(catFound);

        return categoryCotMapper.toDto(catFound);
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<CategoryCot> searchDeleteCat = categoryCotRepo.findById(id);

        if (searchDeleteCat.isEmpty()){
            throw new MyNotFoundExceptionClass("Aucune catégory à supprimer !");
        }

        CategoryCot catDelFound = searchDeleteCat.get();
        catDelFound.setEtat(ETAT_SHARED.SUPPRIME.toString());
        categoryCotRepo.save(catDelFound);

    }

    @Override
    public List<CategoryGetResponseDTO> searchCategoryByName(String nom) {
        return List.of();
    }
}

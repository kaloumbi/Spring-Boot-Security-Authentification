package com.abs.SpringSecurityJWT.controller;

import com.abs.SpringSecurityJWT.dto.CategoryCotDTO;
import com.abs.SpringSecurityJWT.dto.CategoryGetResponseDTO;
import com.abs.SpringSecurityJWT.enitty.CategoryCot;
import com.abs.SpringSecurityJWT.service.gestionCotisationService.CategoryCotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class CategoryCotController {

    @Autowired
    private CategoryCotService categoryCotService;


    @PostMapping("/category/added")
    public ResponseEntity<CategoryGetResponseDTO> addCategory(@RequestBody CategoryCotDTO categoryCotDTO){

        CategoryGetResponseDTO catCot = categoryCotService.addCategoryCot(categoryCotDTO);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(catCot);
    }


    @GetMapping("/categories/list")
    public ResponseEntity<List<CategoryGetResponseDTO>> listCat(){
        List<CategoryGetResponseDTO> categoryCotDTOList = categoryCotService.listeCategories();
        return new ResponseEntity<>(categoryCotDTOList, HttpStatus.OK);
    }

    @GetMapping("/category/{id}/detail")
    public ResponseEntity<CategoryGetResponseDTO> detailCat(@PathVariable Long id){
        CategoryGetResponseDTO detailCateg = categoryCotService.getCategory(id);

        return new ResponseEntity<>(detailCateg, HttpStatus.OK);
    }

    @PutMapping("/category/{id}/update")
    public ResponseEntity<CategoryCotDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryCotDTO categoryCotDTO){

        CategoryCotDTO updateCateg = categoryCotService.updateCategory(id, categoryCotDTO);
        return new ResponseEntity<>(updateCateg, HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}/delete")
    public ResponseEntity<String> deleteCat(@PathVariable Long id){
        categoryCotService.deleteCategory(id);

        return new ResponseEntity<>("Suppression de la category reussie !", HttpStatus.OK);
    }
}

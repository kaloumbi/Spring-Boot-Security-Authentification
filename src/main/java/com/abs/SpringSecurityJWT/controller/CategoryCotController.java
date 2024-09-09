package com.abs.SpringSecurityJWT.controller;

import com.abs.SpringSecurityJWT.dto.CategoryCotDTO;
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
    public ResponseEntity<CategoryCotDTO> addCategory(@RequestBody CategoryCotDTO categoryCotDTO){

        CategoryCotDTO catCot = categoryCotService.addCategoryCot(categoryCotDTO);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(catCot);
    }


    @GetMapping("/categories/list")
    public ResponseEntity<List<CategoryCotDTO>> listCat(){
        List<CategoryCotDTO> categoryCotDTOList = categoryCotService.listeCategories();
        return new ResponseEntity<>(categoryCotDTOList, HttpStatus.OK);
    }

    @GetMapping("/category/{id}/detail")
    public ResponseEntity<CategoryCotDTO> detailCat(@PathVariable Long id){
        CategoryCotDTO detailCateg = categoryCotService.getCategory(id);

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

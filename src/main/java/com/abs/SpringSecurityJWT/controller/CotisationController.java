package com.abs.SpringSecurityJWT.controller;

import com.abs.SpringSecurityJWT.dto.CotisationDTO;
import com.abs.SpringSecurityJWT.service.gestionCotisationService.CotisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class CotisationController {

    @Autowired
    private CotisationService cotisationService;

    @PostMapping("cotisation/added")
    public ResponseEntity<CotisationDTO> addCotisation(@RequestBody CotisationDTO cotisationDTO){

        CotisationDTO addCotisation = cotisationService.addCotisation(cotisationDTO);

        return new ResponseEntity<>(addCotisation, HttpStatus.OK);
    }


    @GetMapping("cotisations/list")
    public ResponseEntity<List<CotisationDTO>> listCotisation(){
        List<CotisationDTO> cotisationDTOList = cotisationService.listeCotisations();

        return new ResponseEntity<>(cotisationDTOList, HttpStatus.OK);
    }

    @GetMapping("cotisation/{id}/detail")
    public ResponseEntity<CotisationDTO> detailCotisation (@PathVariable Long id){
        CotisationDTO cotisationDTO = cotisationService.detailCotisation(id);

        return new ResponseEntity<>(cotisationDTO, HttpStatus.OK);
    }

    @PutMapping("cotisation/{id}/update")
    public ResponseEntity<CotisationDTO> updateCotisation(@PathVariable Long id, @RequestBody CotisationDTO cotisationDTO){

        CotisationDTO cotisatUpdate = cotisationService.updateCotisation(id, cotisationDTO);

        return new ResponseEntity<>(cotisatUpdate, HttpStatus.OK);
    }

    @DeleteMapping("cotisation/{id}/delete")
    public ResponseEntity<String> deleteCotisation(@PathVariable Long id){

        cotisationService.deleteCotisation(id);

        return new ResponseEntity<>("Suppression de la cotisation reussie !", HttpStatus.OK);
    }
}

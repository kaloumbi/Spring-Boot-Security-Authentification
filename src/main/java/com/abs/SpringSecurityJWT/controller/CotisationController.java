package com.abs.SpringSecurityJWT.controller;

import com.abs.SpringSecurityJWT.dto.CotisationDTO;
import com.abs.SpringSecurityJWT.dto.HistoriqueCotisationDTO;
import com.abs.SpringSecurityJWT.dto.StatistiqueCotisationDTO;
import com.abs.SpringSecurityJWT.enitty.Cotisation;
import com.abs.SpringSecurityJWT.enums.ETAT_COTISATION;
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

    @GetMapping("/cotisations/statistique")
    public ResponseEntity<StatistiqueCotisationDTO> statistiqueCotisationDTO(){
        StatistiqueCotisationDTO statistiqueCotisationDTO = cotisationService.calculMontantCotisation();

        return new ResponseEntity<>(statistiqueCotisationDTO, HttpStatus.OK);
    }
    @GetMapping("/cotisations/statistiqueUser")
    public ResponseEntity<StatistiqueCotisationDTO> statistiqueUserCotisationDTO(){
        StatistiqueCotisationDTO statistiqueCotisationDTO = cotisationService.calculUserCotisation();

        return new ResponseEntity<>(statistiqueCotisationDTO, HttpStatus.OK);
    }

    //fonction permettant de changer l'etat d'une cotisation

    @PutMapping("/cotisation/{id}/etat")
    public ResponseEntity<String> changerEtatCotisation(@PathVariable Long id, @RequestParam ETAT_COTISATION etatCotisation){

        boolean iscUpdated = cotisationService.changeEtatCotisation(id, etatCotisation);
        if (iscUpdated){
            return new ResponseEntity<>(" L'etat de la cotisation modifie avec succès! ", HttpStatus.OK);
        }

        return new ResponseEntity<>(" Errreur lors de la modification de L'etat de la cotisation ! ", HttpStatus.OK);

    }

    //Liste Cotisation par l'utilisateur connecté

    @GetMapping("/cotisations/historique/list")
    public ResponseEntity<List<HistoriqueCotisationDTO>> getUserListCotisation(){
        List<HistoriqueCotisationDTO> cotisationDTOS = cotisationService.listCotisationByUser();

        return new ResponseEntity<>(cotisationDTOS, HttpStatus.OK);
    }


    //liste des cotisation par category et association d'un utilisateur connecté

    @GetMapping("/myCotisations/assCat")
    public ResponseEntity<List<HistoriqueCotisationDTO>> historiqueCotisationDTOS(@RequestParam String category, @RequestParam String assocation){

        List<HistoriqueCotisationDTO> historiqueCotisationDTOList = cotisationService.listCotisationByUserCatAssociation(category, assocation);

        return new ResponseEntity<>(historiqueCotisationDTOList, HttpStatus.OK);
    }

    //liste des cotisation par category et association d'un utilisateur connecté
    @GetMapping("/myCotisations/assCat/statistique")
    public ResponseEntity<StatistiqueCotisationDTO> listCotisationByUserCatAssociation(@RequestParam String category, @RequestParam String assocation){

        StatistiqueCotisationDTO statistiqueCotisationDTO = cotisationService.calculMontantUserCotisationByAssCat(category, assocation);

        return new ResponseEntity<>(statistiqueCotisationDTO, HttpStatus.OK);
    }

    //lister les cotisation par evenement
    @GetMapping("cotisations/events/list")
    public ResponseEntity<List<HistoriqueCotisationDTO>> getAllCotisationByEvent(@RequestParam String nom){

        List<HistoriqueCotisationDTO> cotisationDTOList = cotisationService.getCotisationByEvent(nom);

        return new ResponseEntity<>(cotisationDTOList, HttpStatus.OK);
    }


    //Calculer le montant des cotisations reçues par evenement
    @GetMapping("cotisationMontant/statistique")
    public ResponseEntity<StatistiqueCotisationDTO> montantTotalParEvent(@RequestParam String nom){
        StatistiqueCotisationDTO statistiqueCotisationDTO = cotisationService.montantTotalCotisationByEvent(nom);

        return new ResponseEntity<>(statistiqueCotisationDTO, HttpStatus.OK);
    }

}

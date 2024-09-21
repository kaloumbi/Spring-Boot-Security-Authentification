package com.abs.SpringSecurityJWT.controller;


import com.abs.SpringSecurityJWT.dto.AssociationDTO;
import com.abs.SpringSecurityJWT.dto.UserReqResDTO;
import com.abs.SpringSecurityJWT.enitty.User;
import com.abs.SpringSecurityJWT.myExeptions.MyNotFoundExceptionClass;
import com.abs.SpringSecurityJWT.service.gestionCotisationService.AssociationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AssocationController {

    private final AssociationService associationService;

    public AssocationController(AssociationService associationService) {
        this.associationService = associationService;
    }


    @PostMapping("association/added")
    public ResponseEntity<AssociationDTO> addAssociation(@RequestBody AssociationDTO associationDTO){

        if (associationDTO == null){
            throw new MyNotFoundExceptionClass(" Veuillez renseignez les champs !");
        }

        AssociationDTO associationAdd = associationService.addAssociation(associationDTO);

        return new ResponseEntity<>(associationAdd, HttpStatus.OK);
    }

    @GetMapping("associations/list")
    public ResponseEntity<List<AssociationDTO>> listAssociation(){

        List<AssociationDTO> associationGetResponseDTOList = associationService.listAssociation();

        return new ResponseEntity<>(associationGetResponseDTOList, HttpStatus.OK);
    }

    @GetMapping("association/{id}/detail")
    public ResponseEntity<AssociationDTO> detailAssociation(@PathVariable Long id){

        AssociationDTO associationDTO = associationService.detailAssociation(id);

        return new ResponseEntity<>(associationDTO, HttpStatus.OK);
    }

    @PutMapping("association/{id}/updated")
    public ResponseEntity<AssociationDTO> updateAssociation(@PathVariable Long id, @RequestBody AssociationDTO associationDTO){

        AssociationDTO associationGetResponseDTO = associationService.updateAssociation(id, associationDTO);

        return new ResponseEntity<>(associationGetResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("association/{id}/delete")
    public ResponseEntity<String> deleteAssociation(@PathVariable Long id){

        associationService.deleteAssociation(id);

        return new ResponseEntity<>("Association supprimee !", HttpStatus.OK);
    }



}

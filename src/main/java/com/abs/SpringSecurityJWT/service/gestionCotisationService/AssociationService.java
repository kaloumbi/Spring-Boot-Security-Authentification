package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.AssociationDTO;
import com.abs.SpringSecurityJWT.enums.ETAT_COTISATION;

import java.util.List;

public interface AssociationService {

    AssociationDTO addAssociation(AssociationDTO associationDTO);

    List<AssociationDTO> listAssociation();

    AssociationDTO detailAssociation(Long id);

    AssociationDTO updateAssociation(Long id, AssociationDTO associationDTO);

    void deleteAssociation(Long id);

    List<AssociationDTO> searcheAssociation(String nom);

}

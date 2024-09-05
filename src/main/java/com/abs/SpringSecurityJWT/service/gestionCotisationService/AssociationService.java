package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.AssociationDTO;
import com.abs.SpringSecurityJWT.dto.AssociationGetResponseDTO;

import java.util.List;

public interface AssociationService {

    AssociationGetResponseDTO addAssociation(AssociationDTO associationDTO);

    List<AssociationGetResponseDTO> listAssociation();

    AssociationGetResponseDTO detailAssociation(Long id);

    AssociationGetResponseDTO updateAssociation(Long id, AssociationDTO associationDTO);

    void deleteAssociation(Long id);

    List<AssociationGetResponseDTO> searcheAssociation(String nom);
}

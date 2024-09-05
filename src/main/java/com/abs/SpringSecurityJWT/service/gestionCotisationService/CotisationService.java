package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.CotisationDTO;
import com.abs.SpringSecurityJWT.dto.CotisationGetResponseDTO;
import com.abs.SpringSecurityJWT.repository.CotisationRepo;

import java.util.List;

public interface CotisationService {

    CotisationGetResponseDTO addCotisation(CotisationDTO cotisationDTO);

    List<CotisationGetResponseDTO> listeCotisations();

    CotisationGetResponseDTO detailCotisation(Long id);

    CotisationGetResponseDTO updateCotisation(Long id, CotisationDTO cotisationDTO);

    void deleteCotisation (Long id);

    List<CotisationGetResponseDTO> searchCotisations(String nom);
}

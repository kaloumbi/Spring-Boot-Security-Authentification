package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.CotisationDTO;
import com.abs.SpringSecurityJWT.repository.CotisationRepo;

import java.util.List;

public interface CotisationService {

    CotisationDTO addCotisation(CotisationDTO cotisationDTO);

    List<CotisationDTO> listeCotisations();

    CotisationDTO detailCotisation(Long id);

    CotisationDTO updateCotisation(Long id, CotisationDTO cotisationDTO);

    void deleteCotisation (Long id);

    List<CotisationDTO> searchCotisations(String nom);
}

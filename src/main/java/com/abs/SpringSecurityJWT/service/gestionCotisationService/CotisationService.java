package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.CotisationDTO;
import com.abs.SpringSecurityJWT.dto.StatistiqueCotisationDTO;

import java.util.List;

public interface CotisationService {

    CotisationDTO addCotisation(CotisationDTO cotisationDTO);

    List<CotisationDTO> listeCotisations();

    CotisationDTO detailCotisation(Long id);

    CotisationDTO updateCotisation(Long id, CotisationDTO cotisationDTO);

    void deleteCotisation (Long id);

    List<CotisationDTO> searchCotisations(String nom);

    //Calcula de montant suivant l'état
    StatistiqueCotisationDTO calculMontantCotisation();

    StatistiqueCotisationDTO calculUserCotisation(String login);
}

package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.CotisationDTO;
import com.abs.SpringSecurityJWT.dto.HistoriqueCotisationDTO;
import com.abs.SpringSecurityJWT.dto.StatistiqueCotisationDTO;
import com.abs.SpringSecurityJWT.enums.ETAT_COTISATION;

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

    StatistiqueCotisationDTO calculUserCotisation();

    boolean changeEtatCotisation(Long id, ETAT_COTISATION etatCotisation);

    //avoir l'historique des cotisations d'un utilisateur
    List<HistoriqueCotisationDTO> listCotisationByUser();

}

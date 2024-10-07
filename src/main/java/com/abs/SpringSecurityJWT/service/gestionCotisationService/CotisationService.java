package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.CotisationDTO;
import com.abs.SpringSecurityJWT.dto.HistoriqueCotisationDTO;
import com.abs.SpringSecurityJWT.dto.StatistiqueCotisationDTO;
import com.abs.SpringSecurityJWT.enitty.Cotisation;
import com.abs.SpringSecurityJWT.enums.ETAT_COTISATION;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    //avoir la liste des cotisations d'un user connecté par category et association
    List<HistoriqueCotisationDTO> listCotisationByUserCatAssociation(String category, String association);

    StatistiqueCotisationDTO calculMontantUserCotisationByAssCat(String category, String association);

    //lister les cotisation par event
    List<HistoriqueCotisationDTO> getCotisationByEvent(String nom);

    //Calculer le montant total des cotisations par evenement
    StatistiqueCotisationDTO montantTotalCotisationByEvent(String nom);

    //method pour charger mon image
    public String saveFile(MultipartFile file, Long id) throws IOException;

    //methode pour telecharger mon image
    Cotisation getImage(Long id);
}

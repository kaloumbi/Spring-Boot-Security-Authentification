package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.CotisationDTO;
import com.abs.SpringSecurityJWT.dto.HistoriqueCotisationDTO;
import com.abs.SpringSecurityJWT.dto.StatistiqueCotisationDTO;
import com.abs.SpringSecurityJWT.enitty.Cotisation;
import com.abs.SpringSecurityJWT.enitty.User;
import com.abs.SpringSecurityJWT.enums.ETAT_COTISATION;
import com.abs.SpringSecurityJWT.enums.ETAT_SHARED;
import com.abs.SpringSecurityJWT.mapper.CotisationMapper;
import com.abs.SpringSecurityJWT.mapper.HistoriqueCotisationMapper;
import com.abs.SpringSecurityJWT.myExeptions.MyNotFoundExceptionClass;
import com.abs.SpringSecurityJWT.repository.CotisationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CotisationServiceImpl implements CotisationService{


    @Autowired
    private CotisationRepo cotisationRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CotisationMapper cotisationMapper;

    @Autowired
    private HistoriqueCotisationMapper historiqueCotisationMapper;



    @Override
    public CotisationDTO addCotisation(CotisationDTO cotisationDTO) {

        Cotisation addCot = cotisationMapper.toEntity(cotisationDTO);

        //Appel de la fonction pour trouver l'utisateur connecté
        User userConnectedFound = userService.getAuthenticatedUser();

        if (userConnectedFound == null){
            return null;
        }
        addCot.setUser(userConnectedFound);

        addCot.setEtat(ETAT_COTISATION.NON_VALIDE);
        addCot = cotisationRepo.save(addCot);



        return cotisationMapper.toDto(addCot);
    }

    @Override
    public List<CotisationDTO> listeCotisations() {
        List<Cotisation> cotisationList = cotisationRepo.findAll();

        return cotisationMapper.toDto(cotisationList);
    }

    @Override
    public CotisationDTO detailCotisation(Long id) {

        Optional<Cotisation> cotisationSearch = cotisationRepo.findById(id);

        if (cotisationSearch.isEmpty()){
            return null;
        }

        Cotisation cotisationFound = cotisationSearch.get();

        return cotisationMapper.toDto(cotisationFound);
    }

    @Override
    public CotisationDTO updateCotisation(Long id, CotisationDTO cotisationDTO) {
        Optional<Cotisation> cotisationSearch = cotisationRepo.findById(id);

        if (cotisationSearch.isEmpty()){
            return null;
        }

        Cotisation cotisationFound = cotisationSearch.get();
        if (cotisationDTO.getId() == null){
            cotisationDTO.setId(cotisationFound.getId());
        }
        cotisationFound = cotisationMapper.toEntity(cotisationDTO);
//        cotisationFound.setEtat(ETAT_COTISATION.ACTIF);

        cotisationRepo.save(cotisationFound);

        return cotisationMapper.toDto(cotisationFound);
    }

    @Override
    public void deleteCotisation(Long id) {
        Optional<Cotisation> cotisationSearch = cotisationRepo.findById(id);

        if (cotisationSearch.isEmpty()){
            throw new MyNotFoundExceptionClass("Aucune cotisation à supprimer ! ");
        }

        Cotisation cotisationFound = cotisationSearch.get();

        cotisationFound.setEtat(ETAT_COTISATION.SUPPRIME);
        cotisationRepo.save(cotisationFound);
    }

    @Override
    public List<CotisationDTO> searchCotisations(String nom) {
        return List.of();
    }


    //Calcul de montant
    @Override
    public StatistiqueCotisationDTO calculMontantCotisation() {
        //recuperation de la liste des cotisations
        List<Cotisation> cotisationList = cotisationRepo.findAll();

        //instanciation de la statistique dto
        StatistiqueCotisationDTO statistiqueCotisationDTO = new StatistiqueCotisationDTO();

        //initialisation des totaux à zero
        BigDecimal totalMontantCotisation = BigDecimal.ZERO;
        BigDecimal totalCotisationValide = BigDecimal.ZERO;
        BigDecimal totalCotisationNonValide = BigDecimal.ZERO;

        //Parcourir la liste des cotisation
        for (Cotisation cotisation : cotisationList){
            //recuperer le montant total
            BigDecimal montant = cotisation.getMontant();
            totalMontantCotisation = totalMontantCotisation.add(montant);

            //Ajouter les montants suivant l'etat
            if (cotisation.getEtat().equals(ETAT_COTISATION.VALIDE)){
                totalCotisationValide = totalCotisationValide.add(montant);
            } else if (cotisation.getEtat().equals(ETAT_COTISATION.NON_VALIDE)) {
                totalCotisationNonValide = totalCotisationNonValide.add(montant);
            }
        }

        //Setter ces montant dans le dto puis renvoyer le dto
        statistiqueCotisationDTO.setTotalMontantCotisation(totalMontantCotisation);
        statistiqueCotisationDTO.setTotalCotisationValidee(totalCotisationValide);
        statistiqueCotisationDTO.setTotalCotisationNonValidee(totalCotisationNonValide);

        return statistiqueCotisationDTO;
    }

    @Override
    public StatistiqueCotisationDTO calculUserCotisation() {

        //Appel de la fonction pour trouver l'utisateur connecté
        User userConnectedFound = userService.getAuthenticatedUser();

        //recuperation de la liste des cotisations
        List<Cotisation> cotisationList = cotisationRepo.findAll();


        //instanciation de la statistique dto
        StatistiqueCotisationDTO statistiqueCotisationDTO = new StatistiqueCotisationDTO();

        //initialisation des totaux à zero
        BigDecimal totalMontantCotisation = BigDecimal.ZERO;
        BigDecimal totalCotisationValide = BigDecimal.ZERO;
        BigDecimal totalCotisationNonValide = BigDecimal.ZERO;

        //Parcourir la liste des cotisation
        for (Cotisation cotisation : cotisationList){
            if (cotisation.getUser().getLogin().equals(userConnectedFound.getLogin())) {
                //recuperer le montant total
                BigDecimal montant = cotisation.getMontant();
                totalMontantCotisation = totalMontantCotisation.add(montant);

                //Ajouter les montants suivant l'etat
                if (cotisation.getEtat().equals(ETAT_COTISATION.VALIDE)) {
                    totalCotisationValide = totalCotisationValide.add(montant);
                } else if (cotisation.getEtat().equals(ETAT_COTISATION.NON_VALIDE)) {
                    totalCotisationNonValide = totalCotisationNonValide.add(montant);
                }
            }
        }

        //Setter ces montant dans le dto puis renvoyer le dto
        statistiqueCotisationDTO.setTotalMontantCotisation(totalMontantCotisation);
        statistiqueCotisationDTO.setTotalCotisationValidee(totalCotisationValide);
        statistiqueCotisationDTO.setTotalCotisationNonValidee(totalCotisationNonValide);

        return statistiqueCotisationDTO;
    }

    @Override
    public boolean changeEtatCotisation(Long id, ETAT_COTISATION etatCotisation) {
        Optional<Cotisation> cotisationSearch = cotisationRepo.findById(id);

        if (cotisationSearch.isPresent()){
            Cotisation cotisationFound = cotisationSearch.get();
            cotisationFound.setEtat(etatCotisation);
            cotisationRepo.save(cotisationFound);
            return true;
        }
        return false;
    }

    @Override
    public List<HistoriqueCotisationDTO> listCotisationByUser() {

        //Appel de la fonction pour trouver l'utisateur connecté
        User userConnected = userService.getAuthenticatedUser();

        List<Cotisation> cotisationList = cotisationRepo.findByUserLogin(userConnected.getLogin());

        return historiqueCotisationMapper.toDto(cotisationList);

    }

}

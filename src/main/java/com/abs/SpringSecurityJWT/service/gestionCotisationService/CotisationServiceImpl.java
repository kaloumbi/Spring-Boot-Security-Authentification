package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.CotisationDTO;
import com.abs.SpringSecurityJWT.dto.CotisationGetResponseDTO;
import com.abs.SpringSecurityJWT.enitty.Cotisation;
import com.abs.SpringSecurityJWT.enums.ETAT_SHARED;
import com.abs.SpringSecurityJWT.mapper.CotisationGetResponseMapper;
import com.abs.SpringSecurityJWT.mapper.CotisationMapper;
import com.abs.SpringSecurityJWT.myExeptions.MyNotFoundExceptionClass;
import com.abs.SpringSecurityJWT.repository.CotisationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CotisationServiceImpl implements CotisationService{


    @Autowired
    private CotisationRepo cotisationRepo;

    @Autowired
    private CotisationMapper cotisationMapper;

    @Autowired
    private CotisationGetResponseMapper cotisationGetResponseMapper;


    @Override
    public CotisationGetResponseDTO addCotisation(CotisationDTO cotisationDTO) {

        Cotisation addCot = cotisationMapper.toEntity(cotisationDTO);
        addCot.setEtat(ETAT_SHARED.ACTIF.toString());
        addCot = cotisationRepo.save(addCot);

        return cotisationGetResponseMapper.toDto(addCot);
    }

    @Override
    public List<CotisationGetResponseDTO> listeCotisations() {
        List<Cotisation> cotisationList = cotisationRepo.findAll();
        return cotisationGetResponseMapper.toDto(cotisationList);
    }

    @Override
    public CotisationGetResponseDTO detailCotisation(Long id) {

        Optional<Cotisation> cotisationSearch = cotisationRepo.findById(id);

        if (cotisationSearch.isEmpty()){
            return null;
        }

        Cotisation cotisationFound = cotisationSearch.get();

        return cotisationGetResponseMapper.toDto(cotisationFound);
    }

    @Override
    public CotisationGetResponseDTO updateCotisation(Long id, CotisationDTO cotisationDTO) {
        Optional<Cotisation> cotisationSearch = cotisationRepo.findById(id);

        if (cotisationSearch.isEmpty()){
            return null;
        }

        Cotisation cotisationFound = cotisationSearch.get();
        if (cotisationDTO.getId() == null){
            cotisationDTO.setId(cotisationFound.getId());
        }
        cotisationFound = cotisationMapper.toEntity(cotisationDTO);
        cotisationFound.setEtat(ETAT_SHARED.ACTIF.toString());

        cotisationRepo.save(cotisationFound);

        return cotisationGetResponseMapper.toDto(cotisationFound);
    }

    @Override
    public void deleteCotisation(Long id) {
        Optional<Cotisation> cotisationSearch = cotisationRepo.findById(id);

        if (cotisationSearch.isEmpty()){
            throw new MyNotFoundExceptionClass("Aucune cotisation à supprimer ! ");
        }

        Cotisation cotisationFound = cotisationSearch.get();

        cotisationFound.setEtat(ETAT_SHARED.SUPPRIME.toString());
        cotisationRepo.save(cotisationFound);
    }

    @Override
    public List<CotisationGetResponseDTO> searchCotisations(String nom) {
        return List.of();
    }
}

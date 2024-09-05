package com.abs.SpringSecurityJWT.service.gestionCotisationService;


import com.abs.SpringSecurityJWT.dto.AssociationDTO;
import com.abs.SpringSecurityJWT.dto.AssociationGetResponseDTO;
import com.abs.SpringSecurityJWT.enitty.Association;
import com.abs.SpringSecurityJWT.enums.ETAT_SHARED;
import com.abs.SpringSecurityJWT.mapper.AssociationGetResponseMapper;
import com.abs.SpringSecurityJWT.mapper.AssociationMapper;
import com.abs.SpringSecurityJWT.myExeptions.MyNotFoundExceptionClass;
import com.abs.SpringSecurityJWT.repository.AssociationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociationServiceImpl implements AssociationService{

    private final AssociationRepo associationRepo;

    private final AssociationMapper associationMapper;

    private final AssociationGetResponseMapper associationGetResponseMapper;


    public AssociationServiceImpl(AssociationRepo associationRepo, AssociationMapper associationMapper, AssociationGetResponseMapper associationGetResponseMapper) {
        this.associationRepo = associationRepo;
        this.associationMapper = associationMapper;
        this.associationGetResponseMapper = associationGetResponseMapper;
    }

    @Override
    public AssociationGetResponseDTO addAssociation(AssociationDTO associationDTO) {

        Association associationAdded = associationMapper.toEntity(associationDTO);
        associationAdded.setEtat(ETAT_SHARED.ACTIF.toString());
        associationAdded = associationRepo.save(associationAdded);

        return associationGetResponseMapper.toDto(associationAdded);
    }

    @Override
    public List<AssociationGetResponseDTO> listAssociation() {
        List<Association> associationList = associationRepo.findAll();

        return associationGetResponseMapper.toDto(associationList);
    }

    @Override
    public AssociationGetResponseDTO detailAssociation(Long id) {
        Optional<Association> associationSearched = associationRepo.findById(id);

        if (associationSearched.isEmpty()){
            throw new MyNotFoundExceptionClass("Association introuvable !");
        }

        Association associationFound = associationSearched.get();

        return associationGetResponseMapper.toDto(associationFound);
    }

    @Override
    public AssociationGetResponseDTO updateAssociation(Long id, AssociationDTO associationDTO) {

        Optional<Association> associationSearched = associationRepo.findById(id);

        if (associationSearched.isEmpty()){
            throw new MyNotFoundExceptionClass("Association introuvable !");
        }

        Association associationFound = associationSearched.get();

        //prend l'identifiant manquant si null
        if (associationDTO.getId() == null){
            associationDTO.setId(associationFound.getId());
        }

        //
        associationFound = associationMapper.toEntity(associationDTO);

        associationFound = associationRepo.save(associationFound);

        return associationGetResponseMapper.toDto(associationFound);
    }

    @Override
    public void deleteAssociation(Long id) {
        Optional<Association> associationSearched = associationRepo.findById(id);

        if (associationSearched.isEmpty()){
            throw new MyNotFoundExceptionClass("Association introuvable !");
        }

        Association associationFound = associationSearched.get();
        associationFound.setEtat(ETAT_SHARED.SUPPRIME.toString());

        associationRepo.save(associationFound);
    }

    @Override
    public List<AssociationGetResponseDTO> searcheAssociation(String nom) {
        return List.of();
    }
}

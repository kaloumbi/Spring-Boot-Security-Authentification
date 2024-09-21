package com.abs.SpringSecurityJWT.service.gestionCotisationService;


import com.abs.SpringSecurityJWT.dto.AssociationDTO;
import com.abs.SpringSecurityJWT.dto.UserReqResDTO;
import com.abs.SpringSecurityJWT.enitty.Association;
import com.abs.SpringSecurityJWT.enitty.User;
import com.abs.SpringSecurityJWT.enums.ETAT_SHARED;
import com.abs.SpringSecurityJWT.mapper.AssociationMapper;
import com.abs.SpringSecurityJWT.mapper.UserMapper;
import com.abs.SpringSecurityJWT.myExeptions.MyNotFoundExceptionClass;
import com.abs.SpringSecurityJWT.repository.AssociationRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociationServiceImpl implements AssociationService{

    private final AssociationRepo associationRepo;

    private final AssociationMapper associationMapper;

    private final UserMapper userMapper;



    public AssociationServiceImpl(AssociationRepo associationRepo, AssociationMapper associationMapper, UserMapper userMapper) {
        this.associationRepo = associationRepo;
        this.associationMapper = associationMapper;
        this.userMapper = userMapper;
    }

    @Override
    public AssociationDTO addAssociation(AssociationDTO associationDTO) {

        Association associationAdded = associationMapper.toEntity(associationDTO);
        associationAdded.setEtat(ETAT_SHARED.ACTIF.toString());
        associationAdded = associationRepo.save(associationAdded);

        return associationMapper.toDto(associationAdded);
    }

    @Override
    public List<AssociationDTO> listAssociation() {
        List<Association> associationList = associationRepo.findAll();

        return associationMapper.toDto(associationList);
    }

    @Override
    public AssociationDTO detailAssociation(Long id) {
        Optional<Association> associationSearched = associationRepo.findById(id);

        if (associationSearched.isEmpty()){
            throw new MyNotFoundExceptionClass("Association introuvable !");
        }

        Association associationFound = associationSearched.get();

        return associationMapper.toDto(associationFound);
    }

    @Override
    public AssociationDTO updateAssociation(Long id, AssociationDTO associationDTO) {

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

        return associationMapper.toDto(associationFound);
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
    public List<AssociationDTO> searcheAssociation(String nom) {
        return List.of();
    }


}

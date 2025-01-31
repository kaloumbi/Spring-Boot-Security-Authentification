package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.GlobalException.DataResponse;
import com.abs.SpringSecurityJWT.dto.UserGetDTO;
import com.abs.SpringSecurityJWT.dto.UserReqResDTO;
import com.abs.SpringSecurityJWT.enitty.User;
import com.abs.SpringSecurityJWT.enums.CotisationError;
import com.abs.SpringSecurityJWT.enums.ETAT_USER;
import com.abs.SpringSecurityJWT.mapper.EventMapper;
import com.abs.SpringSecurityJWT.mapper.UserGetMapper;
import com.abs.SpringSecurityJWT.mapper.UserMapper;
import com.abs.SpringSecurityJWT.myExeptions.MyNotFoundExceptionClass;
import com.abs.SpringSecurityJWT.repository.EventRepo;
import com.abs.SpringSecurityJWT.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;

    private final UserMapper userMapper;

    private final UserGetMapper userGetMapper;

    private final EventMapper eventMapper;

    private final EventRepo eventRepo;

    @Override
    public DataResponse<List<UserGetDTO>> listUsers(){

        try {
            log.info("Recuperation de la liste des utilisateurs... !");
            List<User> users = userRepo.findAll();

            // Verifier si la liste est vide
            if (users.isEmpty()){
                log.warn("Aucun utilisateur trouvé en base de donnée. ");
                return new DataResponse<>(CotisationError.NOT_FOUND, false, Collections.emptyList());
            }

            // Mapper les entités User vers des DTO
            List<UserGetDTO> userGetDTOS = userGetMapper.toDto(users);

            return new DataResponse<>(CotisationError.OK, true, userGetDTOS);

        }catch (Exception ex){
            log.error("Erreur lors de la recuperation des utilisateurs : {}", ex.getCause(), ex);
            return new DataResponse<>(CotisationError.INTERNAL_SERVER_ERROR.getCode(), CotisationError.INTERNAL_SERVER_ERROR.getMessage(), false, null);
        }

    }

    @Override
    public UserReqResDTO updateUser(Long id, UserReqResDTO userReqResDTO) {
        Optional<User> userSearched = userRepo.findById(id);

        if (userSearched.isEmpty()){
            throw new MyNotFoundExceptionClass(" Aucun utilisateur trouvé !! ");
        }

        User userFound = userSearched.get();

        if (userReqResDTO.getId() != null && !userReqResDTO.getId().equals(userFound.getId())){
            throw new MyNotFoundExceptionClass("L'ID de l'utilisateur ne peut pas être modifié !!");
        }

        if (userReqResDTO.getId() == null){
            userReqResDTO.setId(userFound.getId());
        }

        // Mettre à jour les propriétés de userFound avec les valeurs de userReqResDTO
        userFound = userMapper.toEntity(userReqResDTO);

        // Sauvegarder les modifications
        userRepo.save(userFound);

        // Retourner le DTO mis à jour
        return userMapper.toDto(userFound);
    }

    @Override
    public void deleteUser(Long id) {

        Optional<User> userDelSearched = userRepo.findById(id);

        if (userDelSearched.isEmpty()){
            throw new MyNotFoundExceptionClass(" Aucun utilisateur trouvé à supprimer !!!");
        }

        User userDelFound = userDelSearched.get();

        userDelFound.setEtat(ETAT_USER.SUPPRIME.toString());

        userRepo.save(userDelFound);
    }


    /**
     * LA RECHERCHE DES UTILISATEURS
     */

    @Override
    public List<UserReqResDTO> searcheUsers(String prenom){
        List<User> userSearched = userRepo.findByPrenomStartingWith(prenom);

        return userMapper.toDto(userSearched);
    }

    @Override
    public UserReqResDTO detailUser(Long id) {
        //ÇA PEUT EXISTER OUBIEN ÇA PEUT NE PAS EXISTER D'OÙ OPTIONAL
        Optional<User> userSearched = userRepo.findById(id);

        if (userSearched.isEmpty()){
            throw new MyNotFoundExceptionClass("Aucun utilisateur trouvé avec l'identifiant "+id +"fourni !");
        }

        User userFound = userSearched.get();

        return userMapper.toDto(userFound);
    }


    //recuperer l'utilisateur connecté
    @Override
    public User getAuthenticatedUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("AUTH ::: {}", auth);
        if (Objects.isNull(auth) || AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
            return null;
        }
        //
        log.info("AUTH NAME::: {}", auth.getName());
        Optional<User> userConnecte = userRepo.findByLogin(auth.getName());
        log.info("USER CONNECT::: {}", userConnecte);


        return userConnecte.orElse(null);
    }

    @Override
    public List<UserReqResDTO> findUsersByAssociationName(String nom) {
        List<User> userListByAssociation = userRepo.findByAssociationNom(nom);

        return userMapper.toDto(userListByAssociation);
    }


    @Override
    public List<UserGetDTO> getUsersByAssociation(String nom) {
        List<User> userList = userRepo.findByAssociations_Nom(nom);

        return userGetMapper.toDto(userList);
    }

}

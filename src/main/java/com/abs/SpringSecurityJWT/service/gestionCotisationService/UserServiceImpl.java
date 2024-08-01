package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.UserReqResDTO;
import com.abs.SpringSecurityJWT.enitty.User;
import com.abs.SpringSecurityJWT.enums.ETAT_USER;
import com.abs.SpringSecurityJWT.mapper.UserMapper;
import com.abs.SpringSecurityJWT.notFoundExceptionClass.MyNotFoundExceptionClass;
import com.abs.SpringSecurityJWT.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserReqResDTO> listUsers() {
        List<User> users = userRepo.findAll();

        return  userMapper.toDto(users);
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
        userMapper.toEntity(userReqResDTO);

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

        List<UserReqResDTO> listUsersByPrenom = userMapper.toDto(userSearched);

        return listUsersByPrenom;
    }

    @Override
    public UserReqResDTO detailUser(Long id) {
        Optional<User> userSearched = userRepo.findById(id);

        if (userSearched.isEmpty()){
            throw new MyNotFoundExceptionClass("Aucun utilisateur trouvé avec l'identifiant "+id +"fourni !");
        }

        User userFound = userSearched.get();

        return userMapper.toDto(userFound);
    }

}

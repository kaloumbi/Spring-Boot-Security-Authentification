package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.UserReqResDTO;
import com.abs.SpringSecurityJWT.enitty.User;

import java.util.List;

public interface UserService {


    List<UserReqResDTO> listUsers();

    UserReqResDTO updateUser(Long id, UserReqResDTO userReqResDTO);

    void deleteUser(Long id);

    List<UserReqResDTO> searcheUsers (String prenom);

    UserReqResDTO detailUser (Long id);


    //function to receive User Connected
    User getAuthenticatedUser();


}

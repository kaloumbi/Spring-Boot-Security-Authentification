package com.abs.SpringSecurityJWT.service;


import com.abs.SpringSecurityJWT.dto.AssociationDTO;
import com.abs.SpringSecurityJWT.dto.UserGetDTO;
import com.abs.SpringSecurityJWT.dto.UserReqResDTO;
import com.abs.SpringSecurityJWT.enitty.Association;
import com.abs.SpringSecurityJWT.enitty.Role;
import com.abs.SpringSecurityJWT.enitty.User;
import com.abs.SpringSecurityJWT.enums.ETAT_USER;
import com.abs.SpringSecurityJWT.mapper.RoleMapper;
import com.abs.SpringSecurityJWT.mapper.UserGetMapper;
import com.abs.SpringSecurityJWT.mapper.UserMapper;
import com.abs.SpringSecurityJWT.repository.AssociationRepo;
import com.abs.SpringSecurityJWT.repository.RoleRepository;
import com.abs.SpringSecurityJWT.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AssociationRepo associationRepo;

    //Ajout d'un mapper //cotisation mapper
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserGetMapper userGetMapper;

    @Autowired
    private RoleRepository roleRepository;



    public UserGetDTO signUp(UserReqResDTO registrationRequest){
        UserGetDTO resp = new UserGetDTO();

        try {
            User user = new User();

            user.setPrenom(registrationRequest.getPrenom());
            user.setNom(registrationRequest.getNom());
            user.setTel(registrationRequest.getTel());
            user.setLogin(registrationRequest.getLogin());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setEtat(ETAT_USER.ACTIF.toString());



//             Charger les rôles depuis la base de données
            List<Role> roles = registrationRequest.getRoles().stream()
                    .map(roleDTO -> roleRepository.findById(roleDTO.getId())
                            .orElseThrow(() -> new RuntimeException("Role not found: " + roleDTO.getId())))
                    .toList();



            //Assigner les roles existant à l'utilisateur
            user.setRoles(roles);


            //instansciers la liste d'association
            List<Association> associations = new ArrayList<>();
            //verifier l'existances des association
            if (registrationRequest.getAssociations() != null && ! registrationRequest.getAssociations().isEmpty() ){

                //parcourir la liste si elle n'est pas vide et recuperer tous les identifiants des associations
                for (AssociationDTO associationDTO: registrationRequest.getAssociations()){
                    Association association = associationRepo.findById(associationDTO.getId())
                            .orElseThrow(() -> new RuntimeException("Auncune association trouvée !"));
                    associations.add(association);
                }
                //affecter ces identifiant à l'utilisateurs
                user.setAssociations(associations);
            }



            User userResult = userRepo.save(user);
            UserGetDTO userResultDto = userGetMapper.toDto(userResult);

            if (userResult.getId() > 0){
                resp.setUsers(userResultDto.getUsers());
                resp.setMessage("User Save Successfully");
                resp.setStatusCode(200);
            }

        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }

        return resp;
    }


    public UserReqResDTO signIn(UserReqResDTO signinRequest){
        UserReqResDTO response = new UserReqResDTO();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getLogin(), signinRequest.getPassword() ));
            var user = userRepo.findByLogin(signinRequest.getLogin()).orElseThrow(); // Trouver l'utilisateur connecté !
            System.out.println("USER IS : "+ user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setUsers(user); //j'ai ajouter ici un utilisateur !
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Signed In");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }

        return response;

    }

    public UserReqResDTO refreshToken(UserReqResDTO refreshTokenRequest){
        UserReqResDTO response = new UserReqResDTO();
        String ourEmail = jwtUtils.extractUsername(refreshTokenRequest.getToken());
        User users = userRepo.findByLogin(ourEmail).orElseThrow();

        if (jwtUtils.isTokenValid(refreshTokenRequest.getToken(), users )){
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }

        response.setStatusCode(500);
        return response;
    }



}









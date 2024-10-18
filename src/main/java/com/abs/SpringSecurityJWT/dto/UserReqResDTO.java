package com.abs.SpringSecurityJWT.dto;

import com.abs.SpringSecurityJWT.enitty.Association;
import com.abs.SpringSecurityJWT.enitty.Event;
import com.abs.SpringSecurityJWT.enitty.Role;
import com.abs.SpringSecurityJWT.enitty.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserReqResDTO {
    private Long id;

    private int statusCode;

    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String prenom;
    private String nom;
    private String login;
    private String tel;
    private String password;
//    private String role; //modification
    private String etat;
    private User users;
//    //private List<Event> events;
//
//    private List<EventDTO> events;
//
    private List<RoleDTO> roles = new ArrayList<>(); //ajout des champs roles

    private List<AssociationDTO> associations = new ArrayList<>() ;

}

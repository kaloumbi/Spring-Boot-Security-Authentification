package com.abs.SpringSecurityJWT.dto;

import lombok.Data;

@Data
public class UserGetDTO {

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
    private String role;
    private String etat;
}

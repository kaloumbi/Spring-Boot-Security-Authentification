package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.enitty.MailStructure;

public interface SendMailService {

    void sendMail(String mail, MailStructure mailStructure);
}

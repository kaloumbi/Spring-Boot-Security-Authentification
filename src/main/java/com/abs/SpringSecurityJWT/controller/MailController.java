package com.abs.SpringSecurityJWT.controller;

import com.abs.SpringSecurityJWT.enitty.MailStructure;
import com.abs.SpringSecurityJWT.service.gestionCotisationService.SendMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class MailController {

    private final SendMailService sendMailService;

    @PostMapping("/send/{mail}")
    public ResponseEntity<String> sendMail(@PathVariable String mail, @RequestBody MailStructure mailStructure){
        sendMailService.sendMail(mail, mailStructure);

        return new ResponseEntity<>("Email envoyé avec succès !", HttpStatus.OK);
    }
}

package com.abs.SpringSecurityJWT.repository;

import com.abs.SpringSecurityJWT.enitty.Cotisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;

public interface CotisationRepo extends JpaRepository<Cotisation, Long> {
    // Méthode pour récupérer toutes les cotisations d'un utilisateur via son login
    List<Cotisation> findByUserLogin(String login);
}

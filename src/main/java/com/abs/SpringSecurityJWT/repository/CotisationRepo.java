package com.abs.SpringSecurityJWT.repository;

import com.abs.SpringSecurityJWT.enitty.Cotisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Objects;

public interface CotisationRepo extends JpaRepository<Cotisation, Long> {
    // Méthode pour récupérer toutes les cotisations d'un utilisateur via son login
    List<Cotisation> findByUserLogin(String login);

    //methode pour trouver ses cotisation par login et la category
    // Requête dérivée basée sur les noms des champs et des relations
    //List<Cotisation> findByUserLoginAndCategoryCotNom(String login, String category);

    // Méthode dérivée pour rechercher par login, category et association
    List<Cotisation> findByUserLoginAndCategoryCotNomAndAssociationNom(String login, String category, String association);


}

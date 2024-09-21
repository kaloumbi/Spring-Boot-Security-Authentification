package com.abs.SpringSecurityJWT.repository;

import com.abs.SpringSecurityJWT.dto.UserReqResDTO;
import com.abs.SpringSecurityJWT.enitty.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    List<User> findByPrenomStartingWith (String prenom);

    //liste des utilisateurs par associations

    @Query("SELECT u FROM User u JOIN u.associations a WHERE a.nom = :nom")
    List<User> findByAssociationNom(@Param("nom") String nom);

    // Rechercher des utilisateurs par le nom de l'association
    List<User> findByAssociations_Nom(String nom);
//    @Query("select u from User u left join fetch u.events")
//    List<User> findAllWithEvents();

}

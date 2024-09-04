package com.abs.SpringSecurityJWT.repository;

import com.abs.SpringSecurityJWT.dto.UserReqResDTO;
import com.abs.SpringSecurityJWT.enitty.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    List<User> findByPrenomStartingWith (String prenom);

    @Query("select u from User u left join fetch u.events")
    List<User> findAllWithEvents();

}

package com.abs.SpringSecurityJWT.repository;

import com.abs.SpringSecurityJWT.enitty.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);
}

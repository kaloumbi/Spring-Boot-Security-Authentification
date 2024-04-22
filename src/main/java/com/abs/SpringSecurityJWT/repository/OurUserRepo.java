package com.abs.SpringSecurityJWT.repository;

import com.abs.SpringSecurityJWT.enitty.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OurUserRepo extends JpaRepository<OurUsers, Integer> {

    Optional<OurUsers> findByEmail(String email);
}

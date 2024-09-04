package com.abs.SpringSecurityJWT.repository;

import com.abs.SpringSecurityJWT.enitty.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventRepo extends JpaRepository<Event, Long> {

    List<Event> findByNom(String nom);
}

package com.abs.SpringSecurityJWT.repository;

import com.abs.SpringSecurityJWT.enitty.Event;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepo extends JpaRepository<Event, Long> {

}

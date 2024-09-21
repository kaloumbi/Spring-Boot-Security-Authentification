package com.abs.SpringSecurityJWT.repository;

import com.abs.SpringSecurityJWT.enitty.Association;
import com.abs.SpringSecurityJWT.enitty.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssociationRepo extends JpaRepository<Association, Long> {

}

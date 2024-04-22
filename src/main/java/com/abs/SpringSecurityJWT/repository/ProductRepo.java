package com.abs.SpringSecurityJWT.repository;

import com.abs.SpringSecurityJWT.enitty.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo  extends JpaRepository<Product, Integer> {


}

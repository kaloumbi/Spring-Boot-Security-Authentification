package com.abs.SpringSecurityJWT.controller;

import com.abs.SpringSecurityJWT.dto.RoleDTO;
import com.abs.SpringSecurityJWT.service.gestionCotisationService.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role/add")
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO roleDTO){

        RoleDTO roleAdded = roleService.creatRole(roleDTO);

        return new ResponseEntity<>(roleAdded, HttpStatus.OK);
    }

    @GetMapping("/roles/list")
    public ResponseEntity<List<RoleDTO>> listRoles (){

        List<RoleDTO> roleDTOList = roleService.listRoles();

        return new ResponseEntity<>(roleDTOList, HttpStatus.OK);
    }
}

package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.RoleDTO;
import com.abs.SpringSecurityJWT.enitty.Role;

import java.util.List;

public interface RoleService {

    RoleDTO creatRole(RoleDTO roleDTO);

    List<RoleDTO> listRoles ();
}

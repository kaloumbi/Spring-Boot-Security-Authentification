package com.abs.SpringSecurityJWT.service.gestionCotisationService;

import com.abs.SpringSecurityJWT.dto.RoleDTO;
import com.abs.SpringSecurityJWT.enitty.Role;
import com.abs.SpringSecurityJWT.mapper.RoleMapper;
import com.abs.SpringSecurityJWT.myExeptions.MyNotFoundExceptionClass;
import com.abs.SpringSecurityJWT.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleDTO creatRole(RoleDTO roleDTO) {

        Role role = roleMapper.toEntity(roleDTO);

        role = roleRepository.save(role);

        return roleMapper.toDto(role);
    }

    @Override
    public List<RoleDTO> listRoles() {
        List<Role> roleList = roleRepository.findAll();

        return roleMapper.toDto(roleList);
    }


}

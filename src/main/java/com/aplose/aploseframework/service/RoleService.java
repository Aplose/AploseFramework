package com.aplose.aploseframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplose.aploseframework.model.Role;
import com.aplose.aploseframework.repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository _roleRepository;
    
    public List<Role> getAll(){
        return this._roleRepository.findAll();
    }

    public Role getById(Long roleId){
        return this._roleRepository.findById(roleId).get();
    }

    public Role getByAuthority(String authoriry){
        return this._roleRepository.findByAuthority(authoriry);
    }
}

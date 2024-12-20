package fr.aplose.aploseframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.aplose.aploseframework.model.Role;
import fr.aplose.aploseframework.model.RoleEnum;
import fr.aplose.aploseframework.repository.RoleRepository;
import jakarta.annotation.PostConstruct;

@Service
public class RoleService {

    @Autowired
    private RoleRepository _roleRepository;
    
    @PostConstruct
    private void init(){
        Role role = _roleRepository.findByAuthority(RoleEnum.ROLE_SUPER_ADMIN.toString());
        if (role==null){
            role = new Role(RoleEnum.ROLE_SUPER_ADMIN);
            _roleRepository.save(role);
        }
    }
    
    public List<Role> getAll(){
        return this._roleRepository.findAll();
    }

    public Role getById(Long roleId){
        return this._roleRepository.findById(roleId).get();
    }

    public Role getByAuthority(String authoriry){
        return this._roleRepository.findByAuthority(authoriry);
    }
    
    public void create(RoleEnum role){
        _roleRepository.save(new Role(role));
    }
}

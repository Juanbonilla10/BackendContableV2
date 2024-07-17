/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.repository;

import com.accounting.V2.model.RolesModel;
import com.accounting.V2.repository.crud.RolesCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author L E N O V O
 */
@Repository
public class RolesRepository {
    
    @Autowired
    private RolesCrudRepository rolesCrudRepository;
    
    public List<RolesModel> getAllRoles(){
        return rolesCrudRepository.findAll();
    }
    
    public Optional<RolesModel> getById(Integer idRole){
        return rolesCrudRepository.findById(idRole);
    }
    
}

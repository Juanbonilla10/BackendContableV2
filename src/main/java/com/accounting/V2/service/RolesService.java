/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.service;

import com.accounting.V2.model.RolesModel;
import com.accounting.V2.repository.RolesRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author L E N O V O
 */
@Service
public class RolesService {
    
    @Autowired
    private RolesRepository rolesRepository;
    
    public List<RolesModel> getAllRoles(){
        return rolesRepository.getAllRoles();
    }
    
    public Optional<RolesModel> getByIdRole(Integer idRol){
        return rolesRepository.getById(idRol);
    }
    
}

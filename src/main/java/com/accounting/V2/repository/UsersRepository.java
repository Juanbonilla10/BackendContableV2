/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.repository;

import com.accounting.V2.model.UsersModel;
import com.accounting.V2.repository.crud.UsersCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author L E N O V O
 */

@Repository
public class UsersRepository {
    
    @Autowired
    private UsersCrudRepository usersCrudRepository;
    
    public List<UsersModel> getAllUsers(){
        return usersCrudRepository.findAll();
    }
    
    public Optional<UsersModel> getByEmail(String mail){
        return usersCrudRepository.findOneByMail(mail);
    }
    
    public Optional<UsersModel> getById(Integer idUser){
        return usersCrudRepository.findById(idUser);
    }
    
}

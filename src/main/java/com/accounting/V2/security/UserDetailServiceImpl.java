/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.security;

import com.accounting.V2.model.UsersModel;
import com.accounting.V2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author L E N O V O
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService{
    
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsersModel user = usersRepository.getByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("El usuario con mail " + email + " no existe"));
    
        return new UserDetailsImpl(user);
    }
    
}

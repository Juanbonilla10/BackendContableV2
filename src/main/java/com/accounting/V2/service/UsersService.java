/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.service;

import com.accounting.V2.model.RolesModel;
import com.accounting.V2.model.UsersModel;
import com.accounting.V2.repository.RolesRepository;
import com.accounting.V2.repository.UsersRepository;
import com.accounting.V2.utils.EnumVars;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author L E N O V O
 */
@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private RolesRepository rolesRepository;

    public List<UsersModel> getAllUsers(String email) {
        try {
            System.out.println("Ingresando a obtener todos los usuarios");
            Optional<UsersModel> usrValidateUser = getByEmail(email);
            if (usrValidateUser.isPresent()) {
                System.out.println("Si se encontrarón datos para el ".concat(email));
                Optional<RolesModel> roles = rolesRepository.getById(usrValidateUser.get().getRoles_id());
                if(roles.get().getDescription().equalsIgnoreCase(EnumVars.ADMINISTRADOR.getCodigo())){
                    System.out.println("Retornando datos para un Administrador");
                    return usersRepository.getAllUsers();
                }else{
                    System.out.println("Retornando datos para un Usuario sin elevacion de permisos");
                    List<UsersModel> lista = usrValidateUser
                            .stream()             // Convertimos el Optional a un Stream
                            .collect(Collectors.toList());
                    
                    return lista;
                }
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Error al acceder a toda la lista de".concat(e.getMessage()));
            return new ArrayList<>();
        }
    }

    public Optional<UsersModel> getByEmail(String email) {
        try {
            System.out.println("Obteniendo el usuario con mail ".concat(email));
            Optional<UsersModel> usr = usersRepository.getByEmail(email);
            if (usr.isPresent()) {
                System.out.println("Si hay datos para el objeto");
                return usr;
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            System.out.println("Error al consulta los datos para el mail ".concat(email));
            return Optional.empty();
        }

    }

    public UsersModel getUsersById(Integer idUser) {
        try {
            return usersRepository.getById(idUser).get();
        } catch (Exception e) {
            System.out.println("Error al obtener el usuario ".concat(e.getMessage()));
            return new UsersModel();
        }
    }

}

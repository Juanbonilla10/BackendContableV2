/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.service;

import com.accounting.V2.model.RolesModel;
import com.accounting.V2.model.StateModel;
import com.accounting.V2.model.UsersModel;
import com.accounting.V2.repository.StateRepository;
import com.accounting.V2.utils.EnumVars;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author L E N O V O
 */
@Service
public class StateService {
    
    @Autowired
    private StateRepository stateRepository;
    
    @Autowired
    private UsersService usersService;
    
    @Autowired
    private RolesService rolesService;
    
    public List<StateModel> getAllStates(String mail){
         try {
            System.out.println("Ingresando a obtener los estados ");
            Optional<UsersModel> um = usersService.getByEmail(mail);
            if(um.isPresent()){
                Optional<RolesModel> optionalRol = rolesService.getByIdRole(um.get().getRoles_id());
                if(optionalRol.get().getDescription().equals(EnumVars.ADMINISTRADOR.getCodigo())){
                    System.out.println("Dolviendo stados para el usuario con rol ADMINISTRADOR");
                    return stateRepository.getAll();
                }else{
                    System.out.println("No se pueden obtener datos no es ADMINISTRADOR");
                    return new ArrayList<>() ;
                }
            }else{
                System.out.println("El usuario no existe " .concat(mail));
                return new ArrayList<>() ;
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los estados ");
            return new ArrayList<>() ;
        }
    }
    
    public StateModel getStateByDescription(String description,String mail){
        
        try {
            System.out.println("Ingresando a obtener el estado " .concat(description));
            Optional<UsersModel> um = usersService.getByEmail(mail);
            if(um.isPresent()){
                Optional<RolesModel> optionalRol = rolesService.getByIdRole(um.get().getRoles_id());
                if(optionalRol.get().getDescription().equals(EnumVars.ADMINISTRADOR.getCodigo())){
                    System.out.println("Dolviendo stados para el usuario con rol ADMINISTRADOR");
                    return stateRepository.getStates(description);
                }else{
                    System.out.println("No se pueden obtener datos no es ADMINISTRADOR");
                    return new StateModel();
                }
            }else{
                System.out.println("El usuario no existe " .concat(mail));
                return new StateModel();
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la descripción " .concat(description));
            return new StateModel();
        }
         
    }
    
    public StateModel createState(StateModel stateModel,String mail){
         try {
            System.out.println("Ingresando a guardar el estado " .concat(stateModel.toString()));
            Optional<UsersModel> um = usersService.getByEmail(mail);
            if(um.isPresent()){
                Optional<RolesModel> optionalRol = rolesService.getByIdRole(um.get().getRoles_id());
                if(optionalRol.get().getDescription().equals(EnumVars.ADMINISTRADOR.getCodigo())){
                    System.out.println("Creando el estado para el rol ADMINISTRADOR");
                    return stateRepository.saveState(stateModel);
                }else{
                    System.out.println("No se pueden obtener datos no es ADMINISTRADOR");
                    return new StateModel();
                }
            }else{
                System.out.println("El usuario no existe " .concat(mail));
                return new StateModel();
            }
        } catch (Exception e) {
            System.out.println("Error al crear el estado " .concat(stateModel.toString()));
            return new StateModel();
        }
    }
    
    
    
}

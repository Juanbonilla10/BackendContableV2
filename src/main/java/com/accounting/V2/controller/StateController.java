/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.controller;

import com.accounting.V2.model.StateModel;
import com.accounting.V2.service.StateService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author L E N O V O
 */

@RequestMapping(value = "api/State")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class StateController {

    @Autowired
    private StateService stateService;
    
    @GetMapping(value = "/allStates")
    public List<StateModel> getAllStates(){
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return stateService.getAllStates(authentication.getName());
    }
    
    @GetMapping(value = "/getState")
    public ResponseEntity getState(@PathVariable String state){
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();  
        return new ResponseEntity(stateService.getStateByDescription(state,authentication.getName()), HttpStatus.OK);
    }
    
    @PostMapping(value = "/saveState")
    public ResponseEntity saveState(@RequestBody StateModel stateModel){
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        return new ResponseEntity(stateService.createState(stateModel,authentication.getName()), HttpStatus.OK);
    }
    
}

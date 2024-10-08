/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.controller;

import com.accounting.V2.model.FixedCostsModel;
import com.accounting.V2.service.FixedCostsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author L E N O V O
 */
@RequestMapping(value = "api/fixedCost")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class FixedCostsController {
    
    @Autowired
    private FixedCostsService fixedCostsService;
    
    @GetMapping(value = "/allFixedCost")
    public ResponseEntity getAllFixedCosts(){
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new  ResponseEntity(fixedCostsService.getFixedCostsById(authentication.getName()),HttpStatus.OK);
    }
    
    
    @PostMapping(value = "/createFixedCost")
    public ResponseEntity createFixedCosts(@RequestBody FixedCostsModel fixedCostsModel){
         // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         return new ResponseEntity(fixedCostsService.savesFixedCosts(fixedCostsModel,authentication.getName()), HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/deleteFixedCost")
    public ResponseEntity deleteFixedCosts(@RequestBody  FixedCostsModel fixedCostsModel ){
        Integer state = fixedCostsService.deleteFixedCosts(fixedCostsModel);
        return new ResponseEntity( HttpStatus.valueOf(state));
    }
    
}

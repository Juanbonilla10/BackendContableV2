/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.controller;

import com.accounting.V2.model.IncomesModel;
import com.accounting.V2.model.response.IncomesModelResponse;
import com.accounting.V2.service.IncomesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping(value = "api/Incomes")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class IncomesController {
    
    @Autowired
    private IncomesService incomesService;
    
    @GetMapping(value = "/allIncomes")
    public List<IncomesModelResponse> getAllIncomes(){
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return incomesService.getAllIncomes(authentication.getName());
    }
    
    @PostMapping(value = "/saveIncomes")
    public ResponseEntity saveIncomes(@RequestBody IncomesModel incomesModel){
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(incomesService.saveIncomes(incomesModel, authentication.getName()), HttpStatus.OK);
    }
    
    
    @DeleteMapping(value = "/delIncomes/{idIncomes}")
    public ResponseEntity delIncomes(@PathVariable Integer idIncomes){
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        incomesService.deleteIncomes(idIncomes, authentication.getName());
        return ResponseEntity.status(204).build();
    }
}

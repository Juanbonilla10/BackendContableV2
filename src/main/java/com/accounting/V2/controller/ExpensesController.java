/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.controller;

import com.accounting.V2.model.ExpensesModel;
import com.accounting.V2.service.ExpensesService;
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
@RequestMapping(value = "api/Expenses")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ExpensesController {
    
    @Autowired
    private ExpensesService expensesService;
    
    
    @GetMapping(value = "/allExpensesAdmin")
    public List<ExpensesModel> getAllExpenses(){
        return expensesService.getAllExpenses();
    }

    
    @GetMapping(value = "/allExpenses")
    public ResponseEntity getAllExpensesByUser(){
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(expensesService.expensesAllUser(authentication.getName()), HttpStatus.OK);
    }
    
    @PostMapping(value = "/saveExpenses")
    public ResponseEntity saveExpenses(@RequestBody ExpensesModel expensesModel){
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
        return new ResponseEntity(expensesService.saveExpenses(expensesModel, authentication.getName()), HttpStatus.OK);
        
    }
    
    @DeleteMapping(value = "/delExpenses/{idExpenses}")
    public ResponseEntity delExpenses(@PathVariable String idExpenses){
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        expensesService.deleteExpenses(idExpenses, authentication.getName());
        return  ResponseEntity.status(201).build();
    }
            
    
    
}

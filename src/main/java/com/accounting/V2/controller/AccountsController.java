/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.controller;

import com.accounting.V2.model.AccountsModel;
import com.accounting.V2.model.response.AccountsModelResponse;
import com.accounting.V2.service.AccountsService;
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
@RequestMapping(value = "api/Accounts")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AccountsController {

    @Autowired
    private AccountsService accountsService;

    @GetMapping(value = "/getAccountsV1/{action}")
    public List<AccountsModelResponse> getAllAccounts(@PathVariable String action) {
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Boolean ac = true;
        if(action.equalsIgnoreCase("FullOper")){
        ac = false; 
        }
        
        return accountsService.getAllAccountsByUserResponseModel(authentication.getName(),ac);
    }

    @GetMapping(value = "/getAccounts")
    public List<AccountsModel> getAllAccountsUser() {

        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return accountsService.getAccountsByUser(authentication.getName());
    }

    @GetMapping(value = "/getAccountUser/{accountNumber}")
    public ResponseEntity getAccountUser(@PathVariable String accountNumber) {
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(accountsService.getAccountByUser(accountNumber, authentication.getName()), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAccount/{accountNumber}")
    public ResponseEntity deleteAccount(@PathVariable String accountNumber) {
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>(accountNumber, HttpStatus.valueOf(accountsService.deleteAccount(accountNumber,authentication.getName())));
    }
    
    @PostMapping(value = "/saveAccount")
    public ResponseEntity saveAccount(@RequestBody AccountsModel accountsModel){
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(accountsService.saveAccountByUser(accountsModel, authentication.getName()) , HttpStatus.CREATED);
    }
}

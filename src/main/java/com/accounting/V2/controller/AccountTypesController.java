/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.controller;

import com.accounting.V2.model.AccounttypesModel;
import com.accounting.V2.service.AccountTypesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RequestMapping(value = "api/AccountTypes")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class AccountTypesController {
    
    @Autowired
    private AccountTypesService accountTypesService;
    
    @GetMapping(value = "/getAllAccountTypes")
    public List<AccounttypesModel> getAllAccountType(){
        return accountTypesService.getAllAccountTypes();
    }
    
    @PostMapping(value = "/saveAccountType")
    public ResponseEntity<Object> saveAccountType(@RequestBody AccounttypesModel accounttypesModel ){
        // Si todo está bien, devuelve una respuesta exitosa
        accountTypesService.saveAccountType(accounttypesModel);
        return new ResponseEntity<>("Usuario creado exitosamente", HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/deleteAccountType")
    public ResponseEntity deleteAccountType(@RequestBody AccounttypesModel accounttypesModel ){
        if(accounttypesModel.getIdaccountTypes() != null){
            accountTypesService.deleteAccountType(accounttypesModel);
            return ResponseEntity.status(200).build();
        }else{
            return ResponseEntity.status(422).build();
        }
    }
    
}

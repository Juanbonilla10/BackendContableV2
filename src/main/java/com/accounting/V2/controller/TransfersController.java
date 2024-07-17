/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.controller;

import com.accounting.V2.model.TransfersModel;
import com.accounting.V2.service.TransfersService;
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
@RequestMapping(value = "api/Transfers")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class TransfersController {
    
    @Autowired
    private TransfersService transfersService;
    
    @GetMapping(value = "/allTransfers")
    public List<TransfersModel> getAllTransfers(){
        return transfersService.getAllTransfers();
    }
    
    @PostMapping(value = "/saveTransfer")
    public ResponseEntity saveTransfer(@RequestBody TransfersModel transfersModel){
         // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity(transfersService.saveTransfer(transfersModel, authentication.getName())
                , HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/deleteTransfer/{idTransfer}")
    public ResponseEntity deleteTransfer(@PathVariable Integer idTransfer){
         // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        transfersService.deleteTransfer(idTransfer, authentication.getName());
        return new ResponseEntity( idTransfer
                , HttpStatus.OK);
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.controller;

import com.accounting.V2.model.FranchisesModel;
import com.accounting.V2.service.FranchisesService;
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
@RequestMapping(value = "api/Franchises")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class FranchisesController {
    
    @Autowired
    private FranchisesService franchisesService;
    
    @GetMapping(value = "/allFranchises")
    public List<FranchisesModel> getAllFranchises(){
        return franchisesService.getAllFranchises();
    }
    
    @PostMapping(value = "/createFranchises")
    public ResponseEntity createFranchises(@RequestBody FranchisesModel franchisesModel){
        franchisesService.cretaeFranchises(franchisesModel);
        return new ResponseEntity<>("Franquicia creada exitosamente", HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/deleteFranchises")
    public ResponseEntity deleteFranchises(@RequestBody FranchisesModel franchisesModel ){
        if(franchisesModel.getIdfranchises() != null){
            Integer state =  franchisesService.deleteFranchises(franchisesModel);
            return ResponseEntity.status(state).build();
        }else{
            return ResponseEntity.status(400).build();
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.controller;

import com.accounting.V2.model.BanksModel;
import com.accounting.V2.service.BanksService;
import com.accounting.V2.utils.EnumVarsState;
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

@RequestMapping(value = "api/Banks")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class BanksController {
    
    @Autowired
    private BanksService banksService;
    
    @GetMapping(value = "/allBanks")
    public List<BanksModel> getAllBanks(){
        return banksService.getAllBanks();
    }
    
    @PostMapping(value = "/createBanks")
    public ResponseEntity createBanks(@RequestBody BanksModel banksModel){
        banksService.createBanks(banksModel);
        return new ResponseEntity<>(banksModel, HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/deleteBanks")
    public ResponseEntity deleteBanks(@RequestBody BanksModel banksModel){
        return new ResponseEntity<>(banksModel, HttpStatus.valueOf(banksService.deleteBanks(banksModel)));
    }
    
}

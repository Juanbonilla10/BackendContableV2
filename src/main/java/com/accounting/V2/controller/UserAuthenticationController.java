/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.controller;

import com.accounting.V2.model.UseraAthenticationModel;
import com.accounting.V2.service.UserAuthenticationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author L E N O V O
 */

@RequestMapping(value = "api/UserAuthentication")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class UserAuthenticationController {
    
    @Autowired
    private UserAuthenticationService userAuthenticationService;
    
    @GetMapping(value = "/allUserAuthentication")
    public List<UseraAthenticationModel> getAllUserAuthentication(){
        return userAuthenticationService.getAllUserAuthentication();
    }
    
}

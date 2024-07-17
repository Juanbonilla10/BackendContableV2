/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.controller;

import com.accounting.V2.model.CardsModel;
import com.accounting.V2.model.response.CardsModelResponse;
import com.accounting.V2.service.CardsService;
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
@RequestMapping(value = "api/Cards")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CardsController {

    @Autowired
    private CardsService cardsService;

    @GetMapping(value = "/allCards")
    public List<CardsModel> getAllCards() {
        return cardsService.getAllCards();
    }

    @GetMapping(value = "/idCard/{cardNumber}")
    public CardsModel getByIdCardUser(@PathVariable String cardNumber) {

        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return cardsService.getByIdCardUser(cardNumber, authentication.getName());
    }

    @GetMapping(value = "/idCardsUser/")
    public List<CardsModel> getByIdCardsUser() {

        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return cardsService.getByIdCardsModel( authentication.getName());
    }
    
    @GetMapping(value = "/allCardsByUser")
    public List<CardsModelResponse> getAllCardsByUser(){
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();    
        return cardsService.getAllCardsByUserResponseModel(authentication.getName());
    }
    
    @PostMapping(value = "/saveCard")
    public ResponseEntity saveCard(@RequestBody CardsModel cardsModel){
        
        // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        return  new ResponseEntity(cardsService.saveCard(cardsModel, authentication.getName()), HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/deleteCard/{card}")
    public ResponseEntity deleteCard(@PathVariable String card){
         // Obtenemos la autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();   
        cardsService.deleteCard(card, authentication.getName());
         return  new ResponseEntity(HttpStatus.OK);
    
    }

}

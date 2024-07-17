/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.repository;

import com.accounting.V2.model.CardsModel;
import com.accounting.V2.repository.crud.CardsCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author L E N O V O
 */
@Repository
public class CardsRepository {

    @Autowired
    private CardsCrudRepository cardsCrudRepository;

    public List<CardsModel> getAllCards() {
        return cardsCrudRepository.findAll();
    }

    public Optional<CardsModel> getById(Integer idCards) {
        return cardsCrudRepository.findById(idCards);
    }

    public CardsModel getByIdCard(String idCards) {
        return cardsCrudRepository.findByCardNumber(idCards);
    }

    public Integer idStringCard(String idCardNumber) {
        return cardsCrudRepository.findOneByCardNumber(idCardNumber);
    }

    public List<CardsModel> getCardByUser(Integer idUser) {
        return cardsCrudRepository.findByUsersId(idUser);
    }
    
    public CardsModel saveCard(CardsModel cardsModel){
        return cardsCrudRepository.save(cardsModel);
    }
    
    public void deleteCard(Integer cardNumber){
         cardsCrudRepository.deleteById(cardNumber);
    }

}

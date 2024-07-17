/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.accounting.V2.repository.crud;

import com.accounting.V2.model.CardsModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author L E N O V O
 */
public interface CardsCrudRepository extends JpaRepository<CardsModel, Integer>{
    
    
    // Consulta personalizada para buscar por el campo "idCardNumber"
    @Query("SELECT c.users_id FROM Cards c WHERE c.card_number = :idCardNumber")
    Integer findOneByCardNumber(String idCardNumber);
    
    // Consulta personalizada para buscar por el campo "idCardNumber"
    @Query("SELECT c FROM Cards c WHERE c.card_number = :idCardNumber")
    CardsModel findByCardNumber(String idCardNumber);
   
    
    // Consulta personalizada para buscar por el campo "idUser"
    @Query("SELECT c FROM Cards c WHERE c.users_id = :idUser")
    List<CardsModel> findByUsersId(Integer idUser);
    
}

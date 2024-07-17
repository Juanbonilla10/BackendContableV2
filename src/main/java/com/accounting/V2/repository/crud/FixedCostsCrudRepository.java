/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.accounting.V2.repository.crud;

import com.accounting.V2.model.CardsModel;
import com.accounting.V2.model.FixedCostsModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author L E N O V O
 */
public interface FixedCostsCrudRepository extends JpaRepository<FixedCostsModel, Integer>{
    
     // Consulta personalizada para buscar por el campo "idCardNumber"
    @Query("SELECT c FROM FixedCosts c WHERE c.users_id = :users_id")
    List<FixedCostsModel> findByUserIdFixedCost(String users_id);
    
}

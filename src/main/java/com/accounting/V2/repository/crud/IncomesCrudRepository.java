/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.accounting.V2.repository.crud;

import com.accounting.V2.model.IncomesModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author L E N O V O
 */
public interface IncomesCrudRepository extends JpaRepository<IncomesModel, Integer>{
    
    // Consulta personalizada para buscar por el campo "idCardNumber"
    @Query("SELECT c FROM Incomes c WHERE c.users_id = :users_id")
    List<IncomesModel> findByUserIdIncomes(Integer users_id);
    
}

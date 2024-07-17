/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.accounting.V2.repository.crud;

import com.accounting.V2.model.ExpensesModel;
import com.accounting.V2.model.FixedCostsModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author L E N O V O
 */
public interface ExpensesCrudRepository extends JpaRepository<ExpensesModel, Integer>{
    
     // Consulta personalizada para buscar por el campo "idCardNumber"
    @Query("SELECT c FROM Expenses c WHERE c.users_id = :users_id")
    List<ExpensesModel> findByUserIdExpenses(String users_id);
    
}

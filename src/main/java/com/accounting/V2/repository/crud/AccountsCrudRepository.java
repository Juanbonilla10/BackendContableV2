/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.accounting.V2.repository.crud;

import com.accounting.V2.model.AccountsModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author L E N O V O
 */
public interface AccountsCrudRepository extends JpaRepository<AccountsModel, Integer>{
    
    
    // Consulta personalizada para buscar por el campo "idCardNumber"
    @Query("SELECT c FROM Accounts c WHERE c.users_id = :users_id")
    List<AccountsModel> findAccountsUser(Integer users_id);
    
    // Consulta personalizada para buscar por el campo "idCardNumber"
    @Query("SELECT c FROM Accounts c WHERE c.account_number = :account_number")
    AccountsModel findAccountUser(String account_number);
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.accounting.V2.repository.crud;

import com.accounting.V2.model.AccountBalancesModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author L E N O V O
 */
public interface AccountBalanceCrudRepository extends JpaRepository<AccountBalancesModel, Integer> {
    
     // Consulta personalizada para buscar por el campo "idCardNumber"
    @Query("SELECT c FROM AccountBalances c WHERE c.users_idusers = :users_idusers")
    List<AccountBalancesModel> findAccountsBalanceUser(Integer users_idusers);
    
    @Query("SELECT c FROM AccountBalances c WHERE c.accounts_idaccounts = :accounts_idaccounts")
    AccountBalancesModel findAccountBalanceByAccountId(String accounts_idaccounts);
    
    
}

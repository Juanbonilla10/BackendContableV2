/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.repository;

import com.accounting.V2.model.AccountBalancesModel;
import com.accounting.V2.repository.crud.AccountBalanceCrudRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author L E N O V O
 */
@Repository
public class AccountBalanceRepository {
    
    @Autowired
    private AccountBalanceCrudRepository accountBalanceCrudRepository;
    
    public List<AccountBalancesModel> getAccountsBalancesByUser(Integer idAccountUser){
        return accountBalanceCrudRepository.findAccountsBalanceUser(idAccountUser);
    }
    
    public void saveAccountBalanceByUser(AccountBalancesModel abm){
        accountBalanceCrudRepository.save(abm);
    }
    
    public AccountBalancesModel getAccountBalanceByAccountId(String accountId){
        return accountBalanceCrudRepository.findAccountBalanceByAccountId(accountId);
    }
    
}

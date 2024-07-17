/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.repository;

import com.accounting.V2.model.AccountsModel;
import com.accounting.V2.model.UsersModel;
import com.accounting.V2.repository.crud.AccountsCrudRepository;
import com.accounting.V2.service.UsersService;
import com.accounting.V2.utils.EnumVarsState;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author L E N O V O
 */
@Repository
public class AccountsRepository {
    
    @Autowired
    private AccountsCrudRepository accountsCrudRepository;
    
    @Autowired
    private UsersService usersService;
    
    public List<AccountsModel> getAllAccounts(){
        return accountsCrudRepository.findAll();
    }
    
    public Optional<AccountsModel> getByAccountUser(Integer idAccount){
        return accountsCrudRepository.findById(idAccount);
    }
    
    public List<AccountsModel> getAllAccountsByUser(Integer userId){
        return accountsCrudRepository.findAccountsUser(userId);
    }
    
    public AccountsModel getAccountByUser(String accountNumber){
        return accountsCrudRepository.findAccountUser(accountNumber);
    }
    
    public void deleteAccount(Integer idAccount){
         accountsCrudRepository.deleteById(idAccount);
    }
    
    
    public AccountsModel saveAccountsModel(AccountsModel accountsModel){
        return accountsCrudRepository.save(accountsModel);
    }
}

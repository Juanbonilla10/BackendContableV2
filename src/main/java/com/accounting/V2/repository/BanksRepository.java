/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.repository;

import com.accounting.V2.model.BanksModel;
import com.accounting.V2.repository.crud.BanksCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author L E N O V O
 */
@Repository
public class BanksRepository {
    
    @Autowired
    private BanksCrudRepository banksCrudRepository;
    
    public List<BanksModel> getAllBanks(){
        return banksCrudRepository.findAll();
    }
    
    public BanksModel createBanks(BanksModel banksModel){
        return banksCrudRepository.save(banksModel);
    }
    
    public Optional<BanksModel> getBanksId(Integer idBanks){
        return banksCrudRepository.findById(idBanks);
    }
    
    public void deleteBanks(Integer idBanks){
        banksCrudRepository.deleteById(idBanks);
    }
    
}

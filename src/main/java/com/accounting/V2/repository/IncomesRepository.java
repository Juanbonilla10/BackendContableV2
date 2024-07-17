/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.repository;

import com.accounting.V2.model.IncomesModel;
import com.accounting.V2.repository.crud.IncomesCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author L E N O V O
 */
@Repository
public class IncomesRepository {
    
    @Autowired
    private IncomesCrudRepository incomesCrudRepository;
    
    public List<IncomesModel> getAllIncomes(){
        return incomesCrudRepository.findAll();
    }
    
    public IncomesModel saveIncomes(IncomesModel incomesModel){
        return incomesCrudRepository.save(incomesModel);
    }
    
    public Optional<IncomesModel> getIncomesId(Integer idIncomes){
        return incomesCrudRepository.findById(idIncomes);
    }
    
    public void deleteIncomesId(Integer idIncomes){
        incomesCrudRepository.deleteById(idIncomes);
    }
    
    public List<IncomesModel> getAllIncomesByUser(Integer user_id){
        return incomesCrudRepository.findByUserIdIncomes(user_id);
    }
    
}

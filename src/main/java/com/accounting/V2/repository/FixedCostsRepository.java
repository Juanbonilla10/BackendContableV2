/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.repository;

import com.accounting.V2.model.FixedCostsModel;
import com.accounting.V2.repository.crud.FixedCostsCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author L E N O V O
 */
@Repository
public class FixedCostsRepository {
    
    @Autowired
    private FixedCostsCrudRepository fixedCostsCrudRepository;
    
    public List<FixedCostsModel> getAllFixedCosts(){
        return fixedCostsCrudRepository.findAll();
    }
    
    public Optional<FixedCostsModel> getFixedCostById(Integer idFixedCost){
        return fixedCostsCrudRepository.findById(idFixedCost);
    }
    
    public List<FixedCostsModel> getFixedCostByEmaill(String idUser){
        return fixedCostsCrudRepository.findByUserIdFixedCost(idUser);
    }
    
    public void deleteFixedCostById(Integer idFixedCost ){
        fixedCostsCrudRepository.deleteById(idFixedCost);
    }
    
    public FixedCostsModel saveFixedCost(FixedCostsModel fixedCostsModel){
        return fixedCostsCrudRepository.save(fixedCostsModel);
    }
    
}

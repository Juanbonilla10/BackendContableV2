/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.repository;

import com.accounting.V2.model.StateModel;
import com.accounting.V2.repository.crud.StateCrudRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author L E N O V O
 */
@Repository
public class StateRepository {
    
    @Autowired
    private StateCrudRepository stateCrudRepository;
    
    public List<StateModel> getAll(){
        return stateCrudRepository.findAll();
    }
    
    public StateModel getStates(String description){
        return stateCrudRepository.findByDescription(description);
    }
    
    public StateModel saveState(StateModel stateModel){
        return stateCrudRepository.save(stateModel);
    }
    
    
}

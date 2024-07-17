/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.service;

import com.accounting.V2.model.FranchisesModel;
import com.accounting.V2.repository.FranchisesRepository;
import com.accounting.V2.repository.StateRepository;
import com.accounting.V2.utils.EnumVars;
import com.accounting.V2.utils.Utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author L E N O V O
 */
@Service
public class FranchisesService {

    @Autowired
    private FranchisesRepository franchisesRepository;
    
    @Autowired
    private StateRepository stateRepository;
    

    public List<FranchisesModel> getAllFranchises() {
        return franchisesRepository.getAll();
    }

    public FranchisesModel cretaeFranchises(FranchisesModel franchisesModel) {
            
            //Instancia para la fecha
            Utils utils = new Utils();

            //Setear el valor de la fecha actual
            franchisesModel.setDate(utils.getSystemDate());

            //Recuperamos el objeto StateModel para el estado activo
            franchisesModel.setState_id(stateRepository.getStates(EnumVars.ACTIVO.getCodigo()).getIdstate());
        
        return franchisesRepository.createFranchises(franchisesModel);
    }

    public Integer deleteFranchises(FranchisesModel franchisesModel) {
        try {
            Integer STATE = 500;
            if (franchisesModel.getIdfranchises() != null) {
                System.out.println("Id distinto de null");
                Optional<FranchisesModel> getFranchisesModel = franchisesRepository.getFranchisesId(franchisesModel.getIdfranchises());
                System.out.println("Consulta del objeto " .concat( getFranchisesModel.toString()));
                if (!getFranchisesModel.isEmpty()) {
                    franchisesRepository.deleteFranchises(franchisesModel.getIdfranchises());
                    STATE = 200 ;
                    System.out.println("Eliminado con id: " .concat(franchisesModel.getIdfranchises().toString()));
                }
            }
            return STATE;
        } catch (Exception e) {
            System.out.println("Error el eliminar la franquicia ".concat(e.getMessage()));
            return 500;
        }
    }

}

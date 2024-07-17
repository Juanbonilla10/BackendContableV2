/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.service;

import com.accounting.V2.model.BanksModel;
import com.accounting.V2.repository.BanksRepository;
import com.accounting.V2.repository.StateRepository;
import com.accounting.V2.utils.EnumVars;
import com.accounting.V2.utils.EnumVarsState;
import com.accounting.V2.utils.Utils;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author L E N O V O
 */
@Service
public class BanksService {
    
    @Autowired
    private BanksRepository banksRepository;
    
    @Autowired
    private StateRepository stateRepository;
    
    public List<BanksModel> getAllBanks(){
        return banksRepository.getAllBanks();
    }
    
    public BanksModel createBanks(BanksModel banksModel){
        //Instancia para la fecha
        Utils utils = new Utils();
        //Setear la fecha actual del sistema en el objeto
        banksModel.setDate(utils.getSystemDate());    
        //Recuperamos el objeto StateModel para el estado activo
        banksModel.setState_id(stateRepository.getStates(EnumVars.ACTIVO.getCodigo()).getIdstate());
        //Creamos el nuevo objeto
        return banksRepository.createBanks(banksModel);
    }
    
    public Integer deleteBanks(BanksModel banksModel){
        try {
            
            System.out.println("Ingresando a eliminar banks");
            if(banksModel.getIdbanks() != null){
                //Obtenemos el objeto de la base de datos para valida que existe
                Optional<BanksModel> banksModelGet = banksRepository.getBanksId(banksModel.getIdbanks());
                System.out.println("Objeto consultado " .concat(banksModelGet.toString()));
                if(!banksModelGet.isEmpty()){
                     //Borramos el objeto que recuperamos por el id
                    banksRepository.deleteBanks(banksModelGet.get().getIdbanks());
                    System.out.println("Bank eliminado " .concat(banksModelGet.get().getIdbanks().toString()));
                    //Retornamos un 200 para el borrado existoso del recurso
                    return EnumVarsState.CREATE_200.getCodigo();
                }else{
                    System.out.println("Consulta vacia " .concat(EnumVarsState.EMPTY_204.getCodigo().toString()));
                    return EnumVarsState.EMPTY_204.getCodigo();
                }
               
            }else{
                System.out.println("No hay id de banks para poder eliminar");
                return EnumVarsState.ERROR_400.getCodigo() ;
            }
        } catch (Exception e) {
            System.out.println("Erro al borrar banks " .concat(e.getMessage()));
            return EnumVarsState.ERROR_500.getCodigo() ; 
        }
    }
    
}

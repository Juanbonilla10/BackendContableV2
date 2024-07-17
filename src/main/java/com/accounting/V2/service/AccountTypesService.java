/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.service;

import com.accounting.V2.model.AccounttypesModel;
import com.accounting.V2.model.StateModel;
import com.accounting.V2.repository.AccountTypesRepository;
import com.accounting.V2.repository.StateRepository;
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
public class AccountTypesService {

    private static final String ACTIVO = "ACTIVO";

    @Autowired
    private AccountTypesRepository accountTypesRepository;

    @Autowired
    private StateRepository stateRepository;

    public List<AccounttypesModel> getAllAccountTypes() {
        return accountTypesRepository.getAllAccounts();
    }
    
    public Optional<AccounttypesModel> getAccountType(Integer idAccountType){
        return accountTypesRepository.getAccountType(idAccountType);
    }

    public AccounttypesModel saveAccountType(AccounttypesModel accounttypesModel) {

        // Verifica si el campo description no es nulo
        if (accounttypesModel.getDescription() != null) {
            // Guarda el objeto accounttypesModel en el repositorio

            // Obtener la fecha actual
            LocalDate currentDate = LocalDate.now();
            // Definir el formato de fecha "año-mes-día"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            //Setear el valor de la fecha actual
            accounttypesModel.setDate((String) currentDate.format(formatter));

            //Recuperamos el objeto StateModel para el estado activo
            accounttypesModel.setState_id(stateRepository.getStates(ACTIVO).getIdstate());

            //Crear el objeto
            return accountTypesRepository.saveAccountType(accounttypesModel);

        } else {
            // Si el campo description es nulo, puedes lanzar una excepción o manejar el caso según sea necesario
            throw new IllegalArgumentException("La descripción del tipo de cuenta no puede ser nula");
        }
    }

    public AccounttypesModel deleteAccountType(AccounttypesModel accounttypesModel) {

        try {
             System.out.println("Entrando a eliminar el tipo de cuenta");
            if (accounttypesModel.getDescription() != null) {
                System.out.println("Descripcion distinta a null");
                Optional<AccounttypesModel> stateAccount =  accountTypesRepository.getAccountType(accounttypesModel.getIdaccountTypes());
                System.out.println("stateAccount " .concat(stateAccount.toString()));
                accountTypesRepository.deleteAccountType(stateAccount.get().getIdaccountTypes());
                return accounttypesModel;
            }else{
                return new AccounttypesModel();
            }

        } catch (Exception e) {
            System.out.println("Error".concat(e.getMessage()));
            return new AccounttypesModel();
        }
    }

}

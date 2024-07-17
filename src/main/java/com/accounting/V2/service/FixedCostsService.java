/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.service;

import com.accounting.V2.model.FixedCostsModel;
import com.accounting.V2.model.StateModel;
import com.accounting.V2.model.UsersModel;
import com.accounting.V2.repository.FixedCostsRepository;
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
public class FixedCostsService {

    @Autowired
    private FixedCostsRepository fixedCostsRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private UsersService usersService;

    public List<FixedCostsModel> getAllFixedCosts() {
        return fixedCostsRepository.getAllFixedCosts();
    }

    public List<FixedCostsModel> getFixedCostsById(String mail) {
        try {
            System.out.println("Entrando a obtener los gastos fijos de ".concat(mail));
            Optional<UsersModel> um = usersService.getByEmail(mail);
            if (um.isPresent()) {
                System.out.println("Validando consulta de datos para los gastos fijos");
                return fixedCostsRepository.getFixedCostByEmaill(um.get().getIdusers().toString());
            } else {
                System.out.println("Error el uuario no es valido para consultar");
                return (List<FixedCostsModel>) new FixedCostsModel();
            }

        } catch (Exception e) {
            System.out.println("Error al obtener los gastos fijos del usuario ".concat(mail));
            System.out.println("Error : ".concat(e.getMessage()));
            return (List<FixedCostsModel>) new FixedCostsModel();
        }
    }

    public FixedCostsModel savesFixedCosts(FixedCostsModel fixedCostsModel, String mail) {

        try {
            Optional<UsersModel> um = usersService.getByEmail(mail);
            if (um.isPresent()) {
                if (!fixedCostsModel.getDescription().isEmpty() & !fixedCostsModel.getCost().isBlank()) {
                    Utils dateSystem = new Utils();
                    fixedCostsModel.setDate(dateSystem.getSystemDate());
                    StateModel stateModel = stateRepository.getStates(EnumVars.ACTIVO.getCodigo());
                    fixedCostsModel.setState_id(stateModel.getIdstate());
                    UsersModel userModel = usersService.getUsersById(um.get().getIdusers());
                    fixedCostsModel.setUsers_id(um.get().getIdusers());
                    if (stateModel.getIdstate() != 0 & userModel.getIdusers() != 0) {
                        return fixedCostsRepository.saveFixedCost(fixedCostsModel);
                    }
                    System.out.println("No se puede crear el gastoFijo porque no se encuentra id para el"
                            + "usuario ".concat(fixedCostsModel.getUsers_id().toString())
                            + " stado : ".concat(fixedCostsModel.getState_id().toString()));
                    return new FixedCostsModel();
                } else {
                    return new FixedCostsModel();
                }
            }else{
                System.out.println("El usuario no existe");
                return new FixedCostsModel();
            }
        } catch (Exception e) {
            System.out.println("Erro en la creación del gasto fijo: ".concat(e.getMessage()));
            return new FixedCostsModel();
        }

    }

    public Integer deleteFixedCosts(FixedCostsModel fixedCostsModel) {
        try {
            System.out.println("Ingresando a eliminar fixedCosts");
            if (fixedCostsModel.getIdfixedCosts() != null) {
                Optional<FixedCostsModel> fixedCostsById = fixedCostsRepository.getFixedCostById(fixedCostsModel.getIdfixedCosts());
                if (!fixedCostsById.isEmpty()) {
                    fixedCostsRepository.deleteFixedCostById(fixedCostsById.get().getIdfixedCosts());
                    System.out.println("Se borro el fixedCosts");
                    return EnumVarsState.CREATE_200.getCodigo();
                }
                return EnumVarsState.ERROR_500.getCodigo();
            }
            return EnumVarsState.ERROR_400.getCodigo();
        } catch (Exception e) {
            System.out.println("Erro al borrar banks ".concat(e.getMessage()));
            return EnumVarsState.ERROR_500.getCodigo();
        }
    }

}

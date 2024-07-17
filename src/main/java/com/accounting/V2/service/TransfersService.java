/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.service;

import com.accounting.V2.model.TransfersModel;
import com.accounting.V2.model.UsersModel;
import com.accounting.V2.repository.TransfersRepository;
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
public class TransfersService {
    
    @Autowired
    private TransfersRepository transfersRepository;
    
    @Autowired
    private UsersService usersService;
    
    @Autowired
    private AccountsService accountsService;
    
    public List<TransfersModel> getAllTransfers(){
        return transfersRepository.getAllTransfer();
    }
    
    public void deleteTransfer(Integer idTransfer,String mail){
        try {
            System.out.println("Ingresando a borrar la transferencia");
             Optional<UsersModel> user = usersService.getByEmail(mail);
            if (user.isPresent()){
                System.out.println("Validando el borrado");
                if(transfersRepository.getIdTransfer(idTransfer).get().getUsers_id()
                        .equals(user.get().getIdusers())){
                    System.out.println("Eliminando la transferencia " .concat(idTransfer.toString()));
                    transfersRepository.deleteTransfer(idTransfer);
                }else{
                    System.out.println("No se puede borrar la transferencia no le pertenece");
                }
                
            }
        } catch (Exception e) {
            System.out.println("Error al borrar la transferencia " .concat(idTransfer.toString()));
        }
    }
    
    public TransfersModel saveTransfer(TransfersModel transfersModel,String mail){
        try {
            System.out.println("Ingresando a crear la transferencia");
            Optional<UsersModel> user = usersService.getByEmail(mail);
            if (user.isPresent()){
                System.out.println("Validando transferencia");
                if(accountsService.getAccountsByUser(mail).stream().anyMatch(account -> account.getIdaccounts()
                .equals(transfersModel.getAccounts_origin_id())) && accountsService.getAllAccounts().stream()
                        .anyMatch(accountsFinal -> accountsFinal.getIdaccounts()
                                .equals(transfersModel.getAccounts_destiny_id()))){
                    System.out.println("Guardando transferencia");
                    System.out.println("Seteando id usuario a la transferencia");
                    transfersModel.setUsers_id(user.get().getIdusers());
                    System.out.println("Seteando fecha del ");
                    Utils setDate = new Utils();
                    transfersModel.setDate(setDate.getSystemDate());
                    return transfersRepository.saveTransfer(transfersModel);
                }else{
                    System.out.println("La cuenta origen o la cuenta destino no existen o no son relacionales"
                            + " al usuario");
                    return new TransfersModel();
                }
                
                
            }else{
                System.out.println("El usuario no existe " .concat(mail));
                return new TransfersModel();
            }
        } catch (Exception e) {
            System.out.println("Error al guardar la transferencia ");
            return new TransfersModel();
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.service;

import com.accounting.V2.model.AccountBalancesModel;
import com.accounting.V2.model.UsersModel;
import com.accounting.V2.repository.AccountBalanceRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author L E N O V O
 */
@Service
public class AccountBalanceService {

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    @Autowired
    private UsersService usersService;

    public List<AccountBalancesModel> getAccountsByUser(String mail) {
        try {
            System.out.println("Entrando a obtener las cuenta");
            Optional<UsersModel> user = usersService.getByEmail(mail);
            if (user.isPresent()) {
                return accountBalanceRepository.getAccountsBalancesByUser(user.get().getIdusers());
            } else {
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Error al obtener las cuentas de saldo ".concat(e.getMessage()));
            return new ArrayList<>();
        }
    }

    public void saveAccountBalance(AccountBalancesModel accountBalancesModel, String mail) {
        try {
            System.out.println("Entrando a guardar el account balance");
            Optional<UsersModel> user = usersService.getByEmail(mail);
            if (user.isPresent()) {
                System.out.println("Data para el account balance que se guarda ".concat(accountBalancesModel.toString()));
                accountBalanceRepository.saveAccountBalanceByUser(accountBalancesModel);
            } else {
                System.out.println("El usuario no corresponde ");
            }
        } catch (Exception e) {
            System.out.println("Error al guardar el account balance ");
        }

    }

    public boolean modifyAccountBalance(String account, String balance, Boolean action) {
        try {
            if (!account.isEmpty() & !balance.isEmpty()) {
                System.out.println("Validando inputs ".concat("account :")
                        .concat(account)
                        .concat(" balance : ")
                        .concat(balance));

                //Recuperamos el registro a travez del id de la cuenta
                AccountBalancesModel abm = accountBalanceRepository.getAccountBalanceByAccountId(account);
                System.out.println("Account balance consult : ".concat(abm.toString()));
                if (action) {
                    //Aplicamos la logica para hacer el descuento del balance
                    int resultNewBalanceAccount = Integer.parseInt(abm.getBalance()) - Integer.parseInt(balance);
                    System.out.println("Result balance desc : ".concat(String.valueOf(resultNewBalanceAccount)));
                    abm.setBalance(String.valueOf(resultNewBalanceAccount));
                    if (resultNewBalanceAccount >= 0) {
                        accountBalanceRepository.saveAccountBalanceByUser(abm);
                        return true;
                    } else {
                        System.out.println("No se puede realizar el descuento de la cuenta porque el saldo es negativo");
                        return false;
                    }
                } else {
                    //Aplicamos la logica para hacer el descuento del balance
                    int resultNewBalanceAccount = Integer.parseInt(abm.getBalance()) + Integer.parseInt(balance);
                    System.out.println("Result balance sum : ".concat(String.valueOf(resultNewBalanceAccount)));
                    abm.setBalance(String.valueOf(resultNewBalanceAccount));
                    if (resultNewBalanceAccount >= 0) {
                        accountBalanceRepository.saveAccountBalanceByUser(abm);
                        return true;
                    } else {
                        System.out.println("No se puede realizar el descuento de la cuenta porque el saldo es negativo");
                        return false;
                    }
                }

            } else {
                System.out.println("La cuenta o el balance estan vacios");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al modificar el account balance ".concat(e.getMessage()));
            return false;
        }
    }

}

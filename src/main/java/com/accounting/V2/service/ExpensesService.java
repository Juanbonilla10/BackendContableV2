/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.service;

import com.accounting.V2.model.AccountsModel;
import com.accounting.V2.model.AccounttypesModel;
import com.accounting.V2.model.BanksModel;
import com.accounting.V2.model.CardsModel;
import com.accounting.V2.model.ExpensesModel;
import com.accounting.V2.model.FixedCostsModel;
import com.accounting.V2.model.FranchisesModel;
import com.accounting.V2.model.UsersModel;
import com.accounting.V2.model.response.CardsModelResponse;
import com.accounting.V2.model.response.ExpensesModelResponse;
import com.accounting.V2.repository.ExpensesRepository;
import com.accounting.V2.utils.Utils;
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
public class ExpensesService {

    @Autowired
    private ExpensesRepository expensesRepository;

    @Autowired
    private AccountTypesService accountTypeService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private BanksService banksService;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private FranchisesService franchisesService;

    @Autowired
    private FixedCostsService fixedCostsService;

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Autowired
    private CardsService cardsService;

    public List<ExpensesModel> getAllExpenses() {
        return expensesRepository.getAllExpenses();
    }

    private Optional<ExpensesModel> getExopensesId(Integer idExpenses) {
        return expensesRepository.getExpeneseId(idExpenses);
    }

    public ExpensesModel saveExpenses(ExpensesModel expensesModel, String mail) {
        try {
            Optional<UsersModel> um = usersService.getByEmail(mail);
            if (um.isPresent()) {
                System.out.println("Usuario encontrado ".concat(um.get().getFirstName()));
                System.out.println("Creando gasto ");
                Utils utils = new Utils();
                expensesModel.setUsers_id(um.get().getIdusers());
                expensesModel.setDate(utils.getSystemDate());
                if (expensesModel.getCards_id() != 0) {
                    System.out.println("Entrando a guardar gasto por tarjeta de credito");
                    //SETEAR EL VALOR POR DEFAUTL DE LA CUENTA YA QUE LAS RELACIONES NO PERITEN QUE NO SEA NULL
                    List<CardsModel> cms = cardsService.getByIdCardsModel(mail);
                    if (cms.stream().anyMatch(cards -> cards.getIdcards().equals(expensesModel.getCards_id()))) {

                        if (cardsService.modifyCardDetails(String.valueOf(expensesModel.getCards_id()), expensesModel.getEgress_value(), true)) {
                            return expensesRepository.saveExpenses(expensesModel);
                        } else {
                            return new ExpensesModel();
                        }

                    } else {
                        return new ExpensesModel();
                    }
                } else {
                    System.out.println("Entrando a guardar gasto por cuenta");
                    expensesModel.setCards_id(0);
                    //Validación si la cuenta pertenece al usuario que peticiona
                    List<AccountsModel> accounts = accountsService.getAccountsByUser(mail);
                    if (accounts.stream().anyMatch(account -> account.getIdaccounts().equals(expensesModel.getAccounts_id()))) {
                        // Se modifica el account balance para descontar los gastos de la cuenta indicada
                        if (accountBalanceService.modifyAccountBalance(String.valueOf(expensesModel.getAccounts_id()), expensesModel.getEgress_value(), true)) {
                            // Guardar el gasto
                            return expensesRepository.saveExpenses(expensesModel);
                        } else {
                            return new ExpensesModel();
                        }
                    } else {
                        System.out.println("La cuenta no pertenece al usuario");
                        return new ExpensesModel();
                    }
                }
            } else {
                System.out.println("El usuario no existe ".concat(mail));
                return new ExpensesModel();
            }
        } catch (Exception e) {
            System.out.println("Error al guardar el gasto ".concat(e.getMessage()));
            return new ExpensesModel();
        }

    }

    public List<ExpensesModelResponse> expensesAllUser(String mail) {
        try {
            Optional<UsersModel> um = usersService.getByEmail(mail);
            if (um.isPresent()) {
                System.out.println("Consultando los gastos del usuario  ".concat(mail));
                List<ExpensesModel> expenses = expensesRepository.getAllExpensesUser(um.get().getIdusers().toString());
                System.out.println("Result expenses ".concat(expenses.toString()));
                List<ExpensesModelResponse> expMdlRsp = new ArrayList<>();

                //Recorriendo los gastos consultados para setear información
                for (ExpensesModel exp : expenses) {
                    System.out.println("Consultando primer gasto ".concat(exp.toString()));
                    ExpensesModelResponse expMdRsp = new ExpensesModelResponse();
                    Optional<AccountsModel> accountUser = accountsService.getAccountByAccount(exp.getAccounts_id(), mail);
                    System.out.println("Obteniendo la cuenta del user ".concat(accountUser.toString()));
                    Optional<AccounttypesModel> accTyp = accountTypeService.getAccountType(accountUser.get().getAccountTypes_id());
                    System.out.println("Obteniendo los tipos de cuenta ".concat(accTyp.toString()));
                    List<FranchisesModel> franchisesList = franchisesService.getAllFranchises();
                    System.out.println("Obteniendo las franquicias ".concat(franchisesList.toString()));
                    List<BanksModel> banks = banksService.getAllBanks();

                    expMdRsp.setIdegresos(exp.getIdegresos());
                    expMdRsp.setDate(exp.getDate());
                    expMdRsp.setEgress_value(exp.getEgress_value());

                    for (BanksModel bank : banks) {
                        if (bank.getIdbanks().equals(accountUser.get().getBanks_id())) {
                            expMdRsp.setBanksAccount(bank.getDescription());
                        }
                    }

                    if (exp.getDescription_expense().isBlank()) {
                        for (FixedCostsModel fix : fixedCostsService.getAllFixedCosts()) {
                            if (fix.getIdfixedCosts().equals(exp.getFixedcosts_id())) {
                                expMdRsp.setDescription_expense(fix.getDescription());
                            }
                        }
                    } else {
                        expMdRsp.setDescription_expense(exp.getDescription_expense());
                    }
                    expMdRsp.setAccountsDescription(accTyp.get().getDescription());
                    for (FranchisesModel fran : franchisesList) {
                        if (fran.getIdfranchises().equals(accountUser.get().getFranchises_id())) {
                            expMdRsp.setFranchisesDescription(fran.getDescription());
                        }
                    }
                    if ((exp.getCards_id() == 0) ? false : true) {
                        expMdRsp.setCreditCard(true);
                        List<CardsModelResponse> cmrs = cardsService.getAllCardsByUserResponseModel(mail);
                        System.out.println("CHANCLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA " + cmrs.toString());
                        for (CardsModelResponse cmr : cmrs) {
                            if (cmr.getIdcards().equals(exp.getCards_id())) {
                                expMdRsp.setFranchisesDescription(cmr.getFranchisesDescription());
                                expMdRsp.setBanksAccount(cmr.getBanksDescription());
                                expMdRsp.setAccountsDescription("Tarjeta Credito");
                            }
                        }

                    }

                    expMdlRsp.add(expMdRsp);

                }

                return expMdlRsp;

            } else {
                System.out.println("No existe el usuario");
                return (List<ExpensesModelResponse>) new ExpensesModelResponse();
            }
        } catch (Exception e) {
            System.out.println("Error al obtener los gastos del usuario");
            return (List<ExpensesModelResponse>) new ExpensesModelResponse();
        }
    }

    public void deleteExpenses(String idExpenses, String mail) {
        try {
            Optional<UsersModel> um = usersService.getByEmail(mail);
            if (um.isPresent()) {
                System.out.println("Usuario encontrado ".concat(um.get().getFirstName()));
                if (expensesRepository.getExpeneseId(Integer.valueOf(idExpenses.substring(2))).get().getUsers_id()
                        .equals(um.get().getIdusers())) {
                    System.out.println("Se puede borrar el gasto ".concat(idExpenses.substring(2)));

                    Optional<ExpensesModel> exp = expensesRepository.getExpeneseId(Integer.valueOf(idExpenses.substring(2)));
     
                    if (idExpenses.substring(0, 2).equals("TC")) {
                        
                        if (cardsService.modifyCardDetails(String.valueOf(exp.get().getCards_id()) , exp.get().getEgress_value(), false)) {
                            expensesRepository.deleteExpenses(Integer.valueOf(idExpenses.substring(2)));
                        } else {
                            System.out.println("Error no se logro eliminar el gasto por Tarjeta de credito");
                        }
                        
                    } else {
                        // Se modifica el account balance para descontar los gastos de la cuenta indicada
                        if (accountBalanceService.modifyAccountBalance(String.valueOf(exp.get().getAccounts_id()), exp.get().getEgress_value(), false)) {
                            // Guardar el gasto
                            expensesRepository.deleteExpenses(Integer.valueOf(idExpenses.substring(2)));
                        } else {    
                            System.out.println("No se logro eliminar el gasto");
                        }
                    }

                } else {
                    System.out.println("No se puede eliminar el gasto porque no le pertenece");
                }
            } else {
                System.out.println("El usuario no existe ".concat(mail));
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar el gasto ".concat(e.getMessage()));
        }

    }

}

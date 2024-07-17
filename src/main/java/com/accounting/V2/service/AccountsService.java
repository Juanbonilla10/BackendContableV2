/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.service;

import com.accounting.V2.model.AccountBalancesModel;
import com.accounting.V2.model.AccountsModel;
import com.accounting.V2.model.AccounttypesModel;
import com.accounting.V2.model.BanksModel;
import com.accounting.V2.model.CardsModel;
import com.accounting.V2.model.FranchisesModel;
import com.accounting.V2.model.UsersModel;
import com.accounting.V2.model.response.AccountsModelResponse;
import com.accounting.V2.repository.AccountBalanceRepository;
import com.accounting.V2.repository.AccountsRepository;
import com.accounting.V2.repository.CardsRepository;
import com.accounting.V2.utils.EnumVarsState;
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
public class AccountsService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private AccountTypesService accountTypeService;

    @Autowired
    private BanksService banksService;

    @Autowired
    private FranchisesService franchisesService;

    @Autowired
    private AccountBalanceService accountBalanceService;

    @Autowired
    private AccountBalanceRepository accountBalanceRepository;

    @Autowired
    private CardsRepository cardsRepository;

    public List<AccountsModel> getAllAccounts() {
        return accountsRepository.getAllAccounts();
    }

    public List<AccountsModelResponse> getAllAccountsByUserResponseModel(String mail, Boolean action) {
        try {
            System.out.println("Entrando a obtener las cuenta");
            Optional<UsersModel> user = usersService.getByEmail(mail);
            if (user.isPresent()) {
                //Validamos que no exista ya la cuenta con el numero de cuenta que se pasa
                List<AccountsModel> ams = accountsRepository.getAllAccountsByUser(user.get().getIdusers());
                List<AccountsModelResponse> amrs = new ArrayList<>();

                System.out.println("Todas las cuentas del usuario ".concat(ams.toString()));

                for (AccountsModel am : ams) {
                    AccountsModelResponse amr = new AccountsModelResponse();
                    System.out.println("Obteniendo idAccount ".concat(am.getIdaccounts().toString()));
                    Optional<AccounttypesModel> accTyp = accountTypeService.getAccountType(am.getAccountTypes_id());
                    System.out.println("Resultado de los tipos de cuenta ".concat(accTyp.toString()));
                    List<BanksModel> banks = banksService.getAllBanks();
                    List<FranchisesModel> franchisesList = franchisesService.getAllFranchises();
                    System.out.println("Obteniendo las franquicias ".concat(franchisesList.toString()));

                    amr.setAccountTypesDescription(accTyp.get().getDescription());
                    for (BanksModel bank : banks) {
                        System.out.println("Resultado para todos los bancos ".concat(bank.getDescription()));
                        if (bank.getIdbanks().equals(am.getBanks_id())) {
                            amr.setBanksDescription(bank.getDescription());
                        }
                    }

                    for (FranchisesModel fran : franchisesList) {
                        if (fran.getIdfranchises().equals(am.getFranchises_id())) {
                            amr.setFranchisesDescription(fran.getDescription());
                        }
                    }

                    String accountNumberLastFourDigits = am.getAccount_number().substring(am.getAccount_number().length() - 4);
                    String cardNumberLastFourDigits = am.getCard_number().substring(am.getCard_number().length() - 4);
                    amr.setIdaccounts(am.getIdaccounts());
                    amr.setAccount_number("************".concat(accountNumberLastFourDigits));
                    amr.setCard_number("************".concat(cardNumberLastFourDigits));

                    //Consultando el balance de cada uno
                    AccountBalancesModel abm = accountBalanceRepository.getAccountBalanceByAccountId(String.valueOf(am.getIdaccounts()));

                    amr.setBalance(abm == null ? "0" : abm.getBalance());
                    amr.setSalary(am.getBalance());
                    amr.setUsers_id(am.getUsers_id());
                    amrs.add(amr);

                }

                if (!action) {
                    //Obtenemos los datos de las tarjetas para mostrar
                    List<CardsModel> cms = cardsRepository.getCardByUser(user.get().getIdusers());
                    System.out.println("Datos de las tarjetas ".concat(cms.toString()));
                    for (CardsModel cardsModel : cms) {
                        AccountsModelResponse amrT = new AccountsModelResponse();
                        List<BanksModel> banks = banksService.getAllBanks();
                        for (BanksModel bank : banks) {
                            System.out.println("Resultado para todos los bancos ".concat(bank.getDescription()));
                            if (bank.getIdbanks().equals(cardsModel.getBanks_id())) {
                                amrT.setBanksDescription(bank.getDescription());
                            }
                        }

                        List<FranchisesModel> franchisesList = franchisesService.getAllFranchises();
                        for (FranchisesModel fran : franchisesList) {
                            if (fran.getIdfranchises().equals(cardsModel.getFranchises_id())) {
                                amrT.setFranchisesDescription(fran.getDescription());
                            }
                        }

                        String cardsNumberLastFourDigits = cardsModel.getCard_number().substring(cardsModel.getCard_number().length() - 4);
                        amrT.setAccount_number("************".concat(cardsNumberLastFourDigits));
                        amrT.setAccountTypesDescription("Tarjeta Credito");
                        amrT.setIdaccounts(cardsModel.getIdcards());
                        amrT.setUsers_id(cardsModel.getUsers_id());
                        amrs.add(amrT);
                    }
                }
                System.out.println("Datos finales accounts ".concat(amrs.toString()));
                return amrs;

            } else {
                System.out.println("Error el usuario no existe ");
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Error al obtner las cuentas del usuario ".concat(e.getMessage()));
            return new ArrayList<>();
        }
    }

    public AccountsModel saveAccountByUser(AccountsModel accountsModel, String mail) {
        try {
            System.out.println("Entrando a guardar la cuenta");
            Optional<UsersModel> user = usersService.getByEmail(mail);
            if (user.isPresent()) {
                System.out.println("El usuario si existe ".concat(mail));
                //Validamos que no exista ya la cuenta con el numero de cuenta que se pasa
                AccountsModel accountByUser = getAccountByUser(accountsModel.getAccount_number(), mail);
                System.out.println("Datos para valdiar si la cuenta existe o no ".concat(accountByUser.toString()));
                if (accountByUser.getCard_number() == null) {
                    System.out.println("Se puede crear la cuenta ");
                    accountsModel.setIdaccounts(0);
                    accountsModel.setUsers_id(user.get().getIdusers());

                    AccountsModel am = accountsRepository.saveAccountsModel(accountsModel);
                    // Guardando account service
                    AccountBalancesModel balancesModel = new AccountBalancesModel();
                    balancesModel.setIdaccountbalances(0);
                    balancesModel.setBalance(accountsModel.getBalance());
                    balancesModel.setUsers_idusers(user.get().getIdusers());
                    balancesModel.setAccounts_idaccounts(am.getIdaccounts());
                    accountBalanceService.saveAccountBalance(balancesModel, mail);

                    return am;
                } else {
                    System.out.println("La cuenta ya existe");
                    return new AccountsModel();
                }
            } else {
                System.out.println("El usuario no existe ".concat(mail));
                return new AccountsModel();
            }
        } catch (Exception e) {
            System.out.println("Error no se puede crear la cuenta");
            return new AccountsModel();
        }
    }

    public List<AccountsModel> getAccountsByUser(String mail) {
        try {
            System.out.println("Entrando a obtener las cuentas del usuario ".concat(mail));
            Optional<UsersModel> user = usersService.getByEmail(mail);
            if (user.isPresent()) {
                System.out.println("Obteniendo los datos para el usuario ".concat(user.get().getFirstName()));
                return accountsRepository.getAllAccountsByUser(user.get().getIdusers());
            } else {
                return (List<AccountsModel>) new AccountsModel();
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la cuenta asociada al usuario ");
            return (List<AccountsModel>) new AccountsModel();
        }
    }

    public AccountsModel getAccountByUser(String idAccount, String mail) {
        try {
            System.out.println("Entrando a obtener la cuenta del usuario ".concat(mail));
            Optional<UsersModel> user = usersService.getByEmail(mail);
            if (user.isPresent()) {
                System.out.println("Obteniendo los datos para el usuario ".concat(user.get().getFirstName()));
                AccountsModel account = accountsRepository.getAccountByUser(idAccount);
                System.out.println("Dta : ".concat(account.toString()));
                if (account.getUsers_id().equals(user.get().getIdusers())) {
                    System.out.println("Devolviendo dato de account ".concat(account.toString()));
                    return account;
                } else {
                    System.out.println("Devolviendo dato de account pero vacio");
                    return new AccountsModel();
                }
            } else {
                return new AccountsModel();
            }
        } catch (Exception e) {
            System.out.println("Error al obtener la cuenta asociada al usuario ");
            return new AccountsModel();
        }
    }

    public Integer deleteAccount(String accountNumber, String email) {
        try {
            System.out.println("Entrando a eliminar la cuenta ".concat(accountNumber));
            if (!accountNumber.isEmpty()) {
                Optional<AccountsModel> accountValidate = this.getAccountByAccount(Integer.parseInt(accountNumber), email);
                System.out.println("Cuenta informada ".concat(accountValidate.toString()));
                if (accountValidate.get().getIdaccounts() == Integer.parseInt(accountNumber)) {
                    System.out.println("Borrando la cuenta ".concat(accountValidate.get().getAccount_number()));
                    accountsRepository.deleteAccount(accountValidate.get().getIdaccounts());
                    return EnumVarsState.CREATE_200.getCodigo();
                } else {
                    System.out.println("No se puede borrar la cuenta porque no pertenece al usuario");
                    return EnumVarsState.ERROR_403.getCodigo();
                }
            } else {
                return EnumVarsState.ERROR_400.getCodigo();
            }
        } catch (Exception e) {
            System.out.println("Error al borrar la cuenta".concat(e.getMessage()));
            return EnumVarsState.ERROR_500.getCodigo();
        }
    }

    public Optional<AccountsModel> getAccountByAccount(Integer idAccount, String mail) {
        try {
            System.out.println("Entrando a obtener la cuenta del usuario ".concat(mail));
            Optional<UsersModel> user = usersService.getByEmail(mail);
            if (user.isPresent()) {
                return accountsRepository.getByAccountUser(idAccount);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            System.out.println("Errro al obtener la cuenta ".concat(e.getMessage()));
            return Optional.empty();
        }
    }

}

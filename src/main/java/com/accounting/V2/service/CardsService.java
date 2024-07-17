/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.service;

import com.accounting.V2.model.BanksModel;
import com.accounting.V2.model.CardsModel;
import com.accounting.V2.model.FranchisesModel;
import com.accounting.V2.model.UsersModel;
import com.accounting.V2.model.response.CardsModelResponse;
import com.accounting.V2.repository.CardsRepository;
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
public class CardsService {

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private BanksService banksService;

    @Autowired
    private FranchisesService franchisesService;

    public List<CardsModel> getAllCards() {
        return cardsRepository.getAllCards();
    }

    public CardsModel saveCard(CardsModel cardsModel, String mail) {
        try {
            System.out.println("Obteniedno los datos para ".concat(mail));
            Optional<UsersModel> user = usersService.getByEmail(mail);
            if (user.isPresent()) {
                System.out.println("El usuario si existe");
                cardsModel.setUsers_id(user.get().getIdusers());
                System.out.println("Datos para cardsmodel ".concat(cardsModel.getCard_number()));
                CardsModel cardUser = cardsRepository.getByIdCard(cardsModel.getCard_number());
                //System.out.println("CardUser datos " .concat(cardUser.toString()));
                if (cardUser != null && cardUser.getCard_number() != null) {
                    System.out.println("La tarjeta ya existe");
                    return new CardsModel();
                } else {
                    System.out.println("Se puede crear la tarjeta porque no existe");
                    cardsModel.setLimit_amount(cardsModel.getValue());
                    return cardsRepository.saveCard(cardsModel);
                }
            } else {
                System.out.println("El usuario no existe no se puede crear");
                return new CardsModel();
            }
        } catch (Exception e) {
            System.out.println("No se puede crear la tarjeta para  ".concat(mail));
            System.out.println(e.getMessage());
            return new CardsModel();
        }
    }

    public CardsModel getByIdCardUser(String idCard, String mail) {
        try {
            System.out.println("Obteniedno los datos para ".concat(idCard.toString()));
            Optional<UsersModel> user = usersService.getByEmail(mail);
            if (user.isPresent()) {
                System.out.println("Se encontro usuario con id ".concat(mail));
                Integer userCard = cardsRepository.idStringCard(idCard);
                if (user.get().getIdusers().equals(userCard)) {
                    System.out.println("Se encontrarón datos homogeneos para card ");
                    return cardsRepository.getByIdCard(idCard);
                } else {
                    System.out.println("El id "
                            + user.get().getIdusers()
                            + " es disinto de " + userCard);
                    return new CardsModel();
                }
            } else {
                System.out.println("No se encontro usuario para este mail ".concat(mail));
                return new CardsModel();
            }
        } catch (Exception e) {
            System.out.println("Error al obtner los dato para ".concat(idCard.toString()));
            return new CardsModel();
        }
    }

    public List<CardsModel> getByIdCardsModel(String mail) {
        try {
            System.out.println("Obteniedno los datos para ".concat(mail));
            Optional<UsersModel> user = usersService.getByEmail(mail);
            if (user.isPresent()) {
                System.out.println("Se encontro usuario con id ".concat(mail));
                return cardsRepository.getCardByUser(user.get().getIdusers());
            } else {
                System.out.println("No se encontro usuario para este mail ".concat(mail));
                return (List<CardsModel>) new CardsModel();
            }
        } catch (Exception e) {
            System.out.println("Error al obtner los dato para ".concat(mail));
            return (List<CardsModel>) new CardsModel();
        }
    }

    public void deleteCard(String cardNumber, String mail) {
        try {
            System.out.println("Ingresando a eliminar la tarjeta");
            System.out.println("Obteniedno los datos para ".concat(mail));
            Optional<UsersModel> user = usersService.getByEmail(mail);
            if (user.isPresent()) {
                System.out.println("Card : ".concat(cardNumber));
                Optional<CardsModel> userCard = cardsRepository.getById(Integer.parseInt(cardNumber));
                System.out.println("Obteniendo datos de la card ".concat(userCard.toString()));
                if (user.get().getIdusers().equals(userCard.get().getUsers_id())) {
                    System.out.println("Se encontrarón datos homogeneos para la card informada ");
                    System.out.println("Eliminando card ".concat(cardNumber));
                    cardsRepository.deleteCard(userCard.get().getIdcards());
                } else {
                    System.out.println("No se encontrarón datos relacionados de tarjeta usuario");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al borrar la tarjeta".concat(e.getMessage()));
        }

    }

    public List<CardsModelResponse> getAllCardsByUserResponseModel(String mail) {

        try {

            System.out.println("Entrando a obtener las tarjetas");
            Optional<UsersModel> user = usersService.getByEmail(mail);

            if (user.isPresent()) {

                /*Obtener todos las tarjetas de un usuario*/
                List<CardsModel> cms = cardsRepository.getCardByUser(user.get().getIdusers());
                //Intanciar la respuesta qyue se devolvera
                List<CardsModelResponse> cmrs = new ArrayList<>();

                System.out.println("Todas las cuentas del usuario ".concat(cms.toString()));

                for (CardsModel cmsv1 : cms) {
                    CardsModelResponse cardsModelResponse = new CardsModelResponse();

                    List<BanksModel> banks = banksService.getAllBanks();
                    List<FranchisesModel> franchisesList = franchisesService.getAllFranchises();

                    //Seteamos datos al model response 
                    //1. seteamos el id de la card
                    cardsModelResponse.setIdcards(cmsv1.getIdcards());
                    //2. seteamos la card_number
                    String cardNumberFourDigits = cmsv1.getCard_number().substring(cmsv1.getCard_number().length() - 4);
                    System.out.println("Imprimimos los ultimos 4 datos de la tarjeta".concat(cardNumberFourDigits));
                    cardsModelResponse.setCard_number("************".concat(cardNumberFourDigits));
                    //3. seteamos el limite de los saldos de la tarjeta
                    cardsModelResponse.setLimit_amount(cmsv1.getLimit_amount());
                    //4. seteamos el valor actual de la tarjeta
                    cardsModelResponse.setValue(cmsv1.getValue());
                    //5. seteamos el banco                              
                    //Iteramos los bancos para poder encontrar el id correspondiente a el banco asociado a la tarjeta
                    for (BanksModel bank : banks) {
                        System.out.println("Resultado para todos los bancos ".concat(bank.getDescription()));
                        if (bank.getIdbanks().equals(cmsv1.getBanks_id())) {
                            cardsModelResponse.setBanksDescription(bank.getDescription());
                        }
                    }
                    //6. seteamos la franquicia
                    //Iteramos los franquicias para poder encontrar el id correspondiente a la franquicia asociado a la tarjeta
                    for (FranchisesModel fran : franchisesList) {
                        if (fran.getIdfranchises().equals(cmsv1.getFranchises_id())) {
                            cardsModelResponse.setFranchisesDescription(fran.getDescription());
                        }
                    }
                    cmrs.add(cardsModelResponse);
                }

                System.out.println("Datos finales tarjetas ".concat(cmrs.toString()));
                return cmrs;
            } else {
                System.out.println("Error el usuario no existe ");
                return new ArrayList<>();
            }

        } catch (Exception e) {
            System.out.println("Error al obtner las tarjetas del usuario ".concat(e.getMessage()));
            return new ArrayList<>();
        }

    }

    public Boolean modifyCardDetails(String idCard, String balance, Boolean action) {
        try {
            if (!idCard.isEmpty() & !balance.isEmpty()) {
                System.out.println("Validando inputs ".concat("account :")
                        .concat(idCard)
                        .concat(" balance : ")
                        .concat(balance));

                Optional<CardsModel> crd = cardsRepository.getById(Integer.parseInt(idCard));
                System.out.println("Card balance consult : ".concat(crd.toString()));
                if (action) {
                    //Aplicamos la logica para hacer el descuento del balance
                    int resultNewBalanceCard = Integer.parseInt(crd.get().getValue()) - Integer.parseInt(balance);
                    System.out.println("Result balance desc : ".concat(String.valueOf(resultNewBalanceCard)));
                    crd.get().setValue(String.valueOf(resultNewBalanceCard));
                    if(resultNewBalanceCard >= 0){
                        cardsRepository.saveCard(crd.get());
                        return true;
                    }else{
                        System.out.println("No se puede realizar el descuento de la tarheta porque el saldo es negativo");
                        return false;
                    }
                } else {
                     //Aplicamos la logica para hacer la suma del balance
                    int resultNewBalanceCard = Integer.parseInt(crd.get().getValue()) + Integer.parseInt(balance);
                    System.out.println("Result balance sum : ".concat(String.valueOf(resultNewBalanceCard)));
                    crd.get().setValue(String.valueOf(resultNewBalanceCard));
                    if (resultNewBalanceCard >= 0) {
                        cardsRepository.saveCard(crd.get());
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
            System.out.println("Error al modificar el card balance ".concat(e.getMessage()));
            return false;
        }
    }

}

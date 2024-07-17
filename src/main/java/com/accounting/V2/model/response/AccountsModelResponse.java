/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author L E N O V O
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountsModelResponse {
    
    
    private Integer idaccounts;
    private String account_number;
    private String card_number;
    private String balance;
    private String salary;
    private String banksDescription;
    private String accountTypesDescription;
    private String franchisesDescription;
    private Integer users_id;
    
}

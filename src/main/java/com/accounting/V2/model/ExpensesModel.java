/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity(name = "Expenses")
@Table(name = "expenses")
public class ExpensesModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idegresos")
    private Integer idegresos;
    @Column(name = "egress_value",length = 45)
    private String egress_value;
    @Column(name = "description_expense",length = 100)
    private String description_expense;
    @Column(name = "date",length = 45)
    private String date;
    @Column(name = "accounts_id")
    private Integer accounts_id;
    @Column(name = "users_id")
    private Integer users_id;
    @Column(name = "fixedcosts_id")
    private Integer fixedcosts_id;
    @Column(name = "cards_id")
    private Integer cards_id;
    
}

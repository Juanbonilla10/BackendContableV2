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
@Entity(name = "Incomes")
@Table(name = "incomes")
public class IncomesModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idincomes")
    private Integer idincomes;
    @Column(name = "income_value",length = 45)
    private String income_value;
    @Column(name = "message",length = 45)
    private String message;
    @Column(name = "date",length = 45)
    private String date;
    @Column(name = "accounts_id")
    private Integer accounts_id;
    @Column(name = "users_id")
    private Integer users_id;
    
    
}

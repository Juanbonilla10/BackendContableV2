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
@Entity(name = "Transfers")
@Table(name = "transfers")
public class TransfersModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtransfers")
    private Integer idtransfers;
    @Column(name = "value",length = 45)
    private String value;
    @Column(name = "description",length = 45)
    private String description;
    @Column(name = "date",length = 45)
    private String date;
    @Column(name = "accounts_origin_id")
    private Integer accounts_origin_id;
    @Column(name = "accounts_destiny_id")
    private Integer accounts_destiny_id;
    @Column(name = "users_id")
    private Integer users_id;
    
}

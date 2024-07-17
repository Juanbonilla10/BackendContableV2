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
@Entity(name = "Cards")
@Table(name = "cards")
public class CardsModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcards")
    private Integer idcards;
    @Column(name = "card_number",length = 45)
    private String card_number;
    @Column(name = "limit_amount",length = 45)
    private String limit_amount;
    @Column(name = "value",length = 45)
    private String value;
    @Column(name = "franchises_id")
    private Integer franchises_id;
    @Column(name = "banks_id")
    private Integer banks_id;
    @Column(name = "users_id")
    private Integer users_id;
    
}

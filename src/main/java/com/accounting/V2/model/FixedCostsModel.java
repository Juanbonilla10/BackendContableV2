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
@Entity(name = "FixedCosts")
@Table(name = "fixedcosts")
public class FixedCostsModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfixedcosts")
    private Integer idfixedCosts;
    @Column(name = "description",length = 45)
    private String description;
    @Column(name = "cost",length = 45)
    private String cost;
    @Column(name = "date",length = 45)
    private String date;
    @Column(name = "state_id")
    private Integer state_id;
    @Column(name = "users_id")
    private Integer users_id;

    
    
}

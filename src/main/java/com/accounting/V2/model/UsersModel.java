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
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users")
@Table(name = "users")
public class UsersModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusers")
    private Integer idusers;
    @Column(name = "firstname",length = 45)
    private String firstName;
    @Column(name = "secondlastname",length = 45)
    private String secondLastName;
    @Column(name = "mail",length = 100)
    private String mail;
    @Column(name = "phone",length = 45)
    private String phone;
    @Column(name = "pwd",length = 100)
    private String pwd;
    @Column(name = "photo",length = 100)
    private String photo;
    @Column(name = "state_id")
    private Integer state_id;
    @Column(name = "roles_id")
    private Integer roles_id;
    
}

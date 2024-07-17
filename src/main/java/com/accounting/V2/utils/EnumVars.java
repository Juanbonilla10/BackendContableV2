/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.utils;

/**
 *
 * @author L E N O V O
 */
public enum EnumVars {

    ACTIVO("ACTIVO"),
    CANCELADO("CANCELADO"),
    ADMINISTRADOR("ADMINISTRADOR");

    private String codigo;
    EnumVars(String codigo){
        this.codigo = codigo;
    }

    public String getCodigo() {
        return this.codigo;
    }

}

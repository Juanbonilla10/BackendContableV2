/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.utils;

/**
 *
 * @author L E N O V O
 */
public enum EnumVarsState {
    
    ERROR_500(500),
    ERROR_400(400),
    CREATE_200(200),
    EMPTY_204(204),
    ERROR_403(403);

    private final Integer codigo;

    EnumVarsState(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }
    
}

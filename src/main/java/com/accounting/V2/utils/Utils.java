/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.accounting.V2.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author L E N O V O
 */
public class Utils {
    
    public String getSystemDate(){
                    // Obtener la fecha actual
            LocalDate currentDate = LocalDate.now();
            // Definir el formato de fecha "año-mes-día"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            String dateSystem = currentDate.format(formatter);
            
            return dateSystem;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.accounting.V2.repository.crud;

import com.accounting.V2.model.AccounttypesModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author L E N O V O
 */
public interface AccountTypesCrudRepository extends JpaRepository<AccounttypesModel, Integer> {
    
    
}

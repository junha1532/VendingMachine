/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.math.BigDecimal;

/**
 *
 * @author junha
 */
public interface VendingMachineServiceLayer {
    void listItems();
    void validateSufficientFund(BigDecimal userInput);
    void updateProduct(int productId);
    void calculateChanges();
    
}

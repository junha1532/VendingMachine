/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.InsufficientFundsException;
import dao.NoItemInventoryException;
import dao.VendingMachinePersistenceException;
import dto.Product;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author junha
 */
public interface VendingMachineServiceLayer {
    List<Product> listItems() ;//throws InsufficientFundsException, NoItemInventoryException;
   // void validateSufficientFund(BigDecimal userInput);
    void updateProduct(int productId) throws VendingMachinePersistenceException;//throws InsufficientFundsException, NoItemInventoryException;
    BigDecimal calculateChanges(BigDecimal userMoney,  int productId) throws InsufficientFundsException;//throws InsufficientFundsException, NoItemInventoryException;
    Product getProduct(int productId) throws NoItemInventoryException;
    
}

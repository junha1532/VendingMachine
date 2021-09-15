/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.InsufficientFundsException;
import dao.NoItemInventoryException;
import dao.VendingMachineAuditDao;
import dao.VendingMachineDao;
import dao.VendingMachinePersistenceException;
import dto.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author junha
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;
    private VendingMachineAuditDao auditdao;

    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditdao) {
        this.dao = dao;
        this.auditdao = auditdao;
    }

    @Override
    public List<Product> listItems() {//throws NoItemInventoryException;{
        return validateItemNumber(dao.listProducts());
    }

    public List<Product> validateItemNumber(List<Product> productList) {
        return productList.stream().filter((product) -> product.getNumItems() > 0).collect(Collectors.toList());
    }
    /*
    This method will check whether the user has sufficient fund
    */
   
    public void validateSufficientFund(BigDecimal change, String productName) throws InsufficientFundsException {
        if(change.compareTo(new BigDecimal("0")) == -1){
            throw new InsufficientFundsException(         
            
                    "ERROR: Could not buy the product "
                    + productName
                    + "...Insufficient Fund");
        }
        
        
    }

    @Override
    public void updateProduct(int productId) throws VendingMachinePersistenceException {
        dao.updateProduct(productId);
        auditdao.writeAuditEntry("The product " + productId + " was updated successfully");
    }

    /*
    this method will retrieve the cost of the product and find the change
     */
    @Override
    public BigDecimal calculateChanges(BigDecimal userMoney, int productID) throws InsufficientFundsException{
        Product product = dao.getProduct(productID);
        BigDecimal change;
        change = userMoney.subtract(product.getPrice());
        validateSufficientFund(change, product.getProductName());
        
        return change.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public Product getProduct(int productId) throws NoItemInventoryException {
        return dao.getProduct(productId);
    }
}

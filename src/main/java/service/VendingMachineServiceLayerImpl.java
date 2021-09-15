/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.VendingMachineAuditDao;
import dao.VendingMachineDao;
import dto.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

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
        return dao.listProducts();
    }

    public void validateItemNumber() {
        
    }
    /*
    This method will check whether the user has sufficient fund
    */
   
    public void validateSufficientFund(BigDecimal change, String productName) {
        if(change.compareTo(BigDecimal.valueOf(0)) < 0){
//            throw new InsufficientFundException(         
//            
//                    "ERROR: Could not buy the product "
//                    + productName
//                    + "...Insufficient Fund");
        }
        
        
    }

    @Override
    public void updateProduct(int productId) {
        //dao.UpdateProduct(productId);
        //auditdao.writeAuditentry();
    }

    /*
    this method will retrieve the cost of the product and find the change
     */
    @Override
    public BigDecimal calculateChanges(BigDecimal userMoney, int productID) {
        Product product = dao.getProduct(productID);
        BigDecimal change;
        change = userMoney.subtract(product.getPrice());
        validateSufficientFund(change, product.getProductName());
        return change.setScale(2, RoundingMode.HALF_UP);
    }
}

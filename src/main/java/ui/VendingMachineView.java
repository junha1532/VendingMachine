/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dto.Changes;
import dto.Product;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 *
 * @author junha
 */
public class VendingMachineView {
    private UserIO io;
    
    //constructor for call from the app file
    public VendingMachineView(UserIO io){
        this.io = io;
    }
    
    public void displayProductList(List<Product> productList){
        for(Product currentProduct: productList){
            displayProductInfo(currentProduct);
        }
    }
    
    private void displayProductInfo(Product product){
        io.print(product.getProductId() + " >> ");
        io.print("\t" + product.getProductName());
        io.print("\t" + product.getPrice());
    }
    
    public int getProductChoice(){
        int prodId = io.readInt("Please enter the I.D. number of you chosen item: ");
        return prodId;
    }
    
    public int getSelection(){
        int selection = io.readInt("Please enter your choice (1: Add cash 2: Exit) : ");
        return selection;
    }
    
    public BigDecimal getCash(){
        BigDecimal cash = new BigDecimal(io.readString("Please enter the cash amount you would like to add : "));
        return cash;
    }
    
    public void dispenseChange(BigDecimal bigDecimalChange){
        
        BigDecimal change = bigDecimalChange.multiply(new BigDecimal("100"));
        System.out.println(bigDecimalChange);
        BigDecimal dollar = change.divide(new BigDecimal("100")).setScale(0, RoundingMode.DOWN);
        change = change.remainder(new BigDecimal("100"));
        
        BigDecimal quarters = change.divide(new BigDecimal("25")).setScale(0, RoundingMode.DOWN);
        change = change.remainder(new BigDecimal("25"));
        
        BigDecimal dimes = change.divide(new BigDecimal("10")).setScale(0, RoundingMode.DOWN);
        change = change.remainder(new BigDecimal("10"));
        
        BigDecimal nickels = change.divide(new BigDecimal("5")).setScale(0, RoundingMode.DOWN);
        change = change.remainder(new BigDecimal("5"));
        
        BigDecimal pennies = change.divide(new BigDecimal("1")).setScale(0, RoundingMode.DOWN);
        
        
        io.print(String.format("Your $%s change was returned as follows : \n"
                + "%.0f %s \n"
                + "%.0f %s \n"
                + "%.0f %s \n"
                + "%.0f %s \n"
                + "%.0f %s \n"
       , bigDecimalChange , dollar, Changes.DOLLARS, quarters, Changes.QUARTERS, dimes, Changes.DIMES, nickels, Changes.NICKELS, pennies, Changes.PENNIES));
        
    }
    
    public void displaySuccessPruchase(){
        io.print("Purchase was sucessful");
    }
    
    /*
    public Product addNewProduct(){
        //need way to set ID
        String name = io.readString("Enter product name: ");
        BigDecimal price = new BigDecimal(io.readString("Enter prodcut price: "));
        int numItems = io.readInt("Enter the total of this product being added to machine: ");
        
        return new Product(prodId, name, price, numItems);
    }
    */

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command");
    }

    public void displayExitMessage() {
        io.print("Thanks you");
    }

    public void displayErrorMessage(String errMsg) {
        io.print("ERROR");
        io.print(errMsg);
    }
}

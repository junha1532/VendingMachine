/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dto.Product;
import java.math.BigDecimal;
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

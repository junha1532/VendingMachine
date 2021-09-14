/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.math.BigDecimal;

/**
 *
 * @author junha
 */
public class Product {
    private int productId;
    private String productName;
    private BigDecimal price;
    private int numItems;

    public Product(int productId) {
        this.productId = productId;
    }
    
    public Product(int productIdIn, String name, BigDecimal priceIn, int numItemsIn){
        this.productId = productIdIn;
        this.productName = name;
        this.price = priceIn;
        this.numItems = numItemsIn;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }
    
    
    
}

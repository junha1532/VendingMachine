/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", price=" + price + ", numItems=" + numItems + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + this.productId;
        hash = 41 * hash + Objects.hashCode(this.productName);
        hash = 41 * hash + Objects.hashCode(this.price);
        hash = 41 * hash + this.numItems;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.productId != other.productId) {
            return false;
        }
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author junha
 */
public class VendingMachineDaoImpl implements VendingMachineDao{
    Map<Integer, Product> inventory = new HashMap<Integer, Product>();
    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";
    
    private Product unmarshallProduct(String productAsText){
        
        String[] productTokens = productAsText.split(DELIMITER);

        // Given the pattern above, create Product with constructor
        Product productFromFile = new Product(
                Integer.parseInt(productTokens[0])
        );
        productFromFile.setProductName(productTokens[1]);
        productFromFile.setPrice(new BigDecimal(productTokens[2]));
        productFromFile.setNumItems(Integer.parseInt(productTokens[3]));
        

        return productFromFile;
    }
    private void loadInventory() throws FileNotFoundException  {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(
                    "-_- Could not load roster data into memory.");
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDVD holds the most recent Product unmarshalled
        Product currentProduct;
        // Go through INVENTORY_FILE line by line, decoding each line into a 
        // Product object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a DVD
            currentProduct = unmarshallProduct(currentLine);

            
            inventory.put(currentProduct.getProductId(), currentProduct);
        }
        // close scanner
        scanner.close();
    }
    private String marshallProduct(Product aProduct){
        // We need to turn a Product object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 4321::Charles::Babbage::Java-September1842

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 

        // Start with the Product ID, since that's supposed to be first.
        String productAsText = aProduct.getProductId() + DELIMITER;

        // ProductName
        productAsText += aProduct.getProductName() + DELIMITER;

        // Price
        productAsText += aProduct.getPrice().toString() + DELIMITER;

        // NumItems
        productAsText += aProduct.getNumItems() + DELIMITER;
        
        // We have now turned a Product to text! Return it!
        return productAsText;
    }
    /**
    * Writes all products in the inventory out to a INVENTORY_FILE.  See loadRoster
    * for file format.
    * 
    */
   private void writeInventory() throws IOException {

       PrintWriter out;

       try {
           out = new PrintWriter(new FileWriter(INVENTORY_FILE));
       } catch (IOException e) {
           throw new IOException(
                   "Could not save dvd data.", e);
       }

 
       String productAsText;
       List<Product> productList = this.listProducts();
       for (Product currentProduct : productList) {
           // turn a Product into a String
           productAsText = marshallProduct(currentProduct);
           // write the Product object to the file
           out.println(productAsText);
           // force PrintWriter to write line to the file
           out.flush();
       }
       // Clean up
       out.close();
   }

    @Override
    public List<Product> listProducts() {
        try {
            loadInventory();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VendingMachineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList(inventory.values());
    }

    @Override
    public Product updateProduct(Product product) {
        try {
            loadInventory();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VendingMachineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        Product newProduct = inventory.put(product.getProductId(), product);
        try {
            writeInventory();
        } catch (IOException ex) {
            Logger.getLogger(VendingMachineDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newProduct;
    }
    
}

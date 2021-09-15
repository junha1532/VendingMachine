/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Product;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author junha
 */
public class VendingMachineDaoImplTest {
    VendingMachineDao testDao;
    public VendingMachineDaoImplTest() {
//        String testFile = "testInventory.txt";
//        testDao = new VendingMachineDaoImpl(testFile);
    }
    
    @BeforeEach
    public void setUp() throws IOException {
        String testFile = "testInventory.txt";
        // Use the FileWriter to quickly blank the file
        
        FileWriter fwrite = new FileWriter(testFile);
        fwrite.write("1::CocaCola::2.0::10");
        fwrite.write("2::Chips::1.5::10");
        fwrite.write("3::InstantNoodles::2.0::10");
        fwrite.write("4::MarsBar::1.0::0");


        testDao = new VendingMachineDaoImpl(testFile);
    }


    @Test
    public void testListProducts() {

        // Retrieve the list of all products within the DAO
        List<Product> allProducts = testDao.listProducts();

        // First check the general contents of the list
        assertNotNull(allProducts, "The list of products must not null");
        assertEquals(4, allProducts.size(),"List of products should have 4 products.");
        
        // Then the specifics
//        assertTrue(testDao.getAllMonsters().contains(firstMonster),
//                    "The list of monsters should include Vladmir.");
//        assertTrue(testDao.getAllMonsters().contains(secondMonster),
//                "The list of monsters should include Warwick.");
    }
    
    @Test
    public void testUpdateProduct() {
        //    void updateProduct(int productId);
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testGetProduct() {
        //    Product getProduct(int productId);
        fail("The test case is a prototype.");
    }
    



}

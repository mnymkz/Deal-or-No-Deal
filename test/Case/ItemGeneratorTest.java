/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Case;

import Database.DBManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 *
 * @author Michael
 */
public class ItemGeneratorTest {
    
    public DBManager dbManager;
    public ItemGenerator it;
    
    public ItemGeneratorTest() {
    }
    
    @Before
    public void setUp() {
        dbManager = DBManager.getInstance();
        dbManager.establishConnection();
        it = new ItemGenerator(dbManager);
    }
    
    @After
    public void tearDown() {
        dbManager.closeConnections();
    }

    /**
     * Test of loadItems method, of class ItemGenerator.
     */
    @Test
    public void testLoadItems() throws Exception {
        System.out.println("loadItems");
        assertDoesNotThrow(() -> it.loadItems());
        assertFalse(it.getItems().isEmpty());
    }

    /**
     * Test of getRandomItem method, of class ItemGenerator.
     */
    @Test
    public void testGetRandomItem() {
        System.out.println("getRandomItem");
        ItemGenerator instance = null;
        Item expResult = null;
        Item result = instance.getRandomItem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Case;

import Database.DBManager;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class ItemGeneratorTest {

    private static DBManager dbManager;
    private ItemGenerator itemGenerator;

    @BeforeClass
    public static void setUpClass() {
        dbManager = DBManager.getInstance();
        dbManager.establishConnection();
    }

    @Before
    public void setUp() {
        itemGenerator = new ItemGenerator(dbManager);
    }

    /**
     *  Test of loadItems, of class ItemGenerator.
     */
    @Test
    public void testLoadItems() {
        try {
            itemGenerator.loadItems(); //load items
            assertTrue(itemGenerator.getItems().size() != 0); //getItems is not empty
        } catch (SQLException e) {
            fail("Exception occurred while loading items: " + e.getMessage());
        }
    }

    /**
     *  Test of getRandomItem, of class ItemGenerator.
     */
    @Test
    public void testGetRandomItem() {
        try {
            itemGenerator.loadItems(); //load items
            int initialSize = itemGenerator.getItems().size();
            Item randomItem = itemGenerator.getRandomItem(); //get random item
            assertNotNull(randomItem); //check null
            assertEquals(initialSize - 1, itemGenerator.getItems().size()); //check if item removed
        } catch (SQLException e) {
            fail("Exception occurred while getting a random item: " + e.getMessage());
        }
    }

     /**
     *  Test of getRandomItem, of class ItemGenerator.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetRandomItemWhenSetIsEmpty() {
        itemGenerator.getRandomItem(); // Should throw IllegalArgumentException
    }

    /**
     *  Test of getRandomItem, of class ItemGenerator.
     */
    @Test(expected = IllegalStateException.class)
    public void testGetRandomItemWithNullItemInSet() {
        itemGenerator.getItems().add(null);
        itemGenerator.getRandomItem(); // Should throw IllegalStateException
    }
}

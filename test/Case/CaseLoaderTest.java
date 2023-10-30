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

/**
 *
 * @author Michael
 */
public class CaseLoaderTest {
    
    private static DBManager dbManager;
    private CaseLoader caseLoader;
    
    public CaseLoaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dbManager = DBManager.getInstance();
        dbManager.establishConnection();
    }
    
    @AfterClass
    public static void tearDownClass() {
        dbManager.closeConnections();
    }
    
    @Before
    public void setUp() {
        caseLoader = new CaseLoader(dbManager);
    }
    
    @After
    public void tearDown() {
        dbManager.closeConnections();
    }

    /**
     * Test of load method, of class CaseLoader.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        caseLoader.load();
        assertFalse(caseLoader.getCases().isEmpty());
    }
}

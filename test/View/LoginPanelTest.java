/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package View;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tabit
 */
public class LoginPanelTest {
    
    public LoginPanelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initComponents method, of class LoginPanel.
     */
    @Test
    public void testInitComponents() {
        System.out.println("initComponents");
        LoginPanel instance = new LoginPanel();
        assertNotNull("Components should be initialized", instance.getLoginButton());
    }

    /**
     * Test of setLayout method, of class LoginPanel.
     */
    @Test
    public void testSetLayout() {
        System.out.println("setLayout");
        LoginPanel instance = new LoginPanel();
        assertNotNull("Layout should be set", instance.getLayout());
    }

    /**
     * Test of getUsernameField method, of class LoginPanel.
     */
    @Test
    public void testGetUsernameField() {
        System.out.println("getUsernameField");
        LoginPanel instance = new LoginPanel();
        assertTrue("Username field should be editable", instance.getUsernameField().isEditable());
    }

    /**
     * Test of getPasswordField method, of class LoginPanel.
     */
    @Test
    public void testGetPasswordField() {
        System.out.println("getPasswordField");
        LoginPanel instance = new LoginPanel();
        assertTrue("Password field should be editable", instance.getPasswordField().isEditable());
    }

    /**
     * Test of getSignUpButton method, of class LoginPanel.
     */
    @Test
    public void testGetSignUpButton() {
        System.out.println("getSignUpButton");
        LoginPanel instance = new LoginPanel();
        JButton result = instance.getLoginButton();
        assertNotNull(result);
    }

    /**
     * Test of getReturnButton method, of class LoginPanel.
     */
    @Test
    public void testGetReturnButton() {
        System.out.println("getReturnButton");
        LoginPanel instance = new LoginPanel();
        assertTrue("Return button should be enabled", instance.getReturnButton().isEnabled());
    }

    /**
     * Test of addLoginButtonActionListener method, of class LoginPanel.
     */
    @Test
    public void testAddLoginButtonActionListener() {
        System.out.println("addLoginButtonActionListener");
        LoginPanel instance = new LoginPanel();
        ActionListener listener = e -> {};
        instance.addLoginButtonActionListener(listener);
        assertTrue("Listeners should be added to Login button", instance.getLoginButton().getActionListeners().length > 0);
    }

    /**
     * Test of addReturnButtonActionListener method, of class LoginPanel.
     */
    @Test
    public void testAddReturnButtonActionListener() {
        System.out.println("addReturnButtonActionListener");
        LoginPanel instance = new LoginPanel();
        ActionListener listener = e -> {};
        instance.addReturnButtonActionListener(listener);
        assertTrue("Listeners should be added to Return button", instance.getReturnButton().getActionListeners().length > 0);

    }

    /**
     * Test of signUpButtonClick method, of class LoginPanel.
     */
    @Test
    public void testSignUpButtonClick() {
        System.out.println("signUpButtonClick");
        LoginPanel instance = new LoginPanel();
        JButton loginButton = instance.getLoginButton();
        assertEquals("Default text should be 'Log In'", "Log In", loginButton.getText());
    }

    /**
     * Test of returnButtonClick method, of class LoginPanel.
     */
    @Test
    public void testReturnButtonClick() {
        System.out.println("returnButtonClick");
        LoginPanel instance = new LoginPanel();
        JButton returnButton = instance.getReturnButton();
        assertEquals("Default text should be 'Return'", "Return", returnButton.getText());
    }
}

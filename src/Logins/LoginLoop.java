
package Logins;

import FileIO.LoginManager;
import java.util.Scanner;

/**
 * LoginLoop class holds the login loop at the start of the game
 * used to sign in to the game
 * 
 * @author Michael
 */
public class LoginLoop {    
    
    //LoginLoop fields
    private LoginManager logins;
    private boolean QUIT = false;
    
    //constructor
    public LoginLoop()
    {
        this.logins = new LoginManager();
    }
    
    //run loop
    public void start()
    {
        Scanner scan = new Scanner(System.in);
        
        this.logins.load();
        
        while (true)
        {
            //PROMPT USER
            System.out.println("WELOME TO DEAL OR NO DEAL! PLEASE SIGN IN! X TO QUIT!");
            System.out.println("LOADING LOGINS...");
            System.out.println("NEW USER? Y/N");
            String result = scan.nextLine().trim();
            if (checkX(result)) {
                QUIT = !QUIT;
                break;
            }
            
            if (result.equalsIgnoreCase("Y")) {
                //if new user, create new player          
                System.out.println("PLEASE ENTER YOUR NEW USERNAME: ");
                String username = scan.nextLine().trim();
                if (checkX(username)) {
                    QUIT = !QUIT;
                    break;
                }
                
                System.out.println("PLEASE ENTER YOUR NEW PASSWORD: ");
                String password = scan.nextLine().trim();
                if (checkX(password)) {
                    QUIT = !QUIT;
                    break;
                }
                this.logins.getLogins().put(username, password);
                this.logins.save();
                System.out.println("LOGIN CREATED! WELCOME " + username + "!");
                break;
            }
            else if (result.equalsIgnoreCase("N")) {
                System.out.println("PLEASE ENTER YOUR USERNAME: ");
                String username = scan.nextLine().trim();
                if (checkX(username)) {
                    QUIT = !QUIT;
                    break;
                }
                
                System.out.println("PLEASE ENTER YOUR PASSWORD: ");
                String password = scan.nextLine().trim();
                if (checkX(password)) {
                    QUIT = !QUIT;
                    break;
                }
                
                //if username does not exist create login
                if (!this.logins.getLogins().containsKey(username))
                {
                    System.out.println("USERNAME DOES NOT EXIST. CREATE A NEW ACCOUNT!");
                }
                else {
                    break;
                }
            }
            else 
            {
                System.out.println("Please press Y or N");
            }
        }
    }
    
    /**
     * checkX checks if the user presses x, if x is pressed, return quit 
     * 
     * @param input the string to be checked
     * @return false if input = x
     */
    public boolean checkX(String input) {
        if (input.equalsIgnoreCase("X"))
        {
            return true;
        }
        return false;
    }
    
    //get method

    public boolean isQUIT() {
        return QUIT;
    }
}

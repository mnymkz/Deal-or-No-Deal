
package Main;

/**
 * Login class holds login details
 * 
 * @author Michael
 */
public class Login {
    
    //login fields
    private String userName;
    private String password;
    
    //constructors
    public Login(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    
    //getters and setters
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * changePassword method changes the user's password
     * 
     * @param password the password to change
     * @return true once changed
     */
    public boolean changePassword(String password)
    {
        this.password = password;
        return true;
    }
    
    /**
     * changeUsername method changes the user's username
     * 
     * @param username the username to be changed
     * @return true if successfully changed 
     */
    public boolean changeUsername(String username)
    {
        this.userName = username;
        return true;
    }
}

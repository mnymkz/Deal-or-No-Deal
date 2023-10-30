
package Login;

/**
 * merged player and login object into a single user object for database functions
 * 
 * @author Michael
 */
public class User {

    //user fields
    private String username;
    private String password;
    private double highestEarnings;

    //constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.highestEarnings = 0;
    }

    //getter and setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getHighestEarnings() {
        return highestEarnings;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHighestEarnings(double highestEarnings) {
        this.highestEarnings = highestEarnings;
    }
}

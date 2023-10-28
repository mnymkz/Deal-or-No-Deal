package Main;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Michael
 */
public class LoginManager implements ObjectLoader {
    
    private HashMap<String, String> logins;
    private final String FILE_PATH = "resources/logins.txt";

    public LoginManager()
    {
        this.logins = new HashMap<>();
    }
    
    /**
     * loadLogins method loads logins from logins file into hash map
     */
    private void loadLogins()
    {
        String data = FileIO.readFromFile(FILE_PATH);
        
        //split data into lines
        String[] logins = data.split("\n");
        
        //loop through logins array
        for (String login: logins)
        {
            //create logins
            createLogin(login);
        }
    }
    
    /**
     * createLogin method creates a login and adds it into the hash map
     * 
     * @param loginFields login username and password
     */
    private void createLogin(String loginFields)
    {
        //split data into csv using "," identifier 
        String[] values = loginFields.split(",");

        //input check
        if (values.length == 2) {
            //get key and value 
            String username = values[0];
            String password = values[1];
            //load into hashmap 
            this.logins.put(username, password);
        }
    }
    
    /**
     * saveLogins method saves logins in hash map to text file
     */
    private void saveLogins()
    {
        //clear file 
        FileIO.clearFile(FILE_PATH);
        //loop through map
        //for each key value pair
        //write to text file in csv format 
        for (Map.Entry<String, String> entry : logins.entrySet()) {
            String username = entry.getKey();
            String password = entry.getValue();
            String login = username + "," + password + "\n";
            //write logins
            FileIO.writeToFile(FILE_PATH, login);
        }
    }
    
    //get methods
    public HashMap<String, String> getLogins() {
        return logins;
    }

    public String getFILE_PATH() {
        return FILE_PATH;
    }

    //interface method implementation
    /**
     * load calls protected loadLogins method  
     */
    @Override
    public void load() {
        loadLogins();
    }

    @Override
    /**
     * save calls protected saveLogins method
     */
    public void save() {
        saveLogins();
    }
}

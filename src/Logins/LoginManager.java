package Logins;

import FileIO.FileManager;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Michael
 */
public class LoginManager {
    
    private HashMap<String, String> logins;
    private final String FILEPATH = "resources/logins.txt";

    public LoginManager()
    {
        this.logins = new HashMap<>();
    }
    
    /**
     * loadLogins method loads logins from logins file into hash map
     */
    protected void loadLogins()
    {
        String data = FileManager.readFromFile(FILEPATH);
        
        //split data into lines
        String[] logins = data.split("\n");
        
        //loop through logins array
        for (String login: logins)
        {
            //split data into csv using "," identifier 
            String[] values = login.split(",");
            
            //input check
            if (values.length == 2)
            {
                //get key and value 
                String username = values[0];
                String password = values[1];
                //load into hashmap 
                this.logins.put(username, password);
            }
        }
    }
    
    /**
     * saveLogins method saves logins in hash map to text file
     */
    protected void saveLogins()
    {
        //clear file 
        FileManager.clearFile(FILEPATH);
        //loop through map
        //for each key value pair
        //write to text file in csv format 
        for (Map.Entry<String, String> entry : logins.entrySet()) {
            String username = entry.getKey();
            String password = entry.getValue();
            String login = username + "," + password + "\n";
            //write logins
            FileManager.writeToFile(FILEPATH, login);
        }
    }
    
    //get methods
    public HashMap<String, String> getLogins() {
        return logins;
    }

    public String getFILEPATH() {
        return FILEPATH;
    }
}

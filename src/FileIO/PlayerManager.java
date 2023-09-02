
package FileIO;

import static FileIO.FileIO.readFromFile;
import FileIO.ObjectLoader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Michael
 */
public class PlayerManager implements ObjectLoader{

    //playerManager fields
    private HashMap<String, Double> playerScores;
    private final String FILE_PATH = "resources/highscores.txt";

    public PlayerManager() {
        this.playerScores = new HashMap<>();
    }
    
    /**
     * loadPlayers loads players from the highscores.txt file into the playerScores hashmap
     */
    private void loadPlayers()
    {
        //get data
        String data = readFromFile(FILE_PATH);
        
        //split into individual players
        String playerEarnings[] = data.split("\n");
        //split players into attributes
        for (String p: playerEarnings)
        {
            //load into hash map
            String[] attributes = p.split(",");
            if (attributes.length == 2)
            {
                String name = attributes[0];
                try {
                    Double earning = Double.parseDouble(attributes[1]);
                } catch (NumberFormatException e)
                {
                    System.out.println("Error loading score");
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * savePlayers saves the data from hash map to the txt file
     */
    private void savePlayers()
    {
        //clear file
        FileIO.clearFile(FILE_PATH);
        //loop through map
        //loop through map
        //for each key value pair
        //write to text file in csv format 
        for (Map.Entry<String, Double> entry : playerScores.entrySet()) {
            String playerName = entry.getKey();
            Double highestEarning = entry.getValue();
            //write to file
            String playerEarnings = playerName + "," + highestEarning + "\n";
            FileIO.writeToFile(FILE_PATH, playerEarnings);
        }
    }
    
    /**
     * load method loads player highest earnings from file 
     */
    @Override
    public void load() {
        loadPlayers();
    }

    @Override
    public void save() {
        savePlayers();
    }

    //getters
    public HashMap<String, Double> getPlayerScores() {
        return playerScores;
    }

    public String getFILE_PATH() {
        return FILE_PATH;
    }
}

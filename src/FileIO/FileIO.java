package FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FileIO class contains file reading and writing methods
 * 
 * @author Michael
 */
public class FileIO {

    /**
     * WriteToFileMethod writes data to file
     * 
     * @param filename the name of file to write to
     * @param data the data to write
     */
    public static void writeToFile(String filename, String data) {
        //check if file exists
        if (fileExists(filename)) {
            //write data to file
            BufferedWriter writer;
            try {
                writer = new BufferedWriter(new FileWriter(filename));
                writer.write(data);
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //create new file and then write data to file
            createFile(filename);
            writeToFile(filename, data);
        }
    }

    /**
     * readFromFile reads a string of data from file
     * 
     * @param filename the file to read from
     * @return the data from file 
     */
    public static String readFromFile(String filename) {
        //checks to see if filename.txt exists 
        if (fileExists(filename)) {
            //returns data as a string 
            StringBuilder data = new StringBuilder();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                //read lines from file and append to string
                String line;
                while ((line = reader.readLine()) != null) {
                    data.append(line + "\n");
                }
                reader.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return data.toString();
        } else {
            //error reading from file, return empty string
            System.out.println("Error reading from file. File does not exist.");
            return "";
        }
    }

    /**
     * fileExists method checks if a file exists
     * 
     * @param fileName the file to check
     * @return true if file exists, else return false
     */
    public static boolean fileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }


    /**
     * creates a new file if file doesn't exist
     * 
     * @param fileName the name of newFile
     */
    public static void createFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

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
 *
 * @author Michael
 */
//filiIO class contains static methods for filewriting and filereading
public class FileIO {

    //WriteToFileMethod writes data to file
    public static void writeToFile(String filename, String data, boolean append) {
        //check if file exists
        if (fileExists(filename)) {
            //write data to file
            BufferedWriter writer;
            try {
                writer = new BufferedWriter(new FileWriter(filename, append));
                writer.write(data);
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //create new file and then write data to file
            createFile(filename);
            writeToFile(filename, data, append);
        }
    }

    //readFromFile method returns a string containing data from file
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

    //file exists method checks to see if file exists before createing a new file
    public static boolean fileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    //creats a new file if file doesn't exist
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

    // updateFile method copies text from existing file to a new file without the line containing 
    //the identifier. If line contains the identifier, it is not added to the updated file
    public static void updateFile(String filename, String identifier) {
        try {
            File inputFile = new File(filename);
            File tempFile = new File("./resources/temp.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String line;

            while ((line = reader.readLine()) != null) {
                // check if the line contains the course to be deleted
                if (line.contains(identifier)) {
                    continue;
                }
                // write the line to the temporary file
                writer.write(line + System.getProperty("line.separator"));
            }

            reader.close();
            writer.close();

            // delete the original file
            inputFile.delete();
            // rename the temporary file to the original file name
            tempFile.renameTo(inputFile);

            System.out.println(identifier + " removed successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while removing" + identifier);
            e.printStackTrace();
        }
    }
}

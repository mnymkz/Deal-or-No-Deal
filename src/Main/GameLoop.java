package Main;

import java.util.Scanner;

/**
 *
 * @author Michael
 */
public class GameLoop {

    //scanner field 
    Scanner scan = new Scanner(System.in);

    /**
     * start method starts the game loop
     */
    void start() {
        while (true) {
            //open a case
            System.out.println("SELECT A CASE BETWEEN 1 AND 26!");
            //TODO display cases 
            String userInput = scan.nextLine().trim();
            if (chooseCase(userInput) == -1)
            {
                break;
            }

            //deal or no deal?
            System.out.println("DEAL OR NO DEAL...?");
            
            //TODO implement banker offer
            System.out.println("PLEASE PRESS A TO ACCEPT, R TO REFUSE, X TO QUIT!");

            userInput = scan.nextLine().trim();
            //if user presses x to quit, quit program
            if (dealOrNoDeal(userInput) == -1)
            {
                break;
            }
            //if deal, save money and highscore then quit
            else if (dealOrNoDeal(userInput) == 0)
            {
                //TODO implement player progress  
                System.out.println("CONGTRATULATIONS! YOU HAVE WON $___");
                //TODO save player highscore 
                break;
            }
            //if no deal, continue with the game
            

        }
    }

    /**
     * chooseCase method allows the user to choose a case from the caseList
     *
     * @param input the input string from keyboard
     * @return the choice of the user
     */
    public int chooseCase(String input) {
        while (true) {
            if (input.equalsIgnoreCase("x"))
            {
                break;
            }
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 26) {
                    System.out.println(choice + " HAS BEEN REVEALED!");
                    return choice;
                    //reveal case 
                } else {
                    System.out.println("PLEASE SELECT A CASE BETWEEN 1 AND 26!");
                    input = scan.nextLine().trim();
                }
            } catch (NumberFormatException E) {
                System.out.println("PLEASE ENTER AN INTEGER BETWEEN 1 AND 26!");
                input = scan.nextLine().trim();
            }
        }
        //user pressed x, return -1;
        return -1;
    }
    
    /**
     * dealOrNoDeal method allows the user to accept or refuse the banker's offer
     * 
     * @param input the input from keyboard
     * @return -1 if x is pressed, 0 if deal, 1 if no deal
     */
    public int dealOrNoDeal(String input)
    {
        //check if input is invalid, while invalid keep prompting user to press a, r, or x
        while (true)
        {
            if (input.equalsIgnoreCase("x"))
            {
                return -1;
            } if (input.equalsIgnoreCase("a"))
            {
                return 0;
            } if (input.equalsIgnoreCase("r")) {
                return 1;
            }
            System.out.println("INVALID INPUT! PLEASE PRESS A TO ACCEPT, R TO REFUSE, X TO QUIT!");
            input = scan.nextLine().trim();
        }
    }
}

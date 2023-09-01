package Main;

import Banker.Banker;
import Case.Case;
import FileIO.CaseManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Tabitha
 */
public class Round {

    //round fields 
    protected CaseManager cm;
    protected HashSet chosenNumbers;
    protected Player player;
    protected Banker banker;
    protected int numChoices;
    protected Boolean QUIT = false;
    protected Scanner scan = new Scanner(System.in);

    //round constructor
    public Round(CaseManager cm, HashSet chosenNumbers, Player player, Banker banker, int numChoices) {
        this.cm = cm;
        this.chosenNumbers = chosenNumbers;
        this.player = player;
        this.banker = banker;
        this.numChoices = numChoices;
    }

    /**
     * Display information about the round such as the number of remaining cases
     * Player interaction
     * Case interaction
     */
    public void startRound() {
        //Display cases
        displayCases();

        //get player to choose their cases
        //TODO - choose multiple instead of one
        System.out.println("SELECT A CASE BETWEEN 1 AND 26!");
        String userInput = scan.nextLine().trim();
        if (chooseCase(userInput) == -1) {
            this.QUIT = !QUIT;
            return;
        }

        //TODO - banker offers
        //deal or no deal?
        System.out.println("DEAL OR NO DEAL...?");
        System.out.println("PLEASE PRESS A TO ACCEPT, R TO REFUSE, X TO QUIT!");

        userInput = scan.nextLine().trim();
        //if user presses x to quit, quit program
        if (dealOrNoDeal(userInput) == -1) {
            this.QUIT = !QUIT;
            return;
        } //if deal, save money and highscore then quit
        else if (dealOrNoDeal(userInput) == 0) {
            //TODO implement player progress  
            
            System.out.println("CONGTRATULATIONS! YOU HAVE WON $___");
            //TODO save player highscore 
            this.QUIT = !QUIT;
        }
        else if (dealOrNoDeal(userInput) == 1)
        {
            //new round
            return;
        }
    }

    /**
     * dealOrNoDeal method allows the user to accept or refuse the banker's
     * offer
     *
     * @param input the input from keyboard
     * @return -1 if x is pressed, 0 if deal, 1 if no deal
     */
    protected int dealOrNoDeal(String input) {
        //check if input is invalid, while invalid keep prompting user to press a, r, or x
        while (true) {
            if (input.equalsIgnoreCase("x")) {
                return -1;
            }
            if (input.equalsIgnoreCase("a")) {
                return 0;
            }
            if (input.equalsIgnoreCase("r")) {
                return 1;
            }
            System.out.println("INVALID INPUT! PLEASE PRESS A TO ACCEPT, R TO REFUSE, X TO QUIT!");
            input = scan.nextLine().trim();
        }
    }

    /**
     * chooseCase method allows the user to choose a case from the caseList
     *
     * @param input the input string from keyboard
     * @return the choice of the user
     */
    protected int chooseCase(String input) {
        while (true) {
            if (input.equalsIgnoreCase("x")) {
                break;
            }
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 26) {
                    //if not chosen already
                    if (!chosenNumbers.contains(choice)) {
                        this.chosenNumbers.add(choice);
                        revealCase(choice);
                        return 0;
                    } else { //get user to pick a closed case
                        System.out.println("ALREADY CHOSEN! PLEASE SELECT A CASE THAT HAS NOT BEEN OPENED!");
                        input = scan.nextLine().trim();
                    }
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
     * revealCase takes a case number and reveals it the case is then removed
     * from the cases left list
     *
     * @param caseNo the case to be revealed
     */
    protected void revealCase(int caseNo) {
        int caseIndex = caseNo - 1;
        this.cm.getCases().get(caseIndex).openCase();
    }

    /**
     * displayCase method displays cases to choose from to the user
     */
    protected void displayCases() {
        //loop through cases
        for (int i = 0; i < cm.getCases().size(); i++) {
            //if mod == 0, new line 
            if (i % 5 == 0) {
                System.out.println("");
            }
            //last case, print in the middle  
            if (i == 25) {
                System.out.print("            ");
            }
            //display case
            System.out.print(cm.getCases().get(i).displayCase());
        }
        //newline
        System.out.println("\n");
    }

    //get method
    public Boolean getQUIT() {
        return QUIT;
    }

}

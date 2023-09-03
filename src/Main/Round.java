package Main;

import Banker.Banker;
import Banker.ConservativeBanker;
import Case.Item;
import Case.Case;
import FileIO.CaseManager;
import FileIO.PlayerManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

/**
 *
 * @author Tabitha
 */
public class Round {

    //round fields 
    protected CaseManager caseManager;
    protected PlayerManager playerManager;
    protected HashSet chosenNumbers;
    protected Player player;
    protected Banker banker;
    protected int numChoices;
    protected int currentRound;
    protected Boolean QUIT = false;
    protected Scanner scan = new Scanner(System.in);

    //round constructor

    public Round(CaseManager cm, HashSet chosenNumbers, Player player, int numChoices, int currentRound) {
        this.caseManager = cm;
        this.chosenNumbers = chosenNumbers;
        this.player = player;
        this.numChoices = numChoices;
        this.currentRound = currentRound;
    }


    /**
     * Display information about the round such as the number of remaining cases
     * Player interaction
     * Case interaction
     */
    public void startRound() 
    {
        //Display cases
        displayCases();

        System.out.println("ROUND " + currentRound + ": You have " + numChoices + " briefcases to choose from...\n");

        String userInput = "";
        for(int i = 0; i < numChoices; i++)
        {
            System.out.println("SELECT A CASE BETWEEN 1 AND 26!");
            userInput = scan.nextLine().trim();
            if (chooseCase(userInput) == false) {
                this.QUIT = !QUIT;
                return;
            }
            displayCases();
            printMoney();
        }        
        
        //banker offers
        //depeding on the values average, it must correlate to a type of banker (Aggressive/Conservative/Random) to get an offer
        Banker banker = new ConservativeBanker(userInput);
        banker.bankerOffer(banker.createOffer(caseManager.getCases()));

        //Deal or no deal
        int response = dealOrNoDeal();
         
        //if user presses x to quit, quit program
        if (response == -1) {
             this.QUIT = !QUIT;
             return;
         } //if deal, save money and highscore then quit
         else if (response == 0) 
         {
            //TODO implement players
            double bankerOffer = banker.bankerOffer(Math.round(banker.createOffer(caseManager.getCases())));
            System.out.println("\nCONGTRATULATIONS! YOU HAVE WON $" + bankerOffer);
            //TODO save player highscore 
            player.setEarnings(banker.bankerOffer(bankerOffer));
            this.QUIT = !QUIT;
         }
         else if (response == 1)
         {
            currentRound++;
            numChoices--;

            if(currentRound >= 7 && currentRound <= 9)
            {
                numChoices = 1;
                startRound();
            }
            else if (currentRound == 10)
            {
                return;
            }
            else
            {
                startRound();
            }

            return;
         }
    }  

    /**
     * dealOrNoDeal method allows the user to accept or refuse the banker's
     * offer
     *
     * @return -1 if x is pressed, 0 if deal, 1 if no deal
     */
    protected int dealOrNoDeal() {
        
        System.out.println("DEAL OR NO DEAL...?");
        System.out.println("PLEASE PRESS A TO ACCEPT, R TO REFUSE, X TO QUIT!");
        String input = scan.nextLine().trim();
        
        while (true) {
            if (input.equalsIgnoreCase("x")) {
                return -1; // User pressed 'X' to quit
            } if (input.equalsIgnoreCase("a")) {
                return 0; // User accepted the offer
            } if (input.equalsIgnoreCase("r")) {
                return 1; // User refused the offer
            }
            
            //else read input again
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
    protected boolean chooseCase(String input) {
        while (true) {
            if (input.equalsIgnoreCase("x")) {
                break;
            }
            try {
                int choice = Integer.parseInt(input)-1;
                if (choice >= 0 && choice <= 25) {
                    //if not chosen already and case is not opened 
                    if (!chosenNumbers.contains(choice) && !caseManager.getCases().get(choice).isOpened()) {
                        this.chosenNumbers.add(choice);
                        revealCase(choice);
                        //Retrieve the Item from the selected case
                        Item selectedCase = caseManager.getCases().get(choice).getItem();
                        // Display the value of the current chosen case
                        System.out.println("You selected the case with a " + selectedCase.getName() + " that has a value of $" + selectedCase.getMoneyValue()
                                + "\n\n" + selectedCase.getDescription() + "\n");
                        //return 
                        return true;
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
        return false;
    }

    /**
     * revealCase takes a case number and reveals it the case is then removed
     * from the cases left list
     *
     * @param caseNo the case to be revealed
     */
    protected void revealCase(int caseNo) {
        this.caseManager.getCases().get(caseNo).openCase();
        System.out.println();
    }

    /**
     * displayCase method displays cases to choose from to the user
     */
    protected void displayCases() {
        //loop through cases
        for (int i = 0; i < caseManager.getCases().size(); i++) {
            //if mod == 0, new line 
            if (i % 5 == 0) {
                System.out.println("");
            }
            //last case, print in the middle  
            if (i == 25) {
                System.out.print("            ");
            }
            //display case
            System.out.print(caseManager.getCases().get(i).displayCase());
        }
        //newline
        System.out.println("\n");
    }

    /**
     * print money gets the money value of items from case manager and sorts them
     * by ascending order before printing values to the screen
     */
    protected void printMoney()
    {
        //using arraylist
        ArrayList<Double> moneyRemaining = new ArrayList<>();
        //loop through cases
        for(Case briefCase : caseManager.getCases())
        {
           //if not opened
           if(!briefCase.isOpened())  
           {
               //if case is not empty
               if (briefCase.getItem()!=null)
               {
                   //add money value to money remaining
                   moneyRemaining.add(briefCase.getItem().getMoneyValue());
               }
           }
        }
        //collections.sort
        Collections.sort(moneyRemaining);
        
        //Print sorted values 
        System.out.println("Remaining prizes:");
        for (int i = 0; i < moneyRemaining.size()/2; i++) {
            System.out.print(moneyRemaining.get(i));
        }
    }
    
    //get method
    public Boolean getQUIT() {
        return QUIT;
    }

}

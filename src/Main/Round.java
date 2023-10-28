package Main;

import Logins.Player;
import Banker.Banker;
import Banker.ConservativeBanker;
import Banker.AggressiveBanker;
import Banker.RandomBanker;
import Case.Item;
import Case.Case;
import java.util.ArrayList;
import java.util.Collections;
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
    protected ArrayList<Double> moneyRemaining;

    //round constructor

    public Round(CaseManager caseManager, PlayerManager playerManager, HashSet chosenNumbers, Player player, int numChoices, int currentRound) {
        this.caseManager = caseManager;
        this.playerManager = playerManager;
        this.chosenNumbers = chosenNumbers;
        this.player = player;
        this.numChoices = numChoices;
        this.currentRound = currentRound;
        this.moneyRemaining = new ArrayList<>();
        generateBanker();
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
            System.out.println("Remaining prizes:");
            printRemainingMoney();
        }        
        
        //banker offers
        //depeding on the values average, it must correlate to a type of banker (Aggressive/Conservative/Random) to get an offer
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
            double bankerOffer = banker.bankerOffer(Math.round(banker.createOffer(caseManager.getCases())));
            System.out.println("\nCONGTRATULATIONS! YOU HAVE WON $" + bankerOffer);
            this.player.setEarnings(bankerOffer);
            player.setEarnings(banker.bankerOffer(bankerOffer));
            playerManager.getPlayerScores().put(player.getName(), player.getEarnings());
            this.QUIT = !QUIT;
         }
         else if (response == 1)
         {
             //new round in gameloop
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
    protected void printRemainingMoney()
    {
        //clear arrayList
        moneyRemaining.clear();
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
        for (int i = 0; i < moneyRemaining.size(); i++) {
            System.out.printf("$%10.2f     ", moneyRemaining.get(i)); 
            if (i % 2 != 0) {
                System.out.println(""); // Start a new line after every second value
            }
        }
        System.out.println("");
    }
    
    /**
     * generateBanker method generates a banker based on the players progress into the game
     */
    protected void generateBanker()
    {
        //start of game, normal offers
        if (currentRound < 5)
        {
            //20% chance to be conservative, 20% chance to be aggressive
            Double earlyGameProbability = Math.random();
            if (earlyGameProbability >= 0.2 && earlyGameProbability <= 0.4)
            {
                this.banker = new ConservativeBanker("Banker");
            }
            if (earlyGameProbability < 0.2)
            {
                this.banker = new AggressiveBanker("Banker");
            }
            else
            {
                this.banker = new RandomBanker("Banker");
            }
        }
        //end game, risky offers
        else if (currentRound >= 5)
        {
            //50% chance to be conservative, 50% chance to be aggressive
            Double bankerProbability = Math.random();
            if (bankerProbability >= 0.5)
            {
                this.banker = new ConservativeBanker("Banker");
            }
            if (bankerProbability < 0.5)
            {
                this.banker = new AggressiveBanker("Banker");
            }
        }
    }

    //get method
    public Boolean getQUIT() {
        return QUIT;
    }
}

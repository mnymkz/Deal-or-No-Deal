
package Model;

import Banker.Banker;
import Database.DBManager;
import Case.Case;
import Case.CaseLoader;
import Login.Player;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * model class contains all the game objects 
 * 
 * @author Tabitha 
 */
public class Game {

    //game objects
    private GameManager gameManager;
    private DBManager dbManager;
    private CaseLoader caseLoader;
    private ArrayList<Case> cases;
    private Banker banker;
    private Player user;
    private Case firstCase;

    public Game(DBManager dbManager) {
        this.gameManager = new GameManager(dbManager);
        this.caseLoader = new CaseLoader(dbManager);
        cases = caseLoader.getCases();
    }
   
    public void startNewGame() throws SQLException{
        gameManager.createNewGame(user.getUsername());
    }
    
    public void chooseFirstCase(int caseNumber)
    {
        for (Case briefCase : cases)
        {
            if(briefCase.getNumber() == caseNumber)
            {
                firstCase = briefCase;
                cases.remove(briefCase);
                break;
            }
        }
    }
    
    public void openCase(int caseNumber)
    {
        for(Case briefCase : cases)
        {
            if(briefCase.getNumber() == caseNumber)
            {
                briefCase.openCase();
                break;
            }
        }
    }
    
    public double calculateBankerOffer()
    {
        double offer = banker.createOffer(cases);
        banker.setOffer(offer);
        return offer;        
    }
    
    public boolean allCasesOpened()
    {
        for(Case briefCase : cases)
        {
            if(briefCase != firstCase && !briefCase.isOpened())
            {
                return false;
            }
        }
        return true;
    }
    
    public double lastRound() {
        // Get the last unopened case (other than the first case chosen by the player)
        Case lastUnopenedCase = null;
        for(Case briefCase : cases) {
            if(briefCase != firstCase && !briefCase.isOpened()) {
                lastUnopenedCase = briefCase;
                break;
            }
        }

        if (lastUnopenedCase == null) {
            System.out.println("Error: No unopened cases found.");
            return 0; // Or handle this situation differently if required
        }

        System.out.println("You are left with two cases:");
        System.out.println("1. Your initial case number: " + firstCase.getNumber());
        System.out.println("2. Last unopened case number: " + lastUnopenedCase.getNumber());

        // Here, you'd probably want some kind of user input mechanism to let them choose
        // But for the sake of this example, I'll just simulate that the user always chooses their initial case:
        int userChoice = 1; // This could be 1 for initial case or 2 for the last unopened case

        if(userChoice == 1) {
            System.out.println("You chose your initial case.");
            firstCase.openCase();
            return firstCase.getItem().getMoneyValue();
        } else {
            System.out.println("You chose the last unopened case.");
            lastUnopenedCase.openCase();
            return lastUnopenedCase.getItem().getMoneyValue();
        }
    }
    
//    public ArrayList<Case> getLastCases() {
//        ArrayList<Case> lastCases = new ArrayList<>();
//
//        // Get the last unopened case (other than the first case chosen by the player)
//        Case lastUnopenedCase = null;
//        for(Case briefCase : cases) {
//            if(briefCase != firstCase && !briefCase.isOpened()) {
//                lastUnopenedCase = briefCase;
//                break;
//            }
//        }
//
//        if (lastUnopenedCase != null) {
//            lastCases.add(firstCase);
//            lastCases.add(lastUnopenedCase);
//        }
//
//        return lastCases;
//    }
    
    public void endRound() throws SQLException {
        int currentRound = gameManager.getCurrentRound(user.getUsername());
        gameManager.updateCurrentRound(user.getUsername(), currentRound + 1);
    }
    
    public Case getFirstCase()
    {
        return firstCase;
    }
    
    public ArrayList<Case> getCases() {
        return cases;
    }
    
    public Banker getBanker() {
        return banker;
    }
    
    public Player getUser() {
        return user;
    }
    
    public GameManager getGameManager() {
        return gameManager;
    }
    
    
    public void printRemainingCases()
    {
        System.out.println("User's selected case: " + firstCase.getNumber());
        System.out.println("Remaning cases: ");
        
        
        System.out.println("Banker's offer: $" + banker.getOffer());
    }
    
    public void casesOpened() {
        while(!allCasesOpened()) {
            printRemainingCases();
        }

        // Once loop exits, only the firstCase is left
        double finalPrize = firstCase.getItem().getMoneyValue();
        System.out.println("The game is over! The value of your initial briefcase is: $" + finalPrize);
    }
    
    public void revealFirstCase()
    {
        System.out.println("The value of your intial briefcase is: $" + firstCase.getItem().getMoneyValue());
    }
    
    //To remove briefcase when user clicks on specific briefcase
    public void removeCase(int caseNumber)
    {
        cases.remove(caseNumber - 1);
    }
}
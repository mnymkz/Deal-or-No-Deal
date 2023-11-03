
package Model;

import Banker.Banker;
import Banker.RandomBanker;
import Database.DBManager;
import Case.Case;
import Case.CaseLoader;
import Login.Player;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

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
    private Player player;
    private Case firstCase;

    public Game(DBManager dbManager, Player player) {
        this.dbManager = dbManager;
        this.player = player;
        this.banker = new RandomBanker("Banker");
        this.gameManager = new GameManager(dbManager);
        this.caseLoader = new CaseLoader(dbManager);
        caseLoader.load();
        cases = caseLoader.getCases();
    }
   
    public void startNewGame() throws SQLException{
        gameManager.createNewGame(player.getUsername());
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

        // Here, you'd probably want some kind of player input mechanism to let them choose
        // But for the sake of this example, I'll just simulate that the player always chooses their initial case:
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
    
    public void endRound() throws SQLException {
        int currentRound = gameManager.getCurrentRound(player.getUsername());
        gameManager.updateCurrentRound(player.getUsername(), currentRound + 1);
    }
    
    //getter and setter
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
    
    public Player getPlayer() {
        return player;
    }
    
    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }

    public void setCaseLoader(CaseLoader caseLoader) {
        this.caseLoader = caseLoader;
    }

    public void setCases(ArrayList<Case> cases) {
        this.cases = cases;
    }

    public void setBanker(Banker banker) {
        this.banker = banker;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setFirstCase(int caseNo) {
        this.firstCase = cases.get(caseNo+1);
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
        revealFirstCase();
        System.out.println("The game is over! The value of your initial briefcase is: $" + finalPrize);
    }
    
    public void revealFirstCase()
    {
        System.out.println("The value of your intial briefcase is: $" + firstCase.getItem().getMoneyValue());
    }
    
    //To remove briefcase when player clicks on specific briefcase
    public void removeCase(int caseNumber) {
        Iterator<Case> iterator = cases.iterator();
        while (iterator.hasNext()) {
            Case c = iterator.next();
            System.out.println(c.toString());
            if (c.getNumber() == caseNumber) {
                iterator.remove();
                break; // Break if you just want to remove the first match
            }
        }
    }

}

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

    public Game(CaseLoader caseLoader, DBManager dbManager) {
        this.caseLoader = caseLoader;
        this.gameManager = new GameManager(dbManager);
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
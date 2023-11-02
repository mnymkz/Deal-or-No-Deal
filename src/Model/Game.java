
package Model;

import Banker.Banker;
import Case.Case;
import Case.CaseLoader;
import Login.Player;
import java.util.ArrayList;

/**
 * model class contains all the game objects 
 * 
 * @author Tabitha 
 */
public class Game {

    //game objects
    private CaseLoader caseLoader;
    private ArrayList<Case> cases;
    private Banker banker;
    private Player user;
    private Case firstCase;

    public Game(CaseLoader caseLoader) {
        this.caseLoader = caseLoader;
        cases = caseLoader.getCases();
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
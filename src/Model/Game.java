
package Model;

import Banker.Banker;
import Case.Case;
import Login.User;
import java.util.ArrayList;
import java.util.Collections;

/**
 * model class contains all the game objects 
 * 
 * @author Tabitha 
 */
public class Game {
    //game needs a banker
    //game needs cases
    //game needs a user 
    //game needs to get the user's first case 
    
    private ArrayList<Case> cases;
    private Banker banker;
    private User user;
    private Case firstCase;
    
    
    public Game(User user, Banker banker, ArrayList<Case> cases)
    {
        this.user = user;
        this.banker = banker; 
        this.cases = cases;
    }
    
    public void chooseFirstCase(int caseNumber)
    {
        for (Case briefCase : cases)
        {
            if(briefCase.getNumber() == caseNumber)
            {
                firstCase = briefCase;
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
    
    public User getUser() {
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
}
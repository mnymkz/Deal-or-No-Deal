
package Rounds;

import Logins.Player;
import Banker.Banker;
import FileIO.CaseManager;
import FileIO.PlayerManager;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * LastRound extends Round
 * overrides startRound method
 * 
 * @author Tabitha
 */
public class LastRound extends Round {

    public LastRound(CaseManager caseManager, PlayerManager playerManager, HashSet chosenNumbers, Player player, int numChoices, int currentRound) {
        super(caseManager, playerManager, chosenNumbers, player, numChoices, currentRound);
    }

    @Override
    public void startRound()
    {
        displayCases();
        
        //ask the user if they want to swap cases or keep their current case
        System.out.println("ROUND " + currentRound + "\n\nYou have two cases left!\n\n" + "Do you want to keep your briefcase " + "(Briefcase " + player.getFirstChoice().getNumber() +
        ") or switch it with the last briefcase on the board?" + "(keep/switch)");

        try 
        {
            String userInput = scan.nextLine().trim().toLowerCase();    
            if (userInput.equals("keep")) 
            {
                //display the amount of the chosen briefcase
                System.out.println("\nYou chose to keep your briefcase...\n\nBriefcase " + player.getFirstChoice());
                
                return;
            } 
            else if (userInput.equals("switch")) 
            {
                //display the amount of the unopened briefcase
                System.out.println("\nYou chose to switch your briefcase...\n\nBriefcase " + caseManager.getLastUnopenedCase());

                //switch the cases, set the earnings to the value of the unopened case
                double switchEarnings = caseManager.getLastUnopenedCase().getItem().getMoneyValue();
                player.setEarnings(switchEarnings);

                //display the original briefcase value
                System.out.println("\n***ORIGINAL BRIEFCASE*** \n\nBriefcase " + player.getFirstChoice());

                return;
            } 
            else 
            {
                // Handle invalid input
                System.out.println("Invalid input. Enter 'keep' or 'switch'.");
                // Restart the round
                startRound(); 
            }   
        } 
        catch (InputMismatchException e) 
        {
            // Handle any input mismatch exceptions (e.g., if the user enters something that's not a string)
            System.out.println("Invalid input. Please enter 'keep' or 'switch'.");
            // Restart the round
            startRound(); 
        }
    }
}

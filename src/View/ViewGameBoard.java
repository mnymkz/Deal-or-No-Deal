package View;

import javax.swing.*;
import Case.Case;
import Main.PlayerOld;
import components.Briefcase;
import components.Container;
import components.GameButton;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 
 * @author Tabitha
 */
public class ViewGameBoard extends JPanel
{
    private ArrayList<Briefcase> briefcases;
    private PlayerOld player;
    
    public ViewGameBoard(ArrayList<Case> cases, PlayerOld player)
    {
        this.player = player;//setPlayer
        
        setLayout(new BorderLayout());

        Container briefcaseContainer = new Container(FlowLayout.CENTER, 500, 300);

        briefcases = new ArrayList<>();

        for(Case aCase : cases)
        {
            Briefcase briefcase = new Briefcase(aCase);
            briefcases.add(briefcase);
            briefcaseContainer.add(briefcase);
        }

        GameButton caseOpenButton = new GameButton("OPEN CASE", Color.BLUE, 250, 100);

        Container buttonContainer = new Container(500, 50);
        //buttonContainer.add(openCaseButton);
    }

    public void addBriefCaseActionListener(ActionListener listener)
    {
        for(Briefcase briefcase : briefcases)
        {
            briefcase.addActionListener(listener);
        }
    }
}
package Components;

import javax.swing.JButton;
import Case.Case;

/**
 * 
 * @author Tabitha
 */

public class Briefcase extends JButton
{
    private Case briefCase;

    public Briefcase(Case selectedCase)
    {
        this.briefCase = selectedCase;
        this.setText(String.format("%02d", selectedCase.getNumber()));
        this.setEnabled(!selectedCase.isOpened());
    }

    public Case getBriefCase()
    {
        return briefCase;
    }

    public void openCase()
    {
        briefCase.openCase();
        setEnabled(false);
    }
}

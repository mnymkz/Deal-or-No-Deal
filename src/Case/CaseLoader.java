
package Case;

import Database.DBManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Michael
 */
public class CaseLoader {
    
    private ArrayList<Case> cases;
    private final int NUM_CASES;
    private final ItemGenerator itemGenerator;

    public CaseLoader(DBManager dbManager) {
        this.cases = new ArrayList<>();
        this.NUM_CASES = 26;
        this.itemGenerator = new ItemGenerator(dbManager);
    }
    
    /**
     * generates cases into the case list
     */
    private void generateCases() {
        for (int i = 0; i < NUM_CASES; i++) {
            int caseNo = i + 1;
            int randomCase = generateRandomCase();
            Item item = itemGenerator.getRandomItem();

            switch (randomCase) {
                case 0:
                    Case normalCase = new NormalCase(caseNo, item);
                    cases.add(normalCase);
                    break;
                case 1:
                    double multiplier = generateRandomMultiplier();
                    Case specialCase = new SpecialCase(caseNo, item, multiplier);
                    cases.add(specialCase);
                    break;
                case 2:
                    Case doubleCase = new DoubleCase(caseNo, item);
                    cases.add(doubleCase);
                    break;
                case -1:
                    System.out.println("Error generating case.");
                    break;
                default:
                    System.out.println("Error generating case.");
                    break;
            }
        }
    }

    /**
     * Generate a random case object 
     * 
     * @return an int value representing a case
     */
    private int generateRandomCase() {
        double randomValue = Math.random();
        if (randomValue >= 0.86 && randomValue <= 0.90) {
            return 1; //special case
        } else if (randomValue >= 0.90 && randomValue <= 0.95) {
            return 2; //double case
        } else {
            return 0; //normal case
        }
    }

    //generate a random multiplier
    private double generateRandomMultiplier() {
        Random random = new Random();
        return random.nextDouble() * 2.0;
    }

    //load items
    public void load() {
        try {
            itemGenerator.loadItems();
        } catch (SQLException ex) {
            System.out.println("Error loading items from ITEM");
        }
        generateCases();
    }

    //get methods
    public ArrayList<Case> getCases() {
        return cases;
    }

    public int getNUM_CASES() {
        return NUM_CASES;
    }
    
    public static void main(String[] args) {
        DBManager dbManager = DBManager.getInstance();
        dbManager.establishConnection();
        System.out.println(dbManager.getConnection());
        CaseLoader cl = new CaseLoader(dbManager);
        cl.load();
        System.out.println(cl.getCases().size());
    }
}

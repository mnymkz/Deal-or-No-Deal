package Case;

/**
 * Empty Cases have no items inside them.
 * Cases can get promoted to double/empty cases in a double or nothing round
 * 
 * @author Michael
 */
public class EmptyCase extends Case {

    public EmptyCase(int number) {
        super(number, null);
    }

    //Override toString methods
    @Override
    public String toString()
    {
        return number + ": THIS CASE IS EMPTY!";
    }
    
    @Override
    public String toCSV()
    {
       return this.number+","+this.opened+",null";
    }
}

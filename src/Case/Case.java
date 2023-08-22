
package Case;

/**
 *
 * @author Tabitha
 */
public class Case 
{
    private int number;
    private String object;

    public Case(int number, String object)
    {
        this.number = number;
        this.object = object;
    }

    public int getNumber()
    {
        return number;
    }

    public String getObject()
    {
        return object;
    }

    @Override
    public String toString()
    {
        return number + ": " + object;
    }
}

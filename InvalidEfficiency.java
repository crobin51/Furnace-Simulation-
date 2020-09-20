
/**
 * Write a description of class InvalidEfficiency here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InvalidEfficiency extends Exception
{

    /**
     * Constructor for objects of class InvalidEfficiency
     */
    public InvalidEfficiency()
    {
        super("Efficiency must be between 0 and 1.");
    }

}

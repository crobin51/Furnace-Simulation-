
/**
 * Abstract class Furnace - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Furnace
{
    public int capacity; 
    public double effciency;
    public String type;
    public int year;
    public double output;

    /**
     *  Constructor.
     */
    public Furnace(int cap, double eff, String furn, int built)
    {
        capacity = cap;
        effciency = eff;
        type = furn;
        year = built;

    }

    /**
     *  Method that sets the Furnace capacity depending on the year and size of house.
     */
    public void setCapacity(double size)
    {
        if (year > 1980)
        {
            if (size < 1300) capacity = 40000; 
            else if (size >= 1300 && size <1700) capacity = 45000;
            else if (size >= 1700 && size <2500) capacity = 50000;
            else if (size >= 2500 && size <3500) capacity = 60000;
            else capacity = 70000;

        }
        else 
        {
            if (size < 1300) capacity = 50000; 
            else if (size >= 1300 && size <1700) capacity = 55000;
            else if (size >= 1700 && size <2500) capacity = 65000;
            else if (size >= 2500 && size <3500) capacity = 80000;
            else capacity = 100000;
        }
    }

    /**
     *  Calculates output of furnace.
     */    
    public double getOutput()
    {
        output = effciency*capacity;

        return output;         
    }
}

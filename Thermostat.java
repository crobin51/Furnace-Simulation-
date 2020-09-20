
/**
 * Write a description of class Thermostat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Thermostat
{
    public static final double BLC = 1.0;
    public static final double SHC = 4.0;
    private double roomTemp;
    private double overheat;
    private double previousTemp;
    private boolean maxHeat = false;
    private String pilot = "Starting...";

    /**
     *  Constructor.
     */
    public Thermostat(double room, double over)
    {
        roomTemp = room;
        overheat = over;
        previousTemp = room;
    }

    /**
     *  Updates temperature of the Thermostat Object.
     */
    public double updateTemp(Furnace f, double  size, double newTemp, double outside, double time)
    {
        double qIn = 0, qLoss;

        qLoss = BLC * size * (newTemp-outside) * time;

        if (maxHeat == false)
        {
            if (newTemp < (roomTemp + overheat))
            {
                qIn = f.getOutput() * time;
                pilot = "On";

            }
            else if (newTemp > (roomTemp+ overheat))
            {
                qIn = 0;
                pilot = "Off";
                maxHeat = true;

            }
        }
        else
        {
            if (newTemp <= roomTemp)
            {
                qIn = f.getOutput() * time;
                pilot = "On";
                maxHeat = false;

            }
            else if (newTemp > roomTemp)
            {
                qIn = 0;
                pilot = "Off";

            }
        }
        newTemp+= ((qIn - qLoss)/(SHC * size));

        return newTemp;

    }

    public String getPilot()
    {
        return pilot;
    }

    public double getSHC()
    {
        return SHC;
    }
    public double getBLC()
    {
        return BLC;
    }
}

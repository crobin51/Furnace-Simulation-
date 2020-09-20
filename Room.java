import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
/**
 * 
 * @author (your name) 
 * @version 3.0
 */

public class Room
{
    private Thermostat control;
    private Furnace furn;
    private FileWriter fw ; //file writer object
    private PrintWriter outputFile; 
    private Timer timer;

    private double initialTemp, size,freq,outTemp , furnaceEff, overTemp, desireTemp;
    private int runTime, furnaceCap, year;
    private String furnaceType;
    private int timeChange = 0; //time change in seconds
    private final double TIME_CHANGE_HOURS = (double) 1 / 12; //time change in hours

    /**
     * Constructor
     */
    public Room(double area, double temp, String type, int cap, double eff, int built, double over, double setTemp, double howOften, int run, double outside)
    {
        size = area;
        initialTemp = temp;
        furnaceType = type; //furnace type
        furnaceCap = cap;   //furnace capacity
        furnaceEff = eff;   //furnace efficiency
        year = built;       //furnace year
        overTemp = over;    
        desireTemp = setTemp;
        freq = howOften;
        runTime = run;
        outTemp = outside;
        printReport();
        if (type.equalsIgnoreCase("Gas"))
        {
            furn = new Gas(cap, eff, type, built);
        }    
        else if (type.equalsIgnoreCase("Electric"))
        {
            furn = new Electric(cap, eff, type, built);
        }

        control = new Thermostat(setTemp, over);
        int timerFreq = (int) freq * 1000; 
        timer = new Timer(timerFreq, new TimerListener());
        timer.start();  

    }

    /**
     *  Method that instantiates a timer and updates the Thermostat temperature.
     */
    public void changeTemp()
    {
        initialTemp = control.updateTemp(furn, size, initialTemp, outTemp, TIME_CHANGE_HOURS);            
    }

    /**
     *  Print Methods for Console and File printing.
     */
    public void printReport() 
    {
        try
        {    fw = new FileWriter("Heating Status Report.txt", true);
            outputFile = new PrintWriter(fw);
        }
        catch(IOException f)
        {
            System.out.println("File Could Not Be Found!");
        }
        System.out.println();
        System.out.println("======================================================================================");
        System.out.printf("%-10s %-10s%n", "Furnace Type:", furnaceType);
        System.out.printf("%-10s %-10d", "Capacity:", furnaceCap);
        System.out.printf("%-13s %-10.2f", "Efficiency:", furnaceEff);
        System.out.printf("%n%-10s %n%-10s %-10d", "Room Properties:","Year Built: ", year);
        System.out.printf("%-10s %-10.2f", "Temperature:", initialTemp);
        System.out.printf("%-10s %-10.2f", "Area(Sq ft):", size  );
        System.out.printf("%-10s %-10s%n", "SHC = 4.0", "BLC: 1.0");
        System.out.printf("%-10s %n%-10s %-10.2f%n", "Environment Properties:", "Outside Temperature: ", outTemp );
        System.out.printf("%-10s %n%-10s %-10.2f", "Thermostat: ", "Setting:", desireTemp);
        System.out.printf("%-10s %-10.2f%n", "Overheat:", overTemp);
        System.out.printf("%s%n", "Starting Simulation...");
        System.out.printf("%n%-10s %-10s %-10s %-10s %n", "Time","Inside","Outside", "Furnace Status");

        //Writing on a File
        outputFile.println();
        outputFile.println("======================================================================================");
        outputFile.printf("%-10s %-10s%n", "Furnace Type:", furnaceType);
        outputFile.printf("%-10s %-10d", "Capacity:", furnaceCap);
        outputFile.printf("%-13s %-10.2f", "Efficiency:", furnaceEff);
        outputFile.printf("%n%-10s %n%-10s %-10d", "Room Properties:","Year Built: ", year);
        outputFile.printf("%-10s %-10.2f", "Temperature:", initialTemp);
        outputFile.printf("%-10s %-10.2f", "Area(Sq ft):", size  );
        outputFile.printf("%-10s %-10s%n", "SHC = 4.0", "BLC: 1.0");
        outputFile.printf("%-10s %n%-10s %-10.2f%n", "Environment Properties:", "Outside Temperature: ", outTemp );
        outputFile.printf("%-10s %n%-10s %-10.2f", "Thermostat: ", "Setting:", desireTemp);
        outputFile.printf("%-10s %-10.2f%n", "Overheat:", overTemp);
        outputFile.printf("%s%n", "Starting Simulation...");
        outputFile.printf("%n%-10s %-10s %-10s %-10s %n", "Time","Inside","Outside", "Furnace Status");
    }
    /**
     *  Private Inner class for timer events.
     */
    private class TimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (timeChange <= runTime) // controls and updates time change according to delay
            {
                System.out.printf("%-10d %-10.2f %-10.2f %-10s%n",timeChange,initialTemp,outTemp,control.getPilot());
                outputFile.printf("%-10d %-10.2f %-10.2f %-10s%n",timeChange,initialTemp,outTemp,control.getPilot());
                timeChange += 300;
                changeTemp();

            }
            else 
            {   timer.stop();  // stops the timer if the condition is met
                System.out.println();
                System.out.println("======================================================================================");
                outputFile.println();
                outputFile.println("======================================================================================");
                outputFile.close();
            }
        }
    }
}

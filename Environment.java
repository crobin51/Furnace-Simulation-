
/**
 * Write a description of class Enviroment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Environment
{
    private double outside = 10.0;
    private Room house;
    
    /**
    *  Class Constructor
    *  
    */
    public Environment(double temp)
    {
        outside = temp;
    }
    
    /**
    *  Instantiates a room object
    *  
    */
    public void buildRoom(double area, double temp, String type, int cap, double eff, int built, double over, double setTemp, double howOften, int run,  double outside)
    {
        house = new Room(area, temp, type, cap, eff,  built,  over, setTemp, howOften, run, outside);
    }
    
  
}

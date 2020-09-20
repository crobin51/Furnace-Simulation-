
/**
 * Write a description of class InvalidType here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InvalidRun extends Exception
{
   public InvalidRun()
   {
       super("Runtime must be a positive number");
   }
}
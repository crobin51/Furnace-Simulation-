
/**
 * Write a description of class InvalidType here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class InvalidType extends Exception
{
   public InvalidType()
   {
       super("Type must be either\"Gas\" or \"Electirc\".");
   }
}

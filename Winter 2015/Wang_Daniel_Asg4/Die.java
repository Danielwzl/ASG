import java.util.Random;
/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 4
 *  Description: this class is to generate 8-sides dice!
 */
public class Die
{
   private int topValue;
   private Random generateRoll = new Random();
   private final int MAXVAL = 8; 
   
   /* Name: Die
    * parameters: none
    * purpose: to create a constructor of Die class in order to roll the dice
    * return type: none
    * return: none
    */   
   public Die()
   {  
      roll();
   }
   
   /* Name: getTopValue
    * parameters: none
    * purpose: to get thhe face value of the dice being rolled
    * return type: int
    * return: topValue
    */   
   public int getTopValue() 
   {
       return topValue;
   }
   
   /* Name: roll
    * parameters: none
    * purpose: to roll the dice
    * return type: void
    * return: none
    */  
   public void roll()
   {
      topValue = generateRoll.nextInt(MAXVAL) + 1;
   }
}
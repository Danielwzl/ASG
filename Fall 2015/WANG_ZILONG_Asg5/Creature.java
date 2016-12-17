
/**
 * Abstract class Creature - an abstract Creature for the Snarks and Grumpkins 
 * simulation. 
 * 
 * @author Alan Fedoruk
 * @version Nov 2015
 */
public abstract class Creature
{
   int x = 0;
   int y = 0;
   
   /* Move to the specified location */
   public void moveTo(int x, int y) 
   {
       setX(x);
       setY(y);
   }
   
   protected void setX( int x) 
   { 
       this.x = x;
   }
   
   protected void setY( int y) 
   { 
       this.y = y;
   }
   
   /* Return a symbol for printing purposes */
   public String toString() 
   { 
       return " * "; 
   }
   
   /* Calculate and carry out the next move. */
   public abstract void move();
   
   /* If possible, breed. If a new Creature was born, return true. */
   public abstract boolean breed();
   
   
   
}

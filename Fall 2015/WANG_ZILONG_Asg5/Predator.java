
/**
 * Predators in the Snarks and Grumpkin 
 * simulation must implement these behaviours. 
 * 
 * Alan Fedoruk 
 * Nov 2015
 */
public interface Predator
{
    /* If there is a prey creature adjacent, move 
     * there, eat it and return true, 
     * else return false. 
     */
    public boolean eat();
    
    /* Return true if this predator has just 
     * starved to death. 
     */
    public boolean starve();
}

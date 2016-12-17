/**
 * This game is to compute the population of two creatures under the condition of being living together
 * Snark: can move and eat grumpkin, can breed. If it starves for three rounds then, it will die
 * Grumpkin: can move and breed, can be as a food for snark
 * @author Zilong Wang 
 * Last Modified: <11-27-2015> - <adding comments> <Zilong Wang>
 * @version 1.0 
 */
public class Main
{
    /**
     * this is the main function to start game 
     */
    public static void main(String[] args)
    {
        final int SIZE_OF_WORLD = 20;
        new Game(SIZE_OF_WORLD).run();
    }
}


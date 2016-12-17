import java.util.Random;

/**
 * This is an entity class to creat a single snark
 * @author Zilong Wang 
 * Last Modified: <11-27-2015> - <adding comments> <Zilong Wang>
 * @version 1.0 
 */
public class Snark extends AbstractCreature implements Predator 
{
    private final int BREED_ROUND = 8;
    private final int STARVE_ROUND = 3;
    private int starveCount;
    private final String SNARK = " \u0398 ";

    public Snark(Random rng, int y, int x, Game game)
    {
        super(rng, y, x, game);
    }

    /**
     * it is move in snark turn
     * @param <row>
     * @param <col>
     */
    public void myTurn(int row, int col)
    {
        if(!eat()) move();
        breed();
        if(starve()) game.removeCreatureFromSpot(this);
    }

    /**
     * To breed a new snark if this one survives for eight rounds, at the random spot
     * if random spot is not empty, choose the rest of spot surrounding clockwisely
     * @return  <ture if it breeds a new one>
     */
    @Override
    public boolean breed()
    {
        if(surviveCount == BREED_ROUND)
        {
            surviveCount = 0; //set counter to 0 if it breeds a new one
            int[] spot = chooseNewSpot(); //get the random spot that it can breed
            if(spot != null)
            {
                game.generateOneSnark(spot); //breed a new baby
                return true;
            }
            else
            {
                spot = chooseRestSpots();
                if(spot != null)
                {
                    game.generateOneGrumpkin(spot);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * To eat a grumpking
     * @return  <ture if it eats>
     */
    @Override
    public boolean eat()
    {
        int[] foodSpot = seekFood(); //get the random spot has grumpkin to eat
        if(foodSpot != null)
        {
            game.repositonCreature(this, foodSpot); //eat the grumpkin and move to there
            moveTo(foodSpot[1], foodSpot[0]);
            if(starveCount != 0) starveCount = 0; //once it eats something reset it to 0
            surviveCount++; //count the round it is alive 
            return true;
        }
        starveCount++; //if it did not eat, count one round of starving
        return false;
    }

    /**
     * If it starves for three rounds, then it will die
     * @return  <ture if it is starve to die>
     */
    @Override
    public boolean starve()
    {
        if(starveCount == STARVE_ROUND) return true;
        return false;
    }

    /**
     * To seek a spot that has a grumpkin alive, the seeking motion depends on the order of right, bottom, left, up
     * @return  <a coordinate has "y" and "x" that has grumpkin>
     * @return  <null if there is no food nearby>
     */
    private int[] seekFood()
    {
        if(game.hasGrumpkinNear(y, x + 1)) return new int[]{y, x + 1}; //right
        else if(game.hasGrumpkinNear(y + 1, x)) return new int[]{y + 1, x}; //bottom
        else if(game.hasGrumpkinNear(y, x - 1)) return new int[]{y, x - 1}; //left
        else if(game.hasGrumpkinNear(y - 1, x)) return new int[]{y - 1, x}; //up
        return null;
    }

    public String toString()
    {
        return SNARK;
    }
}

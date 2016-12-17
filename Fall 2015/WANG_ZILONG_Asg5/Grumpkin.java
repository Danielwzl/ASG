import java.util.Random;

/**
 * This is an entity class to creat a single grumpkin
 * @author Zilong Wang 
 * Last Modified: <11-27-2015> - <adding comments> <Zilong Wang>
 * @version 1.0 
 */
public class Grumpkin extends AbstractCreature 
{
    private final int BREED_ROUND = 3;
    private final String GRUMPKIN = " \u0394 ";

    public Grumpkin(Random rng, int y, int x, Game game)
    {
        super(rng, y, x, game);
    }

    /**
     * it is move in grumpkin's turn
     * @param <row>
     * @param <col>
     */
    public void myTurn(int row, int col)
    {
        move();
        breed();
    }

    /**
     * To breed a new grumpkin if this one survives for three rounds, at the random spot
     * if random spot is not empty, choose the rest of spot surrounding clockwisely
     * @return <ture if it breeds a new one>
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
                game.generateOneGrumpkin(spot); //breed a new baby
                return true;
            }
            else
            {
                spot = chooseRestSpots(); //clockwisely choosing spots from the random one
                if(spot != null)
                {
                    game.generateOneGrumpkin(spot);
                    return true;
                }
            }
        }
        return false;
    }

    public String toString()
    {
        return GRUMPKIN;
    }
}
import java.util.Random;

/**
 * This is an entity class to create general creature with moving and finding spot to breed function
 * @author Zilong Wang 
 * Last Modified: <11-27-2015> - <adding comments> <Zilong Wang>
 * @version 1.0 
 */
public abstract class AbstractCreature extends Creature
{
    protected int surviveCount, probability; //count if how many steps this creature survives 
    protected boolean flag; //if the creature is moved, then flag will be true;
    protected Random rng;
    protected Game game; //creatures should communicate with the game
    private final int[][] OFFSET = //has {y, x}
    {                             // the y and x this creature is staying will add those change to move left, right, up and down randomly
                       {-1, 0},
                {0,  1},      {1,  0},    
                       {0, -1}   
    }; //put the position clockwisely, not visually by their coordinates
      //index 0,1,2,3, is up, right, down, left respectively
    public AbstractCreature(Random rng, int y, int x, Game game)
    {
        setY(y);
        setX(x);
        this.game = game;
        this.rng = rng;
    }

    /**
     * it is the creature movement
     * @param <row>
     * @param <col>
     */
    protected abstract void myTurn(int row, int col);

    /**
     * to see if this creature is moved
     * @return <true if it is moved>
     */
    protected boolean isMoved()
    {
        return flag;
    }

    /**
     * to lock or unlock the creature, in order to limit their move
     */
    protected void lock(boolean flag)
    {
        this.flag = flag;
    }

    /**
     * this creature can randomly move to the next spot, up, right, down or left
     * to get the coordinate and move to that spot and update the location of this creature
     * to define if the creature is survive or not
     */
    @Override
    public void move()
    {
        int[] newSpot = chooseNewSpot();
        if(newSpot != null) moveTo(newSpot);
        surviveCount++; //count the round it is alive 
    }

    /**
     * Overloading function
     * move creature to new spot
     * @param <spot>
     */
    protected void moveTo(int[] spot)
    {
        game.repositonCreature(spot, this); //move to new spot
        moveTo(spot[1], spot[0]); //update the location of this creature
    } 

    /**
     * creature can choose the random new spot to move or breed
     * @return <new spot>
     * @return <null if new spot is outside of world or not empty>
     */
    protected int[] chooseNewSpot()
    {
        probability = rng.nextInt(4); //it is determining if the new spot is up, down, left or right;
        int newY = y + OFFSET[probability][0],
            newX = x + OFFSET[probability][1];
        //check if new spot is empty and inside the world
        if(game.isSurroundingEmpty(newY, newX)) return new int[]{newY, newX};
        return null;
    }

    /**
     * creature can choose the rest spots to breed by clockwise starting at the first random spot(not vaild)
     * @return <new spot>
     * @return <null if new spot is outside of world or not empty>
     */
    protected int[] chooseRestSpots()
    {
        int newY = 0,
            newX = 0;
        for(int i = 0; i < OFFSET.length - 1; i++) //run 4 times - the one has checked
        {
            if(probability == OFFSET.length - 1) probability = 0;
            else probability++; //if first random reach the end, like a clock, it will check the 0 index...1 and 2
            newY = y + OFFSET[probability][0];
            newX = x + OFFSET[probability][1]; 
            if(game.isSurroundingEmpty(newY, newX)) return new int[]{newY, newX};
        }
        return null;
    }
}
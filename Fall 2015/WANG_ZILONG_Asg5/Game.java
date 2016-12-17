import java.util.Random;

/**
 * Game - the grid for a Snarks and Grumpkins 
 * simulation.
 * @author Alan Fedoruk
 * @author Zilong Wang 
 * Last Modified: <11-27-2015> - <adding comments> <Zilong Wang>
 *                <11-26-2015> - <adding my own method to communicate with entity classes> <Zilong Wang>
 *                
 * @version 2.0 
 */
public class Game
{
    Creature[][] gamegrid;
    int gamesize = 0;
    private Random rng;
    private GamingInterface ui = new GamingInterface(this);
    public static final int TOTAL_CREATURES = 105;
    private final String SPOT = " \u00B7 ";
    private int numberOfSnarks, numberOfGrumpkins;

    public Game(int s)
    {
        gamesize = s;
        gamegrid = new Creature[gamesize][gamesize];
        if(rng == null) rng = new Random();
    }

    /**
     * run the game
     */
    public void run()
    {
        ui.welcome(); //print welcome page
        initialGame(); //load the creatures into the world
        gameLoaded(); //start the game
    }

    /**
     * start the game, load all function
     */
    private void gameLoaded()
    {
        int userChoice = 0;
        do
        {
            ui.showWorld();
            userRequest(userChoice = ui.askUserInput());
        }while((userChoice != -1));
        if(userChoice == -1 || isAllDie()) ui.end();
    }

    /* Clear the gamegrid -- reset to have no creatures. */
    public void clear() 
    {
        for (int i=0; i < gamesize; i++) 
            for (int j=0; j < gamesize; j++) 
                gamegrid[i][j] = null;
    }

    /* Display the grid. You will need to add to this, but this is 
     * the basic idea. 
     */
    public void display() 
    {
        for (int i = 0; i < gamesize; i++) 
        {
            for (int j = 0; j < gamesize; j++) 
            {
                if(gamegrid[i][j] != null) System.out.print(gamegrid[i][j]);
                else System.out.print(SPOT);
            }
            System.out.println();
        }
    }

    /**
     * generate the creatures into the world
     */
    private void initialGame()
    {
        int[] spot = null;
        int countSnark = 0;
        //generate all creatures at the random spot
        for(int i = 0; i < TOTAL_CREATURES; i++)
        {
            spot = findRandomSpot(); 
            if(countSnark != 5) 
            {
                generateOneSnark(spot);
                countSnark++;
            }
            else generateOneGrumpkin(spot);
        }
    }

    /**
     * generate a new grumpkin on the map
     * @param <spot>
     */
    public void generateOneGrumpkin(int[] spot)
    {
        AbstractCreature grumpkin = new Grumpkin(rng, spot[0], spot[1], this);
        grumpkin.lock(true); //new creature should be locked 
        gamegrid[spot[0]][spot[1]] = grumpkin;
        numberOfGrumpkins++;
    }

    /**
     * generate a new snark on the map
     * @param <spot>
     */
    public void generateOneSnark(int[] spot)
    {
        AbstractCreature snark = new Snark(rng, spot[0], spot[1], this);
        snark.lock(true); //new creature should be locked
        gamegrid[spot[0]][spot[1]] = snark;
        numberOfSnarks++;
    }

    /**
     * when creature is moved or ate something, it should go to new spot in the world
     * when move, there are two steps:
     * 1.move to new spot in the world
     * 2.update the creature's location
     * in this case, to move to new spot should happen before update creaure's location
     * because system will know the old one and set the old spot to null
     * @param <spot: new spot>
     * @param <creature>
     */
    public void repositonCreature(int[] spot, Creature creature)
    {
        gamegrid[spot[0]][spot[1]] = creature;
        gamegrid[creature.y][creature.x] = null; //oringinal y and x, update after this
    }

    /**
     * Overloading function
     * to remove previous spot of this snark after it ate, and decrease the population of grumpkins
     * @param <creature>
     * @param <spot>
     */
    public void repositonCreature(Creature creature, int[] spot)
    {
        repositonCreature(spot, creature);
        numberOfGrumpkins--;
    }

    /**
     * remove the creature from the spot from its current location
     * @param <creature>
     */
    public void removeCreatureFromSpot(Creature creature)
    {
        gamegrid[creature.y][creature.x] = null;
        if(creature instanceof Snark) numberOfSnarks--;
        else numberOfGrumpkins--;
    }

    /**
     * pick a randomly spot which is empty in the world
     * @return <this spot>
     */
    private int[] findRandomSpot()
    {
        int y = 0, x = 0;
        do{
            y = rng.nextInt(gamesize);
            x = rng.nextInt(gamesize);
        }while(gamegrid[y][x] != null);
        return new int[]{y, x};
    }

    /**
     * get the user input, default is 1, let the game go to next round
     * @param <steps>
     */
    private void userRequest(int steps) 
    {
        for(int i = 0; i < steps; i++) 
            nextRound(); 
    }

    /**
     * after user input the steps, to game will fo the next round by computing the creature's movement
     */
    private void nextRound()
    {
        unlockAllCreatures();
        snarksRound();
        grumpkinsRound();
    }

    /**
     * find all snarks, and let them move first
     */
    private void snarksRound()
    {
        AbstractCreature creature = null;
        for(int i = 0; i < gamesize; i++)
            for(int j = 0; j < gamesize; j++)
                if(gamegrid[i][j] != null) 
                    if(!(creature = (AbstractCreature)gamegrid[i][j]).isMoved()) //check if the creature is moved
                        if(creature instanceof Snark) creatureMotion(creature , i, j);
    }

    /**
     * find all grumpkins, and let them move
     */
    private void grumpkinsRound()
    {
        AbstractCreature creature = null;
        for(int i = 0; i < gamesize; i++)
            for(int j = 0; j < gamesize; j++)
                if(gamegrid[i][j] != null) 
                    if(!(creature = (AbstractCreature)gamegrid[i][j]).isMoved()) //check if the creature is moved
                        if(creature instanceof Grumpkin) creatureMotion(creature, i, j);               
    }

    /**
     * get movement request for creatures, lock them after their turn
     * @param <creature>
     * @param <row>
     * @param <col>
     */
    private void creatureMotion(AbstractCreature creature, int row, int col)
    {
        creature.myTurn(row, col);
        if(gamegrid[creature.y][creature.x] != null) creature.lock(true);
    }

    /**
     * after this round finished, all creatures should be unlocked for the next round
     * unlock function is to make sure that a creature move or eat and go to the right or down spot,
     * the system will let them move agian when the loop goes to those spot
     */
    private void unlockAllCreatures()
    {
        for(Creature[] creatures: gamegrid)
            for(Creature creature: creatures)
                if(creature != null) ((AbstractCreature)creature).lock(false);
    }

    /**
     * get the y and x, make sure the spot is valid
     * @param <new spot>
     * @return <true if the spot is not outside of the world or occupied>
     */
    public boolean isSurroundingEmpty(int...newSpot)
    {
        return isInBound(newSpot[0]) && isInBound(newSpot[1]) 
        && gamegrid[newSpot[0]][newSpot[1]] == null;
    }

    /**
     * get the y and x, make sure there is grumpkin to eat
     * @param <new spot>
     * @return <true if the spot is not outside of the world or empty>
     */
    public boolean hasGrumpkinNear(int...newSpot)
    {
        return isInBound(newSpot[0]) && isInBound(newSpot[1]) 
        && gamegrid[newSpot[0]][newSpot[1]] instanceof Grumpkin;
    }

    /**
     * check if the new spot is outside of world
     * @param <oneAxis: y or x axis>
     * @return <true if the spot is inside of world>
     */
    private boolean isInBound(int oneAxis)
    {
        return oneAxis <= gamesize - 1 && oneAxis >= 0;
    }

    /**
     * to make sure if there is at least one creature alive
     * @return <true if all creatures are dead>
     */
    private boolean isAllDie()
    {
        if((numberOfGrumpkins + numberOfSnarks) == 0) return true;
        return false;
    }

    /**
     * collect all the population number and step
     * interface class will use it present to user
     * @return <an array of those information>
     */
    public int[] getCount()
    {
        return new int[]{numberOfSnarks, numberOfGrumpkins, numberOfGrumpkins + numberOfSnarks};
    }
}
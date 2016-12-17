import java.util.Scanner;

/**
 * This is interface for user, for doing display job
 * @author Zilong Wang 
 * Last Modified: <11-27-2015> - <adding comments> <Zilong Wang>
 * @version 1.0 
 */
public class GamingInterface
{
    private Scanner scan; 
    private Game game;
    private final String DECORATION = "              ";
    private int steps;

    public GamingInterface(Game game)
    {
        this.game = game; 
        if(scan == null) scan = new Scanner(System.in); 
    }

    /**
     * To print welcome page
     */
    public void welcome()
    {
        System.out.println(" ===============Welcome==============");
        System.out.println("|                                    |");
        System.out.println("|                                    |");
        System.out.println("|        Snarks and Grumpkins        |");
        System.out.println("|            \u0398  vs  \u0394                |");
        System.out.println("|                                    |");
        System.out.println(" ==================================== ");
        pause();
    }

    /**
     * to print ending
     */
    public void end()
    {
        game.clear();
        showWorld();
        System.out.println(DECORATION + "  <=====Thank you=====>");
    }

    /**
     * to display the world
     */
    public void showWorld()
    {
        flush();
        game.display();
        showPopulation();
    }

    /**
     * to show the population page
     */
    private void showPopulation()
    {
        int[] counts = game.getCount();
        System.out.println();
        System.out.println(DECORATION + "      |===Population===|      ");
        System.out.println(DECORATION + "+----------------------------+");
        System.out.println(DECORATION + "| Grumpkins | Snarks | Total |");
        System.out.println(DECORATION + "+----------------------------+");
        System.out.format(DECORATION + "|%7d    |%5d   |%5d  |\n", counts[1], counts[0], counts[2]);
        System.out.println(DECORATION + "+----------------------------+");
        System.out.format(DECORATION + "|Steps:%9d             |\n", steps);
        System.out.println(DECORATION + "+----------------------------+");
        System.out.println("+-------------+");
        System.out.println("| Snark: \u0398    | \n| Grumpkin: \u0394 |");
        System.out.println("+-------------+");
    }

    /**
     * to ask user enter the steps
     * @return <steps that user entered>
     * @return <default step is 1 if user enter something wrong>
     */
    public int askUserInput()
    {
        System.out.print("Enter the steps (-1 to quit): ");
        try
        {
            String userChoice = scan.nextLine();
            int number = Integer.valueOf(userChoice);
            if(userChoice.isEmpty() || number < -1) 
            {
                steps++;
                return 1;
            }
            else if(number != -1) steps += number; //if user enter -1, game ends
            return number; 
        }
        catch(Exception e)
        {
            steps++; //if user enter other thing instead of integer, using default
            return 1;
        }
    }

    /**
     * to pause the game
     */
    private void pause()
    {
        System.out.println("<Game loaded! Hit enter to Start Game>");
        scan.nextLine();
    }

    /**
     * to flush the screen
     */
    private void flush()
    {
        System.out.print("\f");
    }
}

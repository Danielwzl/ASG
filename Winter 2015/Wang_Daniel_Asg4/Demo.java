/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 4
 *  Description: Matching Gladiatord and Trolls and display the combat result!
 *  Firstly, all of gladiators will attack Raca, and then Raca will attact Tetraies after it is damaged. If Tetraies dies, he will stop attacking and be attacted. Raca will change his target to Verus.Everyone could not 
 *  be damaged or make damage after he dies. This demo indicates all of the battle situation between these two races.
 */
public class Demo
{       
    //creat the instances of each creature and print them out on the teminal window
    private static Troll atalai = new Troll("Atalai", 20, 10); 
    private static Troll raca = new Troll("Raca", 135, 15);    
    private static Troll ekon = new Troll("Ekon", 24, 8);
    private static Troll seji = new Troll("Seji", 12, 20);
    private static Gladiator priscus = new Gladiator("Priscus", 20, 10, true);
    private static Gladiator verus = new Gladiator("Verus", 10, 20, true);
    private static Gladiator tetraites = new Gladiator("Tetraites", 20, 20, false);
    
    /* Name: battleResult 
     * parameters: null
     * purpose: to calculate multiply times of battle of this two races
     * return: none
     * return type: void
     */   
    public static void battleResult()
     {
        //print the welcome paragraph
        welcome();
         
        //generate multiply times of battles
        for(int times = 0; times < 10; times++)
        {
            {  
               gladiatorsAtkRaca();
               RacaAtkGladiators();
            }
        }
        
        System.out.println();
        System.out.println("Battle is now complete"); 
        System.out.println("------------------------");
        
        //get the the result of last time of the loop
        String result1 = atalai.toString(),
               result4 = raca.toString(),
               result2 = ekon.toString(),
               result3 = seji.toString(),
               result5 = priscus.toString(),
               result6 = verus.toString(),
               result7 = tetraites.toString();
    }
    
    /* Name: RacaVs3　
     * parameters: null
     * purpose: 3 gladiators attack Raca
     * return: none
     * return type: void
     */   
    private static void gladiatorsAtkRaca()
    {   
        //initialize the current health of this troll, if at the start of the battle the health will be max health
        int currentHp = raca.getCurrentHealth(),
        
        //to make galdiators to generate the damage and add them up
             damage1 = tetraites.dealtDamage(currentHp),
             damage2 = priscus.dealtDamage(currentHp),
             damage3 = verus.dealtDamage(currentHp),
              damage = damage1 + damage2 + damage3;
        
        //to pass the value of the gladiators dealt to the troll and calculator the damage that the troll could get, include the step of regenerating its health
        raca.setReciveDamage(damage);
        
        //to calculate the current health after damage 
        raca.setCurrentHealth(currentHp);
        currentHp = raca.getCurrentHealth();
    }
    
    /* Name: RacaVsTetraites　
     * parameters: null
     * purpose: to generate the battle between designated gladiators and trolls. 
     * Raca attacks tetraites, and then attack verus after pervious one died.
     * return: none
     * return type: void
     */   
    private static void RacaAtkGladiators()
    {
        //initialize the current health of this gladiator, if at the start of the battle the health will be max health
        int currentHp1 = tetraites.getCurrentHealth(),
            currentHp2 = verus.getCurrentHealth();
       
        if(currentHp1 > 0)
            {
             //to make troll to generate the damage
             int damage = raca.dealtDamage(currentHp1);
             
             //to pass the value of the troll dealt to the gladiator and calculator the damage that the gladiator could get
             tetraites.setReciveDamage(damage);
             
             //to calculate the current health after damage 
             tetraites.setCurrentHealth(currentHp1);
             currentHp1 = tetraites.getCurrentHealth();
            }
        else
            {
             //after one died, change target to next one! 

             //to make troll to generate the damage
             int damage = raca.dealtDamage(currentHp2);

             //to pass the value of the troll dealt to the gladiator and calculator the damage that the gladiator could get
             verus.setReciveDamage(damage);
  
             //to calculate the current health after damage 
             verus.setCurrentHealth(currentHp2);
             currentHp2 = verus.getCurrentHealth();
            }
    }
    
    /* Name: welcome()
     * parameters: null
     * purpose: to print welcome paragraph
     * return: none
     * return type: void
     */   
    private static void welcome()
    {
        System.out.println();
        System.out.println("Good people, loyal citizens of the Empire.");
        System.out.println("Today our great Emperor has brought for you a great wonder, ");
        System.out.println("an epic battle sure to inspire your imagination and capture your senses. ");
        System.out.println("Cheer for your great champions as they battle some horrible trolls most foul. ");
        System.out.println("Raise your voices and let the battle commence!"); 
        System.out.println("------------------------");
    }
}

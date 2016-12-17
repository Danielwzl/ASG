import java.util.Random;
/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 4
 *  Description: this class is to Creat Gladitors include their attributes and define the way the fight!
 */
public class Gladiator
{   
    private String character;
    private int currentHealth, maxHp, str, totalDmg;
    private boolean shield;
    private static int dice1, dice2, dice3;
    
    /* Name: Gladiator
     * parameters: name, health, attackStrength, armor
     * purpose: to create a constructor of gladiator class 
     * return type: none
     * return: none
     */   
    public Gladiator(String name, int health, int attackStrength, boolean armor)
    {
        //to make name user changed in demo class to be only first letter capital.
        character = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        shield = armor;
        str = attackStrength;
        currentHealth = health;
        maxHp = health;
        toString();
    }
    
    /* Name: dealtDamage 
     * parameters: opponentHealth
     * purpose: to deal damage to troll
     * return type: int
     * return: damage
     */   
    public int dealtDamage(int opponentHealth)
    {
        Random rad = new Random();
        
        //generate three die        
        Die die1 = new Die();
        Die die2 = new Die();
        Die die3 = new Die();
        
        dice1 = die1.getTopValue();
        dice2 = die2.getTopValue();
        dice3 = die3.getTopValue();
        
        int damage = 0;
        
        //it is the rate that affecting gladiator's damage
        double rate = (rad.nextInt(50) + 1) / 100.0;
        
        //if and only if both troll and gladiator are ailve, attack will take place; the constraints will follow what the conditions below.
        if(currentHealth > 0 && opponentHealth >0)
        {
            if(dice1 == dice2 && dice2 == dice3)
            {
                damage = (int)(opponentHealth * 0.3);
            }
            else if(dice1 != dice2 && dice1 != dice3)
            {
                damage = (int)(str * rate);
            }
            else
            {
                damage = 0;
            }
        }
        
        return damage;
    }
    
    /* Name: setReciveDamage 
     * parameters: basicDamge
     * purpose: to set the damage that troll deals, and pass the value to the function receiveDamage to get the damage taken
     * return type: void
     * return: none
     */   
    public void setReciveDamage(int basicDamge)
    {
        receiveDamage(basicDamge);
    }
    
    /* Name: getReciveDamage 
     * parameters: none
     * purpose: to get the totalDmg from setter
     * return type: int
     * return: toalDmg
     */   
    public int getReciveDamage()
    {
        return totalDmg;
    }
      
    /* Name: receiveDamage 
     * parameters: amount(the damage troll dealt)
     * purpose: to get damage from troll
     * return type: void
     * return: none
     */   
    private void receiveDamage(int amount)
    {
        //different damage taken depending on if gladiator is shield or 
        if(shield == true)
        {
            totalDmg = (int)(amount * 0.3);
        }
        else
        {
            totalDmg = (int)(amount * 0.5);
        }
    }
    
    /* Name: setCurrentHealth 
     * parameters: nowHp
     * purpose: to set noHp to current health and put it into currentHp function to converte negative value to 0
     * return type: void
     * return: none
     */   
    public void setCurrentHealth(int nowHp)
    {
        currentHealth = nowHp;
        currentHp();
    }
    
    /* Name: getCurrentHealth 
     * parameters: none
     * purpose: to get the current health from setter
     * return type: int
     * return: currentHealth
     */   
    public int getCurrentHealth()
    {
        return currentHealth;
    }
    
    /* Name: currentHp 
     * parameter: none
     * purpose: to determine the hp after damage and make health >= 0
     * return type: void
     * return: none
     */   
    private void currentHp()     
    {
       //get the hp after taking damage
       int hp = currentHealth - totalDmg;
       
       //hp never is below 0
       if(hp < 0)
       {
          hp = 0;
       }
       
       //pass the hp value tp instance variable
       currentHealth = hp;
    }
    
    /* Name: isAlive 
     * parameter: none
     * purpose: to define if gladiator is alive or not
     * return type: boolean
     * return: alive
     */   
    private boolean isAlive()
    {  
        boolean alive = true;
        
        // if current health is 0 ,it means gladiator is not alive. another condition will be alive;
        if(currentHealth <= 0)
        {
            alive = false;
        }

        return alive;
    }
    
    /* Name: toString 
     * parameter: none
     * purpose: to turn every attribute that gladiator has to string type value
     * return type: String
     * return: status
     */   
    public String toString()
    {
        //converte all values to be string type
        String currentHp = Integer.toString(currentHealth),
               maxHealth = Integer.toString(maxHp),
                strength = Integer.toString(str),
                   armor = Boolean.toString(shield),
                  status = Boolean.toString(isAlive());
       
        //convert the stauts of gladiator from ture or false to be alive or dead    
        if(status == "true")
        {
            status = "alive";
        }
        else
        {
            status = "dead";
        }
        
        //creat the format that show the attribute of gladiator
        System.out.println("(" + character + ", " + "Gladiator" +  ", " + status + ", " + maxHp + " " + currentHp + " " + strength + ", " + armor + ")");
     
        return status;
    }
}
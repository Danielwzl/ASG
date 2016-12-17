/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 4
 *  Description: this class is to Creat Trolls include their attributes and define the way the fight!
 */
public class Troll
{   
    //creat the attributes that troll has
    private String character;
    private int currentHealth, maxHp, str, totalDmg;
    private boolean shield = false;
    private int dice1,dice2,dice3;
    
    /* Name: Troll
     * parameters: name, health, attackStrength
     * purpose: to create a constructor of troll class 
     * return type: none
     * return: none
     */   
    public Troll(String name, int health, int attackStrength)
    {   
        //to make name user changed in demo class to be only first letter capital.
        character = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
        maxHp = health;
        str = attackStrength;
        currentHealth = health;
        toString();
    }
    
    /* Name: dealtDamage 
     * parameters: opponentHealth
     * purpose: to deal damage to gladiator
     * return type: int
     * return: damage
     */   
    public int dealtDamage(int opponentHealth)
    {
        //generate two die
        Die die1 = new Die();
        Die die2 = new Die();
        
        dice1 = die1.getTopValue();
        dice2 = die2.getTopValue();
        
        //get the sum of this two die, initialize the damage
        int sum = dice1 + dice2,
            damage = 0;
        
        //if and only if both troll and gladiator are ailve, attack will take place; the constraints will follow what the conditions below.
        if(currentHealth > 0 && opponentHealth >0)
        {
            if(sum == 7 || sum == 11)
            {
                damage = 0;
            }
            else if(sum == 10)
            {    
                damage = opponentHealth;
            }
            else
            {
                damage = str / 2;
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
     * parameters: amount(the damage gladiator dealt)
     * purpose: to get damage from gladiator
     * return type: void
     * return: none
     */   
    private void receiveDamage(int amount)
    {  
        //generate three die
        Die die1 = new Die();
        Die die2 = new Die();
        Die die3 = new Die();
        
        dice1 = die1.getTopValue();
        dice2 = die2.getTopValue();
        dice3 = die3.getTopValue();
        
        //if and only if gladiator makes damage to troll, troll will add the biggest dice number. otherwise there is no damage troll takes
        if (amount > 0)
        {
            if(dice1 >= dice2 && dice1 >= dice3)
            {
                totalDmg = amount + dice1;
            }
            else if(dice2 >= dice3)
            {
                totalDmg = amount + dice2;
            }
            else
            {
                totalDmg = amount + dice3;
            }
        }
        else
        {
            totalDmg = 0;
        }
    }
    
    /* Name: setCurrentHealth 
     * parameters: nowHp
     * purpose: to set noHp to current health and put it into regeneratre function to get health after healing  
     * return type: void
     * return: none
     */   
    public void setCurrentHealth(int nowHp)
    {
       currentHealth = nowHp;
       regenerate();
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
    
    /* Name: regenerate 
     * parameter: none
     * purpose: to let troll regenerate health after being attacked and make health >= 0
     * return type: void
     * return: none
     */   
    private void regenerate()
    {  
       //get the hp after taking damage
       int hp = currentHealth - totalDmg;
     
       //if and only if troll is damaged this turn, it will recover 10% of hp following constraints
       if(hp >= 0 && totalDmg > 0)
       {
           //HP never exceed its max HP
           if(hp > maxHp * 0.9)
           {
               hp = maxHp; 
           }
            else if(hp <= maxHp * 0.9)
           {
               hp = (int)(hp * 1.1);
           } 
       }
       //if hp < 0 after damage, the hp will be 0, else the value of hp will be as same as before when no damage receives
       else if(hp < 0) 
       {
        hp = 0;
       }
      
       //pass the hp value tp instance variable
       currentHealth = hp;
    }
    
    /* Name: isAlive 
     * parameter: none
     * purpose: to define if troll is alive or not
     * return type: boolean
     * return: alive
     */   
    private boolean isAlive()
    {   boolean alive = true;
        
        // if current health is 0 ,it means troll is not alive. another condition will be alive;
        if(currentHealth ==  0)
        {
            alive =  false;
        }
        
        return alive;
    }
    
    /* Name: toString 
     * parameter: none
     * purpose: to turn every attribute that troll has to string type value
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
                  
        //convert the stauts of troll from ture or false to be alive or dead         
        if(status == "true")
        {
            status = "alive";
        }
        else
        {
            status = "dead";
        }
        
        //creat the format that show the attribute of troll
        System.out.println("(" + character + ", " + "Troll" +  ", " + status + ", " + maxHp + " " + currentHp + " " + strength + ", " + armor + ")");
        
        return status;
    }
}
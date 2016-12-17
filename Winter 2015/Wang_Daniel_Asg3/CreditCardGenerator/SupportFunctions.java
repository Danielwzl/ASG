import java.util.*;
import java.text.*;
/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 3
 *  Description: Generate the Credit card information
 */
public class SupportFunctions
{   /* Name: generateCardNo　
     * parameters: expiryMonth, firstNameInitial, surnameInitial.
     * purpose: randomly generates a 16-digit rewards card number, subject to constraints (1) through (4)
     * return type: String
     * return: 4 groups of generated numbers(eg.9999 9999 9999 9999)
     */   
    public String generateCardNo(int expiryMonth, char firstNameInitial, char surnameInitial)
    {   
       // initialize constants
       final int HUNDREDS_DIGITS = 100;
       final int THOUSANDS_DIGITS = 1000;
       final int TEN_THOUSANDS_DIGITS = 10000;
       
       Random generator = new Random();  
       
       //generate random numbers of each groups besides constraints(1) to (4)
       int firstGroup = generator.nextInt(HUNDREDS_DIGITS),
           secondGroup = generator.nextInt(TEN_THOUSANDS_DIGITS),
           thirdGroup = generator.nextInt(HUNDREDS_DIGITS),
           fouthGroup = generator.nextInt(THOUSANDS_DIGITS),
       //constraints(1): generate the number between 2 to 9 inclusive.
           firstNum = generator.nextInt(8) + 2,
       //constraints(2): generate the number related to reverse of month 
           verifyNum = expiryMonth % 10 * 10 + expiryMonth / 10,
       //constraints(3): generate the number related to sum of names' initials (mod10)
           nameInitialNum = (intialNumber(firstNameInitial) + intialNumber(surnameInitial)) % 10,
       //constraints(4): generate the number related to the sum of leftmost digit of each group (mod10)
           rightMostNum = checkSum(firstNum,secondGroup,thirdGroup,fouthGroup);
       
       //Arrange the digits into the right position in each group
       DecimalFormat fmt2 = new DecimalFormat("00"),
                     fmt3 = new DecimalFormat("000"),
                     fmt4 = new DecimalFormat("0000");
       
       String group1 = firstNum + fmt2.format(firstGroup) + nameInitialNum,
              group2 = fmt4.format(secondGroup),
              group3 = fmt2.format(thirdGroup) + fmt2.format(verifyNum),
              group4 = fmt3.format(fouthGroup) + rightMostNum;
        
       //put the 4 groups together with spaces, it is card number 
       return group1 + " " + group2 + " " + group3 + " " + group4;
    } 
    
    /* Name: formatName　
     * parameters: name
     * purpose: convert a name from input to a formatted name 
     * return type: String
     * return: name (eg. bilBo to Bilbo)
     */   
    public String formatName(String name)
    {   
       //all name in lowercase except initial
       name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
       
       return name;
    }
    
    /* Name: expiryTime　
     * parameters: expiryDate
     * purpose: get next year
     * return type: Calendar
     * return: expiryDate (eg 2015 + 1 = 2016)
     */
    public GregorianCalendar expiryTime()
    {
       GregorianCalendar date = new GregorianCalendar();
       //current year + 1
       date.add(Calendar.YEAR, + 1); 
       
       return date;
    }
    
    /* Name: initialNumber　
     * parameters: nameInitial
     * purpose: turn letter into Ascii number and get a digit of credit card  
     * return type: int
     * return: number
     */
    private int intialNumber(char nameInitial)
    {
       int number = nameInitial - 'A' + 1;
        
       return number;
    }
    
    /* Name: checkSum
     * parameters: oneDigit thousandsDigit tensDigit hundredsDigit
     * purpose: test check sum from exsiting io.txt results
     * return type: int
     * return: number
     */
    public int checkSum(int oneDigit, int thousandsDigit, int tensDigit, int hundredsDigit)
    {
        return (oneDigit + thousandsDigit / 1000 + tensDigit / 10 + hundredsDigit / 100) % 10;
    }
}
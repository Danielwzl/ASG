import java.util.*;
import java.text.*;
 /**
  * Name: Zilong Wang   
  * Instructor: Namrata Khemka-Dolan 
  * Course: COMP1501       
  * Assignment#: 3
  * Description: Creat an user interface to input their name, and then get the credit card infomations 
  */
public class CreditCardGenerator
{
    public static void generateCard ()
    {
        // INPUT SECTION: prompt for and read input from user here 
        Scanner input = new Scanner(System.in);
        
        System.out.println();
        System.out.print("Enter cardholder name: ");

        String providedGivenName = input.next(),
               providedSurname = input.next();
        //--------------------------------------------------------------------------------------------------------
        // get the formatted name that user input from main class function (formatName)
        SupportFunctions support = new SupportFunctions();
        
        String formattedGivenName = support.formatName(providedGivenName),
               formattedSurname = support.formatName(providedSurname);
        
        //get initial letters of last, first names and month to put into the function calling from another class, generating a credit card number 
        char initialFirstName = formattedGivenName.charAt(0),
             initialLastName = formattedSurname.charAt(0);
        
        GregorianCalendar date = support.expiryTime();

        int expiryMonth = date.get(Calendar.MONTH) + 1;  
        
        SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/yy"); 
        
        String expiryDate = dateFormatter.format(date.getTime()),
        //get the Card number and formatted expiry date from another class
               cardNo = support.generateCardNo(expiryMonth, initialFirstName, initialLastName);
        //-------------------------------------------------------------------------------------------------------------------
        //user interface to print all of information 
        System.out.println("\nInformation for printed credit card:\n");
        System.out.println("Cardholder\t" + formattedGivenName + " " + formattedSurname);
        System.out.println("Card No.\t" + cardNo);
        System.out.println("Expiry Date\t" + expiryDate);
    }
}
import java.io.IOException;
import java.util.Scanner;

/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 6
 *  Description: this class is the user interface for choosing the functions and printing the new client on the screen
 */
public class A6 
{
    final static String FILE_NAME = "clients.txt";       // name of file with client data

    /* Name: main
     * parameters: args
     * purpose: input the number option to run the function
     * return type: void
     * return: none
     */   
    public static void main(String[] args) throws IOException
    {   
        Scanner kb = new Scanner(System.in);
        MatchMaker dateMatcher; 
        String choice;     // the user input for choice from the menu presented
        char choiceNumber;     // the letter representing user choice from the menu presented

        dateMatcher = new MatchMaker(FILE_NAME);

        do
        {
            showMenu();
            System.out.print ("Enter number of choice: ");
            choice = kb.nextLine();     // read in entire line in case of typo, and take off first character
            choiceNumber = choice.charAt(0);
            switch (choiceNumber)
            {
                case '1':
                System.out.println ("\nAll clients:");
                dateMatcher.printClients();
                break;

                case '2':
                System.out.println ("\nCurrent women:");
                dateMatcher.printClientsGender("F");
                break;

                case '3':
                System.out.println ("\nCurrent men:");
                dateMatcher.printClientsGender("M");
                break;

                case '4':
                System.out.println ("\nCurrent matches:");
                dateMatcher.printMatches();
                break; 

                case '5':
                System.out.println ("\nUnmatched clients:");
                dateMatcher.printUnmatchedClients();
                break;

                case '6': 
                System.out.println ("\nUnmatched Women:");
                dateMatcher.printUnmatchedClients("F");
                break;

                case '7':
                System.out.println ("\nUnmatched Men:");
                dateMatcher.printUnmatchedClients("M");
                break;

                case '8':
                addClient(kb, dateMatcher);    // ask user for data, then add client and try to match
                break;

                case '9': System.out.println("--------- Program ends ---------");
                break;
                default: System.out.println("Unknown menu option *** try again");
            }
        } while (choiceNumber != '9');
    }

    /* Name: showMenu
     * parameters: none
     * purpose: input the number option to run the function
     * return type: void
     * return: none
     */   
    public static void showMenu()
    {
        System.out.println("\nMENU:");
        System.out.println("1) Display details of all clients");
        System.out.println("2) Display details of all women");
        System.out.println("3) Display details of all men");
        System.out.println("4) Display all matches");
        System.out.println("5) Display details of all unmatched clients");
        System.out.println("6) Display details of all unmatched women");
        System.out.println("7) Display details of all unmatched men");
        System.out.println("8) Add client");

        System.out.println("9) Exit");
    }

    /* Name: addClient
     * parameters: none
     * purpose: get the new client from DateHelperSystem class and print it on the screen
     * return type: void
     * return: none
     */   
    public static void addClient(Scanner kb, MatchMaker dateMatcher)
    {
        Client newbie;
        newbie = DatingSystemHelper.addClient(kb);
        System.out.println(dateMatcher.addClient(newbie));
    }
}
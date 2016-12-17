import java.util.*;
import java.io.File;
import java.io.IOException;

/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 6
 *  Description: this class is to read and add clients from the txt file and also for inputing new client information
 *  and make match for clients
 */
public class DatingSystemHelper
{
    /* Name: readResults
     * parameters: fileName, allClients
     * purpose: read the clients information from TXT file and add them to the clients list
     * return type: void
     * return: none
     */   
    public static void readResults(String fileName, ArrayList<Client> allClients) throws IOException
    {
        File file = new File(fileName);
        Scanner scan = new Scanner(file);

        while(scan.hasNext())
        {
            String name = scan.nextLine().trim(), //read the name of clients from txt
            gender = scan.next(), //read the gender of them
            sought = scan.next(); //read the gender sought of them

            Client client = new Client(name, gender, sought);

            int numberOfInterests = scan.nextInt(); //read how many interests of each client

            for(int i = 0; i < numberOfInterests; i++ )  
            {
                client.addInterest(scan.next()); //add interests to client class in order to store them into array list and for sake of printing
            }

            client.setMyMatchIndex(scan.nextInt()); //pass match index of clients to Client class

            scan.nextLine(); //clear the line for next time read

            allClients.add(client); //add client to array list
        }
    }

    /* Name: addClient
     * parameters: kb
     * purpose: use client input to add the new client information to the clients list
     * return type: Client
     * return: newbie
     */   
    public static Client addClient(Scanner kb)
    {
        int numberOfInterests = 0;

        String interest = "";

        //input client information, name, gender, and gender sought
        kb = new Scanner(System.in);
        System.out.print("enter client firstName: "); 
        String firstName = kb.next().toUpperCase();

        System.out.print("enter client lastName: ");
        String lastName = kb.next().toUpperCase();   

        System.out.print("enter client gender (M/F): ");
        String gender = kb.next().toUpperCase();

        //when the gender input is not M or F, let client try again
        while(!(gender.equals("M") || gender.equals("F")))
        {
            System.out.print("invalid input! enter client gender (M/F): ");
            gender = kb.next().toUpperCase();
        }

        System.out.print("enter client gender sought (M/F/E): ");
        String sought = kb.next().toUpperCase();

        //when the gender sought input is not M or F or E, let client try again
        while(!(sought.equals("M") || sought.equals("F") || sought.equals("E")))
        {
            System.out.print("invalid input! enter client gender (M/F): ");
            sought = kb.next().toUpperCase();
        }

        Client newbie = new Client(firstName + " " + lastName, gender, sought);

        do{   
            System.out.print("Do you have any interest? (y/n)");

            //if input n, then end the whole part
            //if input y, then enter interests and add into its list
            if(kb.next().equalsIgnoreCase("y"))
            {
                System.out.print("Please enter your interest: ");
                newbie.addInterest(kb.next().toUpperCase());
            }
            else
            {
                break;
            }

            numberOfInterests++;
        }while(numberOfInterests < 10); //enter more than 10 interest will stop this part

        newbie.setMyMatchIndex(-1); //new client is not match so index of matching is -1

        return newbie;
    }

    /* Name: makeMatch
     * parameters: clientToMatch, allClients
     * purpose: doing match section for two clients
     * return type: String
     * return: result
     */   
    public static String makeMatch (Client clientToMatch, ArrayList<Client> allClients)
    {
        String result;
        boolean matched = false;
        boolean similarGenderRequest;
        String genderOfClientToMatch;
        String genderSoughtForClientToMatch;
        Client otherClient = null;
        int clientToMatchIndex = allClients.indexOf(clientToMatch);
        int matchIndex = -1;

        if (clientToMatch.getMyMatchIndex() == -1)
        {
            genderOfClientToMatch = clientToMatch.getGender(); 
            genderSoughtForClientToMatch = clientToMatch.getGenderSought();

            for (int i = 0; i < allClients.size() && !matched; i += 1)
            {
                otherClient = allClients.get(i);
                similarGenderRequest = (otherClient.isGenderSought(genderOfClientToMatch)
                    || otherClient.isGenderSought("E"))//otherClient.getGenderSought().equals("E");
                && ( genderSoughtForClientToMatch.equals("E") 
                    || (otherClient.isGender(genderSoughtForClientToMatch)));
                if (otherClient.getMyMatchIndex() == -1 && i != clientToMatchIndex && similarGenderRequest 
                && clientToMatch.numMatchingInterests(otherClient) >= 3 )
                {
                    matched = true;
                    matchIndex = i;
                }
            }

            if (matched)
            {
                clientToMatch.setMyMatchIndex(matchIndex);
                otherClient.setMyMatchIndex(clientToMatchIndex);
                result = clientToMatch.getName() + " is matched with " + otherClient.getName();
            }
            else
                result = "No match found";
        }
        else
            result = "Already matched - request ignored";

        return result;     
    }
}
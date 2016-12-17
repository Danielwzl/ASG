import java.util.*;
import java.io.File;
import java.io.IOException;

/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 6
 *  Description: this class is to print clients from user's request and add new client into clients list
 */
public class MatchMaker
{
    private int numOfClients;
    private Client client;
    private File file;
    private ArrayList<Client> allClients = new ArrayList<Client>();

    /* Name: MatchMaker
     * parameters: fileName
     * purpose: get the fileName of gender passed in constructor and pass it to DateHelperSystem class
     * return type: none
     * return: none
     */   
    public MatchMaker(String fileName) throws IOException
    {   
        DatingSystemHelper.readResults(fileName, allClients);
    }

    /* Name: printClients
     * parameters: none
     * purpose: print all clients information from clients list
     * return type: void
     * return: none
     */   
    public void printClients()
    {       
        numOfClients = allClients.size(); // showing the number of client

        //list and print all clients
        for(int indexOfClient = 0; indexOfClient < numOfClients; indexOfClient++)
        {
            client = allClients.get(indexOfClient);
            System.out.println(client);
        }
    }

    /* Name: printClientsGender
     * parameters: gender
     * purpose: print clients selected from each gender
     * return type: void
     * return: none
     */   
    public void printClientsGender(String gender)
    {  
        numOfClients = allClients.size();

        for(int indexOfClient = 0; indexOfClient < numOfClients; indexOfClient++)
        {
            client = allClients.get(indexOfClient);

            //if gender of every client equal what user require to see, print it
            if(client.getGender().equals(gender))
            {
                System.out.println(client);
            }
        }
    }

    /* Name: printMatches
     * parameters: none
     * purpose: print clients who are matched with others
     * return type: void
     * return: none
     */   
    public void printMatches()
    {
        numOfClients = allClients.size();

        for(int indexOfClient = 0; indexOfClient < numOfClients; indexOfClient++)
        {
            Client client = allClients.get(indexOfClient);    

            String name = client.getName(); //showing the client's name

            //finding the matched clients by matching every match index which is not -1
            if(client.getMyMatchIndex() != -1 && indexOfClient < client.getMyMatchIndex())
            {
                System.out.println(client.getName() + " is matched with " 
                    + allClients.get(client.getMyMatchIndex()).getName() + "\n"); //print matched clients'name with format
            }
        }
    }

    /* Name: printUnmatchedClients
     * parameters: none
     * purpose: print unmatched clients 
     * return type: void
     * return: none
     */   
    public void printUnmatchedClients()
    {
        numOfClients = allClients.size();

        for(int indexOfClient = 0; indexOfClient < numOfClients; indexOfClient++)
        {
            client = allClients.get(indexOfClient);

            //showing the unmatched client with match index -1
            if(client.getMyMatchIndex() == -1)
            {
                System.out.println(client);
            }
        }
    }

    /* Name: printUnmatchedClients
     * parameters: gender
     * purpose: print unmatched clients with different genders
     * return type: void
     * return: none
     */   
    public void printUnmatchedClients(String gender)
    {
        numOfClients = allClients.size();

        for(int indexOfClient = 0; indexOfClient < numOfClients; indexOfClient++)
        {
            client = allClients.get(indexOfClient);

            //showing the unmatched client with the pointed gender 
            if(client.getMyMatchIndex() == -1 && client.getGender().equals(gender))
            {
                System.out.println(client);
            }
        }
    } 

    /* Name: addClient
     * parameters: newbie
     * purpose: add the new client to clients list and get the matching result of he or she from DatingHelperSystem class
     * return type: String
     * return: DatingSystemHelper.makeMatch(newbie, allClients)
     */   
    public String addClient(Client newbie)
    {
        allClients.add(newbie); //add the new client from input to the client list

        //place new client into the match function and show the result of if he or she is matched with someone
        return DatingSystemHelper.makeMatch(newbie, allClients);
    }
}  
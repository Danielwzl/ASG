import java.util.*;

/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 6
 *  Description: this class is to creat clients with their attributes 
 */
public class Client
{
    private String name, gender, genderSought, interest = "";
    private int myMatchIndex;
    private Scanner scan = new Scanner(System.in);
    private ArrayList<String> interests = new ArrayList<String>();
    private boolean isMatched;
    
    /* Name: Client
     * parameters: name, gender, genderSought
     * purpose: a constructor to pass the value and creat an client object
     * return type: none
     * return: none
     */   
    public Client(String name, String gender, String genderSought)
    {
        this.name = name;
        this.gender = gender;
        this.genderSought= genderSought;
    }

    /* Name: setName
     * parameters: name
     * purpose: set the value of name to class variable and for test case usage
     * return type: void
     * return: none
     */   
    public void setName(String name)
    {
        this.name = name;
    }

    /* Name: getName
     * parameters: none
     * purpose: get the value of name passed in constructor or setters and for test case usage
     * return type: String
     * return: name
     */   
    public String getName()
    {
        return name;
    }

    /* Name: setGender
     * parameters: gender
     * purpose: set the value of gender to class variable and for test case usage
     * return type: void
     * return: none
     */   
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    /* Name: getGender
     * parameters: none
     * purpose: get the value of gender passed in constructor or setters and for test case usage
     * return type: String
     * return: gender
     */   
    public String getGender()
    {
        return gender;
    }

    /* Name: setGenderSought
     * parameters: genderSought
     * purpose: set the value of genderSought to class variable and for test case usage
     * return type: void
     * return: none
     */   
    public void setGenderSought(String genderSought)
    {
        this.genderSought = genderSought;
    }

    /* Name: getGenderSought
     * parameters: none
     * purpose: get the value of genderSought passed in constructor or setters and for test case usage
     * return type: String
     * return: genderSought
     */   
    public String getGenderSought()
    {
        return genderSought;
    }

    /* Name: setMyMatchIndex
     * parameters: myMatchIndex
     * purpose: set the value of myMatchIndex to class variable and for test case usage
     * return type: void
     * return: none
     */   
    public void setMyMatchIndex(int myMatchIndex)
    {
        this.myMatchIndex = myMatchIndex;
    }

    /* Name: getMyMatchIndex
     * parameters: none
     * purpose: get the value of myMatchIndex passed in constructor or setters and for test case usage
     * return type: int
     * return: myMatchIndex
     */   
    public int getMyMatchIndex()
    {
        return myMatchIndex;
    }

    /* Name: getInterests
     * parameters: none
     * purpose: get the element of interests list
     * return type: ArrayList<String>
     * return: interests
     */   
    public ArrayList<String> getInterests()
    {
        return interests;
    }

    /* Name: addInterest
     * parameters: interest
     * purpose: set the interests to class variable and add them to interests list
     * return type: void
     * return: none
     */   
    public void addInterest(String interest)
    {
        interests.add(interest); //add the interests from input into the ArrayList

        this.interest += " " + interest; // this is for printing client's interests
    }

    /* Name: isGender
     * parameters: gender
     * purpose: determine if two clients have fit sexual orientation
     * return type: boolean
     * return: isMatched
     */   
    public boolean isGender(String gender)
    {
        isMatched = false; //intialize the isMatched variable to default

        //if gender of another client equals new client's gender sought
        //it will return true value
        if(getGender().equals(gender))
        {
            isMatched = true;
        }

        return isMatched;
    }

    /* Name: isGenderSought
     * parameters: genderSought
     * purpose: determine if two clients have fit sexual orientation
     * return type: boolean
     * return: isMatched
     */   
    public boolean isGenderSought(String genderSought)
    {
        isMatched = false; //intialize the isMatched variable to default for next 

        //if gender sought of another client equals new client's gender or gender sought equal "E"
        //it will return true value
        if(getGenderSought().equals(genderSought))
        {
            isMatched = true;
        }

        return isMatched;
    }

    /* Name: numMatchingInterests
     * parameters: otherClient
     * purpose: determine the number of the common interests of two clients
     * return type: int
     * return: numInterests
     */   
    public int numMatchingInterests(Client otherClient)
    {
        int numInterests = 0;

        for(int i = 0; i < otherClient.getInterests().size(); i++)
        {
            String interest1 = otherClient.getInterests().get(i); //list all clients' interests except the one wants match

            for(int k = 0; k < getInterests().size(); k++) 
            {
                String interest2  = getInterests().get(k); //list all interests of the target client

                //see if they have common interest
                if(interest2.equals(interest1))
                {
                    numInterests++;
                }            
            }        
        }

        return numInterests;
    }

    /* Name: toString
     * parameters: none
     * purpose: rebuild the string format of client class and convert match index into words
     * return type: String
     * return: result
     */   
    public String toString()
    {
        String match = "(Matched)";

        //if clients' match index is -1, then their match status is not matched, other will be matched
        switch(myMatchIndex)
        {
            case -1:    match = "(Not matched)";
        }  

        String result = name + " " + gender + " " + genderSought + interest + " " + match + "\n";

        return result;
    }
}
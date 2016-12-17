import java.util.Scanner;
import java.io.*;

/**
 * This class is to read the file and creat each questions object 
 * and put them into array
 * @author  Zilong Wang 
 * @version 1.0
 * Last Modified: <09-19-2015> - <adding comments> <Zilong Wang>
 */
public class FileReader
{
    private Scanner scan;

    /**  
     *  This is a constructor of FileReader class
     *  this is for getting file from main class 
     *  and passing the question objects into TriviaGameSystem class
     *  @param <file> <file from user input> 
     */
    public FileReader(FileInputStream file)
    {
        scan = new Scanner(file);
        new TriviaGameSystem(storeData()); //pass Questions[] questions into "system" class
    }

    /**  
     *  This method is to read file and put elements into question class
     *  and then put the Question objects into array
     *  @multiply return <questions: array contains object of Question class>
     *  @multiply return <null: if the file has no content, "null pointer" will be handled in the class where this object being used>
     */
    private Questions[] storeData()
    {
        if(scan.hasNextInt()) //if first token is an integer
        {
            //if file is not empty, then open these spaces in the stack memory 
            int point = 0, 
                numOfChoices = 0,
                i = 0, //"i" means index
                numOfQuestions = scan.nextInt(); //scan the number of quesions
            Questions[] questions = new Questions[numOfQuestions]; //initial array, plus one "element" that store the total number of question
            char answer = ' ';
            String question = "";
            String[] choices = null;

            for(i = 0; i < numOfQuestions; i++)
            {
                if(scan.hasNextInt()) //if second token is an integer
                {
                    point = scan.nextInt();

                    if(scan.hasNext("[a-zA-Z]{1}")) //if third token is a single letter
                    {
                        answer = scan.next().charAt(0);

                        if(scan.hasNextInt()) //if fourth token is an integer
                        {
                            numOfChoices = scan.nextInt();
                            scan.nextLine(); //clear the line to read next line

                            if(scan.hasNext()) //if there is the fifth token 
                            {
                                question = scan.nextLine();
                            }
                        }
                    }
                } 
                
                choices = new String[numOfChoices += 2]; //creat a container for choices as an array inside the "Questions" object    
                                                        //number of choices + 2 means the last elements are "quit" and "skip"
                for(int j = 0; j < numOfChoices; j++)
                {
                    if((j < numOfChoices - 2) && scan.hasNext()) choices[j] = scan.nextLine(); //put choices into array
                    else if(j == numOfChoices - 2) choices[j] = "Skip"; //store the second last element as "skip"
                    else choices[j] = "Quit"; //store the last element as "quit"
                }
                questions[i] = new Questions(point, numOfChoices, answer, question, choices); //creat one question object
            }

            //total number of questions = "i"(after last loop should be same as "numOfQuestions")
            //it means read from the top to the end of the file, which file is valid and complete
            if(numOfQuestions == i) return questions;
        }   

        return null; //if file is not complete, not valid or empty, return null
    }
}

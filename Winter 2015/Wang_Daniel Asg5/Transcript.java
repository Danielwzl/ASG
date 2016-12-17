import java.io.*;
import java.util.Scanner;

/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 5
 *  Description: this class is to read the tokens from file 
 */
public class Transcript
{ 
    private String subject, grade;
    private int number;
    private Scanner scanFile;

    /* Name: Transcript
     * parameters: input
     * purpose: pass the value of parameter to class variable 
     * return type: none
     * return: none
     */   
    Transcript(FileInputStream input)
    {
        scanFile = new Scanner(input); 
    }

    /* Name: hasNextCourse
     * parameters: none
     * purpose: to check if there is next token in the file
     * return type: boolean
     * return: scanFile.hasNext()
     */   
    public boolean hasNextCourse()
    {   
        return scanFile.hasNext(); 
    }

    /* Name: nextCourse
     * parameters: none
     * purpose: to read the tokens which are valid from files and pass them into Course class to get course sunmmay
     * return type: Course
     * return: course
     */   
    public Course nextCourse()
    {    
        //initiate course to be null in order to return null when there is no tokens in files 
        //and in calculator class, it will print something notice it is empty file
        Course course = null;
        int number = 0;
        String subject = " ",  //create suject, course number and grade to store the value from the file
                 grade = " "; 
        final String ERROR = "invalid transicript file"; //the error is showing when the token is not valid
        
        //to read the file and pass tokens into course class
        if(hasNextCourse())//if there is token in the file
        {   
            if(scanFile.hasNext("[a-zA-Z]{4}")) //if the first token fits the constraint: upper or lower case 4 - letters words
            {
                subject = scanFile.next().toUpperCase(); //convert any valid token to be upper case
                if(scanFile.hasNext("[0-9]{4}")) //if the second token fits the constraint: 4 digits number. 
                {                                //In here, just check the string value in order to catch number like 0000, 0100 which are valid
                    number = scanFile.nextInt(); //get the number with integer type for passing it into the constructor of course class
                    if(scanFile.hasNext())//if there is a third token.
                    {
                        grade = scanFile.next().toUpperCase(); //get grade to upper case
                        if(grade.matches("A[+-]?|B[+-]?|C[+-]?|D[+]?|F")) //this is used to catch the invalid token of grade
                        {
                            course = new Course(subject, number, grade); // pass all values to the constructor of course class if all tokens are valid
                        }
                        else
                        {
                            System.out.println(ERROR);
                        }
                    } 
                }
                else 
                {
                    System.out.println(ERROR); //the error is showing when the second token first is not valid
                }
            }
            else
            {
                System.out.println(ERROR);//the error is showing when the first token first is not valid
            }
        }

        return course;
    }
}
import java.util.Scanner;
import java.io.*;

/**
 * This class is for executing this program 
 * @author  Zilong Wang 
 * @version 1.0
 * Last Modified: <09-09-2015> - <adding comments> <Zilong Wang>
 */
public class Main
{
    /**
     * This is main method for executing this program and printing welcome 
     * @param <args> <This is String type array "arguments" which is formal standard of main class> 
     */
    public static void main(String[] args)throws FileNotFoundException
    {
        Scanner kb = new Scanner(System.in);

        System.out.println("*** Welcome to the Monthly Profiler ***"); //print welcome 

        //user can input file name 
        System.out.print("Please enter the name of the file with the suffix: ");
        String fileName = kb.nextLine();
        System.out.println("You requested: " + fileName);
        
        TemperatureProfiler tp = new TemperatureProfiler(fileName);
        
        System.out.print(tp); //print all of the charts 
    }  
}

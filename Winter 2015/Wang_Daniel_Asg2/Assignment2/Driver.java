import java.util.Scanner;
/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 2
 *  Description: It is a text-based user interface to the functions I did in Assignment 1.
 */
public class Driver
{
    /* Name: userInterface 　
     * parameters: height=m; weight=kg; gae; gender;
     * purpose: offer a API for users and report the test results to them
     * return type: 
     * return: 
     */   
        public static void userInterface()
    {   
        double  height = 0.0,      // input  height(m)
                weight = 0.0,      // input  weight(kg)
                   BMI = 0.0,      // the output of BMI level
               BodyFat = 0.0;      // the output of fat level of body
        
        int age = 0,    // input your age
         gender = 0;    // input 1 for male, 0 for female
       
        Scanner keyboard = new Scanner(System.in);   
        
        System.out.print("your height(m) is:");
        height = keyboard.nextDouble();
        
        System.out.print("your weight(kg) is:");
        weight = keyboard.nextDouble();
        
        System.out.print("your age is:");
        age = keyboard.nextInt();
        
        System.out.print("your gender(male=1,female=0) is:");
        gender = keyboard.nextInt();
        
        BodyTest bodyExam = new BodyTest();            // invoke methods from bodyExam class
        BMI = bodyExam.bodyBMI(height,weight);
        BodyFat = bodyExam.adultBodyFat(age,height,weight,gender);
        
        System.out.println();                                                                  //Those below are showing the complete result from bodyTest class
        System.out.println("This program tests your BMI and BodyFat percentage.");
        System.out.println("*******************************************************************************");
        System.out.println("\t\t\t\t1.BMI TEST");
        System.out.println("\tYour BMI result:"+ BMI); // 1.to show your BMI and related comment of you BMI
        System.out.println("\t[<18.5]:too skinny!\n");
        System.out.println("\t[18.5-24.99]:nice body shape\n");
        System.out.println("\t[24.99-28.0]:you need work out!\n"); 
        System.out.println("\t[>28.0]: Obesity!");
        System.out.println("*******************************************************************************");
        System.out.print("\t2.For your health,your standard weight is:"); // 2.to show your standard weight,male and female are different
        System.out.println(  height * height * 22+"KG,if you are male.");
        System.out.println(  weight * weight * 20+"KG,if you are female.");
        System.out.println("*******************************************************************************");
        System.out.println("\t\t\t3.Adult body fat TEST"); //3. to show the percentage of fat in your body
        System.out.println("\tAdult body fat:\t" + BodyFat+"%");
        System.out.println("\t\t\t\tThank you ");
    }
}
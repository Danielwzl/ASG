
/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 1
 *  Description: Bodytest
 */
public class BodyTest{
    /* Name: BMI calculator　
     * parameters: height=m; weight=kg
     * purpose: test your BMI level
     * return type: double
     * return: BMI = kg/(m * m)
     */   
       private void printing (double m, double kg)
       {
        System.out.println("\t\t\t\tBMI TEST");
        System.out.println("\n\n    Your BMI result:"+ kg/(m * m));
        System.out.println("\t[<18.5]:too skinny!\n");
        System.out.println("\t[18.5-24.99]:nice body shape\n");
        System.out.println("\t[3.25-28]:you need work out!\n");
        System.out.println("\t[>32]: .........\n\n\n");
        System.out.println("\tFor your health,your standard weight is:");
        System.out.println("\t"+ m * m * 22+"KG"+":If you are male");
        System.out.println("\t"+ m * m * 20+"KG"+":If you are female");
       }
       private double bodyBMI(double m, double kg)
       {
        return kg/(m * m);
    }
    /* Name:adultBodyFat
     * parameters:age, height=m, weight=kg, gender(male=1, female=0)
     * purpose:to test the percentage of the fat of your body
     * return type:double
     * return: value = bodyBMI(m,kg) * 1.2 + 0.23 * age - 5.4 - 10.8 * gender
     */ 
       public double adultBodyFat(int age, double m, double kg, int gender)
       {
        double value = bodyBMI(m,kg) * 1.2 + 0.23 * age - 5.4 - 10.8 * gender; // gender (male=1 female=0)
        printing (m,kg);
        System.out.println("\n\n\n\t\t\tAdult body fat TEST");
        System.out.println("\tAdult body fat:" + value+"%"); 
        return value;
       }
     }
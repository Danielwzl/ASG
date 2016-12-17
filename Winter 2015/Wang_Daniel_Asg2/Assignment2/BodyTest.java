/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 2
 *  Description: BodyTest,test both your BMI and percentage of fat in your body
 */

public class BodyTest
{
    /* Name: BMI calculator　
     * parameters: height=m; weight=kg
     * purpose: test your BMI level
     * return type: double
     * return: BMI = kg / (m * m)
     */   
    public double bodyBMI(double m, double kg)
       {
        return kg / (m * m);
       }

    /* Name:adultBodyFat
     * parameters:age, height=m, weight=kg, gender(male=1, female=0)
     * purpose:to test the percentage of the fat of your body
     * return type:double
     * return: value = bodyBMI(m,kg) * 1.2 + 0.23 * age - 5.4 - 10.8 * gender
     */ 
    public double adultBodyFat(int age, double m, double kg, int gender) // gender (male=1 female=0)
       {
        final double fatPerBMI = 1.2;
        final double fatPerAge = 0.23;
        final double adjustingValue = 5.4;
        final double genderDifference = 10.8;
        
        return bodyBMI(m,kg) * fatPerBMI + fatPerAge * age - adjustingValue - genderDifference * gender; // this function is from Wiki 
       }
}
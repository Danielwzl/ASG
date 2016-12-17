import java.util.*;
import java.text.DecimalFormat;

/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 5
 *  Description: this class is to generate a standing chart with arrow poinging the average GPA at the right position of the chart
 */
public class StandingChart
{
    private int maxPossible, granularity;
    private double gpa;

    /* Name: StandingChart
     * parameters: maxPossible, granularity, gpa
     * purpose: pass the values of parameters to class variables and verify the validity 
     * return type: none
     * return: none 
     */   
    public StandingChart(int maxPossible, int granularity, double gpa)
    {
        //when these attributes are negative numbers or greater than max value, it will report error;
        //also for test case usage when passing value into constructor
        assert maxPossible > 0;
        assert granularity > 0;
        assert gpa >= 0 && gpa <= maxPossible;

        //pass the values of parameters to class variables
        this.maxPossible = maxPossible;
        this.granularity = granularity;
        this.gpa = gpa;
    }

    /* Name: setMaxPossible
     * parameters: maxPossible
     * purpose: pass the value of parameter to class variable 
     * return type: void
     * return: none
     */   
    public void setMaxPossible(int maxPossible)
    {
        this.maxPossible = maxPossible;
    }

    /* Name: setGranularity
     * parameters: maxPossible
     * purpose: pass the value of parameter to class variable 
     * return type: void
     * return: none
     */   
    public void setGranularity(int granularity)
    {
        this.granularity = granularity;
    }

    /* Name: setGPA
     * parameters: gpa
     * purpose: pass the value of parameter to class variable 
     * return type: void
     * return: none
     */   
    public void setGPA(double gpa)
    {   
        this.gpa = gpa;
    }

    /* Name: toString
     * parameters: none
     * purpose: to generate the standing chart and make a arrow pointing at the right position
     * return type: String
     * return: none
     */   
    public String toString()
    {
        final String DASH = "-"; //creat a dash showing the sub-level of GPA
        String result = "\n";
        double actualGPA = (int)(gpa * 100) / 100.0; //showing the actual GPA with 2 decimal digits

        DecimalFormat fmt = new DecimalFormat("0.00");  //format GPA into proper way if gpa only had 1 decimal digit or is a whole number
        String arrow = "\t<-- GPA = " + fmt.format(actualGPA) + "\n"; //creat an arrow with GPA 

        gpa = actualGPA * granularity; //convert GPA eg. 3.0 * 3 or 3.5 * 3, compare with whole number like 12, 11 ,10, to precisely locate the positon on the chart

        System.out.print("\nCurrent Standing:\n");

        for(int standing = maxPossible * granularity; standing >= 0; standing--) //standing is where the level will be built for the chart
        {
            int criticalPoint = standing / granularity; //indicating the main level like [4,3,2,1,0]

            if(standing % granularity == 0) //the level standard is whole numbers
            {   
                if(gpa < (standing + 1) && gpa >= standing) 
                {
                    result += (criticalPoint + arrow); //current level <= gpa < next level, point arrow with GPA to current level
                }
                else 
                {
                    result += criticalPoint + "\n"; //then print current level instead
                }
            }
            else //if level standard is decimal number, print dash instead
            {
                if(gpa < (standing + 1) && gpa >= standing)
                {
                    result += (DASH + arrow);
                }
                else
                {
                    result += DASH + "\n";
                }
            }
        }
        // System.out.println(result);  //this is for testing the standing chart class
        return result;
    }
}

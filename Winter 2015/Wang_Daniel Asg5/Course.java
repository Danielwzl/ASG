import java.text.DecimalFormat;

/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 5
 *  Description: to format the course name, course number, grade and GPA, and print them to users
 */
public class Course
{
    private String subject, letterGrade;
    private int number;   
    private double gpa;
    private DecimalFormat fmt = new DecimalFormat("0000");
    
    /* Name: Course
     * parameters: subject, number, letterGrade
     * purpose: constructor to pass the value and check if grade is valid
     * return type: none
     * return: none
     */   
    public Course(String subject, int number, String letterGrade)
    {
        assert letterGrade.matches("A[+-]?|B[+-]?|C[+-]?|D[+]?|F"); // for test case usage, catch the invalid info
        
        this.subject = subject;
        this.letterGrade = letterGrade;
        this.number = number;
    }
    
    /* Name: getSubject
     * parameters: none
     * purpose: get the value of class variable passed in constructor or setters and for test case usage
     * return type: String
     * return: subject
     */   
    public String getSubject()
    {
        return subject;
    }
    
    public void setSubject(String subject)
    {
        this.subject = subject;
    }
    
    /* Name: getNumber
     * parameters: none
     * purpose: get the value of class variable passed in constructor or setters and for test case usage
     * return type: int
     * return: number
     */   
    public int getNumber()
    {
        return number;
    }
    
    public void setNumber(int number)
    {
        this.number = number;
    }
    
    /* Name: getLetterGrade
     * parameters: none
     * purpose: get the value of class variable passed in constructor or setters and for test case usage
     * return type: String
     * return: letterGrade
     */   
    public String getLetterGrade()
    {
        return letterGrade;
    }
      
    /* Name: setLetterGrade
     * parameters: letterGrade
     * purpose: set the value of calss variable and for test case usage
     * return type: String
     * return: letterGrade
     */   
    public void setLetterGrade(String letterGrade)
    {
        this.letterGrade = letterGrade;
    }
    
    /* Name: getLetterGrade
     * parameters: none
     * purpose: convert grade to related GPA value
     * return type: double
     * return: gpa
     */   
    public double getGradePoint()
    {
        switch(letterGrade) //convert grade to GPA
        {
            case "A+":
            case "A":  gpa = 4.0;
                       break;
                       
            case "A-": gpa = 3.7;
                       break;
                       
            case "B+": gpa = 3.3;
                       break;
                       
            case "B":  gpa = 3.0;
                       break;
                       
            case "B-": gpa = 2.7;
                       break;
                       
            case "C+": gpa = 2.3;
                       break;
                       
            case "C":  gpa = 2.0;
                       break;
                       
            case "C-": gpa = 1.7;
                       break;
                       
            case "D+": gpa = 1.5;
                       break;
                       
            case "D":  gpa = 1.0;
                       break;
                       
            case "F":  gpa = 0;
                       break;
        }

        return gpa;
    }
    
    /* Name: toString
     * parameters: none
     * purpose: print course summary in right format
     * return type: String
     * return: result
     */   
    public String toString()
    {
        String num = fmt.format(number), // format course number to be four digits
            result = subject + " " + num + "  " + letterGrade + "\t" + "(worth = " + gpa + ")";
            
        return result; 
    }
}
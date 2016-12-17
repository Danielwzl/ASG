/**
 * This class is created Questions object 
 * which contains questions, points, answers, choices and number of choices
 * this will be put into Array as a single class
 * @author Zilong Wang
 * @version 1.0
 * Last Modified: <09-19-2015> - <adding comments> <Zilong Wang>
 */
public class Questions
{
    private int point, numOfChoises;
    private char answer;
    private String question;
    private String[] choices;

    /**  
     *  This is a constructor of questions class
     */
    public Questions(int point, int numOfChoises, char answer, String question, String[] choices)
    {
        this.point = point;
        this.numOfChoises = numOfChoises;
        this.answer = answer;
        this.question = question;        
        this.choices = choices;
    }

    /**
     * This is a getter
     * @return <point>
     */
    public int getPoint()
    {
        return point;
    }

    /**
     * This is a getter
     * @return <choices>
     */
    public String[] getChoices()
    {
        return choices;
    }

    /**
     * This is a getter
     * @return <numOfChoises>
     */
    public int getNumOfChoices()
    {
        return numOfChoises;
    }

    /**
     * This is a getter
     * @return <answer>
     */
    public char getAnswer()
    {
        return answer;
    }

    /**
     * This is a getter
     * @return <question>
     */
    public String getQuestion()
    {
        return question;
    }
}

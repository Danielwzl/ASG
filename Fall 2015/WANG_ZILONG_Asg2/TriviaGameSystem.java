/**
 * This class is the core of the game system
 * @author  Zilong Wang 
 * @version 1.0 
 * Last Modified: <09-19-2015> - <adding comments> <Zilong Wang>
 *                <09-22-2015> - <adjusting the way of skipping and quiting game> <Zilong Wang>
 *                             - <adding "pauseGame" helper function instead of using ui.pause()> <Zilong Wang>
 *                <09-25-2015> - <changing coreSystem method: change doWhile loop to While loop, and move if statement outside> <Zilong Wang>
 */
public class TriviaGameSystem
{
    private Questions[] questions; //accept array from other class
    //totQuesRight = total number anwser right
    //totQuesSkip = total question skipped
    private int numOfQuestions, score, totQuesRight, totQuesSkip;
    private UserInteraction ui = new UserInteraction();
    private final int ASCII_FOR_A = 65; //ASCII code for char "A" is 65
    //constants used for defining returnning number in the judgeAnswer class
    private final int QUIT_NUM = -1, SKIP_NUM = 0, CHOICE_EXIST = 1, CHOICE_NOT_EXIST = -2;

    /**  
     *  This is a constructor
     *  and passing the values and initialize the function
     *  @param <questions> <array will contain Question class> 
     *  @param <numOfQuestions> <total number of quest ions> 
     */
    public TriviaGameSystem(Questions[] questions)
    {
        this.questions = questions;
        numOfQuestions = questions.length; //get the how man "quesions" objects it has which is the number of questions
        coreSystem(); //run it
    }

    /**  
     *  This method is to show questions and to calculate the scores
     *  or show error when file is not right
     */
    private void coreSystem()
    {
        if(questions != null)
        {
            ui.println("This example trivia game contains " + numOfQuestions + " questions:");

            //"i" is the index of each question include the information associated with it
            for(int i = 0; i < numOfQuestions; i++)
            {
                ui.println(questionHeader(i, questions[i].getPoint(), score)); //show header of each questions
                ui.println("\t\t\t" + questions[i].getQuestion()); //show questions
                showChoices(i); //show choices
                
                //1.determine if their input letter is matching any option that the choices has, if not let user re-enter
                char userKey = ui.getChar("Please enter the answer"); 
                while(judgeAnswer(userKey, i) == CHOICE_NOT_EXIST)//enter invalid choice char will occuer re-enter notice 
                {
                        //if(judgeAnswer(userKey, i) == CHOICE_NOT_EXIST) //re-enter if choice doesn't exist
                        userKey = ui.getChar("Chioce doesn't exist, please re-enter"); 
                }
                
                //2.till they enter the existing choice, matching this input with the choices
                //3.if user input doesn't mean skip or quit, determine if answer is correct
                if(judgeAnswer(userKey, i) == CHOICE_EXIST)
                {
                    if(isCorrect(userKey, questions[i].getAnswer()))
                    {
                        ui.println("*Correct!*");
                        score += questions[i].getPoint();
                        totQuesRight++;                        
                    }
                    else ui.println("*You were wrong!*");
                    pauseGame(i); //pause the game after answer the question
                }
                else if(judgeAnswer(userKey, i) == SKIP_NUM)
                {
                    totQuesSkip++;
                    pauseGame(i); //pause the game when skip the question
                    continue;
                }  
                else if(judgeAnswer(userKey, i) == QUIT_NUM) 
                {
                    //if quit the game then each questions hasn't been answered will be counted as skipped
                    totQuesSkip += (numOfQuestions - i);//"numOfQuestions - i" means questions left
                    break; //if choose the last choice which is "Quit", then qiut the game
                }
            }          
            ending(); //end the game when loop ends (break or run to the end of loop)
        }
        else error(); //print error when there is error information in the file
    }

    /**  
     *  This method is to print the choices and letter associated with the order of choices, eg. A, B , C, D, E...
     */
    private void showChoices(int indexOfQuestions)
    {
        String[] tempChoice = questions[indexOfQuestions].getChoices(); 

        for(int indexOfChoice = 0; indexOfChoice < questions[indexOfQuestions].getNumOfChoices(); indexOfChoice++)
        {
            ui.println("\t\t\t\t" + (char)(ASCII_FOR_A + indexOfChoice) + " " + tempChoice[indexOfChoice]);
        }
    }

    /**  
     *  This method is to judge the user input, if their answer is correct or they want to skip questions, and quit the game
     *  @param <userInput> <the choice made from user> 
     *  @param <indexOfQuestions> <which question it is looking at> 
     *  @multiply return <QUIT_NUM: is -1>
     *  @multiply return <SKIP_NUM: is 0>
     *  @multiply return <CHOICE_EXIST: is 1>
     *  @multiply return <CHOICE_NOT_EXIST: is -2>
     */
    private int judgeAnswer(char userInput, int indexOfQuestions)
    {                                                
        //ascii code for last choice is the option for "Quit".
        int asciiForLastChoices = ASCII_FOR_A + questions[indexOfQuestions].getNumOfChoices() - 1 ;

        if((int)userInput == asciiForLastChoices) return QUIT_NUM; //"-1" for quit the game
        else if((int)userInput == asciiForLastChoices - 1) return SKIP_NUM; //"0" for skip the question 
        else if((int)userInput < asciiForLastChoices - 1 && (int)userInput >= ASCII_FOR_A)return CHOICE_EXIST; //"1" for answer the question

        return CHOICE_NOT_EXIST; // if the user's request doesnt exsit in the options
    }
    
    /**  
     *  This method is always to pause the game before the last question
     *  @param <indexOfQuestion> <which question user is looking at> 
     */
    private void pauseGame(int indexOfQuestion)
    {
        if(indexOfQuestion != numOfQuestions - 1) ui.pause(); //"true" before reach the last question
    }

    /**  
     *  This method is to identify if the file is empty
     *  @param <key> <the choice made from user> 
     *  @param <answer> <correct answer of this question> 
     *  @return <boolean type value: if user type is euqal the answer>
     */
    private boolean isCorrect(char key, char answer)
    {
        return key == answer;
    }

    /**  
     *  This method is to print error message if 
     */
    private void error()
    {
        ui.println("File is not in right format or file is empty!");
    }

    /**  
     *  This method is to print final message when game ends
     */
    private void ending()
    {
        ui.println("\n********************************************");
        ui.println("The final score: " + score + "\n" + "The number of correct questions answered: " + totQuesRight 
            + "\n" + "The number of questions skipped: " + totQuesSkip);
        ui.print("********************************************");
    }

    /**  
     *  This method is to print a line of header above each question
     *  @param <indexOfQuestion> <index of the questions>
     *  @param <point> <how many point that each question worthes>
     *  @param <mark> <current updating mark for user>
     *  @return <a string type value: a line of header above each question>
     */
    private String questionHeader(int indexOfQuestion, int point, int mark)
    {
        int questionLable =  indexOfQuestion + 1; //show "1" is the first question or not "0"

        return "\t" + "(" + questionLable + "/" + numOfQuestions + ")" 
        + ". "  + "points = " + point + ", Your score = " + mark;
    }
}

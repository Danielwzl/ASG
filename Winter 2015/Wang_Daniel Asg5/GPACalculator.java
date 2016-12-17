import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 *  Name: Zilong Wang   
 *  Instructor: Namrata Khemka-Dolan 
 *  Course: COMP1501    
 *  Assignment#: 5
 *  Description: let users choose trangscript file and print Course summary and Standing chart
 */
public class GPACalculator
{
    /* Name: GPACalculator
     * parameters: none
     * purpose: to let users select file and show the information on the screen 
     * return type: void
     * return: none
     */   
    public static void main(String[] args) throws FileNotFoundException
    {
        String transcriptFileName = selectFile("Double-Click to Select Transcript File:");
        FileInputStream fis = new FileInputStream(transcriptFileName);
        Transcript file = new Transcript(fis);
        
        //creat constants of maxpoosible and granularity
        final int MAX_POSSIBLE = 4,
                   GRANULARITY = 3;
        double sum = 0.0; // sum of GPA
        int counter = 0; //count how many courses
        Course course = null;
        
        //show users course summary 
        while(file.hasNextCourse())
        {
            course = file.nextCourse();
            sum += course.getGradePoint();
            System.out.println(course);
            counter++;
        }
        
        double avg = sum / counter; //get the average GPA;
        
        if(course == null)
        {
            System.out.println("undefined GPA – no courses completed"); //when the txt file is empty
        }
        else
        {           
            StandingChart chart = new StandingChart(MAX_POSSIBLE, GRANULARITY, avg);
            System.out.println(chart);//show users the standing chart
        }
    }

    /* Name: selectFile
     * parameters: prompt
     * purpose: subfunction that to select file
     * return type: String
     * return: fileChooser.getSelectedFile().getPath()
     */   
    public static String selectFile(String prompt)
    {
        JFileChooser fileChooser = new JFileChooser(".\\test data");

        fileChooser.setDialogTitle(prompt);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        fileChooser.setControlButtonsAreShown(false);
        fileChooser.showOpenDialog(null);

        return fileChooser.getSelectedFile().getPath();
    }
}

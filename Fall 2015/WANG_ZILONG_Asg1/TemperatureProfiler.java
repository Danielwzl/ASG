import java.util.*;
import java.io.*;

/**
 * This class is to store information to arraylist and initiate the the temperature chart  
 * @author  Zilong Wang 
 * @version 1.0
 * Last Modified: <09-11-2015> - <adding a helper function that can align all datas> <Zilong Wang>
 */
public class TemperatureProfiler
{
    private ArrayList<Information> allInfo;
    //highBound and lowBound are the time zone boundaies(0,6,12,18 are the lowBound to their zones; 5,11,17,23 are the highBound to their zones)
    //totalElements is the total number of temperatures in the file
    private int totalElements, daysInMonth, numOfElement, highBound = 5, lowBound = 0;
    private double avgTemp;
    private String fileName;
    private Information info;

    /**  
     *  This is a constructor of TemperatureProfiler class, to pass the file name from user interface which input by user
     */
    public TemperatureProfiler(String fileName)
    {
        this.fileName = fileName;
    }

    /**  
     *  This method accept every information from the file, and then put these information into Arraylist 
     */
    private void readFile()
    {
        Scanner read = null;

        //make sure the file name is valid, notice error if file name is not exsiting
        try
        {
            read = new Scanner(new FileInputStream(fileName));
        }   
        catch(FileNotFoundException e)
        {
            System.out.println("file not found! please use vaild file!");
        }

        // this loop is to read the file and put into Arraylist, if there is no file for Scanner to scan, then report erorr file
        while(read != null && read.hasNextInt())
        {
            int day = read.nextInt(),
            time = read.nextInt();
            double temperature = read.nextDouble();

            if(allInfo == null)
                allInfo = new ArrayList<Information>(); //make only one container to store information

            allInfo.add(new Information(day, time, temperature)); //Arraylist contains every single Information class;

            totalElements++; //count how many elements in it and prepare it for showDay() method
        }
    }

    /**  
     *  This method is to split a day into four different zones
     *  @return <multiply return String type "Zone in a day" depending on the boundaries of time>
     */
    private String zone()
    {
        //show period in a day depends on the hour boundaries
        if(lowBound == 0 && highBound == 5)
            return "Zone: Night";
        else if(lowBound == 6 && highBound == 11)
            return "Zone: Morning";
        else if(lowBound == 12 && highBound == 17)
            return "Zone: Afternoon";

        return "Zone: Evening";
    }

    /**  
     *  This method is to store temperatures within each day by different time zones
     *  @return <return a String chart with temperatures in four zones>
     */
    private String stat()
    {
        String lineOfTemp = ""; //a string contain all temperatures with specific format
        double temp = 0.0; //store single decimal numbers which is temperatures

        //reading temperatures from arraylist
        for(int i = 1; i <= daysInMonth; i++)
        {
            String day = i + ""; // variable "i" is days in a month, casting it to string type
            
            lineOfTemp += formatContent(day, day.length()); // storing temperature and line-up them

            //reading arraylist
            for(int indexOfElement = 0; indexOfElement < numOfElement; indexOfElement++)
            {
                info = allInfo.get(indexOfElement); // get every element from arraylist from 0 to end of index

                //get time from every element, and select the temp from each time period
                //if(0, 6 , 12, 18) <= time <= (5, 11, 17, 23) respectively in same day, collect the temperature in this period
                if(info.getTime() >= lowBound && info.getTime() <= highBound && info.getDay() == i)
                {
                    temp = info.getTemperature();  //get the temperature 

                    //gap_1 and gap_2 shows if temperature is within 1 degree of the mean (plus or minus) 
                    double gap_1 = temp - avgTemp, // 0 < gap of temp < 1
                    gap_2 = avgTemp - temp; // -1 < gap of temp < 0

                    String tempStr = ""; //string type of temperature

                    //if temperature is within 1 degree of the mean (plus or minus), print asterisks, however printing the regular temperature
                    if((gap_1 < 1.0 && gap_1 > 0.0) || (gap_2 < 1.0 && gap_2 > 0.0))  
                    {
                        {
                                tempStr = "*" + String.format("%5.1f", temp) + "*";
                                lineOfTemp += formatContent(tempStr.length(), tempStr); //line up all temperatures
                        }
                    }
                    else 
                    { 
                        tempStr = String.format("%5.1f", temp);
                        lineOfTemp += formatContent(tempStr.length(), tempStr); //line up all temperatures
                    }
                }
            }
            lineOfTemp += "\n"; //new line after print all temperature in one day between specific hours
        }

        return lineOfTemp;
    }

    /**  
     *  This method is to make time line
     *  @return <return String type heading and time with different zone>
     */
    private String printTime()
    {
        final String HEADING = "Day\\Time:";
        int time = 0;
        String lineOfTime = ""; //storing the time in every day zone

        //reading arraylist
        for(int indexOfClient = 0; indexOfClient < numOfElement; indexOfClient++)
        {
            info = allInfo.get(indexOfClient);  

            //if the time is in sepcific period in a day, then make them a line
            if((time = info.getTime()) >= lowBound && (time = info.getTime()) <= highBound)
            {
                String timeStr = time + ":00"; //casting time from number to string               
                lineOfTime += formatContent(timeStr.length(), timeStr); //organizing the space between time 
            }

            //if time has been read until 23, then stop the function
            if(time > highBound || time == 23)
                break;
        }

        return formatContent(HEADING.length(), HEADING) + lineOfTime;
    }

    /**  
     *  This method is to generate a divider
     *  @return <return String type divider>
     */
    private String printDivider()
    {
        final int NUMBER_OF_DASH = 9,
        GROUP_OF_DASH = 7;
        String lineOfDivider = "";

        //making a divider
        for(int j = 0; j < GROUP_OF_DASH; j++)
        {
            String divider = "";
            for(int i = 0; i < NUMBER_OF_DASH; i++)
            {
                divider += "-"; //store dash
            }
            lineOfDivider += formatContent(divider.length(), divider); //format divider 
        }

        return lineOfDivider;
    }

    /**  
     *  This method is to calculate average temperatures of each zone
     *  @return <return an average temperature of different zone>
     */
    private double avgTemperature()
    {
        double temp = 0.0,
        total = 0.0;
        int count = 0;

        numOfElement = allInfo.size(); //count how many elements in the arraylist and sotre the value into instance variable

        //read all of temperatures that in the same zone of a day
        for(int i = 1; i <= daysInMonth; i++)
        {
            for(int indexOfElement = 0; indexOfElement < numOfElement; indexOfElement++)
            {
                info = allInfo.get(indexOfElement);

                if(info.getTime() >= lowBound && info.getTime() <= highBound && info.getDay() == i) //different zone
                {
                    temp = info.getTemperature(); //get every temperature
                    total += temp; //add them up    
                    count++; //count how many number of elements within a zone
                }
            }
        }

        return avgTemp = total / count; //sum of temperature / total number of element(temperature) within a zone
    }

    /**  
     *  This method is oringal formatContent method
     *  This method is to format every element, which to make them line up and be in the minddle of the divider except "day" column
     *  @param <int length: the length of content>
     *  @param <String content: the content needs to be formatted>
     *  @return <return a String type content after be centred>
     */
    private String formatContent(int length, String content)
    {
        final int SLOTS = 9;
        String spaceAtFront = "",
        spaceAtEnd = "";
        int SpaceAvilable = SLOTS - length; //the spaces available to be allocated
        int numOfSpace = SpaceAvilable / 2; //calculate the space besides a element, decimal value will be casting to integer 

        //numOfSpace is for making a space between two column
        for(int i = 0; i < numOfSpace; i++)
        {
            spaceAtEnd += " "; //store space
        }

        for(int i = 0; i < SpaceAvilable - numOfSpace; i++)
        {
            spaceAtFront += " ";
        }

        return spaceAtFront + content + spaceAtEnd + " "; //this formular is for making every element on chart be in the middle of each column
    }

    //this is method Overloading of formatContent method with two parameters that reversed position
    /**  
     *  This method is an overloading method of "formatContent(int length, String content)", for only formating day column
     *  This overloading method reverse the number of space at both side of "day" value in order to make them centred 
     *  @param <String content: the content needs to be formatted>
     *  @param <int length: the length of content>
     *  @return <return a String type content after be centred>
     */
    private String formatContent(String content, int length)
    {
        final int SLOTS = 9;
        String spaceAtFront = "",
        spaceAtEnd = "";
        int SpaceAvilable = SLOTS - length; //the spaces available to be allocated
        int numOfSpace = SpaceAvilable / 2; //calculate the space besides a element, decimal value will be casting to integer 

        for(int i = 0; i < numOfSpace; i++)
        {
            spaceAtFront += " "; //store space
        }

        for(int i = 0; i < SLOTS - numOfSpace - length; i++)
        {
            spaceAtEnd += " ";
        }

        return spaceAtFront + content + spaceAtEnd + " "; //this formular is for making every element on chart be in the middle of each column
    }

    /**  
     *  This method is computing the days of a month in the file
     *  @return <int daysInMonth: the number of days in a month>
     */
    private int showDays()
    {
        final int ELEMENTS_A_DAY = 24;

        //the temperature is recorded hourly, so total elements = days in a month * total elements in a day
        return daysInMonth = totalElements / ELEMENTS_A_DAY; 
    }

    /**  
     *  This method is combining every part together, waiting to be called from main class in order to show the chart
     *  @return <String chart: complete version of chart of every thing>
     */
    public String toString()
    {   
        final int TIME_CHANGING_GAP = 6; //6 is the hour gap from night to morning, morning to noon and so on

        readFile(); //initial readFile() to store information into arraylist

        String chart = "This month has " + showDays() + " days" + "\n"; //the line show number of days in one month

        while(lowBound <= 18 && highBound <= 23)
        {
            //combine every part together to be final chart
            if(allInfo != null)
                chart += zone() + "\tAverage Temp =" + String.format("%7.1f", avgTemperature()) 
                + "\n" + printTime() + "\n" + printDivider() + "\n" + stat(); 

            lowBound += TIME_CHANGING_GAP; //increase lowBound to get next zone of a day
            highBound += TIME_CHANGING_GAP; //increase highBound to get next zone of a day
        }

        return chart;
    }
}
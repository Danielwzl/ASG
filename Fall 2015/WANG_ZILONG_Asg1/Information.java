/**
 * This class is created information object which contains days, times and temperatures
 * this will be put into Arraylist as a single class
 * @author  Zilong Wang 
 * @version 1.0
 * Last Modified: <09-09-2015> - <adding comments> <Zilong Wang>
 */
public class Information
{
    private int day, time;
    private double temperature;

    /**  
     *  This is a constructor of Information class
     */
    public Information(int day, int time, double temperature)
    {
        this.day = day;
        this.time = time;
        this.temperature = temperature;
    }

    /**
     * This is a getter
     * @return <day>
     */
    public int getDay()
    {
        return day; 
    }

    /**
     * This is a getter
     * @return <time>
     */
    public int getTime()
    {
        return time;
    }

    /**
     * This is a getter
     * @return <temperature>
     */
    public double getTemperature()
    {
        return temperature;
    }
}

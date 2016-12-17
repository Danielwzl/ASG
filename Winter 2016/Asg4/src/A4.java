import java.util.Scanner;

/**
 * This program is to read inputs the configuration 
 * for a square then outputs the number of areas 
 * and the size of each area ordered from largest to smallest.
 * @author Zilong Wang
 * @LastModified: <02-24-2016> - <adding comment> <Zilong Wang>
 * @version 1.0
 */
public class A4
{
    public final static int BLACK = 0; //black space
    private int[][] picture; //the grid need to be checked
    private int size, counter, copyCounter; //copyCounter is the copy version of counter, it used as the index of result list 
    private int[] result; //hold the result of how many sub-space of one large white space 

    public static void main(String[] args)
    {
	A4 task = new A4();
	task.run();
    }

    /**
     * run the whole program
     */
    public void run()
    {
	inputFile();
	checkPicture(0, 0);
	java.util.Arrays.sort(result);
	outputAll();
    }

    /**
     * read from input stream and store data into 2D array
     */
    private void inputFile()
    {
	Scanner scan = new Scanner(System.in);
	int row = 0, col = 0;
	size = scan.nextInt();
	picture = new int[size][size];
	while(scan.hasNext())
	{
	    if(col == size) //reach the end of each row
	    {
		row++; //jump to next row
		col = 0; //jump to left
	    }
	    if(row == size) break; //exceed to the bottom of the array, stop it
	    picture[row][col++] = scan.nextInt();
	}
    }

    /**
     * start at each point in the picture, to find white spaces
     * define the size of result list and put area of each white space into the list
     * @param row
     * @param col
     */
    private void checkPicture(int row, int col)
    {
	int temp = 0; //the size of each white area
	if(col == size) //reach the end of row
	{
	    row++; //jump to next row
	    col = 0; //return to the beginning of the row
	}
	if(row != size) //at end of picture
	{
	    if(!isBlack(row, col))
	    {
		temp = findWhiteSpace(row, col); //if there is white space, check how large it is
		counter++; //how big the result list need to be
	    }
	    checkPicture(row, ++col); //go to next point in the picture
	    if(temp != 0) result[copyCounter--] = temp; //if there is white are, store the size into list
	}
	else // this case happens before storing temp into list
	{ 
	    result = new int[copyCounter = counter]; //define the size of the result list
	    copyCounter--; //use it as temp index of result list
	}
    }

    /**
     * find the white space at each start point
     * @param row
     * @param col
     * @return how many sub-spaces in this whole white space
     */
    private int findWhiteSpace(int row, int col)
    {
	if(isInBound(row, col))
	{
	    if(isBlack(row, col)) return 0;
	    else
	    {
		picture[row][col] = BLACK; //set visited as black
		return findWhiteSpace(row - 1, col) + findWhiteSpace(row, col + 1) + findWhiteSpace(row + 1, col)
			+ findWhiteSpace(row, col - 1) + 1;
	    }
	}
	return 0;
    }

    /**
     * check if the spot is black
     * @param row
     * @param col
     * @return true if it is black
     */
    private boolean isBlack(int row, int col)
    {
	return picture[row][col] == BLACK;
    }

    /**
     * check if the point is inside of picture
     * @param row
     * @param col
     * @return true if it is inside
     */
    private boolean isInBound(int row, int col)
    {
	return row >= 0 && col >= 0 && row < size && col < size;
    }

    /**
     * output all result
     */
    private void outputAll()
    {
	outputSummary();
	if(counter > 0) outputDetail();
    }

    /**
     * output how many white area in the picture
     */
    private void outputSummary()
    {
	System.out.println("There are " + counter + " white areas");
    }

    /**
     * output how large each white area is
     */
    private void outputDetail()
    {
	int[] resultCount = countResult();
	int oneResult = 0, oneResultCount;
	for(int i = counter - 1; i >= 0; i--)
	{
	    oneResult = result[i];
	    oneResultCount = resultCount[oneResult];
	    if(oneResultCount != 0)
	    {
		System.out.println(oneResultCount + " x " + oneResult);
		resultCount[oneResult] = 0;
	    }
	}
    }

    /**
     * count the frequency of areas with same size
     * @return list of frequency of areas with same size
     */
    private int[] countResult()
    {
	int[] resultCount = new int[result[counter - 1] + 1];
	for(int i = counter - 1; i >= 0; i--)
	    resultCount[result[i]]++;
	return resultCount;
    }
}

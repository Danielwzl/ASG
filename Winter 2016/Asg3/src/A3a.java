import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Palindromes - to check if the word is Palindromes
 * 
 * @author Zilong Wang Last Modified: <02-16-2016> - <change printing function> <Zilong Wang>
 * 
 * @version 1.0
 */
public class A3a
{
    private Stack<Integer> originalList, copyList, reversedList;

    public static void main(String[] args)
    {
	A3a task = new A3a();
	try
	{
	    task.run();
	}
	catch(IOException e)
	{
	    System.out.println("File reading Error!");
	}
    }

    /**
     * get the data and run the whole program
     */
    private void run() throws IOException
    {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	originalList = new Stack<Integer>();
	copyList = new Stack<Integer>();
	reversedList = new Stack<Integer>();
	int ascii = 0;
	while((ascii = br.read()) != -1)
	{
	    if(ascii == 32 || (ascii < 14 && ascii > 8))
	    {
		if(!originalList.isEmpty())
		{
		    reverseList();
		    outputData();
		    clear();
		}
	    }
	    else generateList(ascii);
	}
    }

    /**
     * add the data into the Stack and generate representation of the word
     * 
     * @param ascii: the Ascii value of character
     */
    private void generateList(int ascii)
    {
	originalList.push(ascii);
	copyList.push(ascii);
    }

    /**
     * generate a new Stack with reversed order of data make copy list as what
     * it was before popping
     */
    private void reverseList()
    {
	Integer ascii = null;
	if(!copyList.isEmpty())
	{
	    ascii = copyList.pop();
	    reversedList.push(ascii);
	    reverseList(); //keep copyList as before
	    copyList.push(ascii);
	}
    }

    /**
     * check if each single character are same
     * 
     * @return true if they are same
     */
    private boolean check()
    {
	while(!originalList.isEmpty())
	    if(originalList.pop() != reversedList.pop()) return false;
	return true;
    }

    /**
     * output all data
     */
    private void outputData()
    {
	presentWord();
	if(!check()) System.out.println(": No");
	else System.out.println(": Yes");
    }

    /**
     * print the word
     */
    private void presentWord()
    {
	Integer ascii = null;
	if(!copyList.isEmpty())
	{
	    ascii = copyList.pop();
	    presentWord();
	    System.out.print((char)(int)ascii);
	}
    }

    /**
     * clear the list and String for next time use
     */
    private void clear()
    {
	originalList.empty();
	reversedList.empty();
    }
}

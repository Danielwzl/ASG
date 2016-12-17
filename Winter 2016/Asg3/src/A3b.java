import java.util.Scanner;

/**
 * Long addtion
 * 
 * @author Zilong Wang Last Modified: <02-08-2016> - <adding comments> <Zilong
 *         Wang>
 * @version 1.0
 */
public class A3b
{
    private Stack<String> number, otherNumber, sum;
    private String num1, num2;

    public static void main(String[] args)
    {
	A3b task = new A3b();
	task.run();
    }

    /**
     * read from input and run the program
     */
    private void run()
    {
	String[] info = null;
	number = new Stack<String>();
	otherNumber = new Stack<String>();
	Scanner scan = new Scanner(System.in);
	while(scan.hasNext())
	{
	    info = scan.nextLine().trim().split("\\s+");
	    if(info.length == 2)
	    {
		num1 = isValid(info[0]);
		num2 = isValid(info[1]);
		if(num1 != null && num2 != null)
		{
		    generateLists();
		    addition();
		    outputData();
		    clear();
		}
		else error();
	    }
	    else error();
	}
    }

    /**
     * read in data from system
     */
    private void generateLists()
    {
	generateLists(num1, number);
	generateLists(num2, otherNumber);
    }

    /**
     * push single digit from String of number into the Stack
     * 
     * @param num: number String from input
     * @param numberList: the stack contain this number String
     */
    private void generateLists(String num, Stack<String> numberList)
    {
	for(int i = 0, len = num.length(); i < len; i++)
	    numberList.push(new Character(num.charAt(i)).toString());
    }

    /**
     * perform addition between two numbers
     */
    private void addition()
    {
	int tempSum = 0;
	int carryBit = 0;
	sum = new Stack<String>();
	Stack<String> longerStack = pickLongerStack(number, otherNumber);
	while(!longerStack.isEmpty())
	{
	    tempSum = digitOf(number) + digitOf(otherNumber) + carryBit; //adding
	    carryBit = tempSum / 10;
	    sum.push((tempSum % 10) + ""); //store the digit which is not carryBit
	}
	if(carryBit == 1) sum.push("1"); //the last carry bit need to be add as very first digit of sum if there is carry bit
    }

    /**
     * get the stack which has larger size, in order to make complete addition
     * of these two numbers;
     * 
     * @param first: one Stack
     * @param second: another Stack
     * @return the stack which has larger size
     */
    private Stack<String> pickLongerStack(Stack<String> first, Stack<String> second)
    {
	return first.size() > second.size() ? first : second;
    }

    /**
     * get a single digit from the number stack
     * 
     * @param number
     * @return single digit of the number
     * @return 0 if stack is empty
     */
    private int digitOf(Stack<String> number)
    {
	if(number.isEmpty()) return 0;
	return Integer.parseInt(number.pop());
    }

    /**
     * output all data
     */
    private void outputData()
    {
	String number = "";
	while(!sum.isEmpty())
	    number += sum.pop(); //output sum
	System.out.println("   " + whiteSpace(number, num1) + num1); //align number at the right side
	System.out.println(" + " + whiteSpace(number, num2) + num2);
	System.out.println(" = " + number);
	System.out.println();
    }

    /**
     * error message
     */
    private void error()
    {
	System.err.println("This pair contains invalid value!");
    }

    /**
     * check if input is valid number
     * 
     * @param number
     * @return valid number
     */
    private String isValid(String number)
    {
	if(number.matches("\\d+")) return number;
	return null;
    }

    /**
     * make a String of spaces to format the output the standard is sum, because
     * sum is the longest number
     * 
     * @param sum: sum of two numbers
     * @param num: one number
     * @return String of spaces needed
     */
    private String whiteSpace(String sum, String num)
    {
	String space = "";
	for(int i = 0; i < sum.length() - num.length(); i++)
	    space += " ";
	return space;
    }

    /**
     * clear the stack
     */
    private void clear()
    {
	sum.empty();
    }
}

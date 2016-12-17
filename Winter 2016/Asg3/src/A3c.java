import java.util.Scanner;

/**
 * Stock buy and sell
 * 
 * @author Zilong Wang Last Modified: <02-08-2016> - <add comment> <Zilong Wang>
 * 
 * @version 1.0
 */
public class A3c
{
    private Queue<Transaction> buyList;
    private int gain;
    public static final int INVALID = -1;

    public static void main(String[] args)
    {
	A3c a3 = new A3c();
	a3.transactionStart();
    }
    /*
     * Inner class Transaction holding amount and price
     */
    private class Transaction implements Comparable<Transaction>
    {
	private int amount;
	private int price;

	public Transaction(int amount, int price)
	{
	    this.amount = amount;
	    this.price = price;
	}

	public int getAmount()
	{
	    return amount;
	}

	public void setAmount(int amount)
	{
	    this.amount = amount;
	}

	public int compareTo(Transaction tran)
	{
	    return this.amount - tran.amount;
	}

	/**
	 * calculate the total of price of one transaction needed
	 * 
	 * @param amount
	 * @return total price of this transaction needed
	 */
	public int totalPrice(int amount)
	{
	    return amount * price;
	}
    }

    /**
     * read from input and start the whole program
     */
    private void transactionStart()
    {
	Scanner scan = new Scanner(System.in);
	buyList = new Queue<Transaction>();
	String action = "";
	String[] info = null;
	int amount = 0;
	int price = 0;
	while(scan.hasNext())
	{ //check if those info are valid
	    info = scan.nextLine().trim().split("\\s+");
	    if(info.length == 3)
	    {
		action = info[0];
		amount = isValid(info[1]);
		price = isValid(info[2]);
		if(amount != INVALID && price != INVALID) //amount must > 0, price must be >= 0
		{
		    if(action.equalsIgnoreCase("buy")) buyList.enqueue(new Transaction(amount, price));
		    else if(action.equalsIgnoreCase("sell")) sellStock(new Transaction(amount, price));
		    else error();
		}
		else error();
	    }
	    else error();
	}
	output();
	clear();
    }

    /**
     * Take sell from input and calculate the total gain
     * 
     * @param sell
     */
    private void sellStock(Transaction sell)
    {
	final int BE_POSITIVE = -1;
	int restOfHold = 0;
	Transaction oldestBuy = null;
	while((oldestBuy = buyList.peek()) != null && sell.getAmount() != 0)
	{
	    restOfHold = oldestBuy.compareTo(sell);
	    if(restOfHold > 0)
	    { // if amount of buy > amount of sell
		gain += calculateGain(sell, oldestBuy, sell.getAmount());
		oldestBuy.setAmount(restOfHold); // update new amount of buy(rest)
		sell.setAmount(0); //update amount of sell
	    }
	    else
	    { // if amount of buy < amount of sell
		gain += calculateGain(sell, oldestBuy, oldestBuy.getAmount());
		sell.setAmount(restOfHold * BE_POSITIVE); //update new amount of sell
		buyList.dequeue(); // remove finished transaction
	    }
	}
    }

    /**
     * calculate the gain from each transaction
     * 
     * @param sell
     * @param buy
     * @param amount
     * @return gain
     */
    private int calculateGain(Transaction sell, Transaction buy, int amount)
    {
	return sell.totalPrice(amount) - buy.totalPrice(amount);
    }

    /**
     * final output format
     */
    private void output()
    {
	System.out.println("Capital Gain: " + gain);
    }

    /**
     * Print error message
     */
    private void error()
    {
	System.err.println("This is not valid transaction!");
    }

    /**
     * check if number form input is valid
     * 
     * @param number
     * @return error number -1 if String is not valid
     * @return actual number
     */
    private int isValid(String number)
    {
	if(number.matches("\\d+"))
	{
	    Integer num = null;
	    try
	    { // could be BigInteger
		num = Integer.valueOf(number);
	    }
	    catch(Exception e)
	    {
		return INVALID;
	    }
	    return num;
	}
	return INVALID;
    }

    /*
     * empty the queue
     */
    private void clear()
    {
	buyList.empty();
    }
}

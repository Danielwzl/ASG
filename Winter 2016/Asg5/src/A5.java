import java.util.Scanner;
import java.util.Iterator;

/**
 * Read and count the frequency and length of words
 * reading height of tree comparing it with optimun height
 * @author Zilong Wang Last Modified: <02-29-2016> - <change lists to BST> <Zilong Wang>
 * 				      <03-13-2016> - <adding new functions> <Zilong Wang>
 * @version 3.0
 */
public class A5
{
    private static final int TOP = 20; // the number that at most data can show up
  //Store those number because some of list will lose and point to another one
    private int numOfStopWords, numOfUniqueWords, numOfRepeatWords, printCount,
    		alpHeight, freqHeight, lengHeight, sizeForFreqList, sizeForLengList;
    private BST<Word> wordsList; // original list in alphabetically order
    private BST<Word> secList; // least frequency and most frequency

    public static void main(String[] args)
    {
	A5 task = new A5();
	task.inputData();
	task.clearStopWords();
	task.outputData();
    }

    /**
     * read in data from system, count the number of unique words, stop words
     * and repeated words
     */
    private void inputData()
    {
	String term = "";
	Word temp = null, word = null;
	Scanner read = new Scanner(System.in);
	wordsList = new BST<Word>();
	while(read.hasNext())
	{
	    term = read.next().toLowerCase().replaceAll("[^a-z]", "").trim();
	    if(!term.isEmpty())
	    {
		if((temp = wordsList.search(word = new Word(term))) != null)
		{
		    numOfRepeatWords++;
		    temp.countWord();
		}
		else
		{
		    numOfUniqueWords++;
		    wordsList.insert(word);
		}
	    }
	}
	read.close();
    }

    /**
     * delete the stop words from tree
     */
    private void clearStopWords()
    {
	String[] ary = {"a", "about", "all", "am", "an", "and", "any", "are", "as", "at", "be", "been", "but", "by",
		"can", "cannot", "could", "did", "do", "does", "else", "for", "from", "get", "got", "had", "has",
		"have", "he", "her", "hers", "him", "his", "how", "i", "if", "in", "into", "is", "it", "its", "like",
		"more", "me", "my", "no", "now", "not", "of", "on", "one", "or", "our", "out", "said", "say", "says",
		"she", "so", "some", "than", "that", "thats", "the", "their", "them", "then", "there", "these", "they",
		"this", "to", "too", "us", "upon", "was", "we", "were", "what", "with", "when", "where", "which",
		"while", "who", "whom", "why", "will", "you", "your", "up", "down", "left", "right", "man", "woman",
		"would", "should", "dont", "after", "before", "im", "men"};
	for(String one: ary)
	{
	    if(wordsList.delete(new Word(one)) != null)
	    {
		numOfUniqueWords--; //Subtract the stopwords
		numOfStopWords++;
	    }
	}
    }

    /**
     * output all data
     */
    private void outputData()
    {
	System.out.println("\n------\n");
	System.out.println("Total Words: " + (numOfRepeatWords + numOfUniqueWords + numOfStopWords));
	System.out.println("Stop Words: " + numOfStopWords);
	System.out.println("Unique Words: " + numOfUniqueWords);
	System.out.println("\n------\n");
	printMostFreqWords();
	System.out.println("\n------\n");
	printLeastFreqWords();
	System.out.println("\n------\n");
	printLengthSummay();
	System.out.println("\n------\n");
	printAllWords();
	System.out.println("\n------\n");
	printFooter();
	System.out.println("\n------\n");
    }

    /**
     * print the at most 10 words showing up most of time in descending order
     * 
     * @param printCount
     */
    private void printMostFreqWords()
    {
	System.out.println("20 Most Frequent");
	secList = new BST<Word>(Word.mostFreq);
	reorderTree();
	freqHeight = secList.height();
	sizeForFreqList = secList.size();
	printCount = sizeForFreqList > TOP ? TOP : sizeForFreqList; 
	printTops();
    }

    /**
     * print the at most 10 words showing up least of time in ascending order
     * 
     * @param printCount
     */
    private void printLeastFreqWords()
    {
	System.out.println("20 Least Frequent");
	for(int i = sizeForFreqList - 1, len = i - printCount; i > len; i--)
	{
	    System.out.println(secList.get(i));
	}
    }

    /**
     * generate new tree with length of words from most to least and print summary
     */
    private void printLengthSummay()
    {
	secList = new BST<Word>(Word.mostLen);
	Iterator<Word> itr = wordsList.iterator();
	while(itr.hasNext())
	{
	    secList.insert(itr.next());
	}
	lengHeight = secList.height();
	sizeForLengList = secList.size();
	System.out.println("The longest word is " + secList.getVeryLeft());
	System.out.println("The average word length is " + avg());
	secList = null; // pass object to gc
    }

    /**
     * generate a new tree odered by frequency 
     */
    private void reorderTree()
    {
	Iterator<Word> itr = wordsList.iterator();
	Word temp = null;
	while(itr.hasNext())
	{
	    temp = itr.next();
	    if(temp.getCounter() > 2) secList.insert(temp);
	}
    }

    /**
     * calculate the average length of whole words in the length tree
     * @return averge length
     */
    private int avg()
    {
	if(sizeForLengList == 0) return 0;
	int sum = 0;
	Iterator<Word> itr = secList.iterator();
	while(itr.hasNext())
	{
	    sum += itr.next().getLength();
	}
	return sum / sizeForLengList;
    }

    /**
     * print all words in alphabetically order
     */
    private void printAllWords()
    {
	alpHeight = wordsList.height();
	System.out.println("All Words");
	Iterator<Word> itr = wordsList.iterator();
	while(itr.hasNext())
	{
	    System.out.println(itr.next());
	}
    }

    /**
     * print heigth of each trees and optimun height
     */
    private void printFooter()
    {
	System.out.println("Alphabetic Tree: ( Optimum Height: " 
			+ optimunHeight(wordsList.size())
			+ ") ( Actual Height: " + alpHeight + ")");
	System.out.println("Frequency Tree: ( Optimum Height: " 
			+ optimunHeight(sizeForFreqList) + ") ( Actual Height: "
			+ freqHeight + ")");
	System.out.println("Length Tree: ( Optimum Height: " 
			+ optimunHeight(sizeForLengList) + ") ( Actual Height: "
			+ lengHeight + ")");
    }

    /**
     * MAX(node) = 2^h - 1, get the h which is the optimum height
     * if at this height, the node that tree contains > the actual node in the tree
     * height - 1 will be the optimum height
     * @param size: how many node are in the tree
     * @return height
     */
    private int optimunHeight(int size)
    {
	if(size == 0) return 0;
	return (int)(Math.round(Math.log(size) / Math.log(2)));
//	double height = 0.0, maxNode = 0.0;
//	while(size > maxNode)
//	{
//	    height++;
//	    maxNode = Math.pow(2.0, height) - 1;    
//	}
//	return (int)height;
    }

    /**
     * print top_X
     * 
     * @param printCount
     */
    private void printTops()
    {
	int tempCount = printCount;
	Iterator<Word> itr = secList.iterator();
	while(itr.hasNext())
	{
	    if(tempCount == 0) break;
	    System.out.println(itr.next());
	    tempCount--;
	}
    }
}

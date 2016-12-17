import java.util.Scanner;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * Read and count the frequency and length of words
 * 
 * @author Zilong Wang Last Modified: <04-05-2016> - <Using HashMap and TreeMap>
 *         <Zilong Wang> <03-13-2016> - <adding new functions> <Zilong Wang>
 * @version 4.0
 */
public class A6
{
    private static final int TOP = 20;
    private int numOfStopWords, numOfUniqueWords, numOfRepeatWords, printCount, size, totalLen, totalFreq;;
    private HashMap<String, Word> wordsList;
    private TreeMap<String, Word> firstList, secList, trdList;
    /*
     * cpr class compare the most frequency
     */
    private Comparator<String> mostFreq = new Comparator<String>()
    {
	public int compare(String e1, String e2)
	{
	    return Word.mostFreq.compare(wordsList.get(e1), wordsList.get(e2));
	}
    };
    /**
     * cpr compare the length of words
     */
    private Comparator<String> mostLen = new Comparator<String>()
    {
	public int compare(String e1, String e2)
	{
	    return Word.mostLen.compare(wordsList.get(e1), wordsList.get(e2));
	}
    };

    public static void main(String[] args)
    {
	A6 task = new A6();
	task.inputData();
	task.clearStopWords();
	task.generateTree();
	task.outputData();
    }

    /**
     * read in data from system, count the number of unique words, stop words
     * and repeated words
     */
    private void inputData()
    {
	String term = "";
	Scanner read = new Scanner(System.in);
	wordsList = new HashMap<String, Word>();
	while(read.hasNext())
	{
	    term = read.next().toLowerCase().replaceAll("[^a-z]", "").trim();
	    if(!term.isEmpty())
	    {
		if(wordsList.containsKey(term))
		{
		    numOfRepeatWords++;
		    wordsList.get(term).countWord();
		}
		else
		{
		    numOfUniqueWords++;
		    wordsList.put(term, new Word(term));
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
	    if(wordsList.remove(one) != null)
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
	numOfRepeatWords = numOfStopWords = numOfUniqueWords = 0;
	printDash();
	printMostFreqWords(printCount);
	printDash();
	printFreqStats();
	secList = null;
	printDash();
	printLengStats();
	trdList = null;
	printDash();
	printAllWords();
	printDash();
    }

    /**
     * print dash
     */
    private void printDash()
    {
	System.out.println("\n------\n");
    }

    /**
     * print Statistics for Word Frequencies
     */
    private void printFreqStats()
    {
	int avg = average(totalFreq);
	System.out.println("Statistics for Word Frequencies");
	if(avg != 0)
	{
	    System.out.println("The most frequent word is " + secList.firstEntry().getValue());
	    System.out.println("The least frequent word is " + secList.lastEntry().getValue());
	    System.out.println("The average word frequency is " + avg);
	}
	else
	{
	    System.out.println("The most frequent word is None");
	    System.out.println("The least frequent word is None");
	    System.out.println("The average word frequency is None");
	}
    }

    /**
     * print Statistics for Word Length
     */
    private void printLengStats()
    {
	int avg = average(totalLen);
	System.out.println("Statistics for Word Length");
	if(avg != 0)
	{
	    System.out.println("The longest word is " + trdList.firstEntry().getValue());
	    System.out.println("The shortest word is " + trdList.lastEntry().getValue());
	    System.out.println("The average word length is " + avg);
	}
	else
	{
	    System.out.println("The longest word is None");
	    System.out.println("The shortest word is None");
	    System.out.println("The average word length is None");
	}
    }

    /**
     * print the at most 10 words showing up most of time in descending order
     */
    private void printMostFreqWords(int printCount)
    {
	System.out.println("20 Most Frequent");
	for(Iterator<Entry<String, Word>> itr = secList.entrySet().iterator(); printCount-- > 0;)
	    System.out.println(itr.next().getValue());
    }

    /**
     * print all words in alphabetically order
     */
    private void printAllWords()
    {
	System.out.println("All Words");
	for(Iterator<Entry<String, Word>> itr = firstList.entrySet().iterator(); itr.hasNext();)
	    System.out.println(itr.next().getValue());
    }

    /**
     * calculate avg of a attr
     * 
     * @return avg
     */
    private int average(int total)
    {
	return total = size == 0 ? 0 : total / size;
    }

    /**
     * put all from hashmap to treemap and calculate total
     */
    private void generateTree()
    {
	Entry<String, Word> entry = null;
	Word word = null;
	firstList = new TreeMap<String, Word>();
	secList = new TreeMap<String, Word>(mostFreq);
	trdList = new TreeMap<String, Word>(mostLen);
	for(Iterator<Entry<String, Word>> itr = wordsList.entrySet().iterator(); itr.hasNext();)
	{
	    entry = itr.next();
	    word = entry.getValue();
	    firstList.put(entry.getKey(), word);
	    secList.put(entry.getKey(), word);
	    trdList.put(entry.getKey(), word);
	    totalLen += word.getLength();
	    totalFreq += word.getCounter();
	}
	size = wordsList.size();
	printCount = size > TOP ? TOP : size;
	wordsList = null;
    }
}

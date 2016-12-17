

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class A1
{
    private static final int TOP = 10; // the number that at most can show up as
				       // final output
    private Scanner scan;
    private int numOfstopWords, numOfUniqueWords, numOfRepeatWords;
    private ArrayList<Word> wordsList;

    public static void main(String[] args)
    {
	A1 task = new A1();
	task.inputData();
	task.outputData();
    }

    /**
     * input date from command line
     */
    private void inputData()
    {
	scan = new Scanner(System.in);
	Word newWord = null;
	String singleWord = "";
	final String STOP_WORDS = "a, about, all, am, an, and, any, are, as, at, be, been, but, "
		+ "by, can, cannot, could, did, do, does, else, for, from, get, got, had, has, have, "
		+ "he, her, hers, him, his, how, i, if, in, into, is, it, its, like, more, me, my, no, "
		+ "now, not, of, on, one, or, our, out, said, say, says, she, so, some, than, that, the, "
		+ "their, them, then, there, these, they, this, to, too, us, upon, was, we, were, what, "
		+ "with, when, where, which, while, who, whom, why, will, you, your";
	ArrayList<String> stopWordsList = new ArrayList<String>(Arrays.asList(STOP_WORDS.split(", ")));
	wordsList = new ArrayList<Word>();
	while(scan.hasNext())
	{
	    if(!stopWordsList.contains(singleWord = scan.next().toLowerCase().replaceAll("\\W|\\d", "").trim()))
	    {
		if(!singleWord.isEmpty())
		{
		    if(!wordsList.contains(newWord = new Word(singleWord)))
		    {
			wordsList.add(newWord.countWord());
			numOfUniqueWords++;
		    }
		    else numOfRepeatWords++;
		}
	    }
	    else numOfstopWords++;
	}
    }

    /**
     * output data through command line
     */
    private void outputData()
    {
	System.out.println("Total Words: " + (numOfRepeatWords + numOfUniqueWords + numOfstopWords));
	System.out.println("Unique Words: " + numOfUniqueWords);
	System.out.println("Stop Words: " + numOfstopWords);
	printMostFreqWords();
	printLeastFreqWords();
	printAllWords();
    }

    /**
     * print the 10 words showing up most of time in decreasing order
     */
    private void printMostFreqWords()
    {
	System.out.println("\n10 Most Frequent");
	Collections.sort(wordsList);
	showSortedInfo();
    }

    /**
     * print the 10 words showing up least of time in increasing order
     */
    private void printLeastFreqWords()
    {
	System.out.println("\n10 Least Frequent");
	Collections.sort(wordsList, new Comparator<Word>()
	{
	    public int compare(Word word1, Word word2)
	    {
		int result = new Integer(word1.counter).compareTo(new Integer(word2.counter));
		return result == 0 ? word1.compare(word1, word2) : result;
	    }
	});
	showSortedInfo();
    }

    /**
     * print all words in alphabetically order
     */
    private void printAllWords()
    {
	System.out.println("\nAll");
	Collections.sort(wordsList, new Comparator<Word>()
	{
	    public int compare(Word word1, Word word2)
	    {
		return word1.compare(word1, word2);
	    }
	});
	for(Word word: wordsList)
	    System.out.println(word);
    }

    /**
     * print the data that being sorted ofr Top-N section
     */
    private void showSortedInfo()
    {
	for(int i = 0, len = maxDataToShow(); i < len; i++)
	    System.out.println(wordsList.get(i));
    }

    /**
     * to get if the unique words is more than 10, if not tell other method how
     * many words should be shown
     * 
     * @return <number of how many words should be shown>
     */
    private int maxDataToShow()
    {
	return numOfUniqueWords >= TOP ? TOP : numOfUniqueWords;
    }
}

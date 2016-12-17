import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Read and count the frequency of words
 * 
 * @author Zilong Wang Last Modified: <01-22-2016> - <adding comments, deleting
 *         some unused method> <Zilong Wang>
 * @version 2.0
 */
public class A2
{
    private static final int TOP = 10; // the number that at most data can show
				       // up
    private int numOfstopWords, numOfUniqueWords, numOfRepeatWords;
    private SLL<Word> wordsList; // original list in alphabetically order
    private SLL<Word> secList; // the second list with frequency
			       // in ascending order or descending order

    public static void main(String[] args)
    {
	A2 task = new A2();
	task.inputData();
	task.outputData();
    }

    /**
     * read in data from system, count the number of unique words, stop words
     * and repeated words
     */
    private void inputData()
    {
	final ArrayList<String> STOP_WORDS_LIST = generateStopWordList();
	String term = "";
	Scanner read = new Scanner(System.in);
	wordsList = new SLL<Word>();
	while(read.hasNext())
	{
	    term = read.next().toLowerCase().replaceAll("[^a-z]", "").trim();
	    if(!term.isEmpty())
	    {
		if(STOP_WORDS_LIST.contains(term)) numOfstopWords++;
		else if(wordsList.contains(new Word(term))) numOfRepeatWords++;
		else
		{
		    numOfUniqueWords++;
		    wordsList.add(new Word(term));
		}
	    }
	}
	read.close(); // close the stream
    }

    /**
     * output all data
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
     * print the at most 10 words showing up most of time in descending order
     */
    private void printMostFreqWords()
    {
	secList = wordsList.reOrderList(new Comparator<Word>()
	{
	    public int compare(Word word, Word other)
	    {
		int freqDifference = other.getCount() - word.getCount();
		return freqDifference == 0 ? word.compareTo(other) : freqDifference;
	    }
	});
	System.out.println("\n10 Most Frequent");
	secList.getUntil(maxDataToShow());
    }

    /**
     * print the at most 10 words showing up least of time in ascending order
     */
    private void printLeastFreqWords()
    {
	secList = secList.reOrderList(Word.cpr);
	System.out.println("\n10 Least Frequent");
	secList.getUntil(maxDataToShow());
	secList = null; // pass object to gc
    }

    /**
     * print all words in alphabetically order
     */
    private void printAllWords()
    {
	System.out.println("\nAll");
	wordsList.getAll();
    }

    /**
     * generate the list of stop words
     * 
     * @return list of stop words
     */
    private ArrayList<String> generateStopWordList()
    {
	final String STOP_WORDS = "a, about, all, am, an, and, any, are, as, at, be, been, but, "
		+ "by, can, cannot, could, did, do, does, else, for, from, get, got, had, has, have, "
		+ "he, her, hers, him, his, how, i, if, in, into, is, it, its, like, more, me, my, no, "
		+ "now, not, of, on, one, or, our, out, said, say, says, she, so, some, than, that, the, "
		+ "their, them, then, there, these, they, this, to, too, us, upon, was, we, were, what, "
		+ "with, when, where, which, while, who, whom, why, will, you, your";
	return new ArrayList<String>(Arrays.asList(STOP_WORDS.split(", ")));
    }

    /**
     * if top > 10, then show top 10. otherwise show the max data the list has
     * in top-ten section
     * 
     * @return the max data show in one section
     */
    private int maxDataToShow()
    {
	return numOfUniqueWords > TOP ? TOP : numOfUniqueWords;
    }
}

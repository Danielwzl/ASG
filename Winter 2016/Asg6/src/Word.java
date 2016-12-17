import java.util.Comparator;

/**
 * entity class of word with compare function
 * 
 * @author Zilong Wang Last Modified: <02-29-2016> - <increment counter outside
 *         equal function> <Zilong Wang> <02-29-2016> - <put all comparator
 *         inside of word class> <Zilong Wang> <03-13-2016> - <new comparator
 *         for length> <Zilong Wang>
 * @version 2.0
 */
public class Word implements Comparable<Word>
{
    private String term;
    private int counter;
    private int length;

    public Word(String term)
    {
	this.term = term;
	length = term.length();
	counter = 1;
    }
    /**
     * inner class with new way of comparing method: most frequent
     */
    public static Comparator<Word> mostFreq = new Comparator<Word>()
    {
	@Override
	public int compare(Word word, Word other)
	{
	    int freqDifference = other.counter - word.counter;
	    return freqDifference == 0 ? word.compareTo(other) : freqDifference;
	}
    };
    /**
     * inner class that compare length of word from most to least
     */
    public static Comparator<Word> mostLen = new Comparator<Word>()
    {
	@Override
	public int compare(Word word, Word other)
	{
	    int res = other.length - word.length;
	    return res == 0 ? word.compareTo(other) : res;
	}
    };

    @Override
    public int compareTo(Word word)
    {
	return this.term.compareTo(word.term);
    }

    /**
     * incrementing the counter of same word
     */
    public void countWord()
    {
	counter++;
    }

    public int getCounter()
    {
	return counter;
    }

    public int getLength()
    {
	return length;
    }

    public String toString()
    {
	return term + " : " + length + " : " + counter;
    }
}
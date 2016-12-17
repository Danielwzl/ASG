import java.util.Comparator;

/**
 * entity class of word with compare function
 * 
 * @author Zilong Wang Last Modified: <02-29-2016> - <increment counter outside equal function> <Zilong Wang> 
 * 				      <02-29-2016> - <put all comparator inside of word class> <Zilong Wang>
 * 				      <03-13-2016> - <new comparator for length> <Zilong Wang>
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
    /*
     * inner class with new way of comparing method: least frequent
     * only from the frequency > 1
     */
    public static Comparator<Word> leastFreq = new Comparator<Word>()
    {
	@Override
	public int compare(Word word, Word other)
	{
	    int freqDifference = word.counter - other.counter;
	    return freqDifference == 0 ? other.compareTo(word) : freqDifference;
	}
    };
    /**
     * inner class with new way of comparing method: most frequent
     * only from the frequency > 1
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
    
    public static Comparator<Word> mostLen = new Comparator<Word>()
    {
	@Override
	public int compare(Word word, Word other)
	{
	    return other.length - word.length;
	}   
    };

    @Override
    public boolean equals(Object obj)
    {
	if(this == obj) return true;
	if(!(obj instanceof Word)) return false;
	return this.term.equals(((Word)obj).term);
    }

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
    
    public String getTerm()
    {
	return term;
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
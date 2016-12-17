import java.util.Comparator;

/**
 * entity class of word with compare function
 * 
 * @author Zilong Wang Last Modified: <01-22-2016> - <adding comments, adjust
 *         compare method> <Zilong Wang>
 * @version 2.0
 */
public class Word implements Comparable<Word>
{
    private String term;
    private int counter;

    public Word(String term)
    {
	this.term = term;
	counter = 1;
    }
    /**
     * inner class with new way of comparing method
     */
    public static Comparator<Word> cpr = new Comparator<Word>()
    {
	@Override
	public int compare(Word word, Word other)
	{
	    int freqDifference = word.counter - other.counter;
	    return freqDifference == 0 ? word.compareTo(other) : freqDifference;
	}
    };

    @Override
    public boolean equals(Object obj)
    {
	if(this == obj) return true;
	if(!(obj instanceof Word)) return false;
	else
	{
	    if(this.term.equals(((Word) obj).term))
	    {
		counter++;
		return true;
	    }
	    return false;
	}
    }

    @Override
    public int compareTo(Word word)
    {
	return this.term.compareTo(word.term);
    }

    public int getCount()
    {
	return counter;
    }

    public String toString()
    {
	return term + " : " + counter;
    }
}

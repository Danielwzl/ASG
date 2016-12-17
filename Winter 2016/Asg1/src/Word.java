
import java.util.Comparator;

public class Word implements Comparable<Word>, Comparator<Word>
{
    public int counter; //count how many time this word show up in the file
    private String term;

    public Word(String term){this.term = term;}

    public String getTerm(){return term;}

    /**
     * To count the number, if this word is the first time to show up
     * @return <this word>
     */
    public Word countWord()
    {
        counter++;
        return this;
    }

    /**
     * To get the length of the word
     * @return <length>
     */
    public int length(){return term.length();}

    /**
     * To check if two words are same
     * @param <obj>
     * @return <true if they are same>
     */
    @Override
    public boolean equals(Object obj)
    {
        Word temp = null;
        
        if(!(obj instanceof Word)) return false;
        else temp = (Word)obj;
        
        if(this == obj) return true;
        
        if(this.term.equals(temp.term))
        {
            temp.counter++;
            return true;
        }
        return false;
    }

    /**
     * Check each word by comparing how many time they show up in the file, larger one will put ahead of smaller one
     * If two words have same times of showing in the file, then comparing them alphabetically
     * @param <word>
     * @return <minus: this word > new word; positive: this word < new word>
     */
    @Override
    public int compareTo(Word word)
    {
        int result = new Integer(word.counter).compareTo(new Integer(this.counter));
        return result == 0 ? compare(this, word) : result; //if the number of these two words are same, compare them alphabetically
    }
  
    /**
     * Check each word letter by letter to make them to be in order
     * @param <word1>
     * @param <word2>
     * @return <minus: word1 < word2; positive: word1 > word2, 0: equal>
     */
    @Override
    public int compare(Word word1, Word word2)
    {
        final int FIRST_INDEX = 0;
        return compareLetters(word1, word2, FIRST_INDEX);
    }

    /**
     * Check each word letter by letter to make them to be in order
     * No need to check two words are equal, method call it did this task
     * @param <word1>
     * @param <word2>
     * @param <indexOfLetter>
     * @return <minus: word1 < word2; positive: word1 > word2>
     */
    private int compareLetters(Word word1, Word word2, int indexOfLetter)
    {
        if(indexOfLetter == word1.length() || indexOfLetter == word2.term.length()) //check if index reaches the max index. 
            return word1.length() - word2.length(); //if reaches, put the one has smaller length ahead of another one
        char letter1 = word1.term.charAt(indexOfLetter);
        char letter2 = word2.term.charAt(indexOfLetter);
        if(letter1 == letter2) return compareLetters(word1, word2, ++indexOfLetter);
        return letter1 - letter2;
    }

    public String toString(){return term + " : " + counter;}
}

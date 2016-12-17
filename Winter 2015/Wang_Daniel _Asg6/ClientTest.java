
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ClientTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ClientTest
{
    /**
     * Default constructor for test class ClientTest
     */
    public ClientTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testIsGenderSought()
    {
        Client client1 = new Client("DANIEL WANG", "M", "F");
        assertEquals(true, client1.isGenderSought("F"));
        assertEquals(false, client1.isGenderSought("M"));
        client1.setGenderSought("E");
        assertEquals(true, client1.isGenderSought("F"));
        assertEquals(true, client1.isGenderSought("M"));
    }

    public void testIsGender()
    {
        Client client1 = new Client("DANIEL WANG", "M", "F");
        assertEquals(false, client1.isGender("F"));
        assertEquals(true, client1.isGender("M"));
        assertEquals(true, client1.isGender("E"));
        client1.setGenderSought("E");
        assertEquals(true, client1.isGender("F"));
        assertEquals(true, client1.isGender("M"));
        assertEquals(true, client1.isGender("E"));
    }

    @Test
    public void testToString()
    {
        Client client1 = new Client("DANIEL WANG", "M", "F");
        client1.setMyMatchIndex(1);
        assertEquals("DANIEL WANG M F (Matched)\n", client1.toString());
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.setMyMatchIndex(-1);
        assertEquals("DANIEL WANG M F POKING POKING POKING POKING POKING POKING POKING POKING POKING POKING POKING (Not matched)\n", client1.toString());
    }

    @Test
    public void testAddInterest()
    {
        Client client1 = new Client("DANIEL WANG", "M", "F");
        assertEquals("DANIEL WANG M F (Matched)\n", client1.toString());
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        client1.addInterest("POKING");
        assertEquals("DANIEL WANG M F POKING POKING POKING POKING POKING POKING POKING POKING POKING POKING POKING (Matched)\n", client1.toString());
    }

    @Test
    public void testInterestNum()
    {
        Client ALTHES = new Client("ALTHES", "M", "F");
        ALTHES.addInterest("POKING");
        ALTHES.addInterest("POKING");
        ALTHES.addInterest("POKING");        
        Client JOINA = new Client("JOINA", "F", "M");
        JOINA.addInterest("POKING");
        JOINA.addInterest("POKING");
        JOINA.addInterest("POKING");
        JOINA.addInterest("MAGIC");
        assertEquals(9, ALTHES.numMatchingInterests(JOINA));
        Client ALTHES1 = new Client("ALTHES", "M", "F");
        ALTHES1.addInterest("POKING");
        ALTHES1.addInterest("KILL");
        ALTHES1.addInterest("AHHH");  
        Client JOINA1 = new Client("JOINA", "F", "M");
        JOINA1.addInterest("POKING");
        JOINA1.addInterest("MAKEUP");
        JOINA1.addInterest("SHOPPING");
        JOINA1.addInterest("MAGIC");
        assertEquals(1, ALTHES1.numMatchingInterests(JOINA1));
    }
}


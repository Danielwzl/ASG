
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;
import java.util.*;

/**
 * The test class MatchMakerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MatchMakerTest
{

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void test() {

    }

    @Test
    public void addClient()throws IOException
    {
        MatchMaker maker = new MatchMaker("clients.txt");
        Client client = new Client("DANIEL WANG", "M", "F");
        client.addInterest("LOL");
        client.addInterest("WOW");
        client.addInterest("PIANO");
        client.setMyMatchIndex(-1);
        assertEquals("No match found" ,maker.addClient(client));
        Client client1 = new Client("DANIEL WANG", "M", "F");
        client1.addInterest("POKER");
        client1.addInterest("BIRDS");
        client1.addInterest("PIANO");
        client1.setMyMatchIndex(-1);
        assertEquals("DANIEL WANG is matched with GAIL CHANG" ,maker.addClient(client1));
    }
}	


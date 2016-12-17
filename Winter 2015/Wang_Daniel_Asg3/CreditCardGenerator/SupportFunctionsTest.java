

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SupportFunctionsTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SupportFunctionsTest
{
    /**
     * Default constructor for test class SupportFunctionsTest
     */
    public SupportFunctionsTest()
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
    public void instructorProvidedGenerateCardNoTest0()
    {
        // test that card numbers are 16 characters long
        SupportFunctions supportF1 = new SupportFunctions();
        
        
        for (int numCalls = 0; numCalls < 1000; numCalls++)
            assertEquals(true, supportF1.generateCardNo(12, 'B', 'B').matches("\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}") );
            
        assertEquals(true, supportF1.generateCardNo( 1, 'B', 'B').matches("\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}") );
        assertEquals(true, supportF1.generateCardNo(1, 'A', 'I').matches("\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}") );
        assertEquals(19, supportF1.generateCardNo(0, 'B', 'B').length() );
    }
    
    @Test
    public void instructorProvidedGenerateCardNoTest1()
    {
        // test that card numbers begin with a leading digit of > 1
        SupportFunctions supportF1 = new SupportFunctions();
        
        for (int numCalls = 0; numCalls < 1000; numCalls++)
            assertEquals(true, supportF1.generateCardNo(12, 'B', 'B').matches("[2-9].*") );
    }

    
    @Test
    public void instructorProvidedGenerateCardNoTest2()
    {
        // test that card numbers end with a valid checksum
        SupportFunctions supportF1 = new SupportFunctions();
        
        assertEquals(8, supportF1.checkSum(2, 5000, 80, 300));
        assertEquals(6, supportF1.checkSum(7, 3000, 50, 100));
        assertEquals(4, supportF1.checkSum(3, 1000, 40, 600));
    }
    
    @Test
    public void instructorProvidedGenerateCardNoTest3()
    {
        // test that card numbers correctly encode the expiry date
        SupportFunctions supportF1 = new SupportFunctions();
        
        assertEquals(true, supportF1.generateCardNo( 12, 'B', 'B').matches(".*21.....") );
        assertEquals(true, supportF1.generateCardNo(1, 'B', 'B').matches(".*10.....") );
        assertEquals(true, supportF1.generateCardNo(5, 'B', 'B').matches(".*50.....") );
        assertEquals(true, supportF1.generateCardNo(11, 'B', 'B').matches(".*11.....") );
        assertEquals(true, supportF1.generateCardNo(12, 'B', 'B').matches(".*21.....") );
    }
    
     @Test
    public void instructorProvidedGenerateCardNoTest4()
    {
        // test that card numbers correctly encode the cardholder initials
        SupportFunctions supportF1 = new SupportFunctions();
        
        assertEquals(true, supportF1.generateCardNo(12, 'B', 'B').matches("...4.*") );
        assertEquals(true, supportF1.generateCardNo(12, 'A', 'A').matches("...2.*") );
        assertEquals(true, supportF1.generateCardNo(12, 'D', 'E').matches("...9.*") );
        assertEquals(true, supportF1.generateCardNo(12, 'D', 'F').matches("...0.*") );
        assertEquals(true, supportF1.generateCardNo(12, 'Q', 'X').matches("...1.*") );
        assertEquals(true, supportF1.generateCardNo(12, 'Z', 'Z').matches("...2.*") );
    }
    
    @Test
    public void instructorProvidedFormatNameTest1()
    {
        // test that formatName preserves already-correct formatting
        SupportFunctions supportF1 = new SupportFunctions();
        assertEquals("Bilbo", supportF1.formatName("Bilbo"));
    }
    
    @Test
    public void instructorProvidedFormatNameTest2()
    {
        // test that formatName deals with all lower case input
        SupportFunctions supportF1 = new SupportFunctions();
        assertEquals("Frodo", supportF1.formatName("frodo"));
    }
    
    @Test
    public void instructorProvidedFormatNameTest3()
    {
        // test that formatName deals with all upper case input
        SupportFunctions supportF1 = new SupportFunctions();
        assertEquals("Samwise", supportF1.formatName("SAMWISE"));
    }
    
    @Test
    public void instructorProvidedFormatNameTest4()
    {
        // a few more acid tests
        SupportFunctions supportF1 = new SupportFunctions();
        assertEquals("A", supportF1.formatName("a"));
        assertEquals("Z", supportF1.formatName("Z"));
        assertEquals("Merry", supportF1.formatName("mErRy"));
        assertEquals("Pippin", supportF1.formatName("PiPpIn"));
    }
}





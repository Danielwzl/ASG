

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class BodyTestTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class BodyTestTest
{
    /**
     * Default constructor for test class BodyTestTest
     */
    public BodyTestTest()
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
    public void T1()
    {
        BodyTest bodyTest1 = new BodyTest();
        assertEquals(0.4, bodyTest1.adultBodyFat(20, 1, 10, 1), 0.1);
    }

    @Test
    public void T2()
    {
        BodyTest bodyTest1 = new BodyTest();
        assertEquals(-17.17, bodyTest1.adultBodyFat(1, 1, -1, 1), 0.1);
    }

    @Test
    public void T3()
    {
        BodyTest bodyTest2 = new BodyTest();
        assertEquals(-15.97, bodyTest2.adultBodyFat(1, 1, 0, 1), 0.1);
    }

    @Test
    public void T4()
    {
        BodyTest bodyTest1 = new BodyTest();
        assertEquals(0, bodyTest1.adultBodyFat(0, 1, 22.5, 2), 0.1);
    }

    @Test
    public void T5()
    {
        BodyTest bodyTest1 = new BodyTest();
        assertEquals(0.0, bodyTest1.adultBodyFat(540, 1, 0, 11), 0.1);
    }

    @Test
    public void T6()
    {
        BodyTest bodyTest1 = new BodyTest();

    }
}







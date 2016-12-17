
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class StandingChartTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class StandingChartTest
{
    /**
     * Default constructor for test class StandingChartTest
     */
    public StandingChartTest()
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
    public void testChart()
    {
        StandingChart standing0 = new StandingChart(4, 3, 4.0);
        assertEquals("\n4\t<-- GPA = 4.00\n-\n-\n3\n-\n-\n2\n-\n-\n1\n-\n-\n0\n", standing0.toString());
        StandingChart standing1 = new StandingChart(4, 3, 3.99999);
        assertEquals("\n4\n-\t<-- GPA = 3.99\n-\n3\n-\n-\n2\n-\n-\n1\n-\n-\n0\n", standing1.toString());
        StandingChart standing2 = new StandingChart(4, 3, 3.67);
        assertEquals("\n4\n-\t<-- GPA = 3.67\n-\n3\n-\n-\n2\n-\n-\n1\n-\n-\n0\n", standing2.toString());
        StandingChart standing3 = new StandingChart(4, 3, 3.66666);
        assertEquals("\n4\n-\n-\t<-- GPA = 3.66\n3\n-\n-\n2\n-\n-\n1\n-\n-\n0\n", standing3.toString());
        StandingChart standing4 = new StandingChart(4, 3, 3.34);
        assertEquals("\n4\n-\n-\t<-- GPA = 3.34\n3\n-\n-\n2\n-\n-\n1\n-\n-\n0\n", standing4.toString());
        StandingChart standing5 = new StandingChart(4, 3, 3.33333);
        assertEquals("\n4\n-\n-\n3\t<-- GPA = 3.33\n-\n-\n2\n-\n-\n1\n-\n-\n0\n", standing5.toString());
        StandingChart standing6 = new StandingChart(4, 3, 3.0);
        assertEquals("\n4\n-\n-\n3\t<-- GPA = 3.00\n-\n-\n2\n-\n-\n1\n-\n-\n0\n", standing6.toString());
        StandingChart standing7 = new StandingChart(4, 3, 0.0);
        assertEquals("\n4\n-\n-\n3\n-\n-\n2\n-\n-\n1\n-\n-\n0\t<-- GPA = 0.00\n", standing7.toString());
        StandingChart standing8 = new StandingChart(1, 1, 0.0);
        assertEquals("\n1\n0\t<-- GPA = 0.00\n", standing8.toString());
        StandingChart standing9 = new StandingChart(4, 2, 3.5);
        assertEquals("\n4\n-\t<-- GPA = 3.50\n3\n-\n2\n-\n1\n-\n0\n", standing9.toString());
    }
}


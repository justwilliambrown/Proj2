import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ProjectTest for student test cases.
 * Add all new test cases to this task.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ProjectTest
{
	/**
	 * Default constructor for test class ProjectTest
	 */
	public ProjectTest()
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

	//TODO add new test cases from here include brief documentation

	@Test(timeout=1000)
	public void testSensibleToStringSize() {
        NgramAnalyser analyser = new NgramAnalyser("aabcabfaacfaaac"); 
        int minLines = analyser.getAlphabetSize() + 1;
        //This next bit is ridiculously inefficient memory wise but I'm lazy so there.
        String[] lines = analyser.toString().split("\r\n|\r|\n");
        int linesLength = lines.length;
        //System.out.println(linesLength);
        //System.out.println(minLines);
        assertTrue(linesLength >= minLines);
      
	}


	@Test(timeout=1000)
	public void testGetDistinctNgrams() {
		NgramAnalyser analyser = new NgramAnalyser("aaa");
		NgramAnalyser analyser1 = new NgramAnalyser(3, "abc");
		NgramAnalyser analyser2 = new NgramAnalyser(3, "bcbabbcbc");
		NgramAnalyser analyser3 = new NgramAnalyser(2, "baba");

		assertEquals(analyser.getDistinctNgrams().size(),1);
		assertEquals(analyser1.getDistinctNgrams().size(),3);
		assertEquals(analyser2.getDistinctNgrams().size(),6);
		assertEquals(analyser3.getDistinctNgrams().size(),2);



	}

	@Test(timeout=1000)
	public void testLaplaceExample() {
		MarkovModel mkMdl = new MarkovModel(2, "aabcabaacaac");
		double c = mkMdl.laplaceEstimate("aac");
		double b = mkMdl.laplaceEstimate("aab");
		double a = mkMdl.laplaceEstimate("aaa");
		//System.out.println(c);
		assertTrue(c >= 0.4999 && c <= 0.5001);
		assertTrue(b >= 0.3332 && b <= 0.3334);
		assertTrue(a >= 0.1666 && a <= 0.1668);
	}

	@Test(timeout=1000)
	public void testSimpleExample() {
		MarkovModel mkMdl = new MarkovModel(2, "aabcabaacaac");
		double b = mkMdl.simpleEstimate("aab");
		//System.out.println(b);
		assertTrue(b == (1.0/3.0));
	}

	@Test
	public void testTask3example() 
	{
		MarkovModel model = new MarkovModel(2,"aabcabaacaac");
		ModelMatcher match = new ModelMatcher(model,"aabbcaac");
		//Precision issues are amazing...
		assertEquals(-0.3849, match.getAverageLogLikelihood(), 0.0001);
	}
}

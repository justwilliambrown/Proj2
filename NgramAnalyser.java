import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Perform n-gram analysis of a string.
 * 
 * Analyses the frequency with which distinct n-grams, of length n,
 * appear in an input string. For the purposes of all analyses of the input
 * string, the final n-1 n-grams appearing in the string should be
 * "filled out" to a length of n characters, by adding
 * a sequence of contiguous characters from the start of the string.
 * e.g. "abbc" includes "bca" and "cab" in its 3-grams
 * 
 * @author William Chesnutt 22250495
 * @version 23/05/2017
 */
public class NgramAnalyser
{
	/** dictionary of all distinct n-grams and their frequencies */
	private HashMap<String,Integer> ngram;

	/** number of distinct characters in the input */
	private int alphabetSize;

	/** n-gram size for this object (new field) */
	private int ngramSize;

	/** Number of total characters in input */
	private int stringSize = 0;

	/** 
	 * Analyse the frequency with which distinct n-grams, of length n,
	 * appear in an input string. 
	 * n-grams at the end of the string wrap to the front
	 * e.g. "abbbbc" includes "bca" and "cab" in its 3-grams
	 * @param int n size of n-grams to create
	 * @param String inp input string to be modelled
	 */

	public NgramAnalyser(int n, String inp) 
	{     	
		ngram = new HashMap<String, Integer>();
		ngramSize = n;

		// Check for validity
		if (inp == "" || inp == null || n == 0 || inp.length() < n)
		{
			throw new IllegalArgumentException("Invalid Input");
		}

		// Do the string size thing
		stringSize = inp.length();

		int curPos = 0;
		// Splice the n characters (including curPos)
		// Check if they match any ngrams currently in the hashmap
		// If there are: Increase frequency by 1
		// If there aren't: Add it with a frequency of 1
		for (curPos = 0; curPos < (inp.length()); curPos++)
		{	
			String thisNgram = "";
			if (curPos + n <= inp.length())
			{
				thisNgram = inp.substring(curPos, (curPos+n));
			}
			else
			{
				String fromEnd = inp.substring(curPos, (inp.length()));
				String fromStart = inp.substring(0, (n - fromEnd.length()));
				thisNgram = fromEnd + fromStart;
			}

			if (ngram.containsKey(thisNgram))
			{
				ngram.put(thisNgram, (ngram.get(thisNgram) + 1));
			} else
			{
				ngram.put(thisNgram, 1);
				ngramSize++;
			}
		}

		// TODO Count number of unique characters
		if(n == 1)
		{
			alphabetSize = ngram.size();

		}
		else
		{
			alphabetSize = new NgramAnalyser(inp).getAlphabetSize();
		}
	}

	/** 
	 * Analyses the input text for n-grams of size 1.
	 */
	public NgramAnalyser(String inp) 
	{
		this(1,inp);
	}

	/**
	 * @return int the size of the alphabet of a given input
	 */
	public int getAlphabetSize() {
		return alphabetSize;
	}

	/**
	 * @return the total number of distinct n-grams appearing
	 *         in the input text.
	 */
	public int getDistinctNgramCount() {
		System.out.println(ngram.size());
		return(ngram.size());
	}

	/** 
	 * @return Return a set containing all the distinct n-grams
	 *         in the input string.
	 */
	public Set<String> getDistinctNgrams() {
		return(ngram.keySet());
	}

	/**
	 * @return the total number of n-grams appearing
	 *         in the input text (not requiring them to be distinct)
	 */
	public int getNgramCount() {
		return stringSize;
	}

	/** Return the frequency with which a particular n-gram appears
	 * in the text. If it does not appear at all, return 0.
	 * 
	 * @param ngram The n-gram to get the frequency of
	 * @return The frequency with which the n-gram appears.
	 */
	public int getNgramFrequency(String ngramm) {
		if (ngram.get(ngramm) == null)
		{
			return 0;
		}
		else
		{
			return ngram.get(ngramm);
		}
	}

	/**
	 * Generate a summary of the ngrams for this object.
	 * @return a string representation of the n-grams in the input text 
	 * comprising the ngram size and then each ngram and its frequency
	 * where ngrams are presented in alphabetical order.     
	 */
	public String toString()
	{		
		SortedSet<String> keysArray = new TreeSet<String>(ngram.keySet());
		String toRet = "";
		toRet += (ngramSize + "\n");
		for (String key : keysArray)
		{
			Integer freq = ngram.get(key);
			String freqS = Integer.toString(freq); 
			String thisKey = (key + " " + freqS + "\n");
			toRet += thisKey;
		}		
		return toRet;
	}

}

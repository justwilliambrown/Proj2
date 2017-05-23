import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import java.util.HashSet;
import java.util.Arrays;

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
        //TODO replace this line with your code || No fuck off
    	
    	// Check for validity
    	if (inp == "" || inp == null || n == 0 || inp.length() < n)
    	{
    		throw new IllegalArgumentException("Invalid Input");
    	}
    	
    	int cur_pos = 0;
    	// Splice the n characters (including cur_pos)
    	// Check if they match any ngrams currently in the hashmap
    	// If there are: Increase frequency by 1
    	// If there aren't: Add it with a frequency of 1
    	for (cur_pos = 0; cur_pos <= (inp.length() + 1); cur_pos++)
    	{
    		String thisNgram = "";
    		if (cur_pos + n < inp.length()){
    			thisNgram = inp.substring(cur_pos, (cur_pos+n));
    		}
    		else
    		{
    			String fromEnd = inp.substring(cur_pos, (inp.length() + 1));
    			String fromStart = inp.substring(0, (n - fromEnd.length() + 1));
    			thisNgram = fromEnd + fromStart;
    		}
    		
    		if (ngram.containsKey(thisNgram))
    		{
    			ngram.put(thisNgram, (ngram.get(thisNgram) + 1));
    		} else
    		{
    			ngram.put(thisNgram, 1);
    		}
    	}
    	
    	// Deal with the wraparound
    	int fromEnd = (inp.length() % n);
    	int fromStart = (n - fromEnd);
    	String wrapNgram = inp.substring(inp.length() - fromEnd, (inp.length() + 1)) + inp.substring(0, fromStart + 1);
    	if (ngram.containsKey(wrapNgram))
		{
			ngram.put(wrapNgram, (ngram.get(wrapNgram) + 1));
		} else
		{
			ngram.put(wrapNgram, 1);
		}
    	
    	
    	// TODO Count number of unique characters
    	// Fucked if I know how to do that.
    	
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
        //TODO replace this line with your code
        return -1;
    }

    /**
     * @return the total number of distinct n-grams appearing
     *         in the input text.
     */
    public int getDistinctNgramCount() {
        //TODO replace this line with your code
        return -1;
    }

    /** 
     * @return Return a set containing all the distinct n-grams
     *         in the input string.
     */
    public Set<String> getDistinctNgrams() {
        //TODO replace this line with your code
        return null;
    }

    /**
     * @return the total number of n-grams appearing
     *         in the input text (not requiring them to be distinct)
     */
    public int getNgramCount() {
        //TODO replace this line with your code
        return -1;
    }

    /** Return the frequency with which a particular n-gram appears
     * in the text. If it does not appear at all, return 0.
     * 
     * @param ngram The n-gram to get the frequency of
     * @return The frequency with which the n-gram appears.
     */
    public int getNgramFrequency(String ngram) {
        //TODO replace this line with your code
        return -1;
    }



    /**
     * Generate a summary of the ngrams for this object.
     * @return a string representation of the n-grams in the input text 
     * comprising the ngram size and then each ngram and its frequency
     * where ngrams are presented in alphabetical order.     
     */
    public String toString()
    {
        //TODO replace this line with your code
        return null;
    }

}

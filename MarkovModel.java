import java.util.Set;
/**
 * Construct a Markov model of order /k/ based on an input string.
 * 
 * @author William Chesnutt 22250495
 * @version 28/05/2017
 */
public class MarkovModel
{

	/** Markov model order parameter */
	int k; 
	/** ngram model of order k */
	NgramAnalyser ngram; 
	/** ngram model of order k+1 */
	NgramAnalyser n1gram; 

	/**
	 * Construct an order-k Markov model from string s
	 * @param k int order of the Markov model
	 * @param s String input to be modelled
	 */
	public MarkovModel(int k, String s) 
	{
		ngram = new NgramAnalyser(k, s);
		n1gram = new NgramAnalyser((k+1), s);
	}

	/**
	 * @return order of this Markov model
	 */
	public int getK()
	{
		return k;
	}

	/** Estimate the probability of a sequence appearing in the text 
	 * using simple estimate of freq seq / frequency front(seq).
	 * @param sequence String of length k+1
	 * @return double probability of the last letter occuring in the 
	 * context of the first ones or 0 if front(seq) does not occur.
	 */
	public double simpleEstimate(String sequence) {
		double prob;
		String seqNotLast = sequence.substring(0, sequence.length()-1);
		if (ngram.getDistinctNgrams().contains(seqNotLast))
		{
			try{
			prob = (n1gram.getNgramFrequency(sequence)/(ngram.getNgramFrequency(seqNotLast)));
			}
			catch(ArithmeticException e){
				return 0.0;
			}
			return prob;
		}
		else
		{
			return 0.0;
		}
	}
	/**
	 * Calculate the Laplacian probability of string obs given this Markov model
	 * @input sequence String of length k+1
	 * @return Laplacian Probability
	 */
	public double laplaceEstimate(String sequence) 
	{ 
		//TODO replace this line with your code
		//npc
		return -1.0;
	}

	/**
	 * @return String representing this Markov model
	 */
	public String toString()
	{
		//TODO replace this line with your code
		return null;
	}

}

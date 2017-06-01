import java.util.HashMap;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Report the average log likelihood of a test String occuring in a 
 * given Markov model and detail the calculated values behind this statistic.
 * 
 * @author William Chesnutt 22250495
 * @version 30/05/2017
 */
public class ModelMatcher
{

	/** log likelihoods for a teststring under a given model */
	private HashMap<String,Double> logLikelihoodMap;
	/** summary statistic for this setting */
	private double averageLogLikelihood;

	/**
	 * Constructor to initialise the fields for the log likelihood map for 
	 * a test string and a given Markov model and 
	 * the average log likelihood summary statistic
	 * @param MarkovModel model a given Markov model object
	 * @param String teststring the string to check compatability with the model
	 */
	public ModelMatcher(MarkovModel model, String testString)
	{
		logLikelihoodMap = new HashMap<String, Double>();
		int kVal = model.getK();
		String seq;
		for (int curPos = 0; (curPos) < testString.length(); curPos++)
		{
			if (curPos + (kVal + 1) <= testString.length())
			{
				seq = testString.substring(curPos, (curPos + kVal + 1));
			}
			else
			{
				String fromEnd = testString.substring(curPos, (testString.length()));
				String fromStart = testString.substring(0, ((kVal + 1) - fromEnd.length()));
				seq = fromEnd + fromStart;
			}

			// seq should be of length k+1
			// context should be the first k chars of seq
			String context = seq.substring(0, kVal);
			// impChar should be the last character of seq
			String impChar = seq.substring(kVal, seq.length()); 

			double loggedProb = Math.log10(model.laplaceEstimate(seq));
			if (logLikelihoodMap.containsKey(seq))
			{
				logLikelihoodMap.put(seq, (logLikelihoodMap.get(seq) + loggedProb));
			}
			else
			{
				logLikelihoodMap.put(seq, loggedProb);				
			}
		}
		averageLogLikelihood = averageLogLikelihood(logLikelihoodMap, testString.length());

	}

	/** Helper method that calculates the average log likelihood statistic
	 * given a HashMap of strings and their Laplace probabilities
	 * and the total number of ngrams in the model.
	 * 
	 * @param logs map of ngram strings and their log likelihood
	 * @param ngramCount int number of ngrams in the original test string
	 * @return average log likelihood: the total of loglikelihoods 
	 *    divided by the ngramCount
	 */
	private double averageLogLikelihood(HashMap<String,Double> logs, int ngramCount)
	{
		double totalLogs = 0.0;
		for (String i : logs.keySet())
		{
			totalLogs += logs.get(i);
		}
		double avg = totalLogs/ngramCount;
		return avg;
	}

	/** Helper method to calculate the total log likelihood statistic
	 * given a HashMap of strings and their Laplace probabilities
	 * and the total number of ngrams in the model.
	 * 
	 * @param logs map of ngram strings and their log likelihood
	 * @return total log likelihood: the sum of loglikelihoods in logs 
	 */
	private double totalLogLikelihood(HashMap<String,Double> logs)
	{
		double totalLogs = 0.0;
		for (String i : logs.keySet())
		{
			totalLogs += logs.get(i);
		}
		return totalLogs;
	}


	/**
	 * @return the average log likelihood statistic
	 */
	public double getAverageLogLikelihood() 
	{
		return averageLogLikelihood;
	}

	/**
	 * @param  ngram String the ngram to find the log likelihood of.
	 * @return the log likelihood value for a given ngram from the input string
	 */
	public double getLogLikelihood(String ngram) 
	{
		return (logLikelihoodMap.get(ngram));
	}


	/**
	 * Make a String summarising the log likelihood map and its statistics
	 * @return String of ngrams and their loglikeihood differences between the models
	 * The likelihood table should be ordered from highest to lowest likelihood
	 */
	public String toString() 
	{
		SortedSet<String> keysArray = new TreeSet<String>(logLikelihoodMap.keySet());
		String toRet = "";
		for (String key : keysArray)
		{
			double logLike = logLikelihoodMap.get(key);
			String logLikeS = Double.toString(logLike); 
			String thisKey = (key + "     " + logLikeS + "\n");
			toRet += thisKey;
		}
		return toRet;
	}


}

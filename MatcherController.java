import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/** Create and manipulate Markov models and model matchers for lists of training data 
 * a test data String and generate output from it for convenient display.
 * 
 * @author William Chesnutt 22250495
 * @version 31/05/2017
 *
 */
public class MatcherController {

	/** list of training data string used to generate markov models */
	ArrayList<String> trainingDataList;
	/** test data to be matched with the models */
	String testData;
	/** order of the markov models*/
	int k;
	/** generated list of markov models for the given training data*/
	ArrayList<MarkovModel> modelList;
	/** generated list of matchers for the given markov models and test data*/
	ArrayList<ModelMatcher> matcherList;


	/** Generate models for analysis
	 * @param k order of the markov models to be used
	 * @param trainingDataList List of strings used to generate Markov Models
	 * @param testData String to check against different models
	 * @throw unchecked exceptions if the input order or data inputs are invalid
	 */
	public MatcherController(int k, ArrayList<String> trainingDataList, String testData) 
	{
		if (k < 0)
		{
			throw new IllegalArgumentException("K cannot be less than zero");
		}
		if (testData == "" || testData == null)
		{
			throw new IllegalArgumentException("The data to test cannot be blank");
		}
		
		modelList = new ArrayList<MarkovModel>();
		matcherList = new ArrayList<ModelMatcher>();

		this.trainingDataList = trainingDataList;

		//Create a markov model for each entry in trainingDataList
		for (String i : trainingDataList)
		{
			modelList.add(new MarkovModel(k, i));
		}
		// For each Markov Model a Model Matcher
		for (MarkovModel i : modelList)
		{
			matcherList.add(new ModelMatcher(i, testData));
		}

		//Find the best modelmatcher
		ModelMatcher best = getBestMatch(matcherList);
		String bestMatch = explainBestMatch(best);
	}

	/** @return a string containing all lines from a file 
	 *  @param the name of the file to import
	 *  
	 * ff file contents can be got, otherwise null
	 * This method should process any exceptions that arise.
	 * @throws IOException 
	 */
	private static String getFileContents(String filename) throws IOException 
	{
		byte[] encoded = Files.readAllBytes(Paths.get(filename));
		return new String(encoded);
	}

	/**
	 * @param an arraylist of ModelMatchers
	 * @return the ModelMatcher object that has the highest average loglikelihood
	 * (where all candidates are trained for the same test string
	 */
	public ModelMatcher getBestMatch(ArrayList<ModelMatcher> candidates) 
	{
		ModelMatcher currentHighest = null;
		for (ModelMatcher i : candidates)
		{
			ModelMatcher thisLog = i;
			if (currentHighest == null)
			{
				currentHighest = i;
			}
			if (thisLog.getAverageLogLikelihood() > currentHighest.getAverageLogLikelihood())
			{
				currentHighest = thisLog;
			}
		}
		return currentHighest;
	}


	/** @return String an *explanation* of
	 * why the test string is the match from the candidate models
	 */
	public String explainBestMatch(ModelMatcher best)
	{
		double normalProb = Math.pow(10, best.getAverageLogLikelihood());
		String toRet = "This model was the best as the average likelihood was " + normalProb + "\n";
		return null;
	}

	/** Display an error to the user in a manner appropriate
	 * for the interface being used.
	 * 
	 * @param message
	 */
	public void displayError(String message) {
		// LEAVE THIS METHOD EMPTY
	}

}

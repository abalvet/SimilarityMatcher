/**
 * 
 */
package utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author antonio
 * @date 23 mars 2015 2015
 * SimilarityMatcher
 * utils
 * CombinationsGenerator.java
 * 
 */
public class CombinationsGenerator {
	
	public static synchronized List<String> getStrings(String[] segments, int k) {
	    int[] indexes = new int[k]; // prefills with 0
	    List<String> result = new ArrayList<String>();
	    boolean done = false;

	    while (!done) { // stopcondition is inside the loop
	        // construct 1 string with current indexes
	        StringBuffer buffer = new StringBuffer();
	        for (int i = 0; i < k; ++i) {
	        	//System.err.println("index i: " + indexes[i] + ", "+ i);
	        	//System.err.println("segments " + segments[indexes[i]]);
	            buffer.append(segments[indexes[i]]);
	        	
	        }
	        result.add(buffer.toString());

	        indexes[0]++;

	        for (int i = 0; i < k; ++i) {
	            if (indexes[i] == segments.length) { // overflow should go into i+1, set this index back to 0
	                if (i + 1 == k) {
	                    // loop done. would overflow.
	                    done = true;
	                    break;
	                } else {
	                    indexes[i] = 0;
	                    indexes[i + 1]++;
	                }
	            }
	        }
	    }

	    return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] segments = {"aa", "bb", "cc", "dd", "ee"};
		List<String> out = CombinationsGenerator.getStrings(segments, 2);
		System.out.println("2 combinations: " + out);

	}

}

/**
 * 
 */
package stats;

import java.util.ArrayList;
import java.util.HashSet;

import utils.SetOperations;

/**
 * @author antonio
 * @date 18 mars 2015 2015
 * SimilarityMatcher
 * stats
 * Tester.java
 * 
 */
public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<String> b = new ArrayList<String>();
		a.add("la");
		a.add("chambre");
		a.add("était");
		a.add("très");
		a.add("spacieuse");
		b.add("elle");
		b.add("était");
		b.add("très");
		b.add("spacieuse");
		b.add("la");
		b.add("chambre");
		System.out.println("Distances pour\n"  + a + "::" + b );
		System.out.println("Jaccard: " + Jaccard.computeJaccard(a, b));
		System.out.println("Sorensen: " + Sorensen.computeSorensen(a, b));
	}

}

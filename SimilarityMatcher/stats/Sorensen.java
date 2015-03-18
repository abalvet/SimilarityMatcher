/**
 * Sorensen similarity index.
 */
package stats;
import utils.*;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author antonio
 * @date 2015-03-18
 * 
 */
public class Sorensen {
	
	/***
	 * Compute the Sorensen index, based on S(A,B) = 2* |A inter B| / |A| + |B|
	 * @return the Sorensen index for samples A and B.
	 */
	
	public static double computeSorensen(ArrayList<String> A, ArrayList<String> B){
		double sor = 0;
		if(A.size() > 0 && B.size() >0){
			HashSet<String> ASet = new HashSet<String>();
			HashSet<String> BSet = new HashSet<String>();
			ASet.addAll(A);
			BSet.addAll(B);
			
			sor = ( (double) 2 * SetOperations.Intersection(ASet, BSet).size()) / ((double)A.size() + (double)B.size());
			return sor;
		}
		else{
			return 0;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<String> b = new ArrayList<String>();
		a.add("a");
		a.add("p");
		a.add("p");
		a.add("l");
		a.add("e");
		b.add("a");
		b.add("p");
		b.add("p");
		b.add("l");
		b.add("e");
		b.add("t");
		
		System.out.println("Sorensen: " + computeSorensen(a, b));
	}

}

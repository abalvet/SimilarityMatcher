package stats;

import java.util.ArrayList;
import java.util.HashSet;

import utils.SetOperations;

public class Jaccard {
	/***
	 * 
	 * @param A a set of elements (strings, words...)
	 * @param B another set of elements
	 * @return the Jaccard score
	 * Jaccard: J(A, B) = (A inter B) / (A union B)
	 */
	HashSet<String> _Intersection = new HashSet<String>();
	HashSet<String> _Union = new HashSet<String>();

	
	public static double computeJaccard(ArrayList<String> A, ArrayList<String> B){
		double jacc = 0;

		if(A.size() > 0 && B.size()>0){
			HashSet<String> ASet = new HashSet<String>();
			HashSet<String> BSet = new HashSet<String>();
			ASet.addAll(A);
			BSet.addAll(B);
			HashSet<String> _Intersection = SetOperations.Intersection(ASet, BSet);
			HashSet<String> _Union = SetOperations.Union(ASet, BSet);
		//System.err.println("A inter B: " + inter.size());
		//System.err.println("inter: " + inter);
		//System.err.println("A union B: " + union.size());
		//System.err.println("A union B: " + union);
			jacc = (double)_Intersection.size() / (double)_Union.size();
			return jacc;
		}
		else{
			return 0;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<String> b = new ArrayList<String>();
		a.add("A");
		a.add("B");
		a.add("C");
		a.add("D");
		b.add("A");
		b.add("F");
		b.add("F");
		b.add("D");
		b.add("Z");
		
		System.out.println("Jaccard: " + computeJaccard(a, b));
	}

}

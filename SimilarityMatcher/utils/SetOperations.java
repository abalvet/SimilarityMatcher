package utils;

import java.util.HashSet;

public class SetOperations {

	/**
	 * 
	 * @param A first set of Strings
	 * @param B second set of Strings
	 * @return the intersection
	 */
	public static HashSet<String> Intersection(HashSet<String> A, HashSet<String> B){
		HashSet<String> res = new HashSet<String>(A);
		res.retainAll(B);
		return res;
	}
	
	/**
	 * 
	 * @param A first set of Strings
	 * @param B second set of Strings
	 * @return the union
	 */
	public static HashSet<String> Union(HashSet<String> A, HashSet<String> B){
		HashSet<String> res = new HashSet<String>();

		
		for(String element : A){
			res.add(element);
		}
		
		for(String otherElement : B){
			res.add(otherElement);
		}
		return res;
	}
	/**
	 * 
	 * @param A first set of Strings
	 * @param B second set of Strings
	 * @return all 2-tuples composed from A x B
	 */
	public static HashSet<String> CartesianProduct(HashSet<String> A, HashSet<String> B){
		HashSet<String> res = new HashSet<String>();
		
		for(String a : A){
			for(String b: B){
				res.add("["+ a + "," + b + "]");
			}
		}
		return res;
	}
	
	
	/**
	 * 
	 * @param A first set
	 * @param B second set
	 * @return the bar-intersection of A and B, i.e. the subset of elements specific to A and B respectively. 
	 * Example A = {a, b, c, d}, B = {a, z, c, e, d}
	 * Intersection(A, B) = {a, c, d}
	 * Union(A, B) = {a, b, z, c, d, e}
	 * BarIntersection(A, B) = {b, z, e}
	 */
	/*public static HashSet<String> BarIntersection(HashSet<String> A, HashSet<String> B){
		HashSet<String> res = new HashSet<String>();
		HashSet<String> union = Union(A, B);
		HashSet<String> intersection = Intersection(A, B);
		union.removeAll(intersection);
		res = union;
		return res;	
	}*/
	public static HashSet<String> BarIntersection(HashSet<String> A, HashSet<String> B){
		//more native implementation
		HashSet<String> res = new HashSet<String>(A);
		res.addAll(B);
		HashSet<String> tmp = new HashSet<String>(A);
		tmp.retainAll(B);
		res.removeAll(tmp);
		return res;	
	}
	
	/**
	 * 
	 * @param A first set A
	 * @param B second set B
	 * @return the subset of elements specific to A only (A ~B)
	 */
	public static HashSet<String> A_BarB(HashSet<String> A, HashSet<String> B){
		HashSet<String> ASpec = new HashSet<String>(A);
		ASpec.removeAll(B);
		return ASpec;
	}
	
	/**
	 * 
	 * @param A first set A
	 * @param B second set B
	 * @return the subset of elements specific to B only (~A B)
	 */
	public static HashSet<String> B_BarA(HashSet<String> A, HashSet<String> B){
		HashSet<String> BSpec = new HashSet<String>(B);
		BSpec.removeAll(A);
		return BSpec;
	}
	
	final class pair{
		String[] _elts = new String[2];
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<String> A = new HashSet<String>();
		HashSet<String> B = new HashSet<String>();
		A.add("A");
		A.add("B");
		A.add("C");
		A.add("D");
		B.add("A");
		B.add("C");
		B.add("D");
		B.add("E");
		B.add("Z");
		
		
		System.out.println("Intersection: " + Intersection(A, B));
		System.out.println("Union:" + Union(A, B));
		System.out.println("BarIntersection: " + BarIntersection(A, B));
		System.out.println("SpecA: " + A_BarB(A, B));
		System.out.println("SpecB: " + B_BarA(A, B));
		System.out.println("CartesianProduct: " + CartesianProduct(A, B) );

	}

}

package utils;

import java.util.HashSet;

public class SetOperations {
	static HashSet<String> _ASet = new HashSet<String>();
	static HashSet<String> _BSet = new HashSet<String>();

	public static HashSet<String> Intersection(HashSet<String> A, HashSet<String> B){
		//_ASet.clear();
		//_BSet.clear();
		HashSet<String> res = new HashSet<String>();
		
		if(A.size() > B.size()){
			_ASet = A;
			_BSet = B;
		}
		else{
			_ASet = B;
			_BSet = A;
		}//_ASet is always the biggest set
		
		for(String element:A){
			if(B.contains(element)){
				res.add(element);
			}
		}
		return res;
	}
	
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
	
	public static void Product(HashSet<String> A, HashSet<String> B){}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<String> A = new HashSet<String>();
		HashSet<String> B = new HashSet<String>();
		A.add("A");
		A.add("B");
		A.add("C");
		A.add("D");
		B.add("A");
		B.add("F");
		B.add("G");
		B.add("C");
		
		
		System.out.println("Intersection: " + Intersection(A, B));
		
		
		System.out.println("Union:" + Union(A, B));
	}

}

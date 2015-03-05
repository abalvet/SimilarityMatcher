package lcsalgorithms;

public class RecursiveLCS {
	
	public static String rlcs(String a, String b){
	    int aLen = a.length();
	    int bLen = b.length();
	    if(aLen == 0 || bLen == 0){
	        return "";
	    }else if(a.charAt(aLen-1) == b.charAt(bLen-1)){
	        return rlcs(a.substring(0,aLen-1),b.substring(0,bLen-1))
	            + a.charAt(aLen-1);
	    }else{
	        String x = rlcs(a, b.substring(0,bLen-1));
	        String y = rlcs(a.substring(0,aLen-1), b);
	        return (x.length() > y.length()) ? x : y;
	    }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = args[0];
		String b = args[1];
		String lcs = rlcs(a, b);
		System.out.println("recursive LCS: " + lcs);
	}

}

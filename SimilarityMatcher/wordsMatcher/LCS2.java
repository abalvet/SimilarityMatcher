package wordsMatcher;


/**
 * @author Balvet Antonio
 * (C)Krister Ahlander
 * Donne la plus longue sous-chaîne commune entre 2 chaînes, éventuellement non contiguë.
 * Ex: kataba & katabu => katab, kitab & ktib => kib
 */
class LCS2 // Longest Common Subsequence
{

  String[][] _lcsTable ;

  String _X ;
  String _Y ;

  LCS2(String[] args)
  {
	_X = args[0] ;
	_Y = args[1] ;
	int m = _X.length() ;
	int n = _Y.length() ;

	_lcsTable = new String[m][n] ;
	
	String str = lcs(m-1,n-1) ;

	System.out.println( "LCS is " + str ) ;
	/*System.out.println("Last line:");
	for(int u = 0; u < n; u++){
	    System.out.println(_lcsTable[m-1][u] + "|");
	    }*/
  }

  String lcs(int i, int j) 
  {
	if(i==-1 || j==-1)
	    return "" ;

	if( _lcsTable[i][j] == null ) {
	    if(_X.charAt(i)==_Y.charAt(j) )
		_lcsTable[i][j] = lcs(i-1,j-1) + _X.charAt(i) ;
	    else {
		String s1 = lcs(i,j-1) ;
		String s2 = lcs(i-1,j) ;
		if ( s1.length() > s2.length() )
		_lcsTable[i][j] = s1 ;
	    else
		_lcsTable[i][j] = s2 ;
	    }
	}
	return _lcsTable[i][j] ;
  }


  void print() 
  {
    String table ="";
	System.out.println( "X = " + _X ) ;
	System.out.println( "Y = " + _Y ) ;

	System.out.println( "Table is as follows" ) ;
	int m = _X.length() ;
	int n = _Y.length() ;
	int L = lcs(m-1,n-1).length()+1 ;

	//System.out.print("\t") ;
	table = "\t";
	for(int j=0; j<n; j++) 
	    //System.out.print(":"+j + "\t" + L) ;
	    table += ":"+j + "\t" + L;
	//System.out.println() ;
	table += "\n";
	    
	for(int i=0; i<m; i++) {
	    //System.out.print("  " + i+"\t") ;
	    table += "  " + i+"\t";
	    for(int j=0; j<n; j++) 
		if(_lcsTable[i][j] == null)
		    //System.out.print("-"+ "\t" + L) ;
		    table += "-"+ "\t" + L;
		    
		else
		    //System.out.print(_lcsTable[i][j]+ "\t" + L) ;
		    table += _lcsTable[i][j]+ "\t" + L;

	    //System.out.println() ;
	    table += "\n";
	}
	System.out.println(table);
  }
  
  public static void main(String args[]) {
	if(args.length != 2)
	    System.out.println("Give two strings as parameters to program") ;
	else {
	    LCS2 lcs = new LCS2(args) ;
	    lcs.print() ;
	}
  }
}

/*
Sample execution:

kursa{krister}163: java LCS ACCGGT GTCGTT
Welcome to dynamic programming example
LCS is CGT
X = ACCGGT
Y = GTCGTT
Table is as follows
      0   1   2   3   4   5   
0                         -   
1             C   C   C   -   
2             C   C   C   -   
3     G   G   C   CG  CG  -   
4     -   -   -   CG  CG  -   
5     -   -   -   -   -   CGT 

*/

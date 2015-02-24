/*
 * Created on 1 sept. 06
 * Créé le 1 sept. 06
 */
package wordsMatcher;

public class CharacterLCS {
    String _x = "";
    String _y = "";
    String _LCS = "";
    String _verboseLCS = "";
    String _LCSStringTable = "";
    int[][] _LCSIntTable;
    int _M = 0;
    int _N = 0;
    int _diffs = 0;

    public void computeLCS(String x, String y){
        _x = x;
        _y = y;
        _M = _x.length();
        _N = _y.length();

        // opt[i][j] = length of LCS of x[i..M] and y[j..N]
        int[][] opt = new int[_M+1][_N+1];

        // compute length of LCS and all subproblems via dynamic programming
        for (int i = _M-1; i >= 0; i--) {
            for (int j = _N-1; j >= 0; j--) {
                if (_x.charAt(i) == _y.charAt(j)){
                    opt[i][j] = opt[i+1][j+1] + 1;
                }
                else{ 
                    opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);
                }
                
                _LCSStringTable += "[" + _x.charAt(i) + ":" + _y.charAt(j) + "]" + opt[i][j] + " ";
            }
            _LCSStringTable += "\n";
        }            
        _LCSIntTable = opt;
        
        String lcs = "";
        // recover LCS itself
        int i = 0, j = 0, diffs = 0;
        _diffs = 0;
        
        while(i < _M && j < _N) {
            if (_x.charAt(i) == _y.charAt(j)) {
                _verboseLCS += _x.charAt(i);
                lcs += _x.charAt(i);
                i++;
                j++;
            }
            else if (_LCSIntTable[i+1][j] >= _LCSIntTable[i][j+1]){
                _verboseLCS += "(x:" + i + "=" + _x.charAt(i) + "," + "y:" + j + "=" + _y.charAt(j) + ")";
                i++;
                diffs++;
                lcs += "-";
                }
            else{
                _verboseLCS += "(x:" + i + "=" + _x.charAt(i) + "," + "y:" + j + "=" + _y.charAt(j) + ")";
                j++;
                diffs++;
                lcs += "-";
                }                
        /*PB pour:
        - insertions multiples: ZAXBYCW/ABC => -A-B-C (attendu: -A-B-C-) 
        - mouvement: ABC/ACB => A-C
        - effacement: ABC/AB => A-C
        */
        /*System.out.println("\n" + lcs);
        System.out.println("\nDiffs: " + diffs);*/        
        }
        _LCS = lcs;
        _diffs = diffs;

    }
    
    public String getLCS(){
        return _LCS;
    }
    
    public int[][] getLCSIntTable(){
        return _LCSIntTable;
    }
    public String getLCSStringTable(){
        return _LCSStringTable;
    }
    public int getDiffs(){
        return _diffs;
    }
    
    /**
     * @param args: Strings à comparer
     */
    
    public static void main(String[] args)
        {
         String x = args[0];
         String y = args[1];
         CharacterLCS c = new CharacterLCS();
         c.computeLCS(x, y);
         System.out.println("LCS for: " + x + "," + y + "\n" + c.getLCS() + "\n" + c._verboseLCS);         
        }

}

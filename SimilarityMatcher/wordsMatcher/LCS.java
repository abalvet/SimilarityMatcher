/*
 * Created on 15 mars 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package wordsMatcher;

/**
 * @author Balvet Antonio
 * Donne la plus longue sous-chaîne commune, �ventuellement non contiguë de 2 chaînes.
 * Ex: kataba & katabu => k-t--b
 */
public class LCS {

    /*************************************************************************
     *  Compilation:  javac LCS
     *  Execution:    java LCS < example10.txt
     *  Dependencies: StdIn.java
     *  (C)Robert Sedgewick and Kevin Wayne.
     *  Reads in two strings from stdin and computes their longest
     *  common subsequence.
     *
     *************************************************************************/

 

        public static void main(String[] args) {
            String x = args[0];
            String y = args[1];
            int M = x.length();
            int N = y.length();

            // opt[i][j] = length of LCS of x[i..M] and y[j..N]
            int[][] opt = new int[M+1][N+1];

            // compute length of LCS and all subproblems via dynamic programming
            for (int i = M-1; i >= 0; i--) {
                for (int j = N-1; j >= 0; j--) {
                    if (x.charAt(i) == y.charAt(j))
                        opt[i][j] = opt[i+1][j+1] + 1;
                    else 
                        opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);
                    
                    System.out.print("[" + x.charAt(i) + ":" + y.charAt(j) + "]" + opt[i][j] + " ");
                }
                System.out.println();
            }            
            System.out.println("\nLCS for:\t[" + x + "],[" + y + "]");
            // recover LCS itself and print it to standard output
            int i = 0, j = 0, diffs = 0;
            String lcs = "";
            while(i < M && j < N) {
                if (x.charAt(i) == y.charAt(j)) {
                    System.out.print(x.charAt(i));
                    lcs += x.charAt(i);
                    i++;
                    j++;
                }
                else if (opt[i+1][j] >= opt[i][j+1])
                    {
                    System.out.print("(x:" + i + "=" + x.charAt(i) + "," + "y:" + j + "=" + y.charAt(j) + ")");
                    i++;
                    diffs++;
                    lcs += "-";
                    //System.out.print("-");                    
                    }
                else
                    {
                    System.out.print("(x:" + i + "=" + x.charAt(i) + "," + "y:" + j + "=" + y.charAt(j) + ")");
                    j++;
                    diffs++;
                    lcs += "-";
                    //System.out.print("-");
                    }                
            }/*PB pour:
            - insertions multiples: ZAXBYCW/ABC => -A-B-C (attendu: -A-B-C-) 
            - mouvement: ABC/ACB => A-C
            - effacement: ABC/AB => A-C
            */
            System.out.println("\n" + lcs);
            System.out.println("\nDiffs: " + diffs);

        }
}

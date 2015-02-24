/*
 * Created on 1 sept. 06
 * Créé le 1 sept. 06
 */
package wordsMatcher;

import java.lang.Math;

/**
 * The <code>LongestCommonSubsequence</code> class contains a constructor and
 * two public methods <code>toString</code> and <code>length</code>.
 * <p>
 * This LCS algorithm is based on the linear space algorithm of S.Kiran Kumar and C.Pandu Rangan.
 * It has runtime complexity O(n(m-p)) where m stands for the length of the first
 * string and n stands for the length of the second string and p stands for the length
 * of LCS.
 * <p>
 * For more information, see Acta Informatica 24, 353-362 (1987), Copyright Springer-Verlag 1987
 * <p>
 *
 * @author  Mikko Koivisto & Tuomo Saarni
 * @version 2.41, 07/05/01
 */

public class LongestCommonSubsequence
{

    // Global character arrays for the original Strings
    private static char[] A;
    private static char[] B;

    // Global integer variables representing the lenght of A and B strings
    private static int M;
    private static int N;

    // Global integer arrays needed in the computation of the LCS
    private static int[] R1, R2;
    private static int[] LL, LL1, LL2;

    // Global integer variables needed in the computation of the LCS
    private static int R, S;

    // Global integer variable representing the length of the LCS
    // formatted negative to reveal if the length is calculated already or not
    private static int P = -1;
    
    //Tonio
    static String[] _LCSPositions;
    static String[] _LCSGraph;
    static String[] _LCSString;
    static char[] _LCSChar;

    /**
    * Constructs a new LongestCommonSubsequence with two character
    * arrays a and b. Sets M and N to represent the length of a and b.
    *
    * @param a The first string
    * @param b The second string
    */
    public LongestCommonSubsequence(String a, String b)
    {
        // Creates global character arrays A and B from Strings a and b
        A = a.toCharArray();
        B = b.toCharArray();

        // Sets the length of A and B to the global variables M and N
        M = A.length;
        N = B.length;

        R1 = new int[N+1];
        R2 = new int[N+1];

        LL = new int[N+1];        
        LL1 = new int[N+1];
        LL2 = new int[N+1];

        P = -1;
    }

    /**
    * Calculates the length of LCS and returns it. If the length is
    * calculated already it'll not be calculated repeatedly.
    *
    * The time complexity is O(n(m-p)) and the space complexity is O(n+m).
    *
    * @return    The length of LCS
    */
    public int length()
    {
        if (P == -1)
        {
            P = calcOfP(0, M-1, 0, N-1, M, N);
        }
        return P;
    }

    /**
    * Computates the LCS and returns it.
    *
    * @return           The LCS String
    */
    public String toString()
    {
        if (P == -1)
        {
            P = this.length();
        }
        //Tonio
        char[] out = LCS(0, M-1, 0, N-1, M, N, P);
        String lcs = "";
        for(int i = 0; i < out.length; i++){
            lcs += out[i];
        }
        return lcs;
        //Tonio return (new String(LCS(0, M-1, 0, N-1, M, N, P)));
    }
    
    public char[] getLCSChar(){
        return _LCSChar;
    }

    /**
    * Computes the LCS and returns it in character array.
    *
    * The time complexity is O(n(m-p)) and the space complexity is O(n+m).
    *
    * @param a_start    The start index of the string a.
    * @param a_end      The last index of the string a.
    * @param b_start    The start index of the string b.
    * @param b_end      The last index of the string b.
    * @param m          The length of the a string.
    * @param n          The length of the b string.
    * @param p          The length of LCS between indexes a_start and a_end.
    *                   Similarly between indexes b_start and b-loppu.
    * @return           Character array consisting of the LCS string.
    */
    private char[] LCS(int a_start, int a_end, int b_start, int b_end, int m, int n, int p)
    {

        // The indexes of the perfect cut
        int u,v;
        
        //Tonio
        int maxL = Math.max(m, n);
        int minL = Math.min(m, n);
        _LCSPositions = new String[minL];
        _LCSGraph = new String[maxL];
        _LCSChar = new char[maxL];
        _LCSString = new String[maxL];//initialise tous les tableaux nécessaires
        for(int ind = 0; ind < maxL; ind++){
            _LCSString[ind] = "<E>";
            _LCSChar[ind] = '-';
        }
        
        if (m - p < 2)
        {
            //  Solves the base case
            // Waste is less than 2 characters

            char[] C = new char[p];     
            
            int waste = m - p;

            LL = calMid(a_start, a_end, b_start, b_end, m, n, 1, waste);

            int i = 1;

            while (i <= p && A[(i-1) + a_start] == B[(LL[p-i + 1]-1) + b_start])
            {
                //Tonio
                //System.out.println("SimPos: A:" + ((i-1) + a_start) + " & B:" + ((LL[p-i + 1]-1) + b_start));
                if(m > n){//A est plus long
                    _LCSChar[i-1 + a_start] = A[(i-1) + a_start];
                }
                else{
                    _LCSChar[(LL[p-i + 1]-1) + b_start] = B[(LL[p-i + 1]-1) + b_start];
                }//Tonio
                
                C[i-1] = A[(i-1) + a_start];
                i++;
            }                 
            i++;
            
            while (i <= m)
            {
                //Tonio
                //System.out.println("Pos: A:" + ((i-1) + a_start));
                _LCSChar[(i-1) + a_start] = A[(i-1) + a_start];
                //Tonio
                
                C[i-1-1] = A[(i-1) + a_start];
                i++;
            }
            return C;
        }
        else
        {
            // Waste is more than 1 character

            int r1, r2;

            int waste1 = (int)Math.ceil((m - p) / 2.0f);
            LL1 = calMid(a_end, a_start, b_end, b_start, m, n, -1, waste1);
            
            // Saves the value changed in calmid from global variable R to variable r1
            r1 = R;            
            for (int j=0; j<=r1; j++)
            {
                LL1[j] = n+1-LL1[j];
            }

            int waste2 = (int)Math.floor((m - p) / 2.0f);
            LL2 = calMid(a_start, a_end, b_start, b_end, m, n, 1, waste2);

            // Saves the value changed in calmid from global variable R to variable r2
            r2 = R;
            
            int k=Math.max(r1,r2);
            
            while (k>0)
            {
                if (k <= r1 && p - k <= r2 && LL1[k] < LL2[p-k])
                {break;}
                else
                {k--;}
            }

            u = k + waste1;
            v = LL1[k];


            char[] C1 = new char[0];
            char[] C2 = new char[0];

            C1 = LCS(a_start, a_start + u-1, b_start, b_start + v-1, u-1+1, v-1+1, u-waste1);
            C2 = LCS(a_start + u, a_end, b_start + v, b_end, a_end - a_start + 1 - u, b_end - b_start + 1 - v, m-u-waste2);

            // Combines the recursive answers from variables C1 and C2

            char[] C3 = new char[C1.length+C2.length];//TODO: ajouter une méthode qui retourne une LCS avec les positions

            System.arraycopy(C1, 0, C3, 0, C1.length);
            System.arraycopy(C2, 0, C3, C1.length, C2.length);
            
            return C3;
        }
    }

    /**
    * Uses integer arrays to keep track where the longest common subsequence can
    * can be found.
    *
    * The time complexity is O(n(waste+1)) and the space complexity is O(n+m).
    *
    * @param a_start    The start index of the string a.
    * @param a_end      The last index of the string a.
    * @param b_start    The start index of the string b.
    * @param b_end      The last index of the string b.
    * @param m          The length of the a string.
    * @param n          The length of the b string.
    * @param sign       This is used to mark wether to start from the beginning of the string
    *                   or from the end of the string.
    * @param waste      The length of characters not included in the LCS between indexes a_start and a_end.
    *                   Similarly between indexes b_start and b-loppu.
    *
    * @return           Integer array consisting of the ???.
    */
    private int[] calMid(int a_start, int a_end, int b_start, int b_end, int m, int n, int sign, int waste)
    {

        LL = new int[n+1];

        R = 0;
        for (S = m; S >= m - waste; S--)
        //  == (s:=m downto m-x do)
        {
            fillOne(a_start, a_end, b_start, b_end, m, n, sign);
            for (int j = 0; j <= R; j++)
            {
                R1[j] = R2[j];
            }
        }
        for (int j = 0; j <= R; j++)
        {
            LL[j] = R1[j];
        }
        
        return LL;

    } // calMid

    /**
    * This is used to find the index from where the longest common subsequence so far can
    * be found.
    *
    * The time complexity is O(n+m) and the space complexity is O(n).
    *
    * @param a_start    The start index of the string a.
    * @param a_end      The last index of the string a.
    * @param b_start    The start index of the string b.
    * @param b_end      The last index of the string b.
    * @param m          The length of the a string.
    * @param n          The length of the b string.
    * @param sign       This is used to mark wether to start from the beginning of the string
    *                   or from the end of the string.
    *
    */
    private void fillOne(int a_start, int a_end, int b_start, int b_end, int m, int n, int sign)
    {
        int j = 1;
        int i = S;
        boolean over = false;
        R2[0] = n+1;

        int lower_B = 0;
        int pos_B = 0;
        int temp = 0;

        while ( i > 0 & !over)
        {
            if (j > R)
            {
                lower_B = 0;
            }
            else
            {
                lower_B = R1[j];
            }
            pos_B = R2[j-1] - 1;

            // The real index in the global char table is current_index * sign + beginning
            // index of the subchararray

            while (pos_B > lower_B && A[(i-1) * sign + a_start] != B[(pos_B-1) * sign + b_start])
            {
                pos_B--;
            }
            temp = Math.max(pos_B, lower_B);
            if (temp == 0)
            {
                over = true;
            }
            else
            {
                R2[j] = temp;
                i = i - 1;
                j = j + 1;
            }
        }
        R = j - 1;
    } // fillOne

    /**
    * Calculates the length of LCS between indexes of global char arrays.
    * The time complexity is O(n(m-p)) and the space complexity is O(n+m).
    *
    * @param a_start    The start index of the string a.
    * @param a_end      The last index of the string a.
    * @param b_start    The start index of the string b.
    * @param b_end      The last index of the string b.
    * @param m          The length of the a string.
    * @param n          The length of the b string.
    *
    * @return           The length of LCS.
    */
    private int calcOfP(int a_start, int a_end, int b_start, int b_end, int m, int n)
    {

        int p = 0;

        R = 0;
        S = m + 1;
        while (S > R)
        {
            S--;
            fillOne(a_start, a_end, b_start, b_end, m, n, 1);
            for (int j = 0; j <= R; j++)
            {
                R1[j] = R2[j];
            }
        }
        p = S;

        return p;
    }

} // LongestCommonSubsequence



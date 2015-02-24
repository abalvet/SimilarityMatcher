/*
 * Created on 1 sept. 06
 * Créé le 1 sept. 06
 */
package wordsMatcher;

public class TestLCS {

    /**
     * @param args
     */
    public static void main(String[] args)
        {
            String x = args[0];
            String y = args[1];
            LongestCommonSubsequence lcs = new LongestCommonSubsequence(x,y);
            System.out.println("LCS for " + x + ", " + y + ":\n" + lcs.toString());
            int l = lcs._LCSChar.length;
            System.out.println("LCSChar: \n");
            for(int i = 0; i < l; i++){
                System.out.print(lcs._LCSChar[i]);
            }
        }

}

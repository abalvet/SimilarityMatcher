/*
 * Created on 26 mai 2011
 * Créé le 26 mai 2011
 */
package utils;

public class Counting {
    
    public int computeNbOfPairs(int n){
        int res = 0;
        if(n <= 3){//for a small corpus, made of less than 3 patterns
            System.err.println("lower limit");
            res = n;
        }
        else if(n == 4){
            res = 6;
        }
        else{
            System.err.println("recursion");
            while(n > 1){                
                n--;
                res = res + n;
                //System.err.println(n);                
            }
        }
        
        return res;
    }

    /**
     * @param args
     */
    public static void main(String[] args)
        {
            int n = Integer.valueOf(args[0]);
            
            System.err.println("computing nb of possible pairs for corpus made of " + args[0] +  " patterns: " + (new Counting().computeNbOfPairs(n)));

        }

}

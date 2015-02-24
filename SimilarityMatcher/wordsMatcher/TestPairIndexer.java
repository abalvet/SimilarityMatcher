/*
 * Created on 22 nov. 2005
 * Créé le 22 nov. 2005
 */
package wordsMatcher;
import java.io.*;
import java.util.*;
import java.lang.Double;

/**
 * @author Balvet Antonio
 *
 */
public class TestPairIndexer {
    public void run(String file, double seuil)
        {
        File theFile = new File(file);
        MyFileUtils mfu = new MyFileUtils();
        PairIndexer pi = new PairIndexer();
        String s = mfu.getStringFromFile(theFile);
        String[] stab = mfu.getTabFromString(s);
        TreeSet ts = new TreeSet();
        for(int i = 0; i < stab.length; i++){
            ts.add(stab[i]);
            }//à la fin de cette boucle, les lignes de corpus sont rentrées dans le TreeSet, sans doublon et triées 
        LevensteinScore ls = new LevensteinScore();
        Object[] uniq = ts.toArray();
        String curr = "";
        String next = "";
        String[] curString;
        String[] nextString;
        double relscore = 0;
        String tmp = "";
        for(int w = 0; w < uniq.length; w ++){
            //cette boucle parcourt ligne par ligne le corpus pour instancier une forme de référence pour la comparaison
            
            curr = uniq[w].toString().trim();
            for(int u = w + 1; u < uniq.length; u++){
                relscore = ls.getRelLevScore(curr,uniq[u].toString().trim());
                if(relscore < seuil){//si les 2 formes sont suffisamment proches, càd en-dessous du seuil maxi fixé
                    next = uniq[u].toString().trim();
                    //System.out.println("Compare:\n" + curr + "\net:\n" + next);
                    curString = curr.split(" ");//on découpe les lignes en mots
                    //System.out.println("Curr: " + curString.length);
                    nextString = next.split(" ");
                    //System.out.println("Next: " + nextString.length);
                    PairIndexer._element1Size = curString.length;
                    PairIndexer._element2Size = nextString.length;
                    pi.indexPair(curString,nextString);
                    String pat = pi._currentPattern;
                    String out = relscore + "\t" + pat.length()+ "\t" + pat + "\t" + curr + "\t" + next;
                    System.out.println(out); 
                    }
            	}
        	}
        }

    public static void main(String[] args)
        {
        TestPairIndexer tpi = new TestPairIndexer();
        double seuil = Double.parseDouble(args[1]);
        tpi.run(args[0],seuil);
        //la variable seuil permet de sélectionner les patrons les plus proches
        //plus le seuil est haut, plus le nombre de différences permis est élevé
        }
}

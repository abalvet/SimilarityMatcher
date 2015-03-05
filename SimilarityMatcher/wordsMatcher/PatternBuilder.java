/*
 * Created on 22 nov. 2005
 * Créé le 22 nov. 2005
 */
package wordsMatcher;
import java.io.*;

import java.util.*;
import java.lang.Double;
import org.apache.commons.collections.*;
import lcsalgorithms.OmniLCS;
import tools.BufferedPrintFileToEncoding;
import utils.InfoDisplay;
import utils.Counting;

/**
 * @author Balvet Antonio
 * @version 1.0
 * 
 */
public final class PatternBuilder { 
    /**
     * @param file
     * @param seuil
     * @param encoding
     * @param pairsOnly
     * @return
     */
static LevensteinScore _ls = new LevensteinScore();
//static HashMap _pairs = new HashMap();
//static Hashtable _pairs = new Hashtable();
static MultiHashMap _pairs = new MultiHashMap();
static double _relscore;
static double _seuil;
static String _justPairs = "";
static String _pairsAndPatterns = "";
static Object[] _indexOfOccurrences;
static boolean _onlyPairs = false;
static int _maxNbOfPairs = 0;
static int _processedNbOfPairs = 0;
InfoDisplay d = new InfoDisplay();


    public String run(String file, double seuil, String encoding, boolean pairsOnly, boolean charMode, String tokeniser) throws Exception
        {  
        _onlyPairs = pairsOnly;    
        System.err.println("Running patternBuilder with \nfile: " 
                                                    + file 
                                                    + "\nthreshold: " + seuil
                                                    + "\nencoding: " + encoding 
                                                    + "\npairs mode: " + pairsOnly
                                                    + "\nchars mode: " + charMode
                                                    + "\ntokeniser: " + tokeniser);
        String out = "";
        _seuil = seuil;
        File theFile = new File(file);
        MyFileUtils mfu = new MyFileUtils();
        PrintFileToEncoding pte = new PrintFileToEncoding();
        String s = pte.readString(theFile,encoding);
        String[] stab = mfu.getTabFromString(s);//chaque case du tableau contient une ligne détectée par \n
        TreeSet ts = new TreeSet();
        for(int i = 0; i < stab.length; i++){
            ts.add(stab[i].trim());
            }//à la fin de cette boucle, les lignes de corpus sont rentrées dans le TreeSet, sans doublon et triées 
        _indexOfOccurrences = ts.toArray();
        int maxSize = _indexOfOccurrences.length;
        System.err.println("Nb of base patterns: " + maxSize);
        
        _maxNbOfPairs = new Counting().computeNbOfPairs(maxSize);//calcule nb maximal de paires à examiner (factorielle n-1)
        System.err.println("Max. nb of pairs to examine: " + _maxNbOfPairs);
        out = makePairs(ts, _indexOfOccurrences, _seuil, pairsOnly, charMode, tokeniser);
        //System.err.println("out: " + out);
        return out;
        }
    

    
    /*double calculeNBPairs(int n){
        int nb = 0; 
        double res = 0;
        double a, b, c = 0;
        a = factorial(n);
        b = factorial(2);
        c = factorial(n-2);
        System.err.println("a: " + a + ", b: " + b + ", c: " + c);
        //res = factorial(n)/(factorial(2)*factorial(n-2));
        res = a/(b*c);
        System.err.println("Pairs: " + res);
        nb = (int)res;
        return res;
    }
    
    public static double factorial(int x) {  // This method computes x!
        if (x < 0)                             
        return 0;                          
        double fact = 1.0;
        
        while(x > 1) {            
            fact = fact * x;                 
            x = x - 1;                       
            }                                
            return fact;                                        
    }*/
    
    public static String makePairs(TreeSet corpusTreeSet, Object[] corpusArray, double seuil, boolean pairsOnly, boolean charMode, String tokeniser) throws Exception{
        String out = "";
        
        new InfoDisplay().printConsoleStatusBar(0, _maxNbOfPairs, 100);
        
        if(pairsOnly){//si on ne veut que des paires
            _pairs = traverseCorpus(corpusTreeSet, corpusArray, seuil);
            retrievePairsFromSet(pairsOnly, charMode, tokeniser);
            out = _justPairs;
            _processedNbOfPairs++;
            //System.err.print((_processedNbOfPairs/_maxNbOfPairs)*100 + " %\r");
            }
        else{//si on veut des paires + le patron
            _pairs = traverseCorpus(corpusTreeSet, corpusArray, seuil);            
            retrievePairsFromSet(pairsOnly, charMode, tokeniser);
            out = _pairsAndPatterns;
            _processedNbOfPairs++;
            //System.err.print((_processedNbOfPairs/_maxNbOfPairs)*100 + " %\r");
            }
        return out;
    }    
    
    static void retrievePairsFromSet(boolean pairsOnly, boolean charMode, String tokeniser) throws Exception{//OK
        Vector keys = new Vector(_pairs.keySet());
        Vector values;
        int pair1;
        int pair2;
        String s1;
        String s2;
        Enumeration e = keys.elements();
        while(e.hasMoreElements()){
            pair1 = (Integer)e.nextElement();//récupère l'index du 1er élément de la paire
            values = new Vector(_pairs.getCollection(pair1));
            Enumeration v = values.elements();
            while(v.hasMoreElements()){//on récupère chaque 2d élément de la paire
                pair2 = (Integer)v.nextElement();//récupère l'index du 2d élément de la paire
                s1 = _indexOfOccurrences[pair1].toString();            
                s2 = _indexOfOccurrences[pair2].toString();
                //System.out.println("retrieved pair: " + pair1 + ":" + s1 + "\t" + pair2 + ":" + s2 + ":" + _ls.getRelLevScore(s1,s2));
                if(pairsOnly){
                    if(isClose(s1,s2)){
                    _justPairs += "[:" + pair1 + "," + pair2 + "]" + "\t" + s1 + "\t" + s2 + "\t" + _relscore + "\n";
                    //System.out.println("justpairs: " + _justPairs);
                    }
                }
                else if(!pairsOnly){
                    if(isClose(s1,s2)){
                        String pattern = getPatternFromPair(s1, s2, charMode, tokeniser);
                        _pairsAndPatterns += "[" + pair1 + "," + pair2 + "]" + "\t" + _relscore + "\t" + pattern  + "\n";
                        //System.out.println("pairs & patts: " + _pairsAndPatterns);
                    }
                }
            }
        }
    }
    
    
    static boolean isClose(String s1, String s2){//calcule la distance entre 2 segments de paire
        boolean distanceClose = false;
        _relscore = _ls.getRelLevScore(s1, s2);
        if((_relscore < _seuil) && (_relscore > 0)){//si les segments de la paire ont une distance inférieure au seuil mais ne sont pas égaux
            distanceClose = true;
        }
        return distanceClose;
    }
    
    static MultiHashMap traverseCorpus(TreeSet corpusTreeSet, Object[] corpusArray, double seuil) throws Exception{//algo de parcours du corpus
        //TODO: optimiser algo de parcours
        //Hashtable mapOfViewedPairs = new Hashtable();//hashTable contenant une mémoire des paires déjà vues
        MultiHashMap mapOfViewedPairs = new MultiHashMap();
        try{        
        int maxSize = corpusArray.length;
        String current = "";
        String next = "";
        double relscore;
        int c = 1;
        for(int w = 0; w < maxSize; w ++){//cette boucle parcourt ligne par ligne le corpus pour instancier une forme de r�f�rence pour la comparaison
            current = corpusArray[w].toString().trim();
            //processedPairs = w * maxSize;
            //System.err.println("Nb paires vues: " + w * maxSize + " sur: " + nbPairs);
            //System.err.println("Processing line " + w + " " + current);
            for(int u = w + 1; u < maxSize; u++){
                next = corpusArray[u].toString().trim();                     
                //System.err.println("Processing pair " + current + ", " + next);
                relscore = _ls.getRelLevScore(current, next);
                if(isClose(current,next)){
                    mapOfViewedPairs.put(w,u);
                }
            }
        }
        /*System.out.println("Size of Map: " + mapOfViewedPairs.size());
        System.out.println("Keys of Map: " + mapOfViewedPairs.keySet().toString());
        System.out.println("Values of Map: " + mapOfViewedPairs.values().toString());*/
        System.err.println("\nEnd");        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return mapOfViewedPairs;//� ce stade, les paires vues ont �t� index�es dans mapOfViewedPairs
    }
    /*static String getPatternFromPair(String s1, String s2)//PAS OK: 07/01/2007 => utiliser nouvelle version de WordLCS ou CharLCS
        {            
            String pat = "";
            String[] currString = tokenizeStringOnWords(s1);
            String[] nextString = tokenizeStringOnWords(s2);
            PairIndexer pi = new PairIndexer();
            PairIndexer._element1Size = currString.length;
            PairIndexer._element2Size = nextString.length;
            pi.indexPair(currString,nextString);
            pat = pi._currentPattern;
            return pat;
        }*/
    static String getPatternFromPair(String s1, String s2, boolean charMode, String tokeniser) throws Exception{
        //System.err.println("getting patterns from pairs");        
        String pattern = "";
        String xx = "";
        String yy = "";
        String inter = "";
        
        float lcsLength = 0;
        float relscore = 0;
        
        OmniLCS lcs = new OmniLCS();
        lcs.initialise(s1, s2, charMode, tokeniser);//méthode d'initialisation du calcul de la LCS
        
        if(charMode){//mode caractères            
            xx = lcs.getCharsFromTable(lcs.get_aCharPattern());
            yy = lcs.getCharsFromTable(lcs.get_bCharPattern());
        }
        if(!charMode){//mode mots            
            
            xx = lcs.getStringFromTable(lcs.get_aStringPattern());
            yy = lcs.getStringFromTable(lcs.get_bStringPattern());
            //System.err.println("processing " + xx + " / " + yy);
                        

        }
        inter = lcs.getStringFromTable(lcs.get_intersectionPattern());
        lcsLength = lcs.get_nbLCS();                
        relscore = lcsLength/yy.length();
        //System.err.println("xx: " + xx + " " + xx.length() + ", yy:" + yy + ", relscore: " + relscore);
        
        pattern = s1 + "\t" + s2 + "\t" + xx + "\t" + yy + "\t" + inter + "\t" + lcsLength + "\t" + relscore;
        //pattern = xx + "\t" + yy + "\t" + inter + "\t" + lcsLength + "\t" + relscore;
        //System.err.println("pattern found: " + pattern);
        //lcs.printLCSTable();
        
        return pattern;
    }
    
    static String[] tokenizeStringOnWords(String a){
        String[] tokens = a.split("\\s");
        return tokens;
    }
//TODO: mettre à jour le main en fonction de la nouvelle définition de la méthode de calcul de la LCS    
/*    public static void main(String[] args)
        {
        PatternBuilder tpi = new PatternBuilder();
        double seuil = Double.parseDouble(args[1]);
        String encoding = args[2];
        boolean pairsOnly = false;//par d�faut, on veut r�cup�rer les paires + les patrons
        if(args[3].equals("1")){//si on ne veut que les paires (optimisation, test...), on positionne pairsOnly � true
            pairsOnly = true;
        }
        //tpi.run(args[0],seuil,encoding,pairsOnly); 
        String res = tpi.run(args[0],seuil,encoding,pairsOnly);
        System.out.println("res:\n" + res);
        //la variable seuil permet de s�lectionner les patrons les plus proches
        //plus le seuil est haut, plus le nombre de diff�rences permis est �lev�
        }*/
}

/*
 * Created on 22 nov. 2005
 * Cr�� le 22 nov. 2005
 */
package wordsMatcher;
//import java.awt.Toolkit;

import java.io.*;
import java.util.*;
import tools.BufferedPrintFileToEncoding;


/**
 * Classe générant, à partir d'un corpus, soit des paires minimales sans extraction de patron, soit des 
 * paires minimales accompagnées des patrons extraits à partir de ces paires. Le seuil de proximité est
 * borné sur ]0,1[: par défaut, les paires strictement égales (0 différences) ne sont pas traitées; 
 * il n'y a pas d'intérêt à traiter des paires trop différentes (seuil inférieur ou égal à 1).
 * Plus le seuil est bas, moins le nombre de différences acceptées pour former une paire est élevé. 
 * Pour l'exploitation des résultats, plus le seuil est bas, moins le nombre de paires examinées est élevé.
 * @author Balvet Antonio
 * @version 2.1: modification du comportement: possibilité de n'extraire que des paires minimales, 
 * ARGS: <Fichier> <Encodage> <Seuil> <PairsOnly> <br>
 * TODO: vérifier fonctionnement correct des paires
 * @version 2.0 du CorpusMiner:<br>
 * - utilise des méthodes portables de conversion pour lire et écrire dans des fichiers<br>
 * - utilise PairIndexer plutôt que BidiMatcher pour l'extraction de patrons à partir de paires<br>
 * - détecte 2 modes d'utilisation: constituer seulement une liste de paires/extraire des patrons<br>
 *
 * TODO: rajouter un argument en fonction du type de langue:
 * 1) ordre totalement libre <br>
 * 2) ordre partiellement libre <br>
 * 3) ordre fixe <br>
 * En effet, les calculs de distance ne pondèrent pas les 3 paramètres (élision, déplacement, effacement). <br>
 * Or pour 1 et 2), le déplacement est moins pertinent que pour 3). <br>
 * TODO: rajouter une fonction d'estimation du nombre de paires générées: NbPairs = n * n-1; avec n nombre d'éléments
 */
public class CorpusMinerV2 {
    double _seuil = 0;
    static boolean _onlyPairs = false;
    
    public boolean compareStrings(String a, String b)
        {
        boolean Samestrings = false;
        if(a.compareTo(b) == 0)
            {
            Samestrings = true;
            }
        return Samestrings;
        }
    public TreeSet buildCorpusSet(Vector corpus)//construit un ensemble sans doublons à partir des formes du corpus
        {
        TreeSet corpusSet = new TreeSet(corpus);
        return corpusSet;
        }
    public Vector buildCorpusVector(String[] corpus)//construit un vecteur à partir du tableau de String des formes du corpus
        {
        Vector corpusVector = new Vector();
        for(int i = 0; i < corpus.length; i++){
            corpusVector.add(i,corpus[i]);
            }
        return corpusVector;
        }
    /***
     * 
     * @param args: <FICHIER> <SEUIL> <ENCODAGE> <PAIRES_SEULES> <MODE_CARACTERES> <TOKENISEUR><br>
     *              <FICHIER>: fichier à traiter, formatté en un élément de paire par ligne<br>
     *              <SEUIL>: seuil de similarité recherché, exemple 0.4<br>
     *              <ENCODAGE>: exemple cp1252(ANSI), UTF-8(Unix), Unicode<br>
     *              <PAIRES_SEULES>: si 1, seulement des paires minimales, sinon, paires + patrons<br>
     *              <MODE_CARACTERES>: si '-c', LCS en mode caractères, si 'w' LCS en mode mots<br>
     * @throws Exception
     */
    
    static void appendPairsToFile(File f, String encoding, String patterns, Double seuil){
        String justPairsHeader = "ID_Pairs\tREL_LEV_SCORE\tS1\tS2\n";
        String pairsAndPatternsHeader = "ID_Pairs\tREL_LEV_SCORE\tS1\tS2\tXX\tYY\tINTERSECTION\tLCS_LENGTH\tINV_REL_LEV_SCORE\n";
        File out = new File(f + "_patterns_" + encoding + "_" + seuil + ".csv");
        if(_onlyPairs){
            BufferedPrintFileToEncoding.printAppended(out,encoding,patterns);
        }
        else{
            
        }
    }
    
    public static void main(String[] args) throws Exception
        {
            System.err.println("nb argts: " + args.length);
            if(args.length < 6){
                System.out.println("USAGE: <FICHIER> <SEUIL> <ENCODAGE> <PAIRES_SEULES> <MODE_CARACTERES> <TOKENISEUR>"
                                    + "\n<FICHIER>: fichier à traiter, formatté en un élément de paire par ligne"
                                    + "\n<SEUIL>: seuil de similarité recherché, exemple 0.4"
                                    + "\n<ENCODAGE>: exemple cp1252(ANSI), UTF-8(Unix), Unicode"
                                    + "\n<PAIRES_SEULES>: si 1, seulement des paires minimales, sinon, paires + patrons"
                                    + "\n<MODE_CARACTERES>: si '-c', LCS en mode caractères, si 'w' LCS en mode mots"
                                    + "\n<TOKENISEUR>: expression régulière servant à la délimitation des mots, ex: \"\\\b\"");
            }
            else if (args.length == 6){
                long start;
                long end; 
                long dur;
                String f = args[0];
                String pairs = args[3];
                String mode = args[4];
                String tokeniser = args[5];
                
                boolean pairsOnly = false;
                boolean charMode = false;                
                
                if(pairs.equals("1"))
                    pairsOnly = true;
                
                if(mode.equals("-c"))
                    charMode = true;
                else charMode = false;
                
                _onlyPairs = pairsOnly;
                
                String patterns = "";
                //StringBuffer patternsBuffer = new StringBuffer();
                
                double seuil = Double.valueOf(args[1]).doubleValue();
                String encoding = args[2];
                PatternBuilder pb = new PatternBuilder();
                start = System.currentTimeMillis();
                patterns = pb.run(f, seuil, encoding, pairsOnly, charMode, tokeniser);
                //patternsBuffer.append(pb.run(f,seuil,encoding,pairsOnly));
                end = System.currentTimeMillis();
                dur = end - start;
                System.out.println("Durée pb.run: " + dur);                                                                
                File out = new File(f + "_patterns_" + encoding + "_" + seuil + ".csv");
                BufferedPrintFileToEncoding.printAppended(out,encoding,patterns);
                //BufferedPrintFileToEncoding.printAppended(out,encoding,patternsBuffer.toString());
                /*end = System.currentTimeMillis();
                dur = end - start;
                System.out.println("Durée print: " + dur);
                Toolkit.getDefaultToolkit().beep();*/
                 
            }            
        }
}

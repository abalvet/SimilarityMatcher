/*
 * Created on 16 mars 2005
 * Cr�� le 16 mars 2005
 */

/**
 * @author Balvet Antonio
 * Essai d'implémentation d'une simili-LCS sur des mots, et non des caractères, inspirée de 
 * l'approche tabulaire en programmation dynamique. Le résultat de la V1 est la détermination
 * de la sous-chaîne commune contiguë la plus longue.
 * @version 1.0
 * Usage: le main attend 2 arguments, 2 strings.
 * Exemples: 
 * "le chat court" & "le chat mange" => WLCS: "le chat -"
 * "le chat que je vois dort" & "le chat dort" => "le chat"
 * TODO: calculer la plus longue sous-chaîne commune non contiguë, avec un système de pondération
 * pour les opérations d'édition: déplacement, effacement, insertion 
 * TODO: vérifier le problème de taille des tableaux lorsque string2 > string1
 */
public class WordLCS_V1 {
String[] _diagonale;
String[][] _wordLCSTable;
String _clcs = "";
String _dlcs = "";

    public void buildTables(String[] a, String[] b)
        {
        int max = 0;
        if(a.length > b.length)
            max = a.length;
        else
            max = b.length;
            
        String[] x = new String[max];
        String[] y = new String[max];
        
        for(int i = 0; i < max; i++)
            {
            if(!a[i].equals(null) && !b[i].equals(null))
                {
                x[i] = a[i];
                y[i] = b[i];
                }
            else if(a[i].equals(null))
                x[i] = "-";
            else if(b[i].equals(null))
                y[i] = "-";
            }
        //à la fin de cette boucle, on a 2 tables x et y contenant les mots de a et b respectivement
        //ou "-" si a ou b sont vides
        int indx ;
        int indy ;
        String[][] table = new String[max][max];
        for(indx = 0; indx < max; indx++)
            {
            for(indy = 0; indy < max; indy++)
                {
                table[indx][indy] = this.compareStrings(x[indx],y[indy]);
                //pour chaque colonne, on remplit table[indx][indy] avec le résultat
                //de compareStrings, ligne par ligne
                }
            }
        _wordLCSTable = table;
        printTable(table);
        System.out.print("------------------------------" + "\nWLCS:\t");
        //System.out.print("\t");
        for(int n = 0; n < max; n++)
            {
            _clcs += table[n][n] + "\t";
            System.out.print(table[n][n] + "\t");
            }
        //System.out.println("\n" + "------------------------------");
        //this.evaluateTable(table);
        String[] dlcs = new String[max];
        System.out.print("\n");
        String buff = "";
        for(int line = 0; line < table.length; line++)//parcours des lignes pour calculer la LCS discontiguë
            {
            for(int col = 0; col < table.length; col++)//pour la ligne courante, parcours des colonnes
                {
                if(table[col][line].matches("-"))
                    {
                    }
                else
                    dlcs[col]= table[col][line];
                if(dlcs[col] == null)
                    dlcs[col] = "-";
                }//fin des colonnes
            }//fin des lignes
        //à la fin de cette boucle, dlcs contient la LCS discontiguë des 2 strings
        System.out.print("\nDLCS:\t");
        for(int s = 0; s < max; s++)
            {
            if(!(dlcs[s]== null))
                {
                _dlcs += dlcs[s] + "\t";
                System.out.print(dlcs[s] + "\t");
                }
            //else 
                if(dlcs[s] == null)
                System.out.print("-" + "\t");
            }
        System.out.println("\n" + "------------------------------");
        }
    public String compareStrings(String aString, String anotherString)
        {
        String result = "";
        if(aString.equals(anotherString))
            result = aString;
        else
            result = "-";
        return result;
        }
    public void printTable(String[][] aTable)
        {
        
        for(int line = 0; line < aTable.length; line++)
            {
            System.out.print("\t" + line);
            }
        System.out.println();
        for(int x = 0; x < aTable.length; x++)
            {
            System.out.print(x);
            for(int y = 0; y < aTable.length; y++)
                {
                System.out.print("\t" + aTable[x][y]);
                }
            System.out.println("");
            }
        }
    public String evaluateTable(String[][] aTable)
        {
        String res = "";
        int penalty = 0;
        int poids = 0;
        for(int x = 0; x < aTable.length; x++)
            {
            for(int y = 0; y < aTable.length; y++)
                {               
                 if(aTable[x][y].matches("-"))
                     {
                     penalty++;
                     }
                }
            poids = (aTable.length - penalty);
            
            System.out.println("Penalty @: " + x + " = " + penalty);
            penalty = 0;
            poids = 0;
            }
        return res;
        }
    public static void main(String[] args)
        {
        WordLCS_V1 wl = new WordLCS_V1();
        String a = "";
        String b = "";
        String[] aa ;
        String[] bb ;
        if(args.length==2)
            {
            a = args[0];
            b = args[1];
            aa = a.split(" ");
            bb = b.split(" ");
            String[] aab;
            String[] bbb;
            int maxl = 0;
            if(aa.length > bb.length)
                {
                maxl = aa.length;
                bbb = new String[maxl];
                for(int ind = 0; ind < bb.length; ind ++)
                    {
                    bbb[ind] = bb[ind];
                    }
                for(int n = bb.length ; n < maxl; n++)
                    {
                    bbb[n] = "_";
                    }
                wl.buildTables(aa,bbb);
                }
            else
                {
                maxl = bb.length;
                aab = new String[maxl];
                for(int inda = 0; inda < aa.length; inda++)
                    {
                    aab[inda] = aa[inda];
                    }
                for(int m = aa.length; m < maxl; m++)
                    {
                    System.out.println("aab= " + "_");
                    aab[m] = "_";
                    }
                wl.buildTables(bb,aab);
                }
            //System.out.println("la WLCS: " + wl._wlcs);
            //System.out.println("la DLCS: " + wl._dlcs);
            }

        //TODO: voir les configurations de poids 
        
        }
/**
 * @return Returns the _dlcs, or Discontiguous Longest Common Subsequence (with gaps).
 * @return Retourne _dlcs, la plus longue sous-séquence commune discontiguë (avec trous).
 */
public String get_dlcs()
    {
        return _dlcs;
    }
/**
 * @param _dlcs The _dlcs to set.
 * @param _dlcs Le _dlcs à paramétrer.
 */
public void set_dlcs(String _dlcs)
    {
        this._dlcs = _dlcs;
    }
/**
 * @return Returns the _clcs, or Contiguous Longest Common String (no gaps).
 * @return Retourne _clcs, la plus longue sous-chaîne commune (sans trous).
 */
public String get_clcs()
    {
        return _clcs;
    }
/**
 * @param _clcs The _clcs to set.
 * @param _clcs Le _clcs à paramétrer.
 */
public void set_clcs(String _clcs)
    {
        this._clcs = _clcs;
    }
}

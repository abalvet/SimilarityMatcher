/*
 * Created on 15 mars 2005
 *
 */
package wordsMatcher;

/**
 * @author Balvet Antonio
 * Essai d'implémentation d'une simili-LCS sur des mots, et non des caractères, inspirée de 
 * l'approche tabulaire en programmation dynamique.
 * @version 1.0
 * Usage: le main attend 2 arguments, 2 strings.
 * Exemples: "le chat court" & "le chat mange" => WLCS: "le chat -" 
 * @deprecated
 */
public class WordLCS {
String[] _diagonale;
String[][] _wordLCSTable;

    public void buildTables(String[] a, String[] b)
        {
        int max = 0;
        if(a.length > b.length)
            max = a.length;
        else
            max = b.length;
        /*if(a.length > b.length)
            {
            for(int n = a.length; n < max; n++)
                {
                a[n] = "";
                }
            }
        else
            for(int m = b.length; m < max; m++)
                {
                b[m] = "";
                }*/
            
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
            System.out.print(table[n][n] + "\t");
            }
        System.out.println("\n" + "------------------------------");
        //this.evaluateTable(table);
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
        WordLCS wl = new WordLCS();
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
                for(int inda = 0; inda < maxl; inda++)
                    {
                    aab[inda] = aa[inda];
                    }
                for(int m = aa.length; m < maxl; m++)
                    {
                    aab[m] = "_";
                    }
                wl.buildTables(aab,bb);
                }
            }
        

        
        
        //TODO: voir le problème de taille des tableaux
        //TODO: voir les configurations de poids 
        
        }
}

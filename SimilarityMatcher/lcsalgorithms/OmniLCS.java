/*
 * Created on 8 sept. 06
 * Créé le 8 sept. 06
 */
package lcsalgorithms;

/**
 * @author Balvet Antonio
 * @version 1.0<br>
 * Classe de base pour la détection de plus longues sous-chaînes communes, à partir de 
 * l'algorithme suivant (pseudocode):<br>
 * 
 * Soient:<br>
 * - 2 chaînes d'entrées x, y de longueur respective m, n<br>
 * - soit un tableau à 2 dimension L, chaque dimension correspondant à 1 tableau
 * contenant l'une des chaînes d'entrée: A<=x, B<=y<br>
 * - des index permettant de parcourir L, A et B: i, j<br>
 * 
 * -------------------------------------------------------------------<br>
 * Algorithme LCS:<br>
 * <br><verbatim><br>
 *#1) initialisation du tableau L<br>
 * for i:= 0 to m do Li,0 = 0<br>
 * for j:= 0 to n do L0,j = 0<br>
 * #2) comparaison terme à terme des constituants des chaînes d'entrée<br>
 * for i:= 1 to m do                // parcours du tableau L en partant de la nè ligne<br>
 *      for j:= 1 to n do           // nè colonne<br>
 *      if Ai == Bj then            //si égalité des constituants (alignement)<br>
 *          Li,j := 1 + Li-1,j-1    //la cellule courante reçoit le nombre d'alignements déjà trouvés + 1<br>
 *          (verifier bornes inférieures du tableau)
 *      else                        //pas d'alignement<br>
 *          Li,j := Max((Li-1,j),(Li,j-1))  //la cellule courante reçoit le nombre maximum d'alignements déjà trouvés<br>
 *          </verbatim>
 * ---------------------------------------------------------------------<br>
 * <br>
 * A la fin de l'algorithme, la dernière cellule du tableau contient le nombre maximum de sous-chaînes
 * communes.<br>
 * La complexité de cet algorithme dépend essentiellement de la chaîne la plus longue.<br>
 * 
 * Cet algorithme permet de détecter y compris le patron commun suivant:<br>
 * WASBDCG/AXBYC => -A-B-C- <br>
 *  
 * Sur cette base utilisant la programmation dynamique sont ajoutées des méthodes permettant de 
 * détecter les mouvements de constituants (avec indication de la position de départ et d'arrivée).<br>
 * 
 * Les méthodes sont en général déclarées publiques de façon à pouvoir les surclasser facilement (optimisation).<br> 
 */
public class OmniLCS {
    //variables membres destinées à contenir:
    static int _nbLCS = 0;//le nb max d'alignements
    static String _LCSString = "";//la plus longue sous-chaîne commune (mode "mots")
    static char[] _LCSCharTab;//la LCS sous forme d'un tableau de caractères (mode "caractères")
    static String[] _LCSStringTab;//la LCS sous forme d'un tableau de String (mode "mots")
    static int _nbDiffs = 0;//le nb de divergences
    static String _DiffString = "";// la String contenant les divergences
    static String[] _DiffStringTab;//idem sous forme d'un tableau pour connaître les positions
    static char[] _DiffCharTab;//idem pour le mode "caractères"
    
    //variables membres nécessaires au calcul de la LCS elle-même
    static int _i,_j,_m,_n = 0;
    static String _A,_B = "";//les chaînes d'entrée
    static String[] _aTokens, _bTokens, _aStringPattern, _bStringPattern, _intersectionPattern;
    static char[] _aCharPattern;
    static char[] _bCharPattern;//tableaux de caractères contenant les patrons
    static char[] _aChars, _bChars;
    static int[][] _L;
    static String[][] _LL;
    
    /**
     * @param x 1è chaîne d'entrée
     * @param y 2è chaîne d'entrée
     * @param chars spécifie si l'extraction de LCS est faite en mode "caractères" (true) ou "mots" (false)
     * @param tokeniser l'expression régulière utilisée pour tokeniser les String d'entrée
     * 
     * Initialisation du tableau qui servira à stocker les alignements entre les 2 chaînes.
     * Si cars == true: mode "caractères", sinon "mots".
     */
    public void initialise(String x, String y, boolean chars, String tokeniser){
        int xl = x.length();
        int yl = y.length();
        if(xl <= yl){
            _A = x;
            _B = y;            
        }
        else if(xl > yl){
            _A = y;
            _B = x;            
        }//attribue à _A la plus courte String, et à _B la plus longue

        if (chars){//mode "caractères"
            _aCharPattern = new char[_A.toCharArray().length];
            for(int i = 0; i < _aCharPattern.length; i++){
                _aCharPattern[i] = '-';
            }
            _bCharPattern = new char[_B.toCharArray().length];
            _intersectionPattern = new String[_bCharPattern.length];
            for(int i = 0; i < _bCharPattern.length; i++){
                _bCharPattern[i] = '-';
            }

            _A = "#" + _A;
            _B = "#" + _B;//+1 caractère pour avoir le bon nombre lignes et de colonnes à partir de 0,0
            _aChars = _A.toCharArray();
            _bChars = _B.toCharArray();
            _m = _A.length();
            _n = _B.length();
            try{
                initialiseTable(_m,_n);
            }
            catch(Exception e){
                e.printStackTrace();
                System.out.println("pb initializing table");
            }
            try{
                fillTable(chars);
            }
            catch(Exception e){
                e.printStackTrace();
                System.out.println("pb filling table");
                }
            }
        else{//mode "mots"
            /*int a = tokeniseString(_A,tokeniser).length-1;
            int b = tokeniseString(_B,tokeniser).length-1;//TODO: meilleure gestion des différences de taille entre les 2 tableaux
            */
            int a = tokeniseString(_A,tokeniser).length;
            int b = tokeniseString(_B,tokeniser).length;
            
            _aStringPattern = new String[a];
            _bStringPattern = new String[b];
            /*System.err.println("Taille de _aStringPattern: " + a);
            System.err.println("Taille de _bStringPattern: " + b);*/
            for(int i=0; i < a; i++){
                _aStringPattern[i] = "-";                
            }
            for(int i=0; i < b; i++){
                _bStringPattern[i] = "-";            
            }            
            
            _aTokens = tokeniseString("# " + _A,tokeniser);
            _bTokens = tokeniseString("# " + _B,tokeniser);//+1 token pour avoir le bon nombre lignes et de colonnes à partir de 0,0
            _intersectionPattern = new String[_bTokens.length];
            _m = _aTokens.length;
            _n = _bTokens.length;            
            try{
                initialiseTable(_m,_n);
            }
            catch(Exception e){
                e.printStackTrace();
                System.out.println("pb initializing table");
            }
            try{
                fillTable(chars);
               }
            catch(Exception e){
                e.printStackTrace();
                System.out.println("pb filling table");
                }
            
            }
    }
    
    /**
     * 
     * @param m longueur de la 1è chaîne
     * @param n longueur de la 2è chaîne
     */
    static void initialiseTable(int m, int n) throws Exception{        
        _L = new int[m][n];
        _LL = new String[m][n];        
        for(_i = 0; _i < m; _i++){
            for(_j = 0; _j < n; _j++){
                _L[_i][_j] = 0;
                _LL[_i][_j] = "-";
            }
        }
        
        /*
        System.err.println("Tables initialisées: ");
        System.err.println("_LL: ");
        printTable(_LL, m, n);
        System.err.println("_L: ");
        printTable(_L, m, n);
        */
    }
    
    /**
     * 
     * @param chars si true, mode "caractères", si false mode "mots"
     */
    static void fillTable(boolean chars) throws Exception{
        //System.err.println("table bounds: " + _m + ", " + _n);
        for(int i = 1; i < _m; i++){//for 1)
            for(int j = 1; j < _n; j++){//for 2)
                if(chars){//mode caractères
                    if(_aChars[i] == _bChars[j]){//alignement
                        _aCharPattern[i-1] = _aChars[i];
                        _bCharPattern[j-1] = _bChars[j];//il y a un décalage de 1 caractère entre les patrons extraits et les tableaux de caractère en entrée
                        _L[i][j] = (1 + _L[i-1][j-1]);   
                        //_intersectionCharPattern[j] = _bChars[j];
                        if(i == j){                            
                            _intersectionPattern[i-1] = String.valueOf(_bChars[j]);
                        }
                        else{
                            _intersectionPattern[i-1] = ("<MOV UNIT=" 
                                                        + "\""+ String.valueOf(_aChars[i]) + "\"" 
                                                        + " ORIG=" + "\"" + (i-1) + "\"" 
                                                        + " DEST=" + "\"" + (j-1) + "\""
                                                        + ">");
                        }
                        
                    }                    
                    else{//divergence
                        _L[i][j] = (Math.max(_L[i-1][j], _L[i][j-1]));                           
                    }                    
                    _nbLCS = _L[i][j];                    
                }//mode caractères
                
                else{//mode "mots"                           
                    if(_aTokens[i].toString().equals(_bTokens[j].toString())){//alignement
                        _aStringPattern[i-1] = _aTokens[i];                        
                        _bStringPattern[j-1] = _bTokens[j];//il y a un décalage de 1 mot entre les patrons extraits et les chaînes d'entrée

                        _L[i][j] = (1 + _L[i-1][j-1]);     
                        if((i == j)  && (!_aTokens[i].matches(" "))){
                            _intersectionPattern[i-1] = _bTokens[j];
                        }
                        else if(!_aTokens[i].matches(" ")){
                            _intersectionPattern[i-1] = ("<MOV UNIT=" 
                                                        + "\""+ (_aTokens[i]) + "\"" 
                                                        + " ORIG=" + "\"" + (i-1) + "\"" 
                                                        + " DEST=" + "\"" + (j-1) + "\""
                                                        + ">");
                        }
                    }                    
                    else{//divergence
                        _L[i][j] = (Math.max(_L[i-1][j], _L[i][j-1]));
                    }                    
                    _nbLCS = _L[i][j];                    
                }//mode mots
            }//end for 2)
        }//end for 1)
    }//fillTable
    
    /**
     * 
     * @return la taille maximale de la LCS
     */
    static int getLCSLength(){
        return _nbLCS;
    }
    
    /**
     * 
     * @return le nombre de différences
     */
    static int getNbOfDiffs(){
        return _nbDiffs;
    }
    
    /**
     * Affiche la table qui sert à calculer la LCS.Utile en mode débogage.
     *
     */
    public void printLCSTable(){
        for(int l = 0; l < _m; l++){           
            for(int c = 0; c < _n; c++){
                System.out.print("[" + l + "," + c + "]");
                System.out.print(_L[l][c] + "|");
            }
            System.out.println();
        }
    }
    
    static void printTable(String[][] aTable, int m, int n){        
        for(int l = 0; l < m; l++){           
            for(int c = 0; c < n; c++){
                System.err.print("[" + l + "," + c + "]");
                System.err.print(aTable[l][c] + "|");
            }
            System.err.println();
        }
        System.err.println("-----------------------------------");
    }
    
    static void printTable(int[][] aTable, int m, int n){        
        for(int l = 0; l < m; l++){           
            for(int c = 0; c < n; c++){
                System.err.print("[" + l + "," + c + "]");
                System.err.print(aTable[l][c] + "|");
            }
            System.err.println();
        }
        System.err.println("-----------------------------------");
    }
        
    /**
     * 
     * @param aString une chaîne de caractères à tokeniser
     * @param tokeniser une String servant au découpage en tokens, ex: "\\b+", "\\W+" etc...
     * @return un tableau de Strings contenant les tokens
     * 
     */
    static String[] tokeniseString(String aString, String tokeniser){
        String[] tokens = aString.split(tokeniser);//tokenisation de surface pour langues à délimiteurs typographiques: \W+ == tout ce qui n'est pas alphanumérique
        return tokens;
    }
    
    public String printStringTable(String[] aTable){
        String out = "";
        for(int i = 0; i < aTable.length; i++){
            if(aTable[i] != null){
                out += aTable[i] + " ";
            }            
        }
        return out;
    }
    
    static String printIntersectionPattern(String[] intersectionPattern){
        String out = "";
        for(int i = 0; i < intersectionPattern.length; i++){
            if(intersectionPattern[i] != null){
                out += intersectionPattern[i] + " ";
            }            
        }
        return out;
    }
    
    static String printCharTable(char[] aTable){
        String out = "";
        for(int i = 0; i < aTable.length; i++){
            out += aTable[i];            
        }
        return out;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////
//PUBLIC GETTERS    
/////////////////////////////////////////////////////////////////////////////////////////////////////    
    /**
     * @return Returns the _aCharPattern.
     * @return Retourne _aCharPattern.
     */
    public char[] get_aCharPattern()
        {
            return _aCharPattern;
        }

    /**
     * @return Returns the _aChars.
     * @return Retourne _aChars.
     */
    public char[] get_aChars()
        {
            return _aChars;
        }

    /**
     * @return Returns the _aStringPattern.
     * @return Retourne _aStringPattern.
     */
    public String[] get_aStringPattern()
        {
            return _aStringPattern;
        }

    /**
     * @return Returns the _aTokens.
     * @return Retourne _aTokens.
     */
    public String[] get_aTokens()
        {
            return _aTokens;
        }

    /**
     * @return Returns the _bCharPattern.
     * @return Retourne _bCharPattern.
     */
    public char[] get_bCharPattern()
        {
            return _bCharPattern;
        }

    /**
     * @return Returns the _bChars.
     * @return Retourne _bChars.
     */
    public char[] get_bChars()
        {
            return _bChars;
        }

    /**
     * @return Returns the _bStringPattern.
     * @return Retourne _bStringPattern.
     */
    public String[] get_bStringPattern()
        {
            return _bStringPattern;
        }

    /**
     * @return Returns the _bTokens.
     * @return Retourne _bTokens.
     */
    public String[] get_bTokens()
        {
            return _bTokens;
        }

    /**
     * @return Returns the _DiffCharTab.
     * @return Retourne _DiffCharTab.
     */
    public char[] get_DiffCharTab()
        {
            return _DiffCharTab;
        }

    /**
     * @return Returns the _DiffString.
     * @return Retourne _DiffString.
     */
    public String get_DiffString()
        {
            return _DiffString;
        }

    /**
     * @return Returns the _DiffStringTab.
     * @return Retourne _DiffStringTab.
     */
    public String[] get_DiffStringTab()
        {
            return _DiffStringTab;
        }

    /**
     * @return Returns the _intersectionPattern.
     * @return Retourne _intersectionPattern.
     */
    public String[] get_intersectionPattern()
        {
            return _intersectionPattern;
        }

    /**
     * @return Returns the _LCSCharTab.
     * @return Retourne _LCSCharTab.
     */
    public char[] get_LCSCharTab()
        {
            return _LCSCharTab;
        }

    /**
     * @return Returns the _LCSString.
     * @return Retourne _LCSString.
     */
    public String get_LCSString()
        {
            return _LCSString;
        }

    /**
     * @return Returns the _LCSStringTab.
     * @return Retourne _LCSStringTab.
     */
    public String[] get_LCSStringTab()
        {
            return _LCSStringTab;
        }

    /**
     * @return Returns the _nbDiffs.
     * @return Retourne _nbDiffs.
     */
    public int get_nbDiffs()
        {
            return _nbDiffs;
        }

    /**
     * @return Returns the _nbLCS.
     * @return Retourne _nbLCS.
     */
    public int get_nbLCS()
        {
            return _nbLCS;
        }
    
    public String getStringFromTable(String[] aTable){
        String s = "";
        for(int i = 0; i < aTable.length; i++){
            s += aTable[i] + " ";
        }
        return s;
    }
    
    public String getCharsFromTable(char[] aTable){
        String s = "";
        for(int i = 0; i < aTable.length; i++){
            s += aTable[i];
        }
        return s;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////
//  PUBLIC GETTERS    
/////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @param args Dans l'ordre, les arguments qui servent à tester la classe: <br>
     * - args[0]: 1è chaîne<br>
     * - args[1]: 2è chaîne<br>
     * - args[2]: si "-c", mode caractères, sinon mode mots<br>
     * - args[3]: expression régulière pour la tokenisation des chaînes (utile en mode mots).<br>
     */
    public static void main(String[] args)//TODO: pb pour kitab/katib 
        {
            OmniLCS lcs = new OmniLCS();
            
            String x = args[0];
            String y = args[1];
            boolean car = false;
            if (args[2].equals("-c")){
                car = true;
            }            
            String tokeniser = ""; 
            if (!args[3].equals(null)) {
                tokeniser = args[3];
            }
            String xx = "";
            String yy = "";
            
            lcs.initialise(x, y, car,tokeniser);
            lcs.printLCSTable();
            
            if(car == false){
                System.out.println("Word Mode!");
                xx = lcs.printStringTable(_aStringPattern);
                yy = lcs.printStringTable(_bStringPattern);
                System.out.println("nbLCS: " + _nbLCS);            
                System.out.println("A: " + x + "\nA pattern: " + xx);                        
                System.out.println("B: " + y + "\nB pattern: " + yy);
            }
            else{
                System.out.println("Character Mode!");
                xx = printCharTable(_aCharPattern);
                yy = printCharTable(_bCharPattern);
                System.out.println("nbLCS: " + _nbLCS);            
                System.out.println("A: " + x + "\nA pattern: " + xx);                        
                System.out.println("B: " + y + "\nB pattern: " + yy); 
            }
            System.out.println("Intersection Pattern: " + printIntersectionPattern(_intersectionPattern));                                    
        }

}

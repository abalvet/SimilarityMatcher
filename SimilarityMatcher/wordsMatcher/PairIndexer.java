/*
 * Created on 24 janv. 2005
 *
 */
package wordsMatcher;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.TreeSet;
import java.util.ArrayList;
import org.apache.commons.collections.*;

/**
 * @author Antonio
 *
 */

public class PairIndexer {
static String _currentString;
static String _currentWord = "";
static String _separator =" ";//le séparateur d'éléments, par défaut un espace
//TODO: probablement mieux d'avoir recours aux ensembles de caractères prédéfinis 
static String _element1 = "";
static String _element2 = "";
static int _element1Size = 0;
static int _element2Size = 0;
//static TreeSet _sequenceSet1;
//static TreeSet _sequenceSet2;
static MultiHashMap _mhm1;
static MultiHashMap _mhm2;
static TreeSet _generalSequenceSet;
String _currentPattern = "";

	public MultiHashMap indexSequence(String[] sequence)
//On utilise une MultiHashMap pour construire une table des différentes positions (offset) d'un même mot (string)
//ex: a::{0,4,6}
	{
	    _generalSequenceSet = new TreeSet();
	    Hashtable table = new Hashtable();
	    for(int i=0; i< sequence.length; i++)
	    {
	        _currentWord = sequence[i];
	        table.put(new Integer (i), _currentWord);
	        //System.out.println("putting " + i + " " + _currentWord);
	        _generalSequenceSet.add(_currentWord);
	        //System.out.println("seqset: " + sequenceSet.size());
	    }
	    //à la fin de cette boucle, table contient la position de chaque mot, sequenceSet l'ensemble sans doublons
	    //des mots de la String
	    
	    MultiHashMap mhm = new MultiHashMap();
	    Enumeration e = table.keys();
	    //on récupère les offsets, pour les stocker dans mhm sous forme de values: les string seront les keys
	    while(e.hasMoreElements())
	        {
	        	Object k = e.nextElement();
	        	Object w = table.get(k);
	        	mhm.put(w,k);
	        	//on stocke le mot comme une key, et l'offset comme une value
	        	//on obtient une Collection de positions associée à chaque mot-key
	        }
	    return mhm;
	}
	
	public void indexPair(String[] one, String[] two)
	    {
	    	_mhm1 = new MultiHashMap();
	    	_mhm2 = new MultiHashMap();
	    	TreeSet s1 = new TreeSet();
	    	TreeSet s2 = new TreeSet();
	    	set_mhm1(indexSequence(one));
	    	//System.out.println("_mhm1: " + _mhm1.toString());
	    	s1.addAll(_generalSequenceSet);
	    	//_sequenceSet1 = _generalSequenceSet;
	    	set_mhm2(indexSequence(two));
	    	//System.out.println("_mhm2: " + _mhm2.toString());
	    	s2.addAll(_generalSequenceSet);
	    	//_sequenceSet2 = _generalSequenceSet;
	    	//System.out.println(_mhm1.keySet().toString() + _mhm1.values() +"\n" + _mhm2.keySet().toString() + _mhm2.values());
	    	//le keySet permet de lister les séquences, alors que mhm.values retourne les positions
	    	Vector interv = new Vector();
	    	Vector unionv = new Vector();
            Vector disjv = new Vector();
	    	interv.addAll(this.getIntersection(s1,s2));
	    	this.printIntersectionString(interv);
	    	//unionv.addAll(this.getUnion(s1,s2));
            disjv.addAll(this.getDisjunction(s1,s2));
	    }
	
	public Vector getIntersection(Collection a, Collection b)
	    {
	    	Vector intersectionVector = new Vector(); 
	    	intersectionVector.addAll(CollectionUtils.intersection(a,b));
	    	//System.out.println("Intersection:\t" + intersectionVector.toString());
	    	return intersectionVector;
	    }
	public Vector getUnion(Collection a, Collection b)
	    {
	    	Vector unionVector = new Vector();
	    	unionVector.addAll(CollectionUtils.union(a,b));
	    	//System.out.println("Union:\t" + unionVector.toString());
	    	return unionVector;
	    }
    public Vector getDisjunction(Collection a, Collection b)
        {
            Vector disjuctionVector = new Vector();
            disjuctionVector.addAll(CollectionUtils.disjunction(a,b));
            return disjuctionVector;
        }
	public void printDisjunctionString(Vector disjunctionVector)
        {
            System.out.println("");
            String w = "";
            Enumeration e = disjunctionVector.elements();
            w = "Disj:{";
            while(e.hasMoreElements()){
                w += "(" + e.nextElement().toString() + ")";
            }
            w += "}";
            System.out.println(w);
        }
    
	public String printIntersectionString(Vector intersectionVector)
	    {
            String toWrite = "";
	    	MultiHashMap longestMhm = new MultiHashMap();
	    	MultiHashMap shortestMhm = new MultiHashMap();
	    	int tailleTab = 0;
	    	int tailleShortestTab = 0;
	    	if(_mhm1.size() == _mhm2.size()){
	    	    longestMhm = _mhm1;
	    	    shortestMhm = _mhm2;
	    	    }
	    	if(_mhm1.size() > _mhm2.size())
	    	    {
	    	    longestMhm = _mhm1;
	    	    shortestMhm = _mhm2;
	    	    }
	    	else
	    	    {
	    	    longestMhm = _mhm2;
	    	    shortestMhm = _mhm1;
	    	    }
	    	if(_element1Size > _element2Size){
	    	    tailleTab = _element1Size;
	    	    tailleShortestTab = _element2Size;
	    		}
	    	else{
	    	    tailleTab = _element2Size;//on calcule la taille maxi du tableau de mots/positions
	    	    tailleShortestTab = _element1Size;
	    		}
	    		//à la fin de ces 2 tests, tailleTab contient la taille maxi du tableau, tailleShortestTab
	    		//la taille du plus petit des segments
	    	    
	    	//String[] posAndWords = new String[tailleTab];
	    	ArrayList posAndWords = new ArrayList(tailleTab);//une ArrayList est préférable: plus flexible
	    	for(int i = 0; i < tailleTab; i++)//on remplit le tableau de "_"
	    	    {
	    	    //posAndWords[i] = "_";
	    	    posAndWords.add(i,"_");
	    	    }
	    	ArrayList posList = new ArrayList() ;
	    	ArrayList shortestPosList = new ArrayList() ;
	    	String word = "";
	    	
	    	Enumeration e = intersectionVector.elements();
	    	while(e.hasMoreElements()){//on parcourt le vecteur d'intersection
	    	    word = e.nextElement().toString();
	    	    posList.clear();
	    	    shortestPosList.clear();
	    	    posList.addAll(longestMhm.getCollection(word));//on récupère pour le mot courant la liste des positions
	    	    shortestPosList.addAll(shortestMhm.getCollection(word));
	    	    /*System.out.println("poslist: " + posList);
	    	    System.out.println("sposlist: " + shortestPosList);*/
	    	    int pos = 0;
	    	    int posShortest = 0;
	    	    int nbOfMov = 0;
	    	    Integer posInt;
	    	    Integer posIntShortest;
	    	    for(int ind = 0; ind < posList.size(); ind++){//boucle de remplissage de posAndWords
	    	        posInt = (Integer)posList.get(ind);//on récupère la position du mot traité dans le tableau de positions
	    	        pos = posInt.intValue();
	    	        if(ind < shortestPosList.size()){
	    	            posIntShortest = (Integer)shortestPosList.get(ind);
	    	            posShortest = posIntShortest.intValue();
	    	        	}
	    	        /*System.out.println("Mot: " + word + " Pos: " + pos + "\t" + posList);
	    	        System.out.println("Mot: " + word + " shortPos: " + posShortest + "\t" + shortestPosList);*/
	    	        if(shortestPosList.size() < 2){//Pas de répétition
	    	            if((intersectionVector.size() == shortestMhm.size())&&(shortestMhm.size() < longestMhm.size())){//1
	    	                if(aligned(word,pos,word,posShortest)){
	    	                    posAndWords.set(pos,word);
	    	            		}
	    	                else{
	    	                    posAndWords.set(pos,"(MOV:" + word + "," + posShortest + "=>" + pos +")");
	    	                    }
	    	        		}//1
	    	            else{//Déplacement d'élément: shortestMhm < intersectionVector
	    	                if(aligned(word,pos,word,posShortest)){//2
	    	                    //posAndWords.set(pos,"(MOV:" + word + posShortest + "=>" + pos + ")");
	    	                    posAndWords.set(pos,word);//TODO: régler problème plus tard
	    	            		}//2
	    	            	}
	    	            if((intersectionVector.size() == shortestMhm.size())&& (shortestMhm.size() == longestMhm.size())){
	    	                //System.out.println("MOV sans PERTE");
	    	                if(!aligned(word,pos,word,posShortest)){
	    	                    posAndWords.set(pos,"(MOV:" + word + "," + posShortest + "=>" + pos + ")");
	    	                    }
	    	                }
	    	        }
	    	        else{	    	            
	    	            if(aligned(word,pos,word,posShortest)){//tant que les mots sont alignés on écrit dans posAndWords
    	                    posAndWords.set(pos,word);
    	            		}
	    	            else{	    	                
	    	                for(int i = 0; i < shortestPosList.size(); i++){
	    	                    Integer tmp ;
	    	                    int tmpPos = 0;
	    	                    tmp = (Integer)shortestPosList.get(i);
	    	                    tmpPos = tmp.intValue();
	    	                    if(aligned(word,pos,word,tmpPos)){
	    	                        posAndWords.set(tmpPos,word);
	    	                        }
	    	                    else if(!aligned(word,pos,word,tmpPos)){
	    	                        //System.out.println("not aligned: " + word + " " + pos + " " + tmpPos);
                                    //posAndWords.set(tmpPos,word);
	    	                        }
	    	                    }
	    	                }
	    	            }
	    	        /*if(ind < shortestPosList.size()){//tant qu'on est dans les limites de la plus petite forme
	    	            posIntShortest = (Integer)shortestPosList.get(ind);
	    	            posShortest = posIntShortest.intValue();
	    	            System.out.println("Mot: " + word + " shortPos: " + posShortest + "\t");
	    	        	}
	    	        if(posShortest == pos){//si les 2 formes sont alignées
	    	            //posAndWords[pos] = word;
	    	            System.out.println("align: " + word);
	    	            posAndWords.set(pos,word);
	    	        	}	    	        
	    	        else if(posShortest != pos){//si les 2 formes ne sont pas alignées
	    	            //System.out.println("word: " + word);
	    	            if(posAndWords.contains(word)){//si le mot est répété, donc à plusieurs endroits de la chaîne
	    	                System.out.println("repet word: " + word);
	    	                //posAndWords[pos] = word + "(MOV:[" + posShortest + "=>" + pos + "]" + ")";
		    	            posAndWords.set(pos,word + "(REP:[" + posShortest + "," + pos + "]" + ")");
	    	                }
	    	            else if(!posAndWords.contains(word)){
	    	                posAndWords.set(pos,"(ADD:" + word + ")");
	    	                }
 
	    	            //ET si posAndWords ne contient pas déjà word (pas un MOV)
	    	        	}*/
	    	    }//fin: for
	    	}//fin: while
	    	//à la fin de cette boucle, on a un tableau posAndWords rempli de mots de intersectionVector aux positions 
	    	//trouvées dans le longestMhm
	    	
	    	//System.out.print("\n");
	    	/*for(int n = 0; n < posAndWords.length; n++)
	    	    {
	    	    toWrite += posAndWords[n] + " ";
	    	    //System.out.print(posAndWords[n]);
	    	    }
	    	//System.out.println("ToWrite: " + toWrite);
	    	_currentPattern = toWrite;*/
	    	for(int n = 0; n < posAndWords.size(); n++)
	    	    {
	    	    toWrite += posAndWords.get(n) + " ";
	    	    }
	    	//System.out.println("ToWrite: " + toWrite);
	    	_currentPattern = toWrite;
	    	return toWrite;
	    }

	public boolean aligned(String a, int pos1, String b, int pos2){//retourne true si les 2 chaînes sont alignées: même String/même position
	    boolean align = false;
	    if(a.equals(b)&&(pos1 == pos2)){
	        align = true;
	        }
	    return align;
	    }

	public void printStringsFromTable(Hashtable t)
	    {
	    Enumeration e = t.keys();
	    //les keys sont des entiers
	    //Object n = null;
	    while(e.hasMoreElements())
	        {
	        	Object k = e.nextElement();
	        	System.out.println("key: " + k + " value:" + t.get(k));
	        }

	    }

public static void main(String[] args) {
    	//Cas TEST    
    	//cas normal insertions multiples:OK
    	/*String[] tata = {"A","X","B","Y","C"};
    	String[] toto = {"A","B","C"};*/
		//cas identité: OK
		/*String[] tata = {"A","B","C"};
		String[] toto = {"A","B","C"};*/
		//cas déplacement: OK
		/*String[] tata = {"C","B","A"};
		String[] toto = {"A","B","C"};*/
		//cas déplacement: OK
		/*String[] tata = {"A","B","C"};
		String[] toto = {"B","A"};*/
		//cas déplacement: OK
    	/*String[] tata = {"A","B"};
    	String[] toto = {"B","A"};*/
    	//cas déplacement: X
		/*String[] tata = {"A","B","C","D","E"};
		String[] toto = {"A","B","D","E","C"};*/
    	//cas déplacement: OK
		/*String[] tata = {"A","B","C"};
		String[] toto = {"C","B","A"};*/
    	//cas répétition: OK
		/*String[] tata = {"C","B","A"};
		String[] toto = {"C","B","B"};*/
    	//cas répétition:OK
    	/*String[] tata = {"A","B","C","D"};
    	String[] toto = {"A","B","B","D"};*/
    	//cas répétition:OK
    	String[] toto = {"A","B","C"};
    	String[] tata = {"A","X","C","D"};
    	/*String[] tata = {"A","B","C"};
    	String[] toto = {"A","","C"};*/

    	//FIN Cas TEST
    	
    	/*String[] toto = {"le","chat","mange","le","fromage","de","le","voisin","de","toto"};
	    String[] tata = {"le","rat","mange","le","fromage","de","le","chat","de","toto"};*/
    	/*String[] toto = {"???", "?????????", "??????", "????????", "??????", "??????", "????????"};
    	String[] tata = {"???", "?????????", "??????",  "??????", "????????", "????????", "??????"};*/

    	
    	/*String[] titi = {"A","B","C","D"};
    	String[] tata = {"le","chat","mange","la","souris"};
    	 String[] toto = {"le","très","gros","chat","poilu","mange","goulûment","la","toute","petite","souris","grise"};*/
    	PairIndexer p = new PairIndexer();
    	/*String[] toto = {"<NB>", "%","alors", "que", "Kalisto", "les", "cotations", "n'ont", "toujours", "pas", "pu", "reprendre"};
    	String[] tata = {"<NB>" ,"%" ,"alors", "que", "Suez", "recule", "de", "<NB>"};*/
    	
	    _element1Size = toto.length;
	    _element2Size = tata.length;
	    p.indexPair(toto,tata);
	    System.out.println(p._currentPattern);
        MultiHashMap m1;
        MultiHashMap m2;
        m1 = p.indexSequence(toto);
        m2 = p.indexSequence(tata);
        Vector v = p.getDisjunction(m1.entrySet(),m2.entrySet());
        TreeSet s = new TreeSet();
        Enumeration e = v.elements();
        String[] t = new String[v.size()];
        
        System.out.println("Disjunction: " + v.toString());
	}

/*##################################################################################
 * SETTERS & GETTERS 
 *##################################################################################
 */
/**
 * @return Returns the _separator.
 */
public String get_separator() {
	return _separator;
}
/**
 * @param _separator The _separator to set.
 */
public void set_separator(String aseparator) {
    _separator = "";
	_separator = aseparator;
}

/**
 * @return Returns the _element1.
 * @return Retourne _element1.
 */
public String get_element1()
    {
        return _element1;
    }
/**
 * @param _element1 The _element1 to set.
 * @param _element1 Le _element1 à paramétrer.
 */
public void set_element1(String anelement)
    {
    	_element1 = "";
        _element1 = anelement;
    }
/**
 * @return Returns the _element2.
 * @return Retourne _element2.
 */
public String get_element2()
    {
        return _element2;
    }
/**
 * @param _element2 The _element2 to set.
 * @param _element2 Le _element2 à paramétrer.
 */
public void set_element2(String anelement)
    {
    	_element2 = "";
        _element2 = anelement;
    }

/**
 * @return Returns the _generalSequenceSet.
 * @return Retourne _generalSequenceSet.
 */

public TreeSet get_generalSequenceSet()
    {
        return _generalSequenceSet;
    }
/**
 * @param set2 The _sequenceSet2 to set.
 * @param set2 Le _sequenceSet2 à paramétrer.
 */
public void set_generalSequenceSet(TreeSet aset)
    {
    	_generalSequenceSet = null;
    	_generalSequenceSet = aset;
    }
/**
 * @return Returns the _mhm1.
 * @return Retourne _mhm1.
 */
public static MultiHashMap get_mhm1()
    {
        return _mhm1;
    }
/**
 * @param _mhm1 The _mhm1 to set.
 * @param _mhm1 Le _mhm1 à paramétrer.
 */
public static void set_mhm1(MultiHashMap _mhm1)
    {
        PairIndexer._mhm1 = _mhm1;
    }
/**
 * @return Returns the _mhm2.
 * @return Retourne _mhm2.
 */
public static MultiHashMap get_mhm2()
    {
        return _mhm2;
    }
/**
 * @param _mhm2 The _mhm2 to set.
 * @param _mhm2 Le _mhm2 à paramétrer.
 */
public static void set_mhm2(MultiHashMap _mhm2)
    {
        PairIndexer._mhm2 = _mhm2;
    }
/*##################################################################################
 * SETTERS & GETTERS 
 *##################################################################################
 */

}

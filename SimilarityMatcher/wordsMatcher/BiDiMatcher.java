/*
 * Date: 18 mai 2004
 */
package wordsMatcher;

import org.apache.commons.lang.*;
import java.io.File;

/**
 * @author balveta
 * Package: wordsMatcher
 * Class: BiDiMatcher.java
 * TODO
 * - proposer une longest CS et une shortest CS
 * - se servir des étapes de comparaison mot à mot pour calculer des scores
 * - au départ, indépendamment de l'ordre, évaluer si les 2 chaînes utilisent plus ou 
 * moins le même stock de mots => donner 1 score
 * - systématiser les diffA/diffB, réintroduire les marqueurs de position (attrape _ ses, mange _ avidité)
 * - raffiner pour les cas d'insertions multiples: A toto B toto C/A B C
 * - proposer un appariement approché des mots de chaque chaîne 
 * - Ajouté le 20/11/2005: régler le problème de: un = "le chat mange la souris", deux = "le gros chat mange la petite souris"
 * => CommonWords "le _ _ _ _ _ souris". Problème des insertions multiples.
 */

public class BiDiMatcher
{
	String _A;
	String _B;
	String _invA;
	String _invB;
	String[] _tabA;
	String[] _tabB;
	String[] _invTabA;
	String[] _invTabB;
	static int _nbOfRealWords;
	static double _ratioWordsUnderscore;
	static int _nbOfUnderscores;
		
	public void init(String A, String B)//fait tout ce qu'il y a à faire au début
	{
		_nbOfRealWords = 0;
		_ratioWordsUnderscore = 0;
		_nbOfUnderscores = 0;
		set_A(A);
		set_B(B);
		_invA = StringUtils.reverse(_A);//on inverse les 2 chaînes
		_invB = StringUtils.reverse(_B);

		_tabA = StringUtils.split(_A);//on stocke les chaînes dans 2 tableaux	
		_tabB = StringUtils.split(_B);
	
		_invTabA = StringUtils.split(_invA);//on stocke les chaînes inversées dans 2 tableaux
		_invTabB = StringUtils.split(_invB);
	
	}
				
	public String traverseTab(String[] aStringTab, String[] anotherStringTab)//cette classe parcourt 2 tableaux de string et renvoie les mots en commun
	{
		String commonWords = "";
		String diffA = "";
		String diffB = "";
		int maxLen = 0;
		int minLen = 0;
		boolean AGTB = false;
		if(aStringTab.length > anotherStringTab.length)
		{
			AGTB = true;
			maxLen = aStringTab.length;
			minLen = anotherStringTab.length;
		}
		else
		{
			maxLen = anotherStringTab.length;
			minLen = aStringTab.length;
		}
		
		
		for(int i = 0; i < maxLen; i++)//parcours des 2 tableaux
		{
			if(i < minLen)//tant qu'on n'a pas atteint la fin du tableau le plus petit
			{
				/*System.out.println("A: " + aStringTab[i].toString());
				System.out.println("B: " + anotherStringTab[i].toString());*/
				
				if(aStringTab[i].equals(anotherStringTab[i]))
				{
					commonWords = commonWords + aStringTab[i].toString() + " ";
					//System.out.println("1 " + commonWords);
				}
				else if (!aStringTab[i].equals(anotherStringTab[i]))
				{
					commonWords = commonWords + "_ ";
					_nbOfUnderscores ++;//on ajoute 1 au nombre de différences entre les 2 chaînes
					diffA = diffA + " " + aStringTab[i] + " ";
					diffB = diffB + " " + anotherStringTab[i] + " ";
					//System.out.println("2" + commonWords);
				}
			}//on atteint la fin du tableau le plus court 
			 
			else 
			{
				commonWords = commonWords + "_ ";//et on continue à remplir de "_" jusqu'à la fin du tableau le plus long
				_nbOfUnderscores++;//en ajoutant 1 au nombre de différences à chaque fois
			}
				
		}
		//System.out.println("diffA: " + diffA + " diffB: " + diffB);
		return commonWords;
	}
	
	public String joinStrings(String[] aString, String[] anotherString)
	{
		String joinedStrings = "";
		for(int ind = 0; ind < aString.length; ind ++)//on sait que les 2 chaînes ont la même longueur
		{
			if(aString[ind].equals("_"))//si A commence par "_"
			{
				if(!anotherString[ind].equals("_"))//si B n'est pas  "_"
				{
					joinedStrings = joinedStrings + " " + anotherString[ind];
				}
				else //sinon si B = "_"
				{
					joinedStrings = joinedStrings + " _";
				} 
			}
			else if(!aString[ind].equals("_"))//sinon si A n'est pas "_"
			{
				joinedStrings = joinedStrings + " " + aString[ind];
			}
		}
		
		return joinedStrings;
	}
//Classes utilitaires
	/**
	 * @return
	 */
	public String get_A()
	{
		return _A;
	}

	/**
	 * @return
	 */
	public String get_B()
	{
		return _B;
	}

	/**
	 * @param string
	 */
	public void set_A(String string)
	{
		_A = string;
	}

	/**
	 * @param string
	 */
	public void set_B(String string)
	{
		_B = string;
	}
	
	public int get_nbOfUnderscores()
	{
		return _nbOfUnderscores;
	}
//Fin classes utilitaires

	
	
	public static void main(String[] args)
	{
		BiDiMatcher bd = new BiDiMatcher();
		/*String un = 	"?? ????? ?????????? ??? ???? ???? ??????????";
		String deux = 	"?? ????? ?? ???? ???? ????????? ??? ????????";*/
		String un = 	"le chat mange la souris";
		String deux = 	"le gros chat mange la petite souris";
		bd.init(un, deux);
				
		String comWords = bd.traverseTab(bd._tabA, bd._tabB);
		//System.out.println("invA " + bd._invA);
		String invComWords = StringUtils.reverse(bd.traverseTab(bd._invTabA, bd._invTabB));
		String towrite = "";
		towrite = "Common Words:\t" + comWords;
        towrite += "\nInv Common words:\t" + invComWords;
        
        /*System.out.println("Common words:\n" + comWords);
        System.out.println("Inv Common words:\n" + invComWords);*/
        String jWords;
        jWords = bd.joinStrings(StringUtils.split(comWords), StringUtils.split(invComWords));
        //System.out.println("JWords: " + jWords);
        towrite += "\nJWords:\t" + jWords;
        PrintFileToEncoding pe = new PrintFileToEncoding();
        File out = new File("C:\\Test\\biditest.txt");
        pe.writeString(out,towrite,"Unicode");
	}

}

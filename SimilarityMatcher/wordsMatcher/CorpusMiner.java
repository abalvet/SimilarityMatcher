/*
 * Date: 19 mai 2004
 * Notes: un bon seuil pour trouver l'armature des questions se situe aux alentours de 0.7,
 * en revanche, pour trouver la réponse la plus proche, un seuil plus bas (~0.55) est nécessaire.
 */
package wordsMatcher;
import java.io.*;

import org.apache.commons.lang.*;
import org.apache.commons.io.*;
import java.nio.charset.*;

/**
 * @author balveta
 * Package: wordsMatcher
 * Class: CorpusMiner.java
 */
public class CorpusMiner
{
	String _A = "";
	String _B = "";
	double _seuil = 0;
	Charset _charSet; 

	
	
	public static void main(String[] args)
	{
		if (args.length < 3)
		{
			System.out.println("USAGE: wordsMatcher.CorpusMiner [Fichier] [Seuil] [Encodage]");
		}
		else
		{
			CorpusMiner cp = new CorpusMiner();
			BiDiMatcher bd = new BiDiMatcher();
			
			File f = new File(args[0]);
			
			cp.set_seuil(Double.valueOf(args[1]).doubleValue());//récupérer la valeur de seuil (double) à partir d'une string
			File out = new File(f + "_patts" + cp._seuil + ".txt");
			File out_csv = new File(f + "_patts" + cp._seuil + ".csv");
			String enc_in = args [2];
			MyFileUtils mfu = new MyFileUtils();
			LevensteinScore ls = new LevensteinScore();
			int lscore = 0;
			double rellscore = 0;
			int nbOfDiffs = 0;
			double percentOfDiffs = 0;
			int nbOfWords = 0;
			int index = 0;
		
			String firstSentence = "";
			String[] tabOfStrings;
			String fString = "";
		
			//fString = mfu.getStringFromFile(f);
			try{
			    fString = FileUtils.readFileToString(f, enc_in);
			}
			catch(IOException e){
			    e.printStackTrace();
			}
			
			tabOfStrings = mfu.getTabFromString(fString);
			String toWrite = ""; 
			String toWriteCSV = "";
			for(int i = 0; i < tabOfStrings.length; i++)
			{
			
				firstSentence = StringUtils.chomp(tabOfStrings[i]);
				for(int u = 1; u < tabOfStrings.length; u++)
				{
					if(!firstSentence.equals(tabOfStrings[u]))
					{
						String secSentence = StringUtils.chomp(tabOfStrings[u]);
						lscore = ls.getLevScore(firstSentence, secSentence);
						rellscore = ls.getRelLevScore(firstSentence, secSentence);
						if(rellscore < cp._seuil)//si les 2 phrases sont proches
						{
							bd.init(firstSentence, secSentence);
							String comWords = bd.traverseTab(bd._tabA, bd._tabB);
							String invComWords = StringUtils.reverse(bd.traverseTab(bd._invTabA, bd._invTabB)); 
							String jWords;
							jWords = bd.joinStrings(StringUtils.split(comWords), StringUtils.split(invComWords));
							nbOfDiffs = StringUtils.countMatches(jWords, "_");
							if(firstSentence.length() > secSentence.length())//on calcule le pourcentage de vrais mots
							{
								nbOfWords = StringUtils.countMatches(firstSentence," ");
							}
							else nbOfWords = StringUtils.countMatches(secSentence," ");
						
							percentOfDiffs = (double)(1 - ((double)nbOfDiffs/(double)nbOfWords)); 
							if(percentOfDiffs > 0.1)//bonne valeur pour filtrer les différences trop grandes
							{
						
								/*System.out.println("ComWords:\n " + firstSentence + " " + secSentence 
													+ "\n " + comWords
													+ "\n" + "*************");*/
								toWrite += ("<ID>" + index++ + "</ID>"
								+ "<FSentence>" + firstSentence + "</FSentence>" 
								+ "<SSentence>" + secSentence + "</SSentence>"
								+ "<JointWords>" + jWords + "</JointWords>"
								+ "<PercentOfDiffs>" + percentOfDiffs + "</PercentOfDiffs>" + "\n");
								
								toWriteCSV += (
								        index++ + "\t"
										+ firstSentence + "\t" 
										+ secSentence + "\t"
										+ jWords + "\t"
										+ percentOfDiffs + "\t\n"); 
								
							    /*System.out.println("<ID>" + index++ + "</ID>"
													+ "<FSentence>" + firstSentence + "</FSentence>" + "<SSentence>" + secSentence + "</SSentence>"
													+ "<JointWords>" + jWords + "</JointWords>"
													+ "<PercentOfDiffs>" + percentOfDiffs + "</PercentOfDiffs>");*/
							//System.out.println("RelLevScore:\n" + firstSentence + "\n" + tabOfStrings[u] + "\n" + rellscore);
							}
							try{
							    FileUtils.writeStringToFile(out, toWrite, "Unicode");
							    FileUtils.writeStringToFile(out_csv, toWriteCSV, "Unicode");
								}
							catch(IOException e){
							    e.printStackTrace();
							    }
						}
					}
				}	
			}

		}
	}
	
	public void set_seuil(double seuil)
	{
		_seuil = seuil;
	}
}

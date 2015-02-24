/*
 * Date: 19 mai 2004
 */
package wordsMatcher;
import org.apache.commons.lang.*;
/**
 * @author balveta
 * Package: wordsMatcher
 * Class: LevensteinScore.java
 */
public class LevensteinScore
{
	public int getLevScore(String a, String b)//Score de Levenstein simple
	{
		int lscore = 0;
		lscore = StringUtils.getLevenshteinDistance(a, b);
		return lscore;
	}
	
	public double getRelLevScore(String a, String b)//Score de Levenstein rapporté à la longueur de la chaîne la plus longue
	{
		double relLevScore = 0;
		double maxLen = 0;
		double lscore = (double)StringUtils.getLevenshteinDistance(a, b);
		if(a.length() > b.length())
		{
			maxLen = a.length();
		}
		else maxLen = b.length();
		relLevScore = lscore/maxLen;
		return relLevScore;
	}
	
}

/*
 * Date: 19 mai 2004
 */
package wordsMatcher;
import org.apache.commons.io.FileUtils;
import java.io.*;
import java.nio.charset.Charset;
import org.apache.commons.lang.*;

/**
 * @author balveta
 * Package: wordsMatcher
 * Class: FileUtils.java
 */
public class MyFileUtils
{
	String _encoding = "";
	Charset _charSet;
	
	public String getStringFromFile(File aFile)
	{
		get_encoding(aFile);
		String theString = "";
		
		try
		{
			theString = FileUtils.readFileToString(aFile, _encoding);
			//System.out.println("_encoding: " + _encoding); 
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
		return theString;
	}
	
	public String[] getTabFromString(String aString)
	{
		String[] tab = StringUtils.split(aString, "\n");
		
		return tab;
	}

	public static void main(String[] args)
	{
	    if(args.length != 3){
	        System.out.println("USAGE: java wordsMatcher.MyFileUtils [File IN] [ENCODING IN] [ENCODING OUT]");
	        }
	    else
	        {
	        MyFileUtils fu = new MyFileUtils();
	        fu.set_encoding(args[1]);
	        String encoding_out = args[2];
	        File f = new File(args[0]);
		
	        PrintToEncoding ps = new PrintToEncoding();
	        String toprint = fu.getStringFromFile(f);
	        toprint = toprint + "\n" + "??????? ?????? ???? ?????? ??? ?????? ?? ?????? ?? ????";
	        String thefile = "C:\\Test\\test_" + encoding_out + ".txt";
	        ps.print(toprint,thefile,encoding_out);
	        //System.out.println("printing: " + thefile + " " + encoding_out);
	        }

	}
//classes utilitaires
	/**
	 * @return
	 */
	public String get_encoding(File aFile)
	{
		try
		{
			FileReader in = new FileReader(aFile);
			_encoding = in.getEncoding();
			//marche pas
		}
		catch(FileNotFoundException fi)
		{
			fi.printStackTrace();
		}
		return _encoding;
	}

	/**
	 * @param string
	 */
	public void set_encoding(String string)
	{
		_encoding = string;
	}
//	classes utilitaires
	//TODO: r�gler probl�me de la d�tection de l'encodage
}

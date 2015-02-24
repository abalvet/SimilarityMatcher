/*
 * Created on 13 nov. 2005
 * Créé�� le 13 nov. 2005
 */
package wordsMatcher;

import java.io.*;

/**
 * @author Balvet Antonio
 * @version 1.0
 * @param String aString: la String à imprimer
 * @param String aFile: le nom du fichier
 * @param String encoding: l'encodage (UTF-8, UTF-16, Unicode)
 * 
 * Une classe outil pour enregistrer des chaînes (format Unicode par défaut en interne)
 * en choisissant l'encodage: UTF-8, Unicode, UTF-16 etc...
 *  
 */
public class PrintToEncoding {
    public void print(String aString, String aFile, String encoding){
        try
        {
            PrintStream ps = new PrintStream(aFile, encoding);
            ps.print(aString);
            ps.flush();
            ps.close();
        } 
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /*public static void main(String[] args)
        {
        PrintToEncoding f = new PrintToEncoding();
        String aFile = "C:\\Test\\test_";
        String uni = "Unicode";
        String utf8 = "UTF-8";
        String aString =("διάβηκε τόπους αφού πάτησε της Τροίας το κάστρο το άγιο" 
				+  "\n" +
				"<w POS=\"v\">排队</w> <w POS=\"l\">打耳光</w>");
        f.print(aString, aFile+uni+".txt", "Unicode");
        System.out.println("Print: " + utf8 + " " + aString);
        f.print(aString,aFile+utf8+".txt", "UTF-8");
        f.print(aString,aFile+"unicode"+".txt", "Unicode");
        System.out.println("Print: " + uni+ " " + aString);
        }*/
}
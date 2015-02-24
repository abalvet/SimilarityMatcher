/*
 * Created on 16 nov. 2005
 * Cr�� le 16 nov. 2005
 */
package wordsMatcher;
import org.apache.commons.io.*;
import java.io.*;
/**
 * @author Balvet Antonio
 * @version 1.0
 */
public class PrintFileToEncoding {
    /**
     * @param aFile: fichier � lire
     * @param encoding: encodage d'entr�e
     * @return a String: String du fichier
     */
    public String readString(File aFile, String encoding){
        
        String content = "";
        try{
            content = FileUtils.readFileToString(aFile,encoding);
        }
        catch(IOException e){
            e.printStackTrace();
            }
        return content;
        }
    /**
     * @param aFile: fichier de sortie
     * @param content: String � �crire
     * @param encoding: encodage de sortie
     */
    public void writeString(File aFile, String content, String encoding){
        try{
            FileUtils.writeStringToFile(aFile,content,encoding);
            }
        catch(IOException e){
            e.printStackTrace();
            }
        }
    
    /**
     * @param args: args[0] fichier � lire, args[1] encodage d'entr�e (UTF-8, Unicode, etc...), 
     * args[2] encodage de sortie. 
     */
    public static void main(String[] args)
        {
        PrintFileToEncoding t = new PrintFileToEncoding();
        File f = new File(args[0]);
        String enc_in = args[1];
        String enc_out = args[2];
        String cont = t.readString(f, enc_in);
        File o = new File(f.getName()+ enc_out + ".txt");
        t.writeString(o, cont, enc_out);
        }
}

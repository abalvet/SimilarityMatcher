/*
 * Created on 16 nov. 2005
 * Créé le 16 nov. 2005
 */
package wordsMatcher;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;


/**
 * @author Balvet Antonio
 *
 */
public class TestFormatPrint {

    public static void main(String[] args)
        {
        //UTF-8
        String aString = "";
        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream("infilename"), "UTF8"));
            String str = in.readLine();
        } 
        catch (UnsupportedEncodingException e) {
        } 
        catch (IOException e) {
        }
        try {
            Writer out = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("outfilename"), "UTF8"));
            out.write(aString);
            out.close();
        } 
        catch (UnsupportedEncodingException e) {
        } 
        catch (IOException e) {
        }
        //UTF-8
        
        //Decoder
        // Create the encoder and decoder for Unicode
        Charset charset = Charset.forName("Unicode");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();
        
        try {
            // Convert a string to Unicode bytes in a ByteBuffer
            // The new ByteBuffer is ready to be read.
            ByteBuffer bbuf = encoder.encode(CharBuffer.wrap("a string"));
        
            // Convert Unicod bytes in a ByteBuffer to a character ByteBuffer and then to a string.
            // The new ByteBuffer is ready to be read.
            CharBuffer cbuf = decoder.decode(bbuf);
            String s = cbuf.toString();
        } 
        catch (CharacterCodingException e) {
        }
        //Decoder
        }
}

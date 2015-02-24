/*
 * Created on 13 janv. 2006
 * Créé le 13 janv. 2006
 */
package wordsMatcher;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import tools.BufferedPrintFileToEncoding;
import java.util.ArrayList;

/**
 * @author Balvet Antonio
 *
 */
public class TestPrinter {
    public void print(){
        }

    public static void main(String[] args)
        {
        File in = new File("C:\\TEST\\LCMC\\LCMC_TRAIN_tags_only_uni.txt");
        File out = new File(in + "_clean");
        try
            {
                String c = FileUtils.readFileToString(in,"Unicode");
                ArrayList toto = new ArrayList();
                
                String c1 = c.replaceAll("\n"," ");
                String f = "";
                //System.out.println(c1.length());
                /*for(int i = 0; i < c1.length; i++){
                    f += StringUtils.trimToEmpty(c1[i]) + " ";
                    }*/
                System.out.println(c1);
                //BufferedPrintFileToEncoding.printAppended(out,"Unicode",c1);
            } 
        catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
}

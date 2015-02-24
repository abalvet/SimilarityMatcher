/*
 * Created on 26 mai 2011
 * Créé le 26 mai 2011
 */
package utils;

public class InfoDisplay {
    public void printConsoleStatusBar(int begin, int end, int step) throws Exception{
        System.err.print("working: ");
        for(int i = begin; i <=end; i= i + step){
          System.err.print("\b|");
          Thread.sleep(50);   
          System.err.print("\b/");
          Thread.sleep(50);   
          System.err.print("\b=");
          Thread.sleep(50);   
          System.err.print("\b\\");
        }    
        System.err.print("\b ");
        System.err.println("\ndone!");
    }
    

    
}

/*
 * Created on 26 mai 2011
 * Créé le 26 mai 2011
 */
package utils;

public final class InfoDisplay {
    public void printConsoleStatusBar(int begin, int end, int step) throws Exception{
        System.err.print("working: ");
        for(int i = begin; i <=end; i= i + step){
          System.err.print("    |\r");
          Thread.sleep(50);   
          System.err.print("    +\r");
          Thread.sleep(50);   
          System.err.print("    \\r");
          Thread.sleep(50);   
          System.err.print("    =\r");
          Thread.sleep(50);   
          System.err.print("    /\r");
        }    
        System.err.print("    *\r");
        System.err.println("\rDone!!!!         ");
    }
    
    public static void main(String[] args) throws Exception{
    	System.err.println("testing progress bar");
    	InfoDisplay i = new InfoDisplay();
    	i.printConsoleStatusBar(0, 100, 10);
    }
    

    
}

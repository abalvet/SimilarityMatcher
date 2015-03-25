/**
 * 
 */
package utils;

/**
 * @author antonio
 * @date 19 mars 2015 2015
 * SimilarityMatcher
 * utils
 * RunThread.java
 * 
 */
public class RunThread {
		  public static void main(String[] args) {
		    ThreadTest t = new ThreadTest("A");
		    ThreadTest t2 = new ThreadTest("  B", t);
		    try {
		      Thread.sleep(1000);
		    } catch (InterruptedException e) {
		      e.printStackTrace();
		    }
		    System.out.println("statut du thread " + t.getName() + " = " + t.getState());
		    System.out.println("statut du thread " + t2.getName() + " = " +t2.getState());                
		  }
		
}

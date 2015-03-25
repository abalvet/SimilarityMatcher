/**
 * 
 */
package utils;

/**
 * @author antonio
 * @date 19 mars 2015 2015
 * SimilarityMatcher
 * utils
 * ThreadTest.java
 * 
 */
public class ThreadTest extends Thread {

	 Thread t;
	  public ThreadTest(String name){
	    super(name);
	    System.out.println("statut du thread " + name + " = " +this.getState());
	    this.start();
	    System.out.println("statut du thread " + name + " = " +this.getState());
	  }

	  public ThreadTest(String name, Thread t){
	    super(name);
	    this.t = t;
	    System.out.println("statut du thread " + name + " = " +this.getState());
	    this.start();
	    System.out.println("statut du thread " + name + " = " +this.getState());
	  }

	  public void run(){
	    for(int i = 0; i < 10; i++){
	      System.out.println("statut " + this.getName() + " = " +this.getState());
	      if(t != null)
	        System.out.println("statut de " + t.getName() + " pendant le thread " + this.getName() +" = " +t.getState());
	    }
	  }

	  public void setThread(Thread t){
	    this.t = t;
	  }

}

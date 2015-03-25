/**
 * 
 */
package utils;

import java.util.Random;

/**
 * @author antonio
 * @date 20 mars 2015 2015
 * SimilarityMatcher
 * utils
 * CorpusPair.java
 * 
 */
public class CorpusPair {
	
	private int _p1 = 0;
	private int _p2 = 0;
	private boolean _done = false;
	
	public CorpusPair(){}
	
	public CorpusPair(int p1, int p2, boolean done){
		_p1 = p1;
		_p2 = p2;
		_done = done;
		//System.out.println(_p1 + "," + _p2 + ":" + _done);
	}
	
	public String toString(){
		String out = "";
		out = "["+ _p1 + "," + _p2 + ":" + _done + "]";
		return out;
	}

	/**
	 * @return the _p1
	 */
	protected int get_p1() {
		return _p1;
	}

	/**
	 * @param _p1 the _p1 to set
	 */
	protected void set_p1(int _p1) {
		this._p1 = _p1;
	}

	/**
	 * @return the _p2
	 */
	protected int get_p2() {
		return _p2;
	}

	/**
	 * @param _p2 the _p2 to set
	 */
	protected void set_p2(int _p2) {
		this._p2 = _p2;
	}

	/**
	 * @return the _done
	 */
	protected boolean is_done() {
		return _done;
	}

	/**
	 * @param _done the _done to set
	 */
	protected void set_done(boolean _done) {
		this._done = _done;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random id = new Random();
		CorpusPair p = null;
		for(int i = 0; i < 10; i++){
		p = new CorpusPair(id.nextInt(10), id.nextInt(10), id.nextBoolean()); 
		System.out.println(p);
		}
		
	}

}

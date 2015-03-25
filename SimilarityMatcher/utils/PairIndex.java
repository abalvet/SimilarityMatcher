/**
 * 
 */
package utils;

/**
 * @author antonio
 * @date 23 mars 2015 2015
 * SimilarityMatcher
 * utils
 * PairIndex.java
 * 
 */
public class PairIndex {
	private int[] _index = new int[2];

	/**
	 * @return the _index
	 */
	protected int[] get_index() {
		return _index;
	}

	/**
	 * @param _index the _index to set
	 */
	protected void set_index(int[] _index) {
		this._index = _index;
	}
	

	public String printIndex(){
		String out = _index[0] + "," + _index[1];
		return out;
	}
	
	public int getSourceIndex(){
		return this._index[0];
	}
	public int getTargetIndex(){
		return this._index[1];
	}
	public void setSourceIndex(int s){
		this._index[0] = s;
	}
	public void setTargetIndex(int t){
		this._index[1] = t;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PairIndex p = new PairIndex();
		int[] i = {0, 2};
		p.set_index(i);
		System.out.println(p.printIndex());
	}

}

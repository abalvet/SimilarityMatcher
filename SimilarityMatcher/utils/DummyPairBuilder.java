/**
 * 
 */
package utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;


/**
 * @author antonio
 * @date 19 mars 2015 2015
 * SimilarityMatcher
 * utils
 * DummyPairBuilder.java
 * 
 */
public class DummyPairBuilder {
	private static CorpusPair p ;
	private static Set<CorpusPair> _corpusPairs = new HashSet<CorpusPair>();


	
	static synchronized void populateIndex(){
		Random id = new Random();
		Set<CorpusPair> index = new HashSet<CorpusPair>();
		Set<CorpusPair> sync_index = Collections.synchronizedSet(index);

		for(int i = 0; i <10000000; i++){
			p = new CorpusPair( id.nextInt(100),  id.nextInt(100), false);			
			sync_index.add(p);
			
		}
		
		System.err.println("CorpusPair index size: " + sync_index.size());
		set_corpusPairs(sync_index);
		//printStatus(get_corpusPairs());
	}
	
	static synchronized void printStatus(Set<CorpusPair> index){
		Iterator<CorpusPair> i = index.iterator();
		while(i.hasNext()){
			System.out.println(i.next());
		}
	}
	public static synchronized void set_corpusPairs(Set<CorpusPair> pairs){
		
		_corpusPairs = pairs;
	}
	public static synchronized Set<CorpusPair> get_corpusPairs(){
		return _corpusPairs;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		populateIndex();
		printStatus(DummyPairBuilder.get_corpusPairs());
		Set<CorpusPair> p = DummyPairBuilder.get_corpusPairs();
		Iterator<CorpusPair> i = p.iterator();
		CorpusPair cp = new CorpusPair();

		while(i.hasNext()){
			cp = i.next();
			cp.set_done(true);
		}
		printStatus(p);
	}

}

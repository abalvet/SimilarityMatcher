/**
 * 
 */
package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * @author antonio
 * @date 19 mars 2015 2015
 * SimilarityMatcher
 * utils
 * DummyPairStatusModifier.java
 * 
 */
public class DummyPairStatusModifier implements Runnable{
	static Set<CorpusPair> _pairs = new HashSet<CorpusPair>();
	static String _name = "";
	
	/**
	 * Basic constructor.
	 * @param pairs table describing pairs and their status.
	 * @param name the name of current thread.
	 */
	/*public DummyPairStatusModifier(Set<CorpusPair> pairs, String name){
		DummyPairBuilder.populateIndex();
		_pairs = pairs;
		_name = name;
	}*/
	public DummyPairStatusModifier(Set<CorpusPair> corpusPairs, String threadName){
		//DummyPairBuilder.populateIndex();
		_pairs = corpusPairs;
		_name = threadName;
	}
	
	public synchronized void modifyStatus(String threadName){
		//Random status = new Random();
		Set<CorpusPair> sync_index = Collections.synchronizedSet(_pairs);
		Iterator<CorpusPair> i = sync_index.iterator();
		while(i.hasNext()){
			CorpusPair p = i.next();
			if(!p.is_done()){
				p.set_done(true);
				//System.out.println(p + ", modified by: " + threadName);
			}
		}
	}

	public void run(){
		System.out.println("Thread: " + _name);
		this.modifyStatus(_name);
		//DummyPairBuilder.printStatus(_pairs);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		long lStartTime = System.currentTimeMillis();
		
		DummyPairBuilder.populateIndex();
		Set<CorpusPair> corpusPairs = DummyPairBuilder.get_corpusPairs();
		ArrayList<CorpusPair> corpusArray = new ArrayList<CorpusPair>();
		corpusArray.addAll(corpusPairs);
		ArrayList<CorpusPair> c1 = new ArrayList<CorpusPair>();
		ArrayList<CorpusPair> c2 = new ArrayList<CorpusPair>();
		int corpusSize = corpusPairs.size();
		for(int i=0; i < corpusSize/2; i++){
			c1.add(corpusArray.get(i));
		}
		
		for(int j = corpusSize/2; j < corpusSize; j++){
			c2.add(corpusArray.get(j));
		}
		
		Set<CorpusPair> s1 = new HashSet<CorpusPair>();
		Set<CorpusPair> s2 = new HashSet<CorpusPair>();
		s1.addAll(c1);
		s2.addAll(c2);
		
		System.out.println("spawning new thread: T1");
		Thread t = new Thread(new DummyPairStatusModifier(s1, "T1"));
		t.start();
		System.out.println("Thread status: " + t.getName() + " " + t.getState());
		System.out.println("spawning new thread: T2");
		Thread t2 = new Thread(new DummyPairStatusModifier(s2, "T2"));
		t2.start();
		System.out.println("Thread status: " + t2.getName() + " " + t2.getState());
		
		Set<CorpusPair> finalSet = new HashSet<CorpusPair>();
		finalSet.addAll(s1);
		finalSet.addAll(s2);
		System.out.println("size of finalSet: " + finalSet.size());
		
		/*for(int i = 1; i < 2; i++){
			System.out.println("spawning new thread: T" + i);
			Thread t = new Thread(new DummyPairStatusModifier(corpusPairs, "T"+i));
			t.start();
			System.out.println("Thread status: " + t.getName() + " " + t.getState());
		}*/
		
		System.out.println("Modified corpus pair index: " + corpusPairs.size());
		
		long lEndTime = System.currentTimeMillis();
			 
		long difference = lEndTime - lStartTime;
			 
		System.out.println("Elapsed milliseconds: " + difference);
		
		//DummyPairBuilder.printStatus(corpusPairs);

	}

}

package stringalgorithms;

import java.util.List;

import com.googlecode.concurrenttrees.radix.ConcurrentRadixTree;
import com.googlecode.concurrenttrees.radix.node.Node;
import com.googlecode.concurrenttrees.radix.node.NodeFactory;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharArrayNodeFactory;
import com.googlecode.concurrenttrees.radix.node.concrete.DefaultCharSequenceNodeFactory;
import com.googlecode.concurrenttrees.radix.node.util.PrettyPrintable;
import com.googlecode.concurrenttrees.radixreversed.ConcurrentReversedRadixTree;
import com.googlecode.concurrenttrees.radixreversed.ReversedRadixTree;
import com.googlecode.concurrenttrees.solver.LCSubstringSolver;
import com.googlecode.concurrenttrees.suffix.ConcurrentSuffixTree;
import com.googlecode.concurrenttrees.suffix.SuffixTree;
import com.googlecode.concurrenttrees.common.CharSequences;
import com.googlecode.concurrenttrees.common.Iterables;
import com.googlecode.concurrenttrees.common.PrettyPrinter;

public class TestTrees {
	
	public static void main(String[] args) {
	    ConcurrentRadixTree<Integer> tree = new ConcurrentRadixTree<Integer>(new DefaultCharArrayNodeFactory());
	    //ConcurrentRadixTree<Integer> tree = new ConcurrentRadixTree<Integer>(new DefaultCharSequenceNodeFactory());

	    tree.put("atanipenda", 1);
	    tree.put("atampenda", 2);
	    tree.put("utanipenda", 3);

	    System.out.println("Tree structure:");
	    // PrettyPrintable is a non-public API for testing, prints semi-graphical representations of trees...
	    StringBuilder str = new StringBuilder();
	    PrettyPrinter.prettyPrint(tree, str);

	    System.out.println(str);
	    
	    //System.out.println("Value for 'TEST' (exact match): " + tree.getValueForExactKey("TEST"));
	    //System.out.println("Value for 'TOAST' (exact match): " + tree.getValueForExactKey("TOAST"));
	    //System.out.println();
	    /*System.out.println("Keys starting with 'dé': " + Iterables.toString(tree.getKeysStartingWith("dé")));
	    System.out.println("Keys starting with 're': " + Iterables.toString(tree.getKeysStartingWith("re")));
	    System.out.println();
	    System.out.println("Values for keys starting with 'dé': " + Iterables.toString(tree.getValuesForKeysStartingWith("dé")));
	    System.out.println("Key-Value pairs for keys starting with 'dé': " + Iterables.toString(tree.getKeyValuePairsForKeysStartingWith("dé")));
	    System.out.println();
	    System.out.println("Keys closest to 'mentir': " + Iterables.toString(tree.getClosestKeys("mentir")));*/
	    
	        ConcurrentReversedRadixTree<Integer> rtree = new ConcurrentReversedRadixTree<Integer>(new DefaultCharArrayNodeFactory());

	        rtree.put("atanipenda", 1);
	        rtree.put("atanipiga", 2);
	        rtree.put("utanipenda", 3);

	        System.out.println("Tree structure:");
	        // PrettyPrintable is a non-public API for testing, prints semi-graphical representations of trees...
	        StringBuilder str2 = new StringBuilder();
		    PrettyPrinter.prettyPrint(rtree, str2);
	        System.out.println(str2);
	        /////////////////////
	        System.out.println("Suffixes for 'redémonter': " + Iterables.toString(CharSequences.generateSuffixes("redémonter")));
	        System.out.println("Suffixes for 'reredémarrer': " + Iterables.toString(CharSequences.generateSuffixes("reredémarrer")));
	        System.out.println("Suffixes for 'préredémonter': " + Iterables.toString(CharSequences.generateSuffixes("préredémonter")));

	        SuffixTree<Integer> stree = new ConcurrentSuffixTree<Integer>(new DefaultCharArrayNodeFactory());

	        stree.put("asu", 1);
	        stree.put("suasuasu", 2);
	        stree.put("sumasu", 3);

	        System.out.println();
	        System.out.println("Tree structure:");
	        // PrettyPrintable is a non-public API for testing, prints semi-graphical representations of trees...
	        StringBuilder str3 = new StringBuilder();
	        PrettyPrinter.prettyPrint((PrettyPrintable) stree, str3);

	        System.out.println(str3);
	        /*System.out.println("Value for 'TEST' (exact match): " + stree.getValueForExactKey("TEST"));
	        System.out.println("Value for 'TOAST' (exact match): " + stree.getValueForExactKey("TOAST"));
	        System.out.println();
	        System.out.println("Keys ending with 'ST': " + Iterables.toString(stree.getKeysEndingWith("ST")));
	        System.out.println("Keys ending with 'M': " + Iterables.toString(stree.getKeysEndingWith("M")));
	        System.out.println("Values for keys ending with 'ST': " + Iterables.toString(stree.getValuesForKeysEndingWith("ST")));
	        System.out.println("Key-Value pairs for keys ending with 'ST': " + Iterables.toString(stree.getKeyValuePairsForKeysEndingWith("ST")));
	        System.out.println();
	        System.out.println("Keys containing 'TE': " + Iterables.toString(stree.getKeysContaining("TE")));
	        System.out.println("Keys containing 'A': " + Iterables.toString(stree.getKeysContaining("A")));
	        System.out.println("Values for keys containing 'A': " + Iterables.toString(stree.getValuesForKeysContaining("A")));
	        System.out.println("Key-Value pairs for keys containing 'A': " + Iterables.toString(stree.getKeyValuePairsForKeysContaining("A")));
	        */ 
	        
	        String doc1 = "atanipenda"; 
	        String doc2 = "utampenda";
	        String doc3 = "atanipiga";
	        LCSubstringSolver solver = new LCSubstringSolver(new DefaultCharSequenceNodeFactory());

	        solver.add(doc1);
	        solver.add(doc2);
	        solver.add(doc3);

	        String longestCommonSubstring = CharSequences.toString(solver.getLongestCommonSubstring());
	        System.out.println(longestCommonSubstring);	        
	}

}

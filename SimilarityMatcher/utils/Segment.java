/**
 * This class implements a "segment" object.
 * A segment is an element from a corpus: sentences, chunks or other elements 
 * we wish to analyze.
 * A segment has different properties, such as its form (String), the number of basic
 * linguistic units it is made of ("words", characters, any other unit), etc.
 * This object is devised so as to speed up similarity detection in middle to 
 * large corpora. 
 */
package utils;

import java.util.ArrayList;
import java.util.HashSet;


/**
 * @author antonio
 * @date 23 mars 2015 2015
 * SimilarityMatcher
 * utils
 * Segment.java
 * 
 */
public class Segment {
	
	private static String _form = "";
	private static int _nbOfTokens = 0;
	private static int _nbOfChars = 0;
	private static ArrayList<Integer> _outLinks = new ArrayList<Integer>();//each segment should store knowledge about its neighbors
	private static ArrayList<Integer> _incomingLinks = new ArrayList<Integer>();//each segment should store knowledge about its neighbors
	private static HashSet<String> _tokens = new HashSet<String>();
	//each segment is seen as a vector of unique words, in order to compute similarity indexes, such as Jaccard
	private static HashSet<String> _specificVocabulary = new HashSet<String>();
	//each segment should store a vector of its specific "vocabulary" so as to be able to generate "fingerprints" for each segment
	//fingerprints will speed up similarity detection and will provide a foundation for a compressed representation of the corpus
	
	//fingerprint???
	
	public static void printSegmentInfo(){
		System.out.println("[\n"  
							+ "\tform: " + _form + "\n" 
							+ "\tnb_of_chars: " + _nbOfChars + "\n"
							+ "\tnb_of_tokens: "+ _nbOfTokens + "\n"
							+ "]");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Segment s = new Segment();
		s.set_form("toto");
		s.set_nbOfChars(s.get_form().toCharArray().length);
		s.set_nbOfTokens(1);
		s.printSegmentInfo();
	}

	/**
	 * @return the _form
	 */
	protected static String get_form() {
		return _form;
	}

	/**
	 * @param _form the _form to set
	 */
	protected static void set_form(String _form) {
		Segment._form = _form;
	}

	/**
	 * @return the _nbOfTokens
	 */
	protected static int get_nbOfTokens() {
		return _nbOfTokens;
	}

	/**
	 * @param _nbOfTokens the _nbOfTokens to set
	 */
	protected static void set_nbOfTokens(int _nbOfTokens) {
		Segment._nbOfTokens = _nbOfTokens;
	}

	/**
	 * @return the _nbOfChars
	 */
	protected static int get_nbOfChars() {
		if(_nbOfChars > 0){
		return _nbOfChars;
		}
		else{
			Segment._nbOfChars = Segment._form.toCharArray().length;
			return Segment._form.toCharArray().length;
		}
	}

	/**
	 * @param _nbOfChars the _nbOfChars to set
	 */
	protected static void set_nbOfChars(int _nbOfChars) {
		Segment._nbOfChars = _nbOfChars;
	}

	/**
	 * @return the _outLinks
	 */
	protected static ArrayList<Integer> get_outLinks() {
		return _outLinks;
	}

	/**
	 * @param _outLinks the _outLinks to set
	 */
	protected static void set_outLinks(ArrayList<Integer> _outLinks) {
		Segment._outLinks = _outLinks;
	}

	/**
	 * @return the _incomingLinks
	 */
	protected static ArrayList<Integer> get_incomingLinks() {
		return _incomingLinks;
	}

	/**
	 * @param _incomingLinks the _incomingLinks to set
	 */
	protected static void set_incomingLinks(ArrayList<Integer> _incomingLinks) {
		Segment._incomingLinks = _incomingLinks;
	}

	/**
	 * @return the _tokens
	 */
	protected static HashSet<String> get_tokens() {
		return _tokens;
	}

	/**
	 * @param _tokens the _tokens to set
	 */
	protected static void set_tokens(HashSet<String> _tokens) {
		Segment._tokens = _tokens;
	}

	/**
	 * @return the _specificVocabulary
	 */
	protected static HashSet<String> get_specificVocabulary() {
		return _specificVocabulary;
	}

	/**
	 * @param _specificVocabulary the _specificVocabulary to set
	 */
	protected static void set_specificVocabulary(HashSet<String> _specificVocabulary) {
		Segment._specificVocabulary = _specificVocabulary;
	}

}

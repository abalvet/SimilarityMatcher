/**
 * This class generates 2 combinations out of a given corpus, avoiding repeats.
 * It uses a 2 dimensional matrix to store each segments basic properties, 
 * to speed up similarity detection and extraction of Longest Common Subsequences.
 * Once a corpus has been transformed and stored into a Matrix Combinations, it can
 * be processed in an efficient way: either the whole Matrix if it is of reasonable size,
 * or n subsets of it, each one being processed by a different thread (or other divide and conquer approach).
 */
package utils;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;



/**
 * @author antonio
 * @date 23 mars 2015 2015
 * SimilarityMatcher
 * utils
 * MatrixCombinationsGenerator.java
 * 
 */
public class MatrixCombinationsGenerator implements Serializable {
    private  static  final  long serialVersionUID =  1350092881346723535L;

	static PairIndex[][] _matrix ;//matrix stores the indexes of each segment pair
	
	public static synchronized void setMatrix(String[] corpus){
		int len = corpus.length;
		_matrix = new PairIndex[len][len];
		PairIndex ind = new PairIndex();
		
		for(int i = 0; i < len ; i++){//adding a segment in each i,j cell, except whenever i == j
			for(int j = 0; j < len; j++){
				if(i != j){
					//_matrix[i][j] = corpus[i]+ corpus[j];
					ind.setSourceIndex(i);
					ind.setTargetIndex(j);
					_matrix[i][j] = ind;
					//System.out.print(i + "," + j + "\t");
				}
				else{
					//skipping: cells where source and target are the same
					//_matrix[i][j] = null;
					//System.out.print("same\t");
				}
			}
			System.out.println();
		}
	}
	
	public static void printMatrix(PairIndex[][] matrix){
		int x = matrix.length;
		PairIndex p = new PairIndex();
		//System.err.println("matrix size: " + x);
		for(int i = 0; i < x; i++){
			for(int j = 0; j < x; j++){
				if(i !=j){
					p.setSourceIndex(i);
					p.setTargetIndex(j);
					System.out.print(p.printIndex() + "\t");
				}
				else{
					System.out.print("xxx\t");
				}
			}
			System.out.println();
		}
	}
	
	
	/*public static void serializeMatrix(String aFile) throws FileNotFoundException, IOException{
		File out = new File(aFile);
		
		ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(out)) ;
		oos.writeObject(_matrix);
		oos.flush();
		oos.close();
		
	}*/
	
//TODO: add serialization methods to store the generated Matrix
//TODO: add deserialization methods
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] segs = {"a", "b", "c", "d", "e"};
		MatrixCombinationsGenerator.setMatrix(segs);
		MatrixCombinationsGenerator.printMatrix(_matrix);
		
	}

}

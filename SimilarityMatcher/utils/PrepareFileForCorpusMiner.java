package utils;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.io.*;
import org.apache.commons.io.FilenameUtils;

/***
 * 
 * @author antonio
 * @version 0.1
 * This class performs basic segmentation operations on a text, in order for CorpusMiner 
 * to generate pairs and patterns. The text to analyze has to be segmented into near-chunk elements,
 * in order to extract common subsequences.
 */

public class PrepareFileForCorpusMiner {
	static ArrayList<String> _segments = new ArrayList<String>();
	static ArrayList<String> _text = new ArrayList<String>();
	static String _defaultSegmentationRules = "[,\\?;\\.:\\!\\n\\r\\(\\)–…-]+";
	static boolean _default = true;
	/***
	 * 
	 * @param text The text to segment into basic units (chunk-like segments)
	 * @param segmentDelimitor a set of segment delimiters: commas, dots, etc.
	 */
	static void segmentText(ArrayList<String> text, boolean Default, String segmentDelimitor){
		_default = Default;
		Scanner s = null;
		for(String line:text){
			s = new Scanner(line);
			if(_default){
				//System.err.println("default segmentation");
				s.useDelimiter(_defaultSegmentationRules);
			}
			else{
				//System.err.println("custom segmentation");
				s.useDelimiter(segmentDelimitor);
			}
			while(s.hasNext()){
				String seg = s.next();
				//System.err.println("new segment: " + seg);
				//seg.trim();
				if(seg.matches("^ .*")){
					//System.err.println("format segment");
					seg.trim();
					seg = seg.substring(1);
				}
				if(seg.matches(" ?[\\n\\r]?") && (s.hasNext())){
					//System.err.println("skipping");
					s.next();
				}
				
				_segments.add(seg);
				
			}
			
		}
		s.close();
		System.err.println("text segmentation done: " + _segments.size() + " segments found");
	}
	
	static void getTextFromFile(String aFile) throws IOException{
		File input = new File(aFile);
		_text = (ArrayList<String>)FileUtils.readLines(input);
	}
	
	static void prettyPrintSegments(){
		StringBuffer sb = new StringBuffer();
		for(String segment: _segments){
			if(segment.matches(" ?[\\n\\r] ?")){
				System.err.println("carriage return");
			}
			//segment.trim();
			//segment = segment.replace("^ (.*)", "$1");
			sb.append(segment);
		}
		System.out.println(sb);
	}
	
	static void saveToFile(String out){
		File output = new File(out);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(output));
			for(String segment : _segments){
				if(segment.matches(" ?[\\n\\r] ?")){
				}
				
				bw.append(segment);
				bw.append(System.lineSeparator());
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		//USAGE: PrepareFileForCorpusMiner(String file, String customDelimiter, boolean defaultMode)
		String in = args[0];
		try {
			getTextFromFile(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		segmentText(PrepareFileForCorpusMiner._text, true, "");
		//System.out.println("segments: " + PrepareFileForCorpusMiner._segments);
		
		//prettyPrintSegments();
		String outname = FilenameUtils.removeExtension(in);
		outname = outname + "_segmented.txt";
		saveToFile(outname);
	}

}

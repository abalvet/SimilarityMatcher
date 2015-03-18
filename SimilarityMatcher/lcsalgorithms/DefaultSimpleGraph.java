package lcsalgorithms;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DefaultSimpleGraph {
	protected ArrayList<DefaultVertex> _VERTICES = new ArrayList<DefaultVertex>();
	static String _dotOutput ="";
	
	void addVertex(DefaultVertex v){
		v.createVertexID();
		this._VERTICES.add(v);
	}
	
	void addEdge(DefaultVertex source, DefaultVertex target){
		source._OUTGOINGEDGES.add(target);
		target._INCOMINGEDGES.add(source);
	}
	
	void resetGraph(){
		this._VERTICES.clear();
	}
	public static void traverseGraphFromVertex(DefaultVertex v){
		/*ArrayList<String> str = new ArrayList<String>();
		str.add(v.get_VERTEXID().toString());
		str.add(v.get_VERTEXLABEL());*/
		//System.out.println(v.get_VERTEXLABEL() + " ;");
		_dotOutput = _dotOutput + v.get_VERTEXLABEL() + " ;\n";
		
		ArrayList<DefaultVertex> out = v._OUTGOINGEDGES;
		for (DefaultVertex vertex : out) {
			//System.out.print(v.get_VERTEXLABEL() +" -> ");
			_dotOutput = _dotOutput + v.get_VERTEXLABEL() +" -> ";
			traverseGraphFromVertex(vertex);//TODO: add cycle detection
		}	
		
	}
	
	public void asciiPrintGraph(DefaultSimpleGraph g){
		DefaultVertex start = g._VERTICES.get(0);

		System.out.print(start.get_VERTEXLABEL());
		
		ArrayList<DefaultVertex> out = start._OUTGOINGEDGES;
		for (DefaultVertex vertex : out) {
			//System.out.print(" --> " + vertex.get_VERTEXLABEL());
			System.out.print(" -> ");
			//System.out.println();
			traverseGraphFromVertex(vertex);//TODO: add cycle detection
		}
	}
	
	public void dotPrintGraph(DefaultSimpleGraph g, String aFile){
		File outFile = new File(aFile);
		//StringBuilder str = new StringBuilder();
		ArrayList<DefaultVertex> vertices = g._VERTICES;
		
		_dotOutput = "digraph G\n";
		_dotOutput = _dotOutput + "{\n";
		_dotOutput = _dotOutput + "rankdir=LR\n";
		for (DefaultVertex v : vertices) {
			_dotOutput = _dotOutput + v.get_VERTEXLABEL() + " ;\n";
		}
		
		DefaultVertex start = g._VERTICES.get(0);

		_dotOutput = _dotOutput + start.get_VERTEXLABEL() ;
		
		ArrayList<DefaultVertex> out = start._OUTGOINGEDGES;
		for (DefaultVertex vertex : out) {
			_dotOutput = _dotOutput + " -> ";
			traverseGraphFromVertex(vertex);//TODO: add cycle detection
		}
		
		_dotOutput = _dotOutput + "}\n";
		
		System.out.println("Dot output:\n" + _dotOutput);
		try {
			FileWriter fw = new FileWriter(outFile);
			fw.write(_dotOutput);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		/*DefaultSimpleGraph g = new DefaultSimpleGraph();
		DefaultVertex v1 = new DefaultVertex();
		DefaultVertex v2 = new DefaultVertex();
		DefaultVertex v3 = new DefaultVertex();
		DefaultVertex v4 = new DefaultVertex();
		DefaultVertex v5 = new DefaultVertex();

		v1.set_VERTEXLABEL("the");
		v1.createVertexID();
		v2.set_VERTEXLABEL("rooms");
		v2.createVertexID();
		v3.set_VERTEXLABEL("were");
		v3.createVertexID();
		v4.set_VERTEXLABEL("very");
		v4.createVertexID();
		v5.set_VERTEXLABEL("clean");
		v5.createVertexID();
		//A --> B --> C --> E
		//			  \ D /
		
		g.addVertex(v1);
		g.addVertex(v2);
		g.addVertex(v3);
		g.addVertex(v4);
		g.addVertex(v5);
		
		g.addEdge(v1, v2);//A --> B
		g.addEdge(v2, v3);//B --> C
		g.addEdge(v3, v4);//C --> D
		g.addEdge(v4, v5);//D --> E
		g.addEdge(v3, v5);//C --> E
		
		//g.asciiPrintGraph(g);
		g.dotPrintGraph(g, "/Users/antonio/Documents/Recherche/Tests/out.dot");*/

	}

}

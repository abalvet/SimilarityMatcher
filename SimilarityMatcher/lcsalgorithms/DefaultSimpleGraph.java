package lcsalgorithms;

import java.util.ArrayList;

public class DefaultSimpleGraph {
	protected ArrayList<DefaultVertex> _VERTICES = new ArrayList<DefaultVertex>();
	
	void addVertex(DefaultVertex v){
		v.createVertexID();
		this._VERTICES.add(v);
	}
	
	void addEdge(DefaultVertex source, DefaultVertex target){
		source._OUTVERTICES.add(target);
		target._INVERTICES.add(source);
	}
	
	void resetGraph(){
		this._VERTICES.clear();
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DefaultSimpleGraph g = new DefaultSimpleGraph();
		DefaultVertex v1 = new DefaultVertex();
		DefaultVertex v2 = new DefaultVertex();
		v1.set_VERTEXLABEL("v1");
		v2.set_VERTEXLABEL("v2");
		g.addVertex(v1);
		g.addVertex(v2);
		g.addEdge(v1, v2);
		System.out.println(g._VERTICES);

	}

}

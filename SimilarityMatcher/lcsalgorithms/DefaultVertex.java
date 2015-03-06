package lcsalgorithms;

import java.util.ArrayList;
import java.util.UUID;


public class DefaultVertex {
	
	protected UUID _VERTEXID ;
	protected String _VERTEXLABEL = "";
	protected ArrayList<DefaultVertex> _INCOMINGEDGES = new ArrayList<DefaultVertex>();
	protected ArrayList<DefaultVertex> _OUTGOINGEDGES = new ArrayList<DefaultVertex>();
	
	protected void createVertexID(){
		UUID uniqueId = this.GenerateUUID();
		this.set_VERTEXID(uniqueId); 

	}
	
	public String toString(){
		String out = "";
		out = this.get_VERTEXID() 
				+ ", " + this.get_VERTEXLABEL();
		
		return out;
	}
	
	void addIncomingEdge(DefaultVertex in){//TODO: check whether edge source-->this already exists
		//when adding an incoming edge to current node
		//the source node must be notified it now has an 
		//outgoing edge
		this._INCOMINGEDGES.add(in);
		in._OUTGOINGEDGES.add(this);
	}

	void addOutgoingEdge(DefaultVertex out){//TODO: check whether edge this-->target already exists
		//when adding an outgoing edge
		//the target node must be notified it now has an
		//incoming edge
		this._OUTGOINGEDGES.add(out);
		out._INCOMINGEDGES.add(this);
	}
	

	 UUID GenerateUUID() {//generate random UUIDs
		UUID id;
	    id = UUID.randomUUID();
	    return id;
	  }
	
////////////////GETTERS AND SETTERS///////////////////
	
	/**
	 * @return the _VERTEXID
	 */
	protected UUID get_VERTEXID() {
		return _VERTEXID;
	}


	/**
	 * @param _VERTEXID the _VERTEXID to set
	 */
	protected void set_VERTEXID(UUID val) {
		this._VERTEXID = val;
	}


	/**
	 * @return the _VERTEXLABEL
	 */
	protected String get_VERTEXLABEL() {
		return _VERTEXLABEL;
	}


	/**
	 * @param _VERTEXLABEL the _VERTEXLABEL to set
	 */
	protected void set_VERTEXLABEL(String _VERTEXLABEL) {
		this._VERTEXLABEL = _VERTEXLABEL;
	}

	public static void printVertex(DefaultVertex v){
		ArrayList<String> str = new ArrayList<String>();
		//str.add("Digraph g");
		str.add(v.get_VERTEXID().toString());
		str.add(v.get_VERTEXLABEL());
		System.out.println(str);
		
		ArrayList<DefaultVertex> out = v._OUTGOINGEDGES;
		for (DefaultVertex vertex : out) {
			System.out.println(v.get_VERTEXLABEL() + " --> ");
			printVertex(vertex);
		}	
		
	}


	public static void main(String[] args) {
		/**
		 * v1	--> v2 --> v3
		 * 	  	--> v3
		 * 		
		 */
		DefaultVertex dv1 = new DefaultVertex();
		dv1.set_VERTEXLABEL("v1");
		dv1.createVertexID();
		
		DefaultVertex dv2 = new DefaultVertex();
		dv2.set_VERTEXLABEL("v2");
		dv2.createVertexID();
		
		DefaultVertex dv3 = new DefaultVertex();
		dv3.set_VERTEXLABEL("v3");
		dv3.createVertexID();
		
		DefaultVertex dv4 = new DefaultVertex();
		dv4.set_VERTEXLABEL("v4");
		dv4.createVertexID();
		
		dv1.addOutgoingEdge(dv2);
		dv1.addOutgoingEdge(dv3);
		dv2.addOutgoingEdge(dv3);
		dv3.addOutgoingEdge(dv4);
		System.out.println("infos sur dv1:");
		printVertex(dv1);
		System.out.println("infos sur dv3");
		printVertex(dv3);
		
	}

}

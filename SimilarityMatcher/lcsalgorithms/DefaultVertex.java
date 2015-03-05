package lcsalgorithms;

import java.util.ArrayList;
import java.util.UUID;


public class DefaultVertex {
	
	protected UUID _VERTEXID ;
	protected String _VERTEXLABEL = "";
	protected ArrayList<DefaultVertex> _INVERTICES = new ArrayList<DefaultVertex>();
	protected ArrayList<DefaultVertex> _OUTVERTICES = new ArrayList<DefaultVertex>();
	
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
	
	void addInvertex(DefaultVertex in){
		this._INVERTICES.add(in);
	}

	void addOutvertex(DefaultVertex out){
		this._OUTVERTICES.add(out);
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




	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DefaultVertex dv = new DefaultVertex();
		ArrayList<DefaultVertex> in = new ArrayList<DefaultVertex>();
		ArrayList<DefaultVertex> out = new ArrayList<DefaultVertex>();
		dv.set_VERTEXLABEL("test");
		dv.createVertexID();
		System.out.println("vertex: " + dv.get_VERTEXLABEL() + ", " + dv.get_VERTEXID());

	}

}

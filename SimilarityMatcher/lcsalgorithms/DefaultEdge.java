package lcsalgorithms;

public class DefaultEdge {
	protected DefaultVertex _SOURCE;
	protected DefaultVertex _TARGET;
	protected String _EDGELABEL = "";
	protected int _EDGEID = 0;
	
	
	public String toString(){
		String out = "";
		out =  	"{" + this.get_SOURCE() + "}"
				+ "-[:" + this.get_EDGELABEL() + ":]->" 
				+ "{" + this.get_TARGET() + "}";
		return out;
	}
	
/////////////GETTERS & SETTERS/////////////////

	/**
	 * @return the _SOURCE
	 */
	protected DefaultVertex get_SOURCE() {
		return _SOURCE;
	}



	/**
	 * @param _SOURCE the _SOURCE to set
	 */
	protected void set_SOURCE(DefaultVertex _SOURCE) {
		this._SOURCE = _SOURCE;
	}



	/**
	 * @return the _TARGET
	 */
	protected DefaultVertex get_TARGET() {
		return _TARGET;
	}



	/**
	 * @param _TARGET the _TARGET to set
	 */
	protected void set_TARGET(DefaultVertex _TARGET) {
		this._TARGET = _TARGET;
	}



	/**
	 * @return the _EDGELABEL
	 */
	protected String get_EDGELABEL() {
		return _EDGELABEL;
	}



	/**
	 * @param _EDGELABEL the _EDGELABEL to set
	 */
	protected void set_EDGELABEL(String _EDGELABEL) {
		this._EDGELABEL = _EDGELABEL;
	}



	/**
	 * @return the _EDGEID
	 */
	protected int get_EDGEID() {
		return _EDGEID;
	}



	/**
	 * @param _EDGEID the _EDGEID to set
	 */
	protected void set_EDGEID(int _EDGEID) {
		this._EDGEID = _EDGEID;
	}



/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DefaultEdge de = new DefaultEdge();
		de.set_EDGEID(12);
		de.set_EDGELABEL("testlabel");
		DefaultVertex v0 = new DefaultVertex();
		v0.createVertexID();;
		v0.set_VERTEXLABEL("v0");
		
		DefaultVertex v1 = new DefaultVertex();
		v1.createVertexID();;
		v1.set_VERTEXLABEL("v1");
		
		de.set_SOURCE(v0);
		de.set_TARGET(v1);
		v0._OUTGOINGEDGES.add(v1);
		v1._INCOMINGEDGES.add(v0);

	}*/

}

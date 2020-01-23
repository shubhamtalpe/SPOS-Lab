package pass1;

public class OperEntry {
	private String literal;
	private int address;
	private int index;
	
	public OperEntry(String literal, int address){
		this.literal = literal;
		this.address = address;
		this.index = 0;
	}
	
	public OperEntry(String literal, int address, int index){
		this.literal = literal;
		this.address = address;
		this.index = index;
	}
	
	public String getLiteral(){
		return literal;
	}
	
	public int getAddress(){
		return address;
	}
	
	public int getIndex(){
		return index;
	}
	
	public OperEntry setAddress(int address){
		this.address = address;
		return this;
	}
	public OperEntry setIndex(int index){
		this.index = index;
		return this;
	}
	
	public String toString(){
		if(literal.contains("=")){
			return "(L," + index + ")";
		}
		else{
			return "(S," + index + ")";
		}
	}
}

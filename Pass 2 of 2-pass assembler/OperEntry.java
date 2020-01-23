package pass2;

public class OperEntry {//store entries of tables
	private String literal;//symbol in symtab
	private int address;
	private int index;
	
	public OperEntry(String literal, int address){
		this.literal = literal;
		this.address = address;
		index = 0;
	}

	public OperEntry(String literal, int address, int index){
		this.literal = literal;
		this.address = address;
		this.index = index;
	}
	
	public String getLiteral(){//return symbol or literal
		return literal;
	}
	
	public int getAddress(){//return address
		return address;
	}
	
	public int getIndex(){// return index
		return index;
	}
	
	public OperEntry setAddress(int address){// set address
		this.address = address;
		return this;
	}
	
	public OperEntry setIndex(int index){//set index
		this.index = index;
		return this;
	}
	
	public String toString(){
		if(literal.contains("=")){
			return "(L," + index + ")";
		}
		return "(S," + index + ")";
	}
}

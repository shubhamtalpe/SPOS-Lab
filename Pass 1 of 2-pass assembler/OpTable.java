package pass1;

import java.util.HashMap;

public class OpTable {
	private final HashMap<String, OpEntry> OpTab;
	
	public OpTable(){
		OpTab = new HashMap<>();
		OpTab.put("STOP", new OpEntry("IS", 0));
		OpTab.put("ADD", new OpEntry("IS", 1));
		OpTab.put("SUB", new OpEntry("IS", 2));
		OpTab.put("MULT", new OpEntry("IS", 3));
		OpTab.put("MOVER", new OpEntry("IS", 4));
		OpTab.put("MOVEM", new OpEntry("IS", 5));
		OpTab.put("COMP", new OpEntry("IS", 6));
		OpTab.put("BC", new OpEntry("IS", 7));
		OpTab.put("DIV", new OpEntry("IS", 8));
		OpTab.put("READ", new OpEntry("IS", 9));
		OpTab.put("PRINT", new OpEntry("IS", 10));
		OpTab.put("START", new OpEntry("AD", 1));
		OpTab.put("END", new OpEntry("AD", 2));
		OpTab.put("ORIGIN", new OpEntry("AD", 3));
		OpTab.put("EQU", new OpEntry("AD", 4));
		OpTab.put("LTORG", new OpEntry("AD", 5));
		OpTab.put("DS", new OpEntry("DL", 1));
		OpTab.put("DC", new OpEntry("DL", 2));
		OpTab.put("AREG", new OpEntry("RG", 1));
		OpTab.put("BREG", new OpEntry("RG", 2));
		OpTab.put("CREG", new OpEntry("RG", 3));
		OpTab.put("EQ", new OpEntry("CC", 1));
		OpTab.put("LT", new OpEntry("CC", 2));
		OpTab.put("GT", new OpEntry("CC", 3));
		OpTab.put("LE", new OpEntry("CC", 4));
		OpTab.put("GE", new OpEntry("CC", 5));
		OpTab.put("NE", new OpEntry("CC", 6));
		OpTab.put("ANY", new OpEntry("CC", 7));
	}
	
	//Check for key in table
	public boolean containsKey(String key){
		return OpTab.containsKey(key);
	}
	
	//Return entry with given key
	public OpEntry get(String key){
		return OpTab.get(key);
	}
}

class OpEntry{
	String OpClassName;
	int OpCode;
	
	public OpEntry(String OpClassName, int OpCode){
		this.OpClassName = OpClassName;
		this.OpCode = OpCode;
	}
	
	//Return Class Name
	public String getOpClassName(){
		return OpClassName;
	}
	
	//Return Operand Code
	public int getOpCode(){
		return OpCode;
	}
	
	//Check whether given statement is Declarative
	public boolean isDL(){
		return OpClassName.equals("DL");
	}

	//Check whether given statement is Assembler directive
	public boolean isAD(){
		return OpClassName.equals("AD");
	}

	//Check whether given statement is Imperative Statement
	public boolean isIS(){
		return OpClassName.equals("IS");
	}
	
	public String toString(){
		if(OpClassName.equals("RG")){//Return OpCode only if it is register
			return "(" + OpCode + ")";
		}
		return "(" + OpClassName + "," + String.format("%02d", OpCode) + ")"; 
	}
}

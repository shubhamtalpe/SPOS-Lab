package pass1;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Analysis {
	LinkedHashMap<String, OperEntry> SymTab;
	ArrayList<OperEntry>LitTab;
	ArrayList<Integer>PoolTab;
	
	OpTable OpTab;
	String line;
	StringBuilder code;
	
	int lc = 0;
	int pooltab_ptr = 0;
	int littab_ptr = 0;
	int symtab_ptr = 0;
	
	public Analysis(){
		SymTab = new LinkedHashMap<>();
		LitTab = new ArrayList<>();
		PoolTab = new ArrayList<>();
		OpTab = new OpTable();
		code = new StringBuilder();
	}
	
	public String parseCode(BufferedReader read) throws IOException{
		PoolTab.add(pooltab_ptr);
		while((line = read.readLine()) != null){//Read input code till end of file

			String parts[] = line.split("\\s+");// Split each statement into label, mnemonic and operands
			
			//Check for label
			if(!parts[0].isEmpty()){//there is label
				//Check if label is there in  symbol table
				if(SymTab.containsKey(parts[0])){//the label is there in symbol table
					//Check if label is previously declared
					if(SymTab.get(parts[0]).getAddress() == -1){//not declared previously
						SymTab.put(parts[0], SymTab.get(parts[0]).setAddress(lc));//set address of label
					}
					else{//label already declared
						System.err.println("Multiple declarations");//show error
					}
				}
				else{//label is not there in symbol table
					SymTab.put(parts[0], new OperEntry(parts[0], lc, symtab_ptr++));//add label to table with current lc as address
				}
			}
			
			//Check the mnemonic
			if(OpTab.get(parts[1]).isIS()){//mnemonic is an imperative statement
				if(parts[1].equals("STOP")){//Check whether mnemonic in STOP
					code.append((!OpTab.get(parts[1]).isAD())? (lc + "  ") : ("---  "));
					code.append(OpTab.get(parts[1])).append("\n");
					lc++;
				}
				else{//mnemonic is not stop
					String operands[] = parts[2].split(",");//Split the operands
					code.append((!OpTab.get(parts[1]).isAD()) ? (lc + "  ") : ("---  "));
					code.append(OpTab.get(parts[1])).append("  ");
					String operand;
					if(operands.length == 1){//there is only one operand
						operand = operands[0];
					}
					else{//there are two operands
						code.append(OpTab.get(operands[0])).append("  ");//first operand is there in optable
						operand = operands[1];
					}
					OperEntry entry;
					//check whether second operand is literal
					if(operand.contains("=")){//it is literal
						boolean isPresent = false;
						int idx = 0;
						for(int i=PoolTab.get(pooltab_ptr); i<littab_ptr; i++){
							//check if operand is in literal table
							if(LitTab.get(i).getLiteral().equals(operand)){
								isPresent = true;
								idx = i;
								break;
							}
						}
						if(!isPresent){//literal is not in littab
							entry = new OperEntry(operand, -1, littab_ptr++);//add entry to littab
							LitTab.add(entry);
						}
						else{//entry found
							entry = LitTab.get(idx);//get the entry
						}
						code.append(entry).append("\n");
					}
					else if(operand.matches("\\d+")){
						code.append(constantPair(operand)).append("\n");//get value of literal
					}
					else if(!SymTab.containsKey(operand)){//symbol table does not contain the operand
						entry = new OperEntry(operand, -1, symtab_ptr++);//add entry to symbol table
						SymTab.put(operand, entry);
						code.append(entry).append("\n");
					}
					else{//operand is there in symbol table
						entry = SymTab.get(operand);//get entry
						code.append(entry).append("\n");
					}
					lc++;
					
				}
			}
			
			if(parts[1].equals("LTORG") || parts[1].equals("END")){//LTORG or END statement
				int ptr = PoolTab.get(pooltab_ptr);//get pointer to first literal with no address
				for(int j=ptr; j<littab_ptr; j++){
					LitTab.set(j, LitTab.get(j).setAddress(lc));//set address of literal
					//add entry to ic
					code.append(lc + "  ");
					code.append(OpTab.get("DC")).append("  ");
					code.append(constantPair(LitTab.get(j).getLiteral()) + "\n");
					lc++;
				}
				if(littab_ptr == ptr){
					code.append(lc + "  ");
					code.append(OpTab.get("END")).append("\n");
				}
				if(!parts[1].equals("END")){
					pooltab_ptr++;
					PoolTab.add(littab_ptr);
				}
			}
			if(parts[1].equals("START") || parts[1].equals("ORIGIN")){//Start or origin statement
				lc = expr(parts[2]);//calculate address
				//add entry to ic
				code.append((!OpTab.get(parts[1]).isAD()) ? (lc + "  ") : ("---  "));
				code.append(OpTab.get(parts[1])).append("  ");
				code.append(constantPair(lc) + "\n");				
			}
			if(parts[1].equals("EQU")){
				int addr = expr(parts[2]);//get address of operand
				SymTab.put(parts[0], SymTab.get(parts[0]).setAddress(addr));//set address of label
				//add entry to ic
				code.append((!OpTab.get(parts[1]).isAD()) ? (lc + "  ") : ("---  "));
				code.append(OpTab.get("EQU")).append("  ");
				code.append(constantPair(addr) + "\n");
			}
			if(OpTab.get(parts[1]).isDL()){//Declarative statement
				code.append((!OpTab.get(parts[1]).isAD()) ? (lc + "  ") : ("---  "));
				//add entry to symtab
				SymTab.put(parts[0], SymTab.get(parts[0]).setAddress(lc));
				int size = Integer.parseInt(parts[2].replace("'", ""));//get size
				if(parts[1].equals("DS")){
					lc += size;
				}
				else{
					lc++;
				}
				code.append(OpTab.get(parts[1])).append("  ");
				code.append(constantPair(size) + "\n");
			}
		}
		return code.toString();
	}
	
	public String getPoolTable(){
		StringBuilder sb = new StringBuilder();
		for(int idx: PoolTab){
			sb.append("#").append(idx).append("\n");
		}
		return sb.toString();
	}
	
	public String getLiteralTable() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<LitTab.size(); i++){
			OperEntry entry = LitTab.get(i);
			sb.append(i).append("\t").append(entry.getLiteral()).append("\t").append(entry.getAddress()).append("\n");
		}
		return sb.toString();
	}
	
	public String getSymbolTable(){
		StringBuilder sb = new StringBuilder();
		for(Entry<String, OperEntry> entry: SymTab.entrySet()){
			sb.append(entry.getValue().getIndex()).append("\t").append(entry.getKey()).append("\t").append(entry.getValue().getAddress()).append("\n");
		}
		return sb.toString();
	}
	
	public int expr(String str){
		int temp = 0;
		if(str.contains("+")){
			String splits[] = str.split("\\+");
			temp = SymTab.get(splits[0]).getAddress() + Integer.parseInt(splits[1]);
		}
		else if(str.contains("-")){
			String splits[] = str.split("\\-");
			temp = SymTab.get(splits[0]).getAddress() - (Integer.parseInt(splits[1]));
		}
		else if(SymTab.containsKey(str)){
			temp = SymTab.get(str).getAddress();
		}
		else{
			temp = Integer.parseInt(str);
		}
		return temp;
	}
	
	public String constantPair(String literal){
		String value = literal.replace("=", "").replace("'", "");
		return "(C," + value + ")";
	}
	
	public String constantPair(int value){
		return "(C," + value + ")";
	}
	
	public String literalPair(OperEntry entry){
		int idx = LitTab.indexOf(entry);
		return "(L," + idx + ")";
	}
}

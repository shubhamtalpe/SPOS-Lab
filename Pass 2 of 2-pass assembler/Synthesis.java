package pass2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Synthesis {
	ArrayList<OperEntry> SYMTAB;
	ArrayList<OperEntry> LITTAB;
	StringBuilder code;
	
	public Synthesis(){
		SYMTAB = new ArrayList<OperEntry>();
		LITTAB = new ArrayList<OperEntry>();
		code = new StringBuilder();
	}
	
	public void readTable(BufferedReader reader, ArrayList<OperEntry> table) throws IOException{
		String line;
		while((line = reader.readLine()) != null){
			String parts[] = line.split("\\s+");//index, symbol, address
			table.add(new OperEntry(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[0])));//symbol, address, index
		}
	}
	
	public void readSymbolTable(BufferedReader reader)throws IOException{
		readTable(reader, SYMTAB);
	}
	
	public void readLiteralTable(BufferedReader reader)throws IOException{
		readTable(reader, LITTAB);
	}
	
	public String generateObjectCode(BufferedReader reader)throws IOException{
		String line;
		while((line = reader.readLine()) != null){
			String parts[] = line.split("\\s+");//split on white spaces
			
			if(parts[1].contains("AD")){
				code.append("\n");//leave empty line for assembler directives
				continue;//ignore rest go to next line in ic
			}
			
			if(parts[1].contains("IS")){
				code.append(parts[0]).append("  ");//add lc
				int opcode = Integer.parseInt(parts[1].replaceAll("[^0-9]", ""));//remove all non-numeric characters
				code.append(String.format("%02d", opcode)).append("  ");//add opcode
				
				switch(parts.length){
					case 2://only lc and opcode
						code.append("\n");
						break;
					case 3://only one operand
						code.append("00").append("  ");
						handleOperand(parts[2]);
						break;
						
					case 4://two operands
						int regNo = Integer.parseInt(parts[2].replaceAll("[^0-9]", ""));//remove all non-numeric characters
						code.append(String.format("%02d", regNo)).append("  ");
						handleOperand(parts[3]);
						break;
				}
			}
			
			if(parts[1].contains("DL")){//statement is declarative
				code.append(parts[0]).append("  ");
				if((Integer.parseInt(parts[1].replaceAll("[^0-9]", ""))) == 1){
					code.append("\n");
				}
				else{
					code.append("00").append("  ").append("00").append("  ");
					handleOperand(parts[2]);
				}
			}
		}
		return code.toString();
	}
	
	private void handleOperand(String operand){
		int value = Integer.parseInt(operand.replaceAll("[^0-9]", ""));//remove all non-numeric characters
		String output = "000";
		if(operand.contains("S")){//operand is in symtab
			output = String.format("%03d", SYMTAB.get(value).getAddress());
		}
		else if(operand.contains("L")){//operand is in littab
			output = String.format("%03d", LITTAB.get(value).getAddress());
		}
		else if(operand.contains("C")){//operand is constant
			output = String.format("%03d", value);
		}
		code.append(output).append("\n");
	}
}

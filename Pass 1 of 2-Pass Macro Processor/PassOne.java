package macroPass1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PassOne {
	ArrayList<MNTentry> MNT;//Macro name table
	ArrayList<String> MDT;//Macro definition table
	ArrayList<String> PNTAB;//Parameter name table
	HashMap<String,	String> KPDTAB;//keyword parameter default table
	
	public PassOne(){
		MNT = new ArrayList<MNTentry>();
		MDT = new ArrayList<String>();
		PNTAB = new ArrayList<String>();
		KPDTAB = new HashMap<String, String>();
	}
	
	public macroOutput getOutput(BufferedReader reader) throws IOException{
		String line;
		int mdtp = 0;
		int kpdtp = 0;
		
		StringBuilder icode = new StringBuilder();
		while((line = reader.readLine())!=null){
			String parts[] = line.split("\\s+");
			
			if(parts[0].equals("MACRO")){
				PNTAB.clear();
				String prototype[] = reader.readLine().split("\\s+|, \\s+");
				MNTentry entry = new MNTentry(prototype[0], mdtp, kpdtp);
				
				for(int i=1; i<prototype.length; i++){
					if(prototype[i].contains("=")){
						entry.incKP();
						kpdtp++;
						String param[] = prototype[i].replace("&", "").replace(",", "").split("=");
						KPDTAB.put(param[0], (param.length == 2) ? param[1] : "-");
						PNTAB.add(param[0]);
					}
					else{
						entry.incPP();
						PNTAB.add(prototype[i].replace("&", "").replace(",", ""));
					}
				}
				MNT.add(entry);
				
				String instruction = reader.readLine();
				StringBuilder instrRow = new StringBuilder();
				while(!instruction.equals("MEND")){
					String instr[] = instruction.split("\\s+|,\\s+");
					instrRow.append(mdtp).append("  ");
					instrRow.append(instr[0]).append("\t");
					instrRow.append(handleOperand(instr[1]));
					
					if(instr.length == 3){
						instrRow.append(", ").append(handleOperand(instr[2]));
					}
					instrRow.append("\n");
					mdtp++;
					instruction = reader.readLine();
				}
				instrRow.append(mdtp++).append("  MEND\n");
				MDT.add(instrRow.toString());
			}
			else{
				icode.append(line).append("\n");
			}
		}
		macroOutput output = new macroOutput();
		output.setIC(icode.toString());
		
		final StringBuilder mnt = new StringBuilder();
		for(MNTentry entry:MNT){
			mnt.append(entry.toString()).append("\n");
		}
		output.setMNT(mnt.toString());
		
		final StringBuilder mdt = new StringBuilder();
		for(String entry:MDT){
			mdt.append(entry);
		}
		output.setMDT(mdt.toString());
		
		final StringBuilder kpdtab = new StringBuilder();
		for(HashMap.Entry<String, String>entry: KPDTAB.entrySet()){
			kpdtab.append(entry.getKey()).append("\t").append(entry.getValue()).append("\n");
		}
		output.setKPDTAB(kpdtab.toString());
		
		final StringBuilder pntab = new StringBuilder();
		for(String entry:PNTAB){
			pntab.append(entry).append("\n");
		}
		output.setPNTAB(pntab.toString());
		
		return output;
	}
	
	public String handleOperand(String operand){
		int idx = PNTAB.indexOf(operand.replace("&", ""));
		String op;
		if(idx != -1){
			op = "(P," + (idx+1) + ")";
		}
		else{
			op = operand;
		}
		return op;
	}
}

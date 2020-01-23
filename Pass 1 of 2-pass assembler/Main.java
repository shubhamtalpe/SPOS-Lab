package pass1;

import java.io.*;

public class Main {
	public static void main(String args[]){
		FileReader file;
		BufferedReader reader;
		BufferedWriter writer;
		FileWriter fileWriter;
		Analysis assembler = new Analysis();
		try{
			//Read input (.asm) file
			file = new FileReader("input.asm");
			reader = new BufferedReader(file);
			
			//Analyze the input code
			String ic = assembler.parseCode(reader);
			System.out.println("Intermediate Code : ");
			System.out.println(ic);
			file.close();
			reader.close();
			
			//write intermediate code
			fileWriter = new FileWriter("IC.txt");
			writer = new BufferedWriter(fileWriter);
			writer.write(ic);
			writer.close();
			
			//Write symbol table
			String SymTab = assembler.getSymbolTable();
			System.out.println("Symbol Table : ");
			System.out.println(SymTab);
			fileWriter = new FileWriter("SYMTAB.txt");
			writer = new BufferedWriter(fileWriter);
			writer.write(SymTab);
			writer.close();
			
			//Write literal table
			String LitTab = assembler.getLiteralTable();
			System.out.println("Literal Table : ");
			System.out.println(LitTab);
			fileWriter = new FileWriter("LITTAB.txt");
			writer = new BufferedWriter(fileWriter);
			writer.write(LitTab);
			writer.close();
			
			//Write pool table
			String PoolTab = assembler.getPoolTable();
			System.out.println("Pool Table : ");
			System.out.println(PoolTab);
			PoolTab = PoolTab.replace("#", "");
			fileWriter = new FileWriter("POOLTAB.txt");
			writer = new BufferedWriter(fileWriter);
			writer.write(PoolTab);
			writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}

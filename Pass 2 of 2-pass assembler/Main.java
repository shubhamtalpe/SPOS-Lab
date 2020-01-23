package pass2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {
	public static void main(String args[]){
		BufferedReader reader;
		BufferedWriter writer;
		Synthesis assembler = new Synthesis();
		
		try{
			//read symbol table
			reader = new BufferedReader(new FileReader("SYMTAB.txt"));
			assembler.readSymbolTable(reader);
			
			//read literal table
			reader = new BufferedReader(new FileReader("LITTAB.txt"));
			assembler.readLiteralTable(reader);
			
			//read intermediate code
			reader = new BufferedReader(new FileReader("IC.txt"));
			String objectCode = assembler.generateObjectCode(reader);
			reader.close();
			
			//print code
			System.out.println("Object Code : ");
			System.out.println(objectCode);
			
			//write object code to file
			writer = new BufferedWriter(new FileWriter("OC.txt"));
			writer.write(objectCode);
			writer.close();
		}
		catch(Exception e){
			System.err.println(e);
		}
	}
}

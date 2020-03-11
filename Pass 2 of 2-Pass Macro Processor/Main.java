package macroPass2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws Exception{
		HashMap<String, MNTentry> MNT = new HashMap();
		ArrayList<String> MDT = new ArrayList<String>();
		HashMap<String, String> KPDTAB = new HashMap<String, String>();
		
		BufferedReader reader = new BufferedReader(new FileReader("MNT.txt"));
		String line;
		while((line = reader.readLine()) != null){
			String parts[] = line.split("\\s+");
			MNT.put(parts[0], new MNTentry(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));		
		}
		reader.close();
		reader = new BufferedReader(new FileReader("MDT.txt"));
		while((line = reader.readLine()) != null){
			MDT.add(line);
		}
		reader.close();
		reader = new BufferedReader(new FileReader("KPDTAB.txt"));
		while((line = reader.readLine()) != null){
			String parts[] = line.split("\\s+");
			KPDTAB.put(parts[0], parts[1]);
		}
		reader.close();
		reader = new BufferedReader(new FileReader("IC.txt"));
		while((line = reader.readLine()) != null){
			String parts[] = line.split("\\s+");
			if(MNT.containsKey(parts[0])){
				int pp = MNT.get(parts[0]).getPp();
				int kp = MNT.get(parts[0]).getKp();
				int kpdtp = MNT.get(parts[0]).getKpdtp();
				int mdtp = MNT.get(parts[0]).getMdtp();
			}
		}
		reader.close();
	}
}

package macroPass2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws Exception{
		HashMap<String, MNTentry> MNT = new HashMap();
		ArrayList<String> MDT = new ArrayList<String>();
		HashMap<String, String> KPDTAB = new HashMap<String, String>();
		HashMap<Integer, String> aptab = new HashMap<Integer, String>();
		HashMap<String, Integer> aptabInv = new HashMap<String, Integer>();
		
		BufferedReader reader = new BufferedReader(new FileReader("MNT.txt"));
		BufferedWriter writer = new BufferedWriter(new FileWriter("out.txt"));
		
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
				int paramNo = 1;
				for(int i=0; i<pp; i++) {
					parts[paramNo] = parts[paramNo].replace(",", "");
					aptab.put(paramNo, parts[paramNo]);
					aptabInv.put(parts[paramNo], paramNo);
					paramNo++;
				}
				int j=kpdtp;
				java.util.Set<String> s = KPDTAB.keySet();
				String[] arr = new String[s.size()];
				s.toArray(arr);
				for(int i=0; i<kp; i++) {
					aptab.put(paramNo, KPDTAB.get(arr[j]));
					aptabInv.put(arr[j], paramNo);
					j++;
					paramNo++;
				}
				for(int i=pp+1; i<parts.length; i++) {
					parts[i] = parts[i].replace(",", "");
					String splits[] = parts[i].split("=");
					String name = splits[0].replaceAll("&", "");
					aptab.put(aptabInv.get(name), splits[1]);
				}
				int i=mdtp;
				while(!MDT.get(i).contains("MEND")) {
					String splits[] = MDT.get(i).split("\\s+");
					writer.write("+");
					for(int k=0; k<splits.length; k++) {
						if(splits[k].contains("(P,")) {
							splits[k] = splits[k].replaceAll("[^0-9]", "");
							String value = aptab.get(Integer.parseInt(splits[k]));
							writer.write(value + "\t");
						}
						else {
							writer.write(splits[k] + "\t");
						}
						System.out.println("\n");
					}
					writer.write("\n");
					i++;
				}
				aptab.clear();
				aptabInv.clear();
			}
			else {
				writer.write(line + "\n");
			}
		}
		writer.close();
		reader.close();
	}
}

package pageReplacement;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
	public static void main(String args[]) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
			String line = reader.readLine();
			reader.close();
			if(line == null) {
				throw new Exception("Invalid file");
			}
			String[] parts = line.split("\\s+");
			int[] pageString = new int[parts.length];
			for(int i=0; i<parts.length; i++) {
				pageString[i] = Integer.parseInt(parts[i]);
			}
			System.out.println("-----------\nFIFO Algorithm\n");
			FifoAlgo algoFIFO = new FifoAlgo();
			float avgPageFault = algoFIFO.getAvgPageFault(pageString);
			System.out.println("\nAverage page fault for FIFO algorithm is : " + avgPageFault + "\n\n");
			System.out.println("-----------\nLeast Recently Used Algorithm\n");
			LruAlgo algoLRU = new LruAlgo();
			avgPageFault = algoLRU.getAvgPageFault(pageString);
			System.out.println("\nAverage page fault for LRU algorithm is : " + avgPageFault + "\n\n");
			System.out.println("-----------\nOptimal Algorithm\n");
			OptimalAlgo algoOPTI = new OptimalAlgo();
			avgPageFault = algoOPTI.getAvgPageFault(pageString);
			System.out.println("\nAverage page fault for optimal algorithm is : " + avgPageFault + "\n\n");
		}
		catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}

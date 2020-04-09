package pageReplacement;

import java.util.ArrayList;

public class OptimalAlgo {
	public float getAvgPageFault(int[] pageString) {
		int noPages = pageString.length, noPageFaults = 0;
		ArrayList<Integer> cache = new ArrayList<Integer>(4);
		for(int i=0; i<noPages; i++) {
			if(cache.size()<4) {
				if(!cache.contains(pageString[i])) {
					noPageFaults++;
					cache.add(pageString[i]);
					System.out.println("After page fault " + noPageFaults + " " + cache);
				}
			}
			else {
				if(!cache.contains(pageString[i])) {
					noPageFaults++;
					int a = getFarthestRef(cache, pageString, i);
					cache.remove(a);
					cache.add(pageString[i]);
					System.out.println("After page fault " + noPageFaults + " " + cache);
				}
			}
		}
		return (float)noPageFaults/noPages;
	}
	public int getFarthestRef(ArrayList<Integer> cache, int[] pageString, int i) {
		int[] futureRef = new int[4];
		for(int j=i+1; j<pageString.length; j++) {
			if(cache.contains(pageString[j]) && futureRef[cache.indexOf(pageString[j])] == 0) {
				futureRef[cache.indexOf(pageString[j])] = j;
			}
			
		}
		int max=-1, idx = -1;
		for(int j=0; j<4; j++) {
			if(max<futureRef[j]) {
				max = futureRef[j];
				idx = j;
			}
			if(futureRef[j]==0) {
				idx = j;
				break;
			}
		}
		return idx;
	}
}

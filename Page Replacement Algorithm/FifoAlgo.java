package pageReplacement;

import java.util.concurrent.ArrayBlockingQueue;

public class FifoAlgo {
	public float getAvgPageFault(int[] pageString) {
		int noPages = pageString.length, noPageFaults = 0;
		ArrayBlockingQueue<Integer> fifo = new ArrayBlockingQueue<Integer>(4);
		for(int i=0; i<noPages; i++) {
			if(fifo.size() < 4) {
				if(!fifo.contains(pageString[i])) {
					noPageFaults++;
					fifo.add(pageString[i]);
					System.out.println("After page fault " + noPageFaults + " " + fifo);
				}
			}
			else {
				if(!fifo.contains(pageString[i])) {
					noPageFaults++;
					fifo.poll();
					fifo.add(pageString[i]);
					System.out.println("After page fault " + noPageFaults + " " + fifo);
				}
			}
		}
		return (float)noPageFaults/noPages;
	}
}

package schedulingAlgorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Sjf {
	LinkedList <Job> jobList;
	BufferedReader read;
	StringBuilder ret = new StringBuilder();
	float TAT;
	
	public Sjf() {
		jobList = new LinkedList<Job>();
		TAT = 0;
		try {
			read = new BufferedReader(new FileReader("job.txt"));
			String line = "";
			while((line = read.readLine()) != null) {
				String parts[] = line.split("\\s+");
				if(parts.length != 4) {
					System.out.println("Invalid input format");
					System.exit(1);
				}
				Job newJob = new Job(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]));
				if(!jobList.add(newJob)){
					System.out.println("Error while adding job to job list");
					System.exit(1);
				}
			}
			read.close();
		}
		catch(Exception e) {
			System.err.println("Error during creation of job list");
		}
		Collections.sort(jobList, new Comparator<Job>() {
			public int compare(Job j1, Job j2){
				return j1.getArrivalTime() - j2.getArrivalTime();
			}
		});
	}
	
	public String retTable(){
		return ret.toString();
	}
	
	public float calculateAverageWaitTime() {
		int currentTime = 0, waitTime = 0, jobCount=jobList.size();
		ret.append("JID\tArr\tBurst\tWait\tTAT\n\n");
		LinkedList<Job> pool = new LinkedList<Job>();
		while(!jobList.isEmpty() || !pool.isEmpty()) {
			while(!jobList.isEmpty() && (jobList.peek()).getArrivalTime() <= currentTime) {
				pool.add(jobList.poll());
			}
			Collections.sort(pool, new Comparator<Job>() {
				public int compare(Job j1, Job j2) {
					return j1.getRemainingTime() - j2.getRemainingTime();
				}
			});
			//System.out.println(pool);
			Job obj = pool.poll();
			if(obj == null) {
				currentTime+=1;
			}
			else {
				obj.incrementCompletedExecution(1);
				currentTime+=1;
				if(obj.isDone()) {
					obj.setTurnAroundTime(currentTime - obj.getArrivalTime());
					obj.setWaitTime(obj.getTurnAroundTime() - obj.getBurstTime());
					waitTime += obj.getWaitTime();
					TAT+=obj.getTurnAroundTime();
					ret.append(obj + "\n");
				}
				else {
					pool.add(obj);
				}
			}
		}
		TAT/=jobCount;
		ret.append("\nAverage Turn Around Time = " + TAT + "\n");
		ret.append("Total wait time = " + waitTime + "\n");
		return ((float)waitTime/jobCount);
	}
}

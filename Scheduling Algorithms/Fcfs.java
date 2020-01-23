package schedulingAlgorithms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Fcfs {
	private LinkedList<Job> jobList;
	private BufferedReader read;
	StringBuilder ret = new StringBuilder();
	float TAT;
	
	public Fcfs(){
		jobList = new LinkedList<Job>();
		TAT = 0;
		try{
			read = new BufferedReader(new FileReader("job.txt"));
			String line = "";
			while((line = read.readLine()) != null){
				String parts[] = line.split("\\s+");
				if(parts.length != 4){
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
		catch(Exception e){
			System.out.println("Error during read");
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
	
	public float calculateAverageWaitTime(){
		int currentTime = 0, waitTime = 0, jobCount = jobList.size();
		ret.append("JID\tArr\tBurst\tWait\tTAT\n\n");
		while(!jobList.isEmpty()){
			Job temp = jobList.poll();
			currentTime = (currentTime < temp.getArrivalTime()) ? temp.getArrivalTime() : currentTime;
			int wait = currentTime - temp.getArrivalTime();
			temp.setTurnAroundTime(wait + temp.getBurstTime());
			TAT+=wait + temp.getBurstTime();
			temp.setWaitTime(wait);
			waitTime += wait;
			currentTime += temp.getBurstTime();
			ret.append(temp + "\n");
		}
		TAT /= jobCount;
		ret.append("\nAverage Turn Around Time = " + TAT + "\n");
		ret.append("Total wait time = " + waitTime + "\n");
		return ((float)waitTime/jobCount);
	}	
}

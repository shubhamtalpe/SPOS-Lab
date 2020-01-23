package schedulingAlgorithms;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String args[]){
		StringBuilder output = new StringBuilder();
		float waitTimes[] = new float[4];
		output.append("--------------------------------------\n" + "First Come First Serve" + "\n--------------------------------------\n\n");
		Fcfs schedulerFCFS = new Fcfs();
		waitTimes[0] = schedulerFCFS.calculateAverageWaitTime();
		output.append(schedulerFCFS.retTable());
		output.append("Average Wait Time = " + waitTimes[0] + "\n");
		output.append("--------------------------------------" + "\n");
		output.append("\n" + "\n");
		Scanner cin = new Scanner(System.in);
		System.out.print("Enter time quantum for round robin : ");
		int x = cin.nextInt();
		cin.close();
		output.append("--------------------------------------\n" + "Round Robin (Time Quantum = " + x + ")" + "\n--------------------------------------\n" + "\n");
		RoundRobin schedulerRR = new RoundRobin();
		waitTimes[1] = schedulerRR.calculateAverageWaitTime(x);
		output.append(schedulerRR.retTable());
		output.append("Average Wait Time = " + waitTimes[1] + "\n");
		output.append("--------------------------------------" + "\n");
		output.append("\n" + "\n");
		output.append("--------------------------------------\n" + "Shortest Job First" + "\n--------------------------------------\n" + "\n");
		Sjf schedulerSJF = new Sjf();
		waitTimes[2] = schedulerSJF.calculateAverageWaitTime();
		output.append(schedulerSJF.retTable());
		output.append("Average Wait Time = " + waitTimes[2] + "\n");
		output.append("--------------------------------------" + "\n");
		output.append("\n" + "\n");
		output.append("--------------------------------------\n" + "Priority" + "\n--------------------------------------\n" + "\n");
		Priority schedulerP = new Priority();
		waitTimes[3] = schedulerP.calculateAverageWaitTime();
		output.append(schedulerP.retTable());
		output.append("Average Wait Time = " + waitTimes[3] + "\n");
		output.append("--------------------------------------" + "\n");
		output.append("\n" + "\n");
		float min=1000;
		int idx = -1;
		for(int i=0; i<4; i++){
			if(waitTimes[i] < min){
				idx = i;
				min = waitTimes[i];
			}
		}
		output.append("Best algorithm for given inputs is ");
		switch(idx){
		
			case 0:
				output.append("FCFS with average wait time = " + waitTimes[idx] + "\n");
				break;
				
			case 1:
				output.append("Round Robin with average wait time = " + waitTimes[idx] + "\n");
				break;
				
			case 2:
				output.append("SJF with average wait time = " + waitTimes[idx] + "\n");
				break;
				
			case 3:
				output.append("Priority with average wait time = " + waitTimes[idx] + "\n");
				break;
				
			default:
				output.append("-----" + "\n");
				break;
		}
		System.out.println(output);
		try{
			BufferedWriter write = new BufferedWriter(new FileWriter("schedule.txt"));
			write.flush();
			write.write(output.toString());
			write.close();
		}
		catch(IOException e){
			System.out.println("!!!Error while writing result to file!!!");
			System.err.println(e);
		}
		
	}
}

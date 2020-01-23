package schedulingAlgorithms;

public class Job {
	private int jobId;
	private int arrivalTime;
	private int burstTime;
	private int completedExecution;
	private int waitTime;
	private int priority;
	private int turnAroundTime;
	
	public Job(int jobId, int arrivalTime, int burstTime, int priority){
		this.jobId = jobId;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
		this.waitTime = 0;
		this.completedExecution = 0;
		this.priority = priority;
		this.turnAroundTime = 0;
	}
	
	public void setTurnAroundTime(int turnAroundTime){
		this.turnAroundTime = turnAroundTime;
	}
	
	public int getTurnAroundTime(){
		return this.turnAroundTime;
	}
	
	public int getPriority(){
		return this.priority;
	}
	
	public int getRemainingTime() {
		return (this.burstTime - this.completedExecution);
	}
	
	public int getJobId(){
		return this.jobId;
	}
	
	public int getCompletedExecution() {
		return this.completedExecution;
	}
	
	public void incrementCompletedExecution(int increment) {
		this.completedExecution += increment;
	}
	
	public boolean isDone() {
		return (completedExecution == burstTime);
	}
	
	public int getArrivalTime(){
		return this.arrivalTime;
	}
	
	public int getBurstTime(){
		return this.burstTime;
	}
	
	public void setWaitTime(int waitTime){
		this.waitTime = waitTime;
	}
	
	public int getWaitTime(){
		return this.waitTime;
	}
	
	public String toString(){
		return (jobId + "\t" + arrivalTime + "\t" + burstTime + "\t" + waitTime + "\t" + turnAroundTime);
	}
}

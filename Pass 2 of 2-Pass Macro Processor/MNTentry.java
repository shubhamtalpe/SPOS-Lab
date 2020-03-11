package macroPass2;

public class MNTentry {
	String name;
	int pp, kp, mdtp, kpdtp;
	
	public MNTentry(String name, int pp, int kp, int mdtp, int kpdtp){
		this.name = name;
		this.pp = pp;
		this.kp = kp;
		this.mdtp = mdtp;
		this.kpdtp = kpdtp;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getPp(){
		return this.pp;
	}
	
	public void setPp(int pp){
		this.pp = pp;
	}
	
	public int getKp(){
		return this.kp;
	}
	
	public void setKp(int kp){
		this.kp = kp;
	}
	
	public int getMdtp(){
		return this.mdtp;
	}
	
	public void setMdtp(int mdtp){
		this.mdtp = mdtp;
	}
	
	public int getKpdtp(){
		return this.kpdtp;
	}
	
	public void setKpdtp(int kpdtp){
		this.kpdtp = kpdtp;
	}
}

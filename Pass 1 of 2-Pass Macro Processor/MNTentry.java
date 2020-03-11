package macroPass1;

public class MNTentry {
	private String name;
	private int pp;//number of positional parameters
	private int kp;//number of keyword parameters
	private int mdtp;//MDT (macro definition table) pointer
	private int kpdtp;//KPDT (keyword parameter default table) pointer
	
	public MNTentry(String name, int mdtp, int kpdtp){
		this.name = name;
		this.pp = 0;
		this.kp = 0;
		this.mdtp = mdtp;
		this.kpdtp = kpdtp;
	}

	public MNTentry(String name, int pp, int kp, int mdtp, int kpdtp){
		this.name = name;
		this.pp = pp;
		this.kp = kp;
		this.mdtp = mdtp;
		this.kpdtp = kpdtp;
	}
	
	public void incPP(){
		this.pp++;
	}
	
	public void incKP(){
		this.kp++;
	}
	
	public String toString(){
		return (this.name + "  " + this.pp + "  " + this.kp + "  " + this.mdtp + "  " + this.kpdtp);
	}
}


package model;
import java.io.Serializable;

public class REPORT implements Serializable {
	private int REPORT_ID;
	private int REPLY_ID;
	private int POSTER_ID;

	public  REPORT(int REPLY_ID, int POSTER_ID
			) {
		super();
		this.REPLY_ID = REPLY_ID;
		this.POSTER_ID = POSTER_ID;
	}

	public REPORT() {
		super();
		this.REPLY_ID = 0;
		this.POSTER_ID = 0;

	}
	public int getREPORT_ID() {
		return REPORT_ID;
	}
	public void setREPORT_ID(int REPORT_ID) {
		this.REPORT_ID = REPORT_ID;
	}
	public int getREPLY_ID() {
		return REPLY_ID;
	}
	public void setREPLY_ID(int REPLY_ID) {
		this.REPLY_ID = REPLY_ID;
	}
	public int getPOSTER_ID() {
		return POSTER_ID;
	}
	public void setPOSTER_ID(int POSTER_ID) {
		this.POSTER_ID = POSTER_ID;
	}
}
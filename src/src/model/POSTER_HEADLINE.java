package model;
import java.io.Serializable;

public class POSTER_HEADLINE implements Serializable {
	private int POSTER_ID;
	private String TITLE;
	private String POSTED_DATE;


	public  POSTER_HEADLINE(String TITLE, String POSTED_DATE) {
		super();
		this.TITLE = TITLE;
		this.POSTED_DATE = POSTED_DATE;
	}

	public POSTER_HEADLINE() {
		super();
		this.TITLE = "";
		this.POSTED_DATE = "";

	}
	public int getPOSTER_ID() {
		return POSTER_ID;
	}
	public void setPOSTER_ID(int POSTER_ID) {
		this.POSTER_ID = POSTER_ID;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String TITLE) {
		this.TITLE = TITLE;
	}
	public String getPOSTED_DATE() {
		return POSTED_DATE;
	}
	public void setPOSTED_DATE(String POSTED_DATE) {
		this.POSTED_DATE = POSTED_DATE;
	}
}






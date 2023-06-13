package model;
import java.io.Serializable;

public class HASHTAGS implements Serializable {
	private int HASHTAGS_ID;
	private String HASHTAGS_NAME;


	public  HASHTAGS(int HASHTAGS_ID, String HASHTAGS_NAME) {
		super();
		this.HASHTAGS_ID = HASHTAGS_ID;
		this.HASHTAGS_NAME = HASHTAGS_NAME;
	}

	public HASHTAGS() {
		super();
		this.HASHTAGS_ID = 0;
		this.HASHTAGS_NAME = "";

	}
	public int getHASHTAGS_ID() {
		return HASHTAGS_ID;
	}
	public void setHASHTAGS_ID(int HASHTAGS_ID) {
		this.HASHTAGS_ID = HASHTAGS_ID;
	}
	public String getHASHTAGS_NAME() {
		return HASHTAGS_NAME;
	}
	public void setHASHTAGS_NAME(String HASHTAGS_NAME) {
		this.HASHTAGS_NAME = HASHTAGS_NAME;
	}

}






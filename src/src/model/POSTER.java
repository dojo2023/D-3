package model;
import java.io.Serializable;

public class POSTER implements Serializable {
	private int POSTER_ID;
	private String TITLE;
	private String CATEGORY_ID;
	private String MAIN_SENTENCE;
	private String HASHTAGS_ID;
	private String POSTED_DATE;
	private String ANIMAL_ID;
	private String USER_ID;
	private int USER_NAME_SWITCH;

	public  POSTER(String TITLE, String CATEGORY_ID, String MAIN_SENTENCE,
			String HASHTAGS_ID, String POSTED_DATE, String ANIMAL_ID, String USER_ID, int USER_NAME_SWITCH
			) {
		super();
		this.TITLE = TITLE;
		this.CATEGORY_ID = CATEGORY_ID;
		this.MAIN_SENTENCE = MAIN_SENTENCE;
		this.HASHTAGS_ID = HASHTAGS_ID;
		this.POSTED_DATE = POSTED_DATE;
		this.ANIMAL_ID = ANIMAL_ID;
		this.USER_ID = USER_ID;
		this.USER_NAME_SWITCH = USER_NAME_SWITCH;
	}

	public POSTER() {
		super();
		this.TITLE = "";
		this.CATEGORY_ID = "";
		this.MAIN_SENTENCE = "";
		this.HASHTAGS_ID = "";
		this.POSTED_DATE = "";
		this.ANIMAL_ID = "";
		this.USER_ID ="";
		this.USER_NAME_SWITCH = 0;

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
	public String getCATEGORY_ID() {
		return CATEGORY_ID;
	}
	public void setCATEGORY_ID(String CATEGORY_ID) {
		this.CATEGORY_ID = CATEGORY_ID;
	}
	public String getMAIN_SENTENCE() {
		return MAIN_SENTENCE;
	}
	public void setMAIN_SENTENCE(String MAIN_SENTENCE) {
		this.MAIN_SENTENCE = MAIN_SENTENCE;
	}
	public String getHASHTAGS_ID() {
		return HASHTAGS_ID;
	}
	public void setHASHTAGS_ID(String HASHTAGS_ID) {
		this.HASHTAGS_ID = HASHTAGS_ID;
	}
	public String getPOSTED_DATE() {
		return POSTED_DATE;
	}
	public void setPOSTED_DATE(String POSTED_DATE) {
		this.POSTED_DATE = POSTED_DATE;
	}
	public String getANIMAL_ID() {
		return ANIMAL_ID;
	}
	public void setANIMAL_ID(String ANIMAL_ID) {
		this.ANIMAL_ID = ANIMAL_ID;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String USER_ID) {
		this.USER_ID = USER_ID;
	}
	public int getUSER_NAME_SWITCH() {
	    return USER_NAME_SWITCH;
	}
	public void setUSER_NAME_SWITCH(int USER_NAME_SWITCH) {
		this.USER_NAME_SWITCH = USER_NAME_SWITCH;
	}
	}





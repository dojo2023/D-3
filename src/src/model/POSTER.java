package model;
import java.io.Serializable;

public class POSTER implements Serializable {
	private int POSTER_ID;
	private String TITLE;
	private int CATEGORY_ID;
	private String MAIN_SENTENCE;
	private int HASHTAGS_ID1;
	private int HASHTAGS_ID2;
	private int HASHTAGS_ID3;
	private int HASHTAGS_ID4;
	private int HASHTAGS_ID5;
	private String POSTED_DATE;
	private String ANIMAL_ID;
	private String USER_ID;
	private int USER_NAME_SWITCH;

	public  POSTER(String TITLE, int CATEGORY_ID, String MAIN_SENTENCE,
			int HASHTAGS_ID1, int HASHTAGS_ID2, int HASHTAGS_ID3, int HASHTAGS_ID4, int HASHTAGS_ID5,
			String POSTED_DATE, String ANIMAL_ID, String USER_ID, int USER_NAME_SWITCH
			) {
		super();
		this.TITLE = TITLE;
		this.CATEGORY_ID = CATEGORY_ID;
		this.MAIN_SENTENCE = MAIN_SENTENCE;
		this.HASHTAGS_ID1 = HASHTAGS_ID1;
		this.HASHTAGS_ID2 = HASHTAGS_ID2;
		this.HASHTAGS_ID3 = HASHTAGS_ID3;
		this.HASHTAGS_ID4 = HASHTAGS_ID5;
		this.HASHTAGS_ID5 = HASHTAGS_ID5;
		this.POSTED_DATE = POSTED_DATE;
		this.ANIMAL_ID = ANIMAL_ID;
		this.USER_ID = USER_ID;
		this.USER_NAME_SWITCH = USER_NAME_SWITCH;
	}

	public POSTER() {
		super();
		this.TITLE = "";
		this.CATEGORY_ID = 0;
		this.MAIN_SENTENCE = "";
		this.HASHTAGS_ID1 = 0;
		this.HASHTAGS_ID2 = 0;
		this.HASHTAGS_ID3 = 0;
		this.HASHTAGS_ID4 = 0;
		this.HASHTAGS_ID5 = 0;
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
	public int getCATEGORY_ID() {
		return CATEGORY_ID;
	}
	public void setCATEGORY_ID(int CATEGORY_ID) {
		this.CATEGORY_ID = CATEGORY_ID;
	}
	public String getMAIN_SENTENCE() {
		return MAIN_SENTENCE;
	}
	public void setMAIN_SENTENCE(String MAIN_SENTENCE) {
		this.MAIN_SENTENCE = MAIN_SENTENCE;
	}
	public int getHASHTAGS_ID1() {
		return HASHTAGS_ID1;
	}
	public void setHASHTAGS_ID1(int HASHTAGS_ID1) {
		this.HASHTAGS_ID1 = HASHTAGS_ID1;
	}
	public int getHASHTAGS_ID2() {
		return HASHTAGS_ID2;
	}
	public void setHASHTAGS_ID2(int HASHTAGS_ID2) {
		this.HASHTAGS_ID2 = HASHTAGS_ID2;
	}
	public int getHASHTAGS_ID3() {
		return HASHTAGS_ID3;
	}
	public void setHASHTAGS_ID3(int HASHTAGS_ID3) {
		this.HASHTAGS_ID3 = HASHTAGS_ID3;
	}
	public int getHASHTAGS_ID4() {
		return HASHTAGS_ID4;
	}
	public void setHASHTAGS_ID4(int HASHTAGS_ID4) {
		this.HASHTAGS_ID4 = HASHTAGS_ID4;
	}
	public int getHASHTAGS_ID5() {
		return HASHTAGS_ID5;
	}
	public void setHASHTAGS_ID5(int HASHTAGS_ID5) {
		this.HASHTAGS_ID5 = HASHTAGS_ID5;
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





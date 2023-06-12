package model;
import java.io.Serializable;

public class REPLY implements Serializable {
	private String REPLY_ID;
	private String POSTER_ID;
	private String REPLY_SENTENCE;
	private String REPLIED_DATE;
	private int USER_NAME_SWITCH;
	private String USER_ID;
	private String ANIMAL_ID;


	public  REPLY(String REPLY_ID, String POSTER_ID, String REPLY_SENTENCE, String REPLIED_DATE,
			       int USER_NAME_SWITCH, String USER_ID, String ANIMAL_ID) {
		super();
		this.REPLY_ID = REPLY_ID;
		this.POSTER_ID = POSTER_ID;
		this.REPLY_SENTENCE = REPLY_SENTENCE;
		this.REPLIED_DATE = REPLIED_DATE;
		this.USER_NAME_SWITCH = USER_NAME_SWITCH;
		this.USER_ID = USER_ID;
		this.ANIMAL_ID = ANIMAL_ID;
	}

	public REPLY() {
		super();
		this.REPLY_ID = "";
		this.POSTER_ID = "";
		this.REPLY_SENTENCE = "";
		this.REPLIED_DATE = "";
		this.USER_NAME_SWITCH = 0;
		this.USER_ID = "";
		this.ANIMAL_ID = "";
	}
	public String getREPLY_ID() {
		return REPLY_ID;
	}
	public void setREPLY_ID(String REPLY_ID) {
		this.REPLY_ID = REPLY_ID;
	}
	public String getPOSTER_ID() {
		return POSTER_ID;
	}
	public void setPOSTER_ID(String POSTER_ID) {
		this.POSTER_ID = POSTER_ID;
	}
	public String getREPLY_SENTENCE() {
		return REPLY_SENTENCE;
	}
	public void setREPLY_SENTENCE(String REPLY_SENTENCE) {
		this.REPLY_SENTENCE = REPLY_SENTENCE;
	}
	public String getREPLIED_DATE() {
		return REPLIED_DATE;
	}
	public void setREPLIED_DATE(String REPLIED_DATE) {
		this.REPLIED_DATE = REPLIED_DATE;
	}
	public String getUSER_NAME_SWITCH() {
		return USER_NAME_SWITCH;
	}
	public void setUSER_NAME_SWITCH(int USER_NAME_SWITCH) {
		this.USER_NAME_SWITCH = USER_NAME_SWITCH;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String USER_ID) {
		this.USER_ID = USER_ID;
	}
	public String getANIMAL_ID() {
		return ANIMAL_ID;
	}
	public void setANIMAL_ID(String ANIMAL_ID) {
		this.ANIMAL_ID = ANIMAL_ID;
	}

}






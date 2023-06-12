package model;
import java.io.Serializable;

public class CATEGORY implements Serializable {
	private String CATEGORY_ID;
	private String CATEGORY_NAME;


	public  CATEGORY(String CATEGORY_ID, String CATEGORY_NAME) {
		super();
		this.CATEGORY_ID = CATEGORY_ID;
		this.CATEGORY_NAME = CATEGORY_NAME;
	}

	public CATEGORY() {
		super();
		this.CATEGORY_ID = "";
		this.CATEGORY_NAME = "";

	}
	public String getCATEGORY_ID() {
		return CATEGORY_ID;
	}
	public void setCATEGORY_ID(String CATEGORY_ID) {
		this.CATEGORY_ID = CATEGORY_ID;
	}
	public String getCATEGORY_NAME() {
		return CATEGORY_NAME;
	}
	public void setCATEGORY_NAME(String CATEGORY_NAME) {
		this.CATEGORY_NAME = CATEGORY_NAME;
	}

}






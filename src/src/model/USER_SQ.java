package model;
import java.io.Serializable;

public class USER_SQ implements Serializable {
	private String id;
	private String name;

	public USER_SQ(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public USER_SQ() {
		this.id = "";
		this.name = "";
	}

	public String getUSER_SQ_ID() {
		return id;
	}

	public void setUSER_SQ_ID(String id) {
		this.id = id;
	}

	public String getUSER_SQ_NAME() {
		return name;
	}

	public void setUSER_SQ_NAME(String name) {
		this.name = name;
	}
}

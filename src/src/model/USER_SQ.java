package model;
import java.io.Serializable;

public class USER_SQ implements Serializable {
	private int id;
	private String name;

	public USER_SQ(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public USER_SQ() {
		this.id = 0;
		this.name = "";
	}

	public int getUSER_SQ_ID() {
		return id;
	}

	public void setUSER_SQ_ID(int id) {
		this.id = id;
	}

	public String getUSER_SQ_NAME() {
		return name;
	}

	public void setUSER_SQ_NAME(String name) {
		this.name = name;
	}
}

package model;

import java.io.Serializable;

public class LOGIN_USER implements Serializable {
	private String id;	// ログイン時のID

	public LOGIN_USER() {
		super();
		this.id = "";
	}

	public LOGIN_USER(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setUserId(String id) {
		this.id = id;
	}
}

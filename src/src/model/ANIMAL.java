package model;
import java.io.Serializable;

public class ANIMAL implements Serializable {
	private String id;
	private String name;

	public ANIMAL(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public ANIMAL() {
		this.id = "";
		this.name = "";
	}

	public String getANIMAL_ID() {
		return id;
	}

	public void setANIMAL_ID(String id) {
		this.id = id;
	}

	public String getANIMAL_NAME() {
		return name;
	}

	public void setANIMAL_NAME(String name) {
		this.name = name;
	}
}

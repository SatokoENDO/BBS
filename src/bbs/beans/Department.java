package bbs.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class Department implements Serializable {
	private static final long serialVersionUID = 1L;
	public int id;
	public String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
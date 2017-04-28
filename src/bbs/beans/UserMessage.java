package bbs.beans;

import java.io.Serializable;
import java.util.Date;

public class UserMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private int message_id;
	private int login_id;
	private int branch_id;
	private int department_id;
	private String title;
	private String text;
	private String category;
	private String name;
	private Date insert_date;



	public int message_id() {
		return message_id;
	}

	public void message_id(int message_id) {
		this.message_id = message_id;

	}

	public int getLoginId() {
		return login_id;
	}

	public void setLoginId(int login_id) {
		this.login_id = login_id;
	}

	public int getBranchId() {
		return branch_id;
	}

	public void setBranchId (int branch_id) {
		this.branch_id = branch_id;
	}

	public int getDepartmentId() {
		return department_id;
	}

	public void setDepartmentId (int department_id) {
		this.department_id = department_id;

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date insert_date() {
		return insert_date;
	}

	public void insert_date(Date insert_date) {
		this.insert_date = insert_date;
	}



}
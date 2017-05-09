package bbs.beans;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class UserComment implements Serializable {
	private int id;
	private int userId;
	private int messageId;
	private String name;
	private String branchId;
	private String departmentId;
	private String text;
	private Timestamp insertDate;;


	public int getId() {
		return id;
	}

	public void setId(int Id) {
		this.id = Id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId =branchId;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;	}


	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Timestamp insertDate) {
		this.insertDate = insertDate;
	}

}
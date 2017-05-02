package bbs.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	public int userId;
	private int messageId;
	private int loginId;
	private int branchId;
	private int departmentId;
	private String title;
	private String text;
	private String category;
	private String name;
	private Timestamp insertDate;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int GetMessageId() {
		return messageId;
	}

	public void SetMessageId(int messageId) {
		this.messageId = messageId;

	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId (int branchId) {
		this.branchId = branchId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId (int departmentId) {
		this.departmentId = departmentId;

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

	public void setInsertDate(Timestamp insertDate){
		this.insertDate = insertDate;
	}

	public Timestamp getInsertDate(){
		return insertDate;
	}
}

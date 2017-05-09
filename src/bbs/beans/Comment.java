package bbs.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int messageId;
	private int userId;
	private String text;
	private Timestamp insertDate;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/*public Date getdate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}*/

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setInsertDate(Timestamp insertDate){
		this.insertDate = insertDate;
	}

	public Timestamp getInsertDate(){
		return insertDate;
	}

}
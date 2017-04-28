package bbs.beans;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int user_id;
	private int message_id;
	private String text;
	private Date insertDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return user_id;
	}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	public int getMessageId() {
		return message_id;
	}
	public void setMessageId(int message_id) {
		this.message_id = message_id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getInsertDate() {
		return insertDate;
	}
	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}
}
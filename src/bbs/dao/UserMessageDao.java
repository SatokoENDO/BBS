package bbs.dao;


import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.UserMessage;
import bbs.exception.SQLRuntimeException;

public class UserMessageDao {

	public List<UserMessage> getUserMessage(Connection connection, int limitNum){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_message ");
			sql.append("ORDER BY insert_date DESC limit " + limitNum);

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			List<UserMessage> ret = toUserMessageList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close (ps);
		}
	}


	private List<UserMessage> toUserMessageList(ResultSet rs)
			throws SQLException {
		List<UserMessage> ret = new ArrayList<UserMessage>();
		try{
			int message_id = rs.getInt("message_id");
			int userId = rs.getInt("user_id");
			String name = rs.getString("name");
			String title = rs.getString("title");
			String text = rs.getString("text");
			String category = rs.getString("category");
			Timestamp insert_date = rs.getTimestamp("insert_date");

			UserMessage message = new UserMessage();
			message.SetMessageId(message_id);
			message.setUserId(userId);
			message.setName(name);
			message.setTitle(title);
			message.setText(text);
			message.setCategory(category);
			message.SetInsertDate(insert_date);

			ret.add(message);
			return ret;
		} finally{

			close(rs);
		}

	}
}

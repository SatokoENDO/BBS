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
			sql.append("ORDER BY insert_date DESC  ");

			ps = connection.prepareStatement(sql.toString());

			System.out.println(ps.toString());

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
			while(rs.next()){
			int user_id = rs.getInt("user_id");
			int message_id = rs.getInt("message_id");
			String title = rs.getString("title");
			String text = rs.getString("text");
			String category = rs.getString("category");
			String name = rs.getString("name");
			Timestamp insert_date = rs.getTimestamp("insert_date");

			UserMessage message = new UserMessage();
			message.setUserId(user_id);
			message.SetMessageId(message_id);
			message.setTitle(title);
			message.setText(text);
			message.setCategory(category);
			message.setName(name);
			message.setInsertDate(insert_date);

			ret.add(message);
			}
			System.out.println(ret.size());
			return ret;

		} finally{

			close(rs);
		}

	}
}

package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.UserComment;
import bbs.exception.SQLRuntimeException;

public class UserCommentDao {


	public List<UserComment> getUserComment(Connection connection){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM user_comment ");
			sql.append("ORDER BY insert_date ASC  ");

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			List<UserComment> ret = toUserCommentList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close (ps);
		}
	}


	private List<UserComment> toUserCommentList(ResultSet rs)
			throws SQLException {
		List<UserComment> ret = new ArrayList<UserComment>();
		try{
			while(rs.next()){
				int id = rs.getInt("id");
				String text = rs.getString("text");
				String name = rs.getString("name");
				int user_id = rs.getInt("user_id");
				int message_id = rs.getInt("message_id");
				Timestamp insert_date = rs.getTimestamp("insert_date");

				UserComment comment = new UserComment();
				comment.setId(id);
				comment.setUserId(user_id);
				comment.setText(text);
				comment.setName(name);
				comment.setMessageId(message_id);
				comment.setInsertDate(insert_date);

				ret.add(comment);
			}
			return ret;
		} finally{
			close(rs);
		}

	}
}



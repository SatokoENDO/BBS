package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.Comment;
import bbs.exception.SQLRuntimeException;

public class CommentDao {
	public void insert(Connection connection, Comment comment){
		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("id");
			sql.append("message_id");
			sql.append(", user_id");
			sql.append(", text");
			sql.append(", insert_date");
			sql.append(") VALUES (");
			sql.append("null");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, comment.getMessageId());
			ps.setInt(2, comment.getUserId());
			ps.setString(3, comment.getText());

			ps.executeUpdate();

			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally{
				close(ps);
			}
	}

	public List<Comment> getUserComment(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM comments");
			sql.append("ORDER BY insert_date ASC ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Comment> ret = toCommentList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public List<Comment> toCommentList (ResultSet rs)throws SQLException {
	List<Comment> ret = new ArrayList<Comment>();
		try {

			while (rs.next()) {
				int id = rs.getInt("id");
				String text = rs.getString("text");
				int user_id = rs.getInt("user_id");
				int message_id = rs.getInt("message_id");
				Timestamp insert_date = rs.getTimestamp("insert_date");

				Comment message = new Comment();
				message.setId(id);
				message.setText(text);
				message.setUserId(user_id);
				message.setMessageId(message_id);
				message.setInsertDate(insert_date);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}



	public void delete(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM comments WHERE id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, comment.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
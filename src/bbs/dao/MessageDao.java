package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbs.beans.Message;
import bbs.exception.SQLRuntimeException;

public class MessageDao {

	public void insert(Connection connection, Message message) {

		PreparedStatement ms = null;
		try {
			StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO messages ( ");
		sql.append("id");
		sql.append(", user_id");
		sql.append(", title");
		sql.append(", text");
		sql.append(", category");
		sql.append(", insert_date");
		sql.append(") VALUES (");
		sql.append("null");
		sql.append(", ?");
		sql.append(", ?");
		sql.append(", ?");
		sql.append(", ?");
		sql.append(", CURRENT_TIMESTAMP");
		sql.append(")");

		ms = connection.prepareStatement(sql.toString());
		ms.setInt(1, message.getUserId());
		ms.setString(2, message.getTitle());
		ms.setString(3, message.getText());
		ms.setString(4, message.getCategory());

		ms.executeUpdate();


		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ms);
		}
	}

	public void delete(Connection connection, int deleted_id){
		PreparedStatement ps = null;
		try{
			String sql = "DELETE FROM messages WHERE id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1,  deleted_id);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally{
			close(ps);
		}
	}


}

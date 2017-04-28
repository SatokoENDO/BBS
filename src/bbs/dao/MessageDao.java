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
			sql.append("INSERT INTO message ( ");
			sql.append(",login_id");
			sql.append(", title");
			sql.append(", text");
			sql.append(", category");
			sql.append(", insert_date");
			sql.append(") VALUES ("); //id
			sql.append(", ?"); //login_id
			sql.append(", ?"); // title
			sql.append(", ?"); // text
			sql.append(", ?"); // category
			sql.append(", CURRENT_TIMESTAMP"); // insert_date
			sql.append(")");

			ms = connection.prepareStatement(sql.toString());

			ms.setInt(1, message.getLoginId());
			ms.setString(2, message.getText());
			ms.setString(3, message.getCategory());

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ms);
		}
	}


}

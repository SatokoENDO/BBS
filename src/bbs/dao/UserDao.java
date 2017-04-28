package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bbs.beans.User;
import bbs.exception.NoRowsUpdatedRuntimeException;
import bbs.exception.SQLRuntimeException;

public class UserDao {

	public void userUpdete(Connection connection, User user, String password) {
		PreparedStatement ps = null;
		try {
			StringBuilder mySql = new StringBuilder();
			mySql.append("update users set ");
			mySql.append("login_id = ?");
			mySql.append(", name = ?");
			mySql.append(", branch_id = ?");
			mySql.append(", department_id = ? ");
			if (StringUtils.isEmpty(password) == false){
				mySql.append(", password = ?");
			}
			mySql.append("where ");
			mySql.append("id = ?");

			ps = connection.prepareStatement(mySql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getName());
			ps.setInt(3, user.getBranchId());
			ps.setInt(4, user.getDepartmentId());
			if (StringUtils.isEmpty(password) == false){
				ps.setString(5, user.getPassword());
				ps.setInt(6, user.getId());
			} else {
				ps.setInt(5, user.getId());
			}

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void doStop(Connection connection, Boolean is_locked, int user_id) {

		PreparedStatement ps = null;
		try {
			StringBuilder mySql = new StringBuilder();
			mySql.append("update users set");
			mySql.append(" is_locked = ?");
			mySql.append(" where");
			mySql.append(" id = ?");

			ps = connection.prepareStatement(mySql.toString());

			ps.setBoolean(1, is_locked);
			ps.setInt(2, user_id);

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder mySql = new StringBuilder();
			mySql.append("insert into users (");
			mySql.append("login_id");
			mySql.append(", password");
			mySql.append(", name");
			mySql.append(", branch_id");
			mySql.append(", department_id");
			mySql.append(", is_locked");
			mySql.append(") values (");
			mySql.append("?");
			mySql.append(", ?");
			mySql.append(", ?");
			mySql.append(", ?");
			mySql.append(", ?");
			mySql.append(", ?");
			mySql.append(")");

			ps = connection.prepareStatement(mySql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranchId());
			ps.setInt(5, user.getDepartmentId());
			ps.setBoolean(6, true);

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, String login_id, String password, int id) {

		PreparedStatement ps = null;
		try {
			StringBuilder mySql = new StringBuilder();
			if(login_id != null && password != null) {
				mySql.append("select * from users where login_id = ? and password = ?");
			}
			else if(id != 0) {
				mySql.append("select * from users where id = ? ");
			}
			ps = connection.prepareStatement(mySql.toString());

			if(login_id != null && password != null){
				ps.setString(1, login_id);
				ps.setString(2, password);
			}
			if(id != 0) {
				ps.setInt(1, id);
			}

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void delete(Connection connection, String id) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM users WHERE id = ? ;");
			ps =  connection.prepareStatement(sql.toString());
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				Boolean is_locked = rs.getBoolean("is_locked");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setBranchId(branch_id);
				user.setDepartmentId(department_id);
				user.setIsLocked(is_locked);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<User> getUsers(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder mySql = new StringBuilder();
			mySql.append("select * from users ");

			ps = connection.prepareStatement(mySql.toString());

			ResultSet rs = ps.executeQuery();
			List<User> ret = toComment(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toComment(ResultSet rs)
			throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int branch_id = rs.getInt("branch_id");
				int department_id = rs.getInt("department_id");
				Boolean is_locked = rs.getBoolean("is_locked");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setBranchId(branch_id);
				user.setDepartmentId(department_id);
				user.setIsLocked(is_locked);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}



}
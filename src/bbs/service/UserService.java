package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbs.beans.Branch;
import bbs.beans.Department;
import bbs.beans.User;
import bbs.dao.BranchDao;
import bbs.dao.DepartmentDao;
import bbs.dao.UserDao;
import bbs.utils.CipherUtil;


public class UserService {



	public void register(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.insert(connection, user);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	public List<User> getUsers() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			List<User> ret =  userDao.getUsers(connection);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Department> department() {
		Connection connection = null;
		try {
			connection = getConnection();
			DepartmentDao departmentDao = new DepartmentDao();
			List<Department> department = departmentDao.getDepartmentList(connection);

			commit(connection);
			return department;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Branch> branch() {
		Connection connection = null;
		try {
			connection = getConnection();
			BranchDao branchDao = new BranchDao();
			List<Branch> branch = branchDao.getBranchList(connection);

			commit(connection);
			return branch;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	//ユーザーロック
	public void doLock(Boolean is_locked, int user_id) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.doStop(connection, is_locked, user_id);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	//ログイン
	public User getUser(int user_id) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getUser(connection, null, null);

			commit(connection);
			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	//ユーザー編集
	public void userUpdete(User user, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.userUpdete(connection, user, password);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	//ユーザー削除
	public void userDelete(User user, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.userUpdete(connection, user, password);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void delete(String id) {
		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.delete(connection, id);
			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
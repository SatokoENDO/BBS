package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbs.beans.User;
import bbs.dao.UserDao;

public class AdminService {
	public List<User> getUsers(){
		Connection connection = null;
		try{
			connection = getConnection();

			UserDao userDao = new UserDao();
			List<User> ret = userDao.getUsers(connection);

			commit(connection);
			return ret;
		} catch(RuntimeException e){
			rollback(connection);
			throw e;
		} catch(Error e){
			rollback(connection);
			throw e;
		} finally{
			close(connection);
		}
	}


	public void doIsLocked(String isLocked, int userId){
		Connection connection = null;
		try{
			connection = getConnection();
			UserDao userDao = new UserDao();
			userDao.doIsLocked(connection, isLocked, userId);
			commit(connection);

		} finally{
			close(connection);
		}
	}

	/*public void delete(int deletedId){
		Connection connection = null;
		try{
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.delete(connection, deletedId);

			commit(connection);
		} catch(RuntimeException e){
			rollback(connection);
			throw e;
		} catch(Error e){
			rollback(connection);
			throw e;
		} finally{
			close(connection);
		}*/
}
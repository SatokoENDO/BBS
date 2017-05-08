package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;

import bbs.beans.Comment;
import bbs.dao.CommentDao;

public class CommentService {
	public void register(Comment comment){
		Connection connection = null;
		try{
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.insert(connection, comment);

			commit(connection);
		} catch(RuntimeException e){
			rollback(connection);
			throw e;
		} catch(Error e){
			throw e;
		} finally{
			close(connection);
		}
	}


}
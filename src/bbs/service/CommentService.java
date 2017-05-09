package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbs.beans.Comment;
import bbs.beans.UserComment;
import bbs.dao.CommentDao;
import bbs.dao.UserCommentDao;

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

	public List<UserComment> getCommentList() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserCommentDao UsercommentDao = new UserCommentDao();
			List<UserComment> ret = UsercommentDao.getUserComment(connection);

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

	public void delete(Comment comment) {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.delete(connection, comment);

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
}



package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbs.beans.Message;
import bbs.beans.UserMessage;
import bbs.dao.MessageDao;
import bbs.dao.UserMessageDao;

public class MessageService {

	public void register(Message message){
		Connection connection = null;
		try{
			connection = getConnection();

			MessageDao messageDao = new MessageDao();
			messageDao.insert(connection, message);

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

	private static final int limitNum = 1000;

	public List<UserMessage> getMessage() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserMessageDao usermessageDao = new UserMessageDao();

		List<UserMessage> message = usermessageDao.getUserMessage(connection, limitNum);

			commit(connection);
			return message;
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
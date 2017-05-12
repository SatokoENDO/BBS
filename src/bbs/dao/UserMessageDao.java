	package bbs.dao;


	import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.UserMessage;
import bbs.exception.SQLRuntimeException;

	public class UserMessageDao {

		public List<UserMessage> getUserMessage(Connection connection, String category, String start, String end){
			PreparedStatement ps = null;
			try{
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM user_message ");
				sql.append("WHERE '"+ start + "' <= insert_date AND '" + end + "' >= insert_date ");
				if(category == null || category.isEmpty()){
				} else{
					sql.append("AND category = ? ");
				}
				sql.append("ORDER BY insert_date DESC");

				ps = connection.prepareStatement(sql.toString());

				System.out.println("ssssssss");
				System.out.println(ps);


				if(category == null || category.isEmpty()){
				}else{
					ps.setString(1, category);
				}
				System.out.println(ps);
				ResultSet rs = ps.executeQuery();

				List<UserMessage> ret = toUserMessageList(rs);

				return ret;
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {
				close (ps);
			}
		}


		private List<UserMessage> toUserMessageList(ResultSet rs)
				throws SQLException {
			List<UserMessage> ret = new ArrayList<UserMessage>();
			try{
				while(rs.next()){
					int userId = rs.getInt("user_id");
					int id = rs.getInt("id");
					int branchId = rs.getInt("branch_id");
					int departmentId = rs.getInt("department_id");
					String title = rs.getString("title");
					String text = rs.getString("text");
					String category = rs.getString("category");
					String name = rs.getString("name");
					Timestamp insert_date = rs.getTimestamp("insert_date");

					UserMessage message = new UserMessage();
					message.setId(id);
					message.setUserId(userId);
					message.setTitle(title);
					message.setText(text);
					message.setCategory(category);
					message.setName(name);
					message.setBranchId(branchId);
					message.setDepartmentId(departmentId);
					message.setInsertDate(insert_date);

					ret.add(message);
				}
				System.out.println(ret.size());
				return ret;

			} finally{

				close(rs);
			}

		}

		public List<String> getCategories(Connection connection){
			PreparedStatement ps = null;
			try{
				String sql = "SELECT category FROM user_message GROUP BY category";
				ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();

				List<String> categories = toCategoriesList(rs);

				return categories;
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {
				close (ps);
			}
		}

		private List<String> toCategoriesList(ResultSet rs)
				throws SQLException {
			List<String> ret = new ArrayList<String>();
			try{
				while(rs.next()){
					String category = rs.getString("category");
					ret.add(category);
				}
				return ret;
			} finally {
				close(rs);
			}
		}


		public List<UserMessage> getUserMessage(Connection connection, int limitNum){
			PreparedStatement ps = null;
			try{
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM user_message ");
				sql.append("ORDER BY insert_date DESC  ");

				ps = connection.prepareStatement(sql.toString());

				System.out.println(ps.toString());

				ResultSet rs = ps.executeQuery();

				List<UserMessage> ret = toUserMessageList(rs);

				return ret;
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {
				close (ps);
			}
		}

		//一番最古の書き込みを出す
		public  String getOldestDate(Connection connection){
			PreparedStatement ps = null;
			try{
				String sql = "SELECT MIN(insert_date) FROM user_message";
				ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();

				String oldestDate = null;
				while(rs.next()){
					Timestamp insertDate = rs.getTimestamp("MIN(insert_date)");
					oldestDate = insertDate.toString();
					System.out.println(oldestDate);
				}
				return oldestDate;
			}  catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {
				close (ps);
			}
		}





	}

package bbs.dao;

import static bbs.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bbs.beans.Department;
import bbs.exception.SQLRuntimeException;

public class DepartmentDao {

	public List<Department> getDepartmentList(Connection connection) {

		try {
			Statement statement = connection.createStatement();
			String mySql = "select * from departments";
			ResultSet rs = statement.executeQuery(mySql);
			List<Department> departmentList = toDepartmentList(rs);
			if (departmentList.isEmpty() == true) {
				return null;
			} else {
				return departmentList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	private List<Department> toDepartmentList(ResultSet rs) throws SQLException {

		List<Department> ret = new ArrayList<Department>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Department Department = new Department();
				Department.setId(id);
				Department.setName(name);

				ret.add(Department);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public Department getDepartment(Connection connection, int department_id) {

		PreparedStatement ps = null;
		try {
			StringBuilder mySql = new StringBuilder();
			mySql.append("select * from deprtments where id = ? ");

			ps = connection.prepareStatement(mySql.toString());

			ps.setInt(1, department_id);

			ResultSet rs = ps.executeQuery();
			List<Department> departmentList = toDepartmentList(rs);
			if (departmentList.isEmpty() == true) {
				return null;
			} else if (2 <= departmentList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return departmentList.get(0);
			}

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
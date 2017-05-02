package bbs.service;

import static bbs.utils.CloseableUtil.*;
import static bbs.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbs.beans.Department;
import bbs.dao.DepartmentDao;

public class DepartmentService {

	public List<Department> select() {
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
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	
	public Department getDepartment(int department_id) {
		Connection connection = null;
		try {
			connection = getConnection();
			DepartmentDao departmentDao = new DepartmentDao();
			Department department = departmentDao.getDepartment(connection, department_id);

			commit(connection);
			return department;
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
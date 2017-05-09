package bbs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bbs.beans.Branch;
import bbs.beans.Department;
import bbs.beans.User;
import bbs.service.BranchService;
import bbs.service.DepartmentService;
import bbs.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {


		List<Branch> branch = new UserService().branch();
		List<Department> department = new UserService().department();
		HttpSession session = request.getSession();
		session.setAttribute("branchId", branch);
		session.setAttribute("departmentId", department);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		User user = new User();
		user.setLoginId(request.getParameter("loginId"));
		user.setPassword(request.getParameter("password"));
		user.setCheckPassword(request.getParameter("checkPassword"));
		user.setName(request.getParameter("name"));
		user.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		user.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

		HttpSession session = request.getSession();

		if (isValid(request, messages) == true) {
			new UserService().register(user);
			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			User editUser = user;
			request.setAttribute("editUser", editUser);
			List<Branch> branches = new BranchService().select();
			request.setAttribute("branches", branches);
			List<Department> departments = new DepartmentService().select();
			request.setAttribute("departments", departments);

			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String name = request.getParameter("name");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");



		if (loginId.length() < 6 || loginId.length() > 20) {
			messages.add("ログインIDの文字数は6文字以上20文字以下で入力してください");

		} else if (!loginId.matches("[a-zA-Z0-9]{6,20}")){
			messages.add("ログインIDは半角英数字で入力してください");
		}

		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");

		} else if (password.length() < 6 || password.length() > 20) {
			messages.add("パスワードの文字数は6文字以上20文字以下で入力してください");

		} else if (!password.matches("[a-zA-Z0-9]{6,255}")){
			messages.add("パスワードは半角英数字で入力してください");
		}

		if (!password.equals(checkPassword)) {
			messages.add("パスワードが確認用と一致しません。");
		}

		if (StringUtils.isEmpty(name) == true) {
			messages.add("名前を入力してください");

		} else if (name.length() > 10) {
			messages.add("名前は10文字以下で入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}

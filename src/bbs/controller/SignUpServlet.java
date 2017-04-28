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
		session.setAttribute("branch", branch);
		session.setAttribute("department", department);

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		User user = new User();
		user.setLoginId(request.getParameter("login_id"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setBranchId(Integer.parseInt(request.getParameter("branch_id")));
		user.setDepartmentId(Integer.parseInt(request.getParameter("department_id")));

		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {

			new UserService().register(user);

			response.sendRedirect("./");
		} else {


			request.setAttribute("user", user);

			session.setAttribute("errormessages", messages);

			request.getRequestDispatcher("signup.jsp").forward(request, response);


		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String name = request.getParameter("name");
		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");
		String password_check = request.getParameter("password_check");



		if (login_id.length() < 6 || login_id.length() > 20) {
			messages.add("ログインIDの文字数は6文字以上20文字以下で入力してください");

		} else if (!login_id.matches("[a-zA-Z0-9]{6,20}")){
			messages.add("ログインIDは半角英数字で入力してください");
		}

		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");

		} else if (password.length() < 6 || password.length() > 20) {
			messages.add("パスワードの文字数は6文字以上20文字以下で入力してください");

		} else if (!password.matches("[a-zA-Z0-9]{6,255}")){
			messages.add("パスワードは半角英数字で入力してください");
		}

		if (!password.equals(password_check)) {
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

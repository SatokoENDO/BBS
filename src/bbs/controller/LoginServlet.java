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

import bbs.beans.User;
import bbs.service.LoginService;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String login_id = request.getParameter("login_id");
		String password = request.getParameter("password");

		LoginService loginService = new LoginService();
		User user = loginService.login(login_id, password);

		HttpSession session = request.getSession();

		List<String> messages = new ArrayList<String>();

		if(isValid(request, messages, user) == true) {
			session.setAttribute("user_id", user);
			response.sendRedirect("home");
		} else {
			session.setAttribute("errorMessages", messages);
			request.setAttribute("editUser", login_id);

			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	private boolean isValid (HttpServletRequest request, List<String> messages, User user) {
		String login_id = (request.getParameter("login_id"));
		String password = (request.getParameter("password"));

		if (StringUtils.isEmpty(login_id) == true) {
			messages.add("ログインIDを入力してください");
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}
		if (user != null) {
			if (!user.is_locked()) {
				messages.add("ログインに失敗しました");
			}
		} else {
			messages.add("ログインに失敗しました");
		}
		if (messages.size() != 0) {
			return false;
		} else {
			return true;
		}
	}

}
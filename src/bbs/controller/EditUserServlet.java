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

import bbs.beans.User;
import bbs.service.AdminService;
import bbs.service.UserService;

@WebServlet(urlPatterns = { "/edituser" })
public class EditUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		int id = Integer.parseInt(request.getParameter("userId"));
		User editUser = new AdminService().getUser(id);
		request.setAttribute("editUser", editUser);
		request.getRequestDispatcher("edituser.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		User user = new User();

		if(isValid(request, messages) == true){
			user.setId(Integer.parseInt(request.getParameter("userId")));
			user.setLoginId(request.getParameter("loginId"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setBranchId(Integer.parseInt(request.getParameter("branchId")));
			user.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

			new UserService().update(user);


			String validationMessage =  "更新完了";
			session.setAttribute("validationMessage", validationMessage);
			request.getRequestDispatcher("updated.jsp").forward(request, response);

		} else {
			user.setLoginId(request.getParameter("loginId"));
			user.setName(request.getParameter("name"));
			user.setBranchId(Integer.parseInt(request.getParameter("branchId")));
			user.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

			request.setAttribute("user", user);

			session.setAttribute("errorMassages", messages);
			request.getRequestDispatcher("updated.jsp").forward(request, response);
		}

	}

	private boolean isValid(HttpServletRequest request, List<String> messages){

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");
		String name = request.getParameter("name");


		if(!password.equals(checkPassword)){
			messages.add("パスワードが一致しません");
		}

		if(loginId.length() >=20 ||  loginId.length() < 6 || !loginId.matches("[0-9a-zA-Z_]+$")){
			messages.add("ログインIDは6文字以上20文字以下の半角英数字です");
		}
		if(password.length() >= 255 || password.length() < 6 || !password.matches("[ -~｡-ﾟ]+$")){
			messages.add("パスワードは6文字以上255文字以下の半角文字です");
		}
		if(name.length() >= 10){
			messages.add("名前は10文字以下です");
		}
		if(messages.size() == 0){
			return true;
		} else {
			return false;
		}
	}
}
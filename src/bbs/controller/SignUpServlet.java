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

		List<Branch> branches = new BranchService().getBranchList();
		request.setAttribute("branches", branches);

		List<Department> departments = new DepartmentService().getDepartmentList();
		request.setAttribute("departments", departments);
		request.getRequestDispatcher("signup.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		User user = new User();
		user.setLoginId(request.getParameter("loginId"));
		user.setPassword(request.getParameter("password"));
		user.setCheckPassword(request.getParameter("checkPassword"));
		user.setName(request.getParameter("name"));
		user.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		user.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));


		if (isValid(request, messages) == true) {
			new UserService().register(user);
			response.sendRedirect("./admin");

		} else {
			User errorUser = new User();
			errorUser.setName(request.getParameter("name"));
			errorUser.setLoginId(request.getParameter("loginId"));
			errorUser.setPassword(request.getParameter("password"));

			errorUser.setBranchId(Integer.parseInt(request.getParameter("branchId")));
			errorUser.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
			session.setAttribute("errorUser", errorUser);
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String name = request.getParameter("name");
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		String stringBranchId = String.valueOf(branchId);
		int departmentId =Integer.parseInt(request.getParameter("departmentId"));
		String stringDepartmentId = String.valueOf(departmentId);


		if(loginId.length() >=20 ||  loginId.length() < 6 || !loginId.matches("[0-9a-zA-Z_]+$")){
			messages.add("ログインIDは6文字以上20文字以下の半角英数字です");
		}

		if(password.length() >= 255 || password.length() < 6 || !password.matches("[ -~｡-ﾟ]+$")){
			messages.add("パスワードは6文字以上255文字以下の半角文字です");
		}

		if (!password.equals(checkPassword)) {
			messages.add("パスワードが確認用と一致しません");
		}

		if (name.length() ==0) {
			messages.add("名前を入力してください");

		} else if (name.length() > 10) {
			messages.add("名前は10文字以下で入力してください");
		}

		if(branchId >= 5 || stringBranchId.length() >1 || !stringBranchId.matches("[0-9]")){
			messages.add("存在しない支店IDです");
		}

		if(departmentId >= 5 || stringDepartmentId.length() >1 || !stringDepartmentId.matches("[0-9]")){
			messages.add("存在しない部署IDです");
		}

		if(branchId == 1 && departmentId == 3){
			messages.add("本社の方は、店長では登録できません");
		}

		if(branchId != 1 && departmentId <= 2){
			messages.add("支店の方は、店長か社員です");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
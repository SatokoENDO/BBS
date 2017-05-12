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
import bbs.service.AdminService;
import bbs.service.BranchService;
import bbs.service.DepartmentService;
import bbs.service.UserService;

@WebServlet(urlPatterns = { "/edituser" })
public class EditUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		if(isNumber(request, messages)==true){
			if(isValidURL(request,messages ) == true){
				int id = Integer.parseInt(request.getParameter("userId"));
				User editUser = new AdminService().getUser(id);

				List<Branch> branches = new BranchService().getBranchList();
				session.setAttribute("branches", branches);

				List<Department> departments = new DepartmentService().getDepartmentList();
				session.setAttribute("departments", departments);

				session.setAttribute("editUser", editUser);
				request.getRequestDispatcher("edituser.jsp").forward(request, response);
			} else{
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("admin");
			}
		} else{
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("admin");
		}
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

			String validationMessage =  "更新完了しました";
			session.setAttribute("validationMessage", validationMessage);
			request.getRequestDispatcher("updated.jsp").forward(request, response);

		} else {
			user.setLoginId(request.getParameter("loginId"));
			user.setName(request.getParameter("name"));
			user.setBranchId(Integer.parseInt(request.getParameter("branchId")));
			user.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));

			session.setAttribute("user", user);

			session.setAttribute("errorMessages", messages);
			request.getRequestDispatcher("updated.jsp").forward(request, response);
		}

	}
	//url以下のid欄に数字が打ち込まれているかどうか
		private boolean isNumber(HttpServletRequest request, List<String> messages) {
			try {
				Integer.parseInt(request.getParameter("userId"));
				return true;
			} catch (NumberFormatException e) {
				messages.add("指定されたURLは存在しません");
				return false;
			}
		}

	// urlのid以下の数字が存在するかどうか
	private boolean isValidURL(HttpServletRequest request, List<String> messages){
		int userId = Integer.parseInt(request.getParameter("userId"));
		String id = request.getParameter("userId");
		User user = new AdminService().getUser(userId);


		if(user == null || id == null){
			messages.add("指定されたURLは存在しません");
		}
		if(messages.size() == 0){
			return true;
		} else {
			return false;
		}
	}



	//ユーザー編集のエラーメッセージ
	private boolean isValid(HttpServletRequest request, List<String> messages){

		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");
		String name = request.getParameter("name");
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int departmentId =Integer.parseInt(request.getParameter("departmentId"));


		if(!password.equals(checkPassword)){
			messages.add("パスワードが一致しません");
		}

		if(loginId.length() >=20 ||  loginId.length() < 6 || !loginId.matches("[0-9a-zA-Z_]+$")){
			messages.add("ログインIDは6文字以上20文字以下の半角英数字です");
		}

		if(password.length() >= 255 || password.length() < 6 || !password.matches("[ -~｡-ﾟ]+$")){
			messages.add("パスワードは6文字以上255文字以下の半角文字です");
		}

		if(name.length() ==0){
			messages.add("名前が入力されていません");
		}

		if(name.length() >= 10){
			messages.add("名前は10文字以下です");
		}

		if(branchId >= 5){
			messages.add("存在しない支店IDです");
		}

		if(departmentId >= 5){
			messages.add("存在しない部署IDです");
		}

		if(branchId == 1 && departmentId == 3){
			messages.add("本社の方は、店長では登録できません");
		}

		if(branchId != 1 && departmentId <= 2){
			messages.add("支店の方は、店長か社員です");
		}

		if(messages.size() == 0){
			return true;
		} else {
			return false;
		}
	}


}
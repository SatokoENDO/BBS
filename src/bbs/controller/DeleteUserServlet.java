package bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbs.service.UserService;

@WebServlet(urlPatterns = {"/delete"})
public class DeleteUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		int id = Integer.parseInt(request.getParameter("id"));
		new UserService().delete(id);
		String messages = "ユーザーを削除しました";

		session.setAttribute("messages", messages);
		response.sendRedirect("./admin");
	}

}
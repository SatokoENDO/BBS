package bbs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.User;
import bbs.service.AdminService;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException{
		List<User> users = new AdminService().getUsers();

		request.setAttribute("users", users);
		request.getRequestDispatcher("admin.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)throws IOException, ServletException{
		String isLocked = (request.getParameter("isLocked"));
		int userId = Integer.parseInt(request.getParameter("userId"));

		new AdminService().doIsLocked(isLocked, userId);

		response.sendRedirect("admin");
	}


}
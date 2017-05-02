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

import bbs.beans.Message;
import bbs.beans.User;
import bbs.service.MessageService;

@WebServlet(urlPatterns = {"/message"})
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();

		if (isValid(request, messages) == true) {
			User user = (User) session.getAttribute("login_user");
			Message message = new Message();

			message.setTitle(request.getParameter("title"));
			message.setText(request.getParameter("text"));
			message.setCategory(request.getParameter("category"));
			message.setId(user.getId());
			//messages.add("投稿に成功しました");
			session.setAttribute("messages", messages);
			new MessageService().register(message);
			response.sendRedirect("./");
		} else {
			session.setAttribute("messages", messages);
			response.sendRedirect("message");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String subject = request.getParameter("title");
		String body = request.getParameter("text");
		String category = request.getParameter("category");

		if (subject.length() == 0) {
			messages.add("投稿に失敗しました");

			messages.add("件名を入力してください");
		}

		if (50 < subject.length()) {
			messages.add("件名は50文字以下で入力してください");
		}

		if (category.length() == 0) {
			messages.add("カテゴリーを入力してください");
		}

		if (10 < category.length()) {
			messages.add("カテゴリーは10文字以下で入力してください");
		}

		if (body.length() == 0) {
			messages.add("本文を入力してください");
		}

		if (1000 < body.length()) {
			messages.add("本文は1000文字以下で入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
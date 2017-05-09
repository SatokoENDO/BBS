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

import bbs.beans.Comment;
import bbs.beans.User;
import bbs.service.CommentService;
@WebServlet(urlPatterns = {"/comment"})
public class CommentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		Comment comment = new Comment();
		if (isValid(request, messages) == true) {
			User user = (User) session.getAttribute("loginUser");



			String text = request.getParameter("text");

			text = text.replaceAll("\n","<br>");

			session.setAttribute("messages", text);
			comment.setText(text);
			comment.setUserId(user.getId());
			comment.setMessageId(Integer.parseInt(request.getParameter("messageId")));

			System.out.println("message");
			System.out.println(request.getParameter("messageId"));

			new CommentService().register(comment);
			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> errorMessages) {
		String comment = request.getParameter("text");
		if (comment.length() == 0) {
			errorMessages.add("コメントを入力してください");
		}
		if (500 < comment.length()) {
			errorMessages.add("コメントは500文字以下で入力してください");
		}
		if (errorMessages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
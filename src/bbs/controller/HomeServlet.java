package bbs.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbs.beans.UserComment;
import bbs.beans.UserMessage;
import bbs.service.CommentService;
import bbs.service.MessageService;


@WebServlet(urlPatterns = { "/index.jsp" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> categories = new MessageService().getCategories();
		request.setAttribute("categories", categories);
		String category = request.getParameter("category");
		request.setAttribute("selectedCategory", category);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String startDate;
		String startDateParameter = request.getParameter("startDate");

		String endDate;
		String endDateParameter = request.getParameter("endDate");


		if(startDateParameter == null || !startDateParameter.matches("[0-9]{4}[-][0-9]{2}[-][0-9]{2}")){
			startDate = new MessageService().getOldestDate();
		}else if (startDateParameter.isEmpty()){
			startDate = new MessageService().getOldestDate();

		} else {
			startDate = startDateParameter;
			request.setAttribute("startDate", startDate);
		}

		if(endDateParameter == null || !endDateParameter.matches("[0-9]{4}[-][0-9]{2}[-][0-9]{2}")){
			endDate = sdf.format(date).toString();
		}else if (endDateParameter.isEmpty()){
			endDate = sdf.format(date).toString();
		} else {
			endDate = endDateParameter;
			request.setAttribute("endDate", endDate);
		}


		List<UserMessage> userMessage = new MessageService().getMessage(category, startDate, endDate);
		request.setAttribute("messages", userMessage);

		List<UserComment> userComment = new CommentService().getCommentList();
		request.setAttribute("comments", userComment);

		request.getRequestDispatcher("home.jsp").forward(request, response);
	}


}
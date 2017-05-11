package bbs.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/message", "/home", "/home.jsp", "/index.jsp", "/admin", "/signup", "/edituser"})
public class LoginFilter implements Filter{


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
		HttpSession session = ((HttpServletRequest)request).getSession();

		List<String> messages = new ArrayList<>();
		if(session.getAttribute("loginUser") == null){
			messages.add("ログインしてください");
			session.setAttribute("messages", messages);
			((HttpServletResponse)response).sendRedirect("login");
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config){

	}

	public void destroy(){

	}

}
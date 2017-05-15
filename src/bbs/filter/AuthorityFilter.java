package bbs.filter;

import java.io.IOException;

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

import bbs.beans.User;
@WebFilter(description = "権限フィルター", filterName = "AuthorityFilter", urlPatterns = {"/admin", "/edituser", "/signup"})
public class AuthorityFilter implements Filter{
	 public void doFilter(ServletRequest request, ServletResponse response,
	            FilterChain chain) throws IOException, ServletException {

	try{
		HttpSession session = ((HttpServletRequest)request).getSession();

		User user = (User) session.getAttribute("loginUser");
		if(user != null){
			if((user.getBranchId()==1 && user.getDepartmentId()==1)){
				chain.doFilter(request, response);
			} else{
				String errorMessage = "指定されたURLへのアクセス権限がありません。";
				session.setAttribute("errorMessages", errorMessage);
				((HttpServletResponse)response).sendRedirect("./");
				return;
			}
		} else {
			chain.doFilter(request, response);
		}
	 } catch (ServletException se){
	    }catch (IOException e){
	    }
	}

    public void init(FilterConfig arg0) throws ServletException {

    }


	 public void destroy() {

	 }


}
package cn.BX.Users.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*
		 * 1.获取session中的user 2.判断是否为null > 如果为null：保存错误信息，转发到msg.jsp >
		 * 如果不为null：放行
		 */
		HttpServletRequest req = (HttpServletRequest) request;
		Object user = req.getSession().getAttribute("sessionUser");
		Object uname = req.getSession().getAttribute("sessionUname");
		Object phone = req.getSession().getAttribute("sessionPhone");
		Object email = req.getSession().getAttribute("sessionEmail");
//		Object fname = req.getSession().getAttribute("sessionFname");
		Object address = req.getSession().getAttribute("sessionAddress");
		if (user == null || uname == null || phone == null || email == null || address == null) {
			req.setAttribute("msg", "您访问路径有错，或者您还没有填全信息，不能执行下一步操作");
			req.getRequestDispatcher("/jsps/msg1.jsp").forward(req, response);
		} else {
			chain.doFilter(request, response);// 放行
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

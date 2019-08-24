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
		 * 1.��ȡsession�е�user 2.�ж��Ƿ�Ϊnull > ���Ϊnull�����������Ϣ��ת����msg.jsp >
		 * �����Ϊnull������
		 */
		HttpServletRequest req = (HttpServletRequest) request;
		Object user = req.getSession().getAttribute("sessionUser");
		Object uname = req.getSession().getAttribute("sessionUname");
		Object phone = req.getSession().getAttribute("sessionPhone");
		Object email = req.getSession().getAttribute("sessionEmail");
//		Object fname = req.getSession().getAttribute("sessionFname");
		Object address = req.getSession().getAttribute("sessionAddress");
		if (user == null || uname == null || phone == null || email == null || address == null) {
			req.setAttribute("msg", "������·���д���������û����ȫ��Ϣ������ִ����һ������");
			req.getRequestDispatcher("/jsps/msg1.jsp").forward(req, response);
		} else {
			chain.doFilter(request, response);// ����
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}

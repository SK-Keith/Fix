package cn.BX.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet("/BaseServlet")
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");//������Ӧ����
		
		/**
		 * 1. ��ȡmethod�����������û�����õķ��� 2. �ѷ������Ʊ��Method���ʵ������ 3. ͨ��invoke()�������������
		 */
		String methodName = request.getParameter("method");
		Method method = null;
		/**
		 * 2. ͨ���������ƻ�ȡMethod����
		 */
		try {
			method = this.getClass().getMethod(methodName,
					HttpServletRequest.class, HttpServletResponse.class);
		} catch (Exception e) {
			throw new RuntimeException("��Ҫ���õķ�����" + methodName + "�������ڣ�", e);
		}
		
		/**
		 * 3. ͨ��method������������
		 */
		try {
			String result = (String)method.invoke(this, request, response);
			if(result != null && !result.trim().isEmpty()) {//��������������ز�Ϊ��
				int index = result.indexOf(":");//��ȡ��һ��ð�ŵ�λ��
				if(index == -1) {//���û��ð�ţ�ʹ��ת��
					request.getRequestDispatcher(result).forward(request, response);
				} else {//�������ð��
					String start = result.substring(0, index);//�ָ��ǰ׺
					String path = result.substring(index + 1);//�ָ��·��
					if(start.equals("f")) {//ǰ׺Ϊf��ʾת��
						request.getRequestDispatcher(path).forward(request, response);
					} else if(start.equals("r")) {//ǰ׺Ϊr��ʾ�ض���
						response.sendRedirect(request.getContextPath() + path);
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

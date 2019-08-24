package cn.BX.user.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.BX.base.BaseServlet;
import cn.BX.Users.floor.domain.Floor;
import cn.BX.tool.CommonUtils;
import cn.BX.user.domain.User;
import cn.BX.user.service.UserService;

@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserService();
	/**
	 * ��¼����
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	public String login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/*
		 * 1.��װ�����ݵ�User 
		 * 5.������ڣ��鿴״̬�����״̬Ϊfalse:
		 * 	* ���������Ϣ����û�м��� * �����û����ݣ�Ϊ�˻���
		 *  * ����������Ϣ����ʾ���ѵ�¼���� * ���湫����Ϣ����ʾ���ѵ�¼���� *
		 * 		ת����login.jsp 
		 * 6.��¼�ɹ� * ���浱ǰ��ѯ����user��session�� *
		 * ���浱ǰ�û������Ƶ�cookie�У�ע��������Ҫ���봦��
		 */
		/*
		 * 1.��װ�����ݵ�user
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		/*
		 * 2.У��
		 */
		// Map<String, String> errors = validateLogin(formUser,
		// req.getSession());
		// if (errors.size() > 0) {
		// req.setAttribute("form", formUser);
		// req.setAttribute("errors", errors);
		// return "f:/jsps/user/login.jsp";
		// }
		/*
		 * 3.����userService#login()����
		 */
		User user = userService.login(formUser);
		/*
		 * 4.��ʼ�ж�
		 */
		if (user.getLoginname() == null || user.getLoginpass() == null) {
			req.setAttribute("msg", "�û������������");
			req.setAttribute("user", formUser);
			return "f:/jsps/user/login.jsp";
		} else { 
			// �����û���session
			req.getSession().setAttribute("sessionUser", user);
			req.getSession().setAttribute("sessionUid", user.getUid());
			req.getSession().setAttribute("sessionUname", user.getUname());
			req.getSession().setAttribute("sessionPhone", user.getPhone());
			req.getSession().setAttribute("sessionEmail", user.getEmail());
//			req.getSession().setAttribute("sessionFname", user.getFloor().getFname());
			req.getSession().setAttribute("sessionAddress", user.getAddress());
			// ��ȡ�û������浽cookie��
			String loginname = user.getLoginname();
			loginname = URLEncoder.encode(loginname, "utf-8");
			Cookie cookie = new Cookie("loginname", loginname);
			cookie.setMaxAge(60 * 60 * 24 * 10);// ����10��
			resp.addCookie(cookie);
			if(user.getUname() == null || user.getPhone() == null || user.getEmail() == null
					|| user.getAddress() == null){
				return "f:/jsps/student/index.jsp";
			}else{
				return "f:/jsps/student/index2.jsp";
			}
		}
	}

	/**
	 * �༭������Ϣ
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	public String edit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		Map map = req.getParameterMap();
		User user = CommonUtils.toBean(map, User.class);
		Floor floor = CommonUtils.toBean(map, Floor.class);
		floor.setFid(CommonUtils.uuid());
		user.setFloor(floor);

		userService.edit(user);
		req.getSession().setAttribute("sessionUser", user);
		req.getSession().setAttribute("sessionUname", user.getUname());
		req.getSession().setAttribute("sessionPhone", user.getPhone());
		req.getSession().setAttribute("sessionEmail", user.getEmail());
//		req.getSession().setAttribute("sessionFname", user.getFloor().getFname());
		req.getSession().setAttribute("sessionAddress", user.getAddress());
		req.setAttribute("msg", "��Ϣ��ӳɹ�");
		return "f:/jsps/msg.jsp";
	}

	/**
	 * ҳ���Զ��ж��û����Ƿ����
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateUname(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1.��ȡ�û���
		 */
		String uname = req.getParameter("uname");
		/*
		 * 2.ͨ��service�õ�У����
		 */
		boolean b = userService.ajaxValidateLoginname(uname);
		/*
		 * 3.���ؿͻ���
		 */
		resp.getWriter().print(b);
		
		return null;
	}
	
	/**
	 * ajax Email�Ƿ�ע��У��
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateEmail(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1.��ȡEmail
		 */
		String email = req.getParameter("email");
		/*
		 * 2.ͨ��service�õ�У����
		 */
		boolean b = userService.ajaxValidateEmail(email);
		/*
		 * 3.���ؿͻ���
		 */
		resp.getWriter().print(b);
		return null;
	}
	
	/**
	 * ajax Email�Ƿ�ע��У��
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidatePhone(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1.��ȡEmail
		 */
		String phone = req.getParameter("phone");
		/*
		 * 2.ͨ��service�õ�У����
		 */
		boolean b = userService.ajaxValidatePhone(phone);
		/*
		 * 3.���ؿͻ���
		 */
		resp.getWriter().print(b);
		return null;
	}
	
	/**
	 * �������е��û�
	 * @param req
	 * @param resp
	 * @return
	 */
	public String loadAllUser(HttpServletRequest req, HttpServletResponse resp){
		List<User> userList = userService.loadAllUser();
		req.setAttribute("userList", userList);
		return "f:/adminjsps/admin/table5.jsp";
	}
	
	/**
	 * ע�Ṧ��
	 * @param req
	 * @param resp
	 * @return
	 */
	public String register(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("loginname");
		boolean b = userService.findByUsername(username);
		if(b) {
			String uid = CommonUtils.uuid();
			String loginpass = req.getParameter("loginpass");
			User u = new User();
			u.setUid(uid);
			u.setLoginname(username);
			u.setLoginpass(loginpass);
			userService.createUser(u);
			return "f:/jsps/user/message_s.jsp";
		}else {
			return "f:/jsps/user/message_f.jsp";
		}
	}
	
	/**
	 * �˳�����
	 * @param req
	 * @param resp
	 * @return
	 */
	public String exit(HttpServletRequest req, HttpServletResponse resp){
		req.getSession().invalidate();
		return "r:/jsps/user/login.jsp";
	}
	
}

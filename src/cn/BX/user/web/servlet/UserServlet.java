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
	 * 登录功能
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	public String login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/*
		 * 1.封装表单数据到User 
		 * 5.如果存在，查看状态，如果状态为false:
		 * 	* 保存错误信息，您没有激活 * 保存用户数据，为了回显
		 *  * 保存新闻信息，显示在已登录界面 * 保存公告信息，显示在已登录界面 *
		 * 		转发到login.jsp 
		 * 6.登录成功 * 保存当前查询出的user到session中 *
		 * 保存当前用户的名称到cookie中，注意中文需要编码处理
		 */
		/*
		 * 1.封装表单数据到user
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		/*
		 * 2.校验
		 */
		// Map<String, String> errors = validateLogin(formUser,
		// req.getSession());
		// if (errors.size() > 0) {
		// req.setAttribute("form", formUser);
		// req.setAttribute("errors", errors);
		// return "f:/jsps/user/login.jsp";
		// }
		/*
		 * 3.调用userService#login()方法
		 */
		User user = userService.login(formUser);
		/*
		 * 4.开始判断
		 */
		if (user.getLoginname() == null || user.getLoginpass() == null) {
			req.setAttribute("msg", "用户名或密码错误！");
			req.setAttribute("user", formUser);
			return "f:/jsps/user/login.jsp";
		} else { 
			// 保存用户到session
			req.getSession().setAttribute("sessionUser", user);
			req.getSession().setAttribute("sessionUid", user.getUid());
			req.getSession().setAttribute("sessionUname", user.getUname());
			req.getSession().setAttribute("sessionPhone", user.getPhone());
			req.getSession().setAttribute("sessionEmail", user.getEmail());
//			req.getSession().setAttribute("sessionFname", user.getFloor().getFname());
			req.getSession().setAttribute("sessionAddress", user.getAddress());
			// 获取用户名保存到cookie中
			String loginname = user.getLoginname();
			loginname = URLEncoder.encode(loginname, "utf-8");
			Cookie cookie = new Cookie("loginname", loginname);
			cookie.setMaxAge(60 * 60 * 24 * 10);// 保存10天
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
	 * 编辑个人信息
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
		req.setAttribute("msg", "信息添加成功");
		return "f:/jsps/msg.jsp";
	}

	/**
	 * 页面自动判断用户名是否存在
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateUname(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1.获取用户名
		 */
		String uname = req.getParameter("uname");
		/*
		 * 2.通过service得到校验结果
		 */
		boolean b = userService.ajaxValidateLoginname(uname);
		/*
		 * 3.返回客户端
		 */
		resp.getWriter().print(b);
		
		return null;
	}
	
	/**
	 * ajax Email是否注册校验
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidateEmail(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1.获取Email
		 */
		String email = req.getParameter("email");
		/*
		 * 2.通过service得到校验结果
		 */
		boolean b = userService.ajaxValidateEmail(email);
		/*
		 * 3.返回客户端
		 */
		resp.getWriter().print(b);
		return null;
	}
	
	/**
	 * ajax Email是否注册校验
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String ajaxValidatePhone(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/*
		 * 1.获取Email
		 */
		String phone = req.getParameter("phone");
		/*
		 * 2.通过service得到校验结果
		 */
		boolean b = userService.ajaxValidatePhone(phone);
		/*
		 * 3.返回客户端
		 */
		resp.getWriter().print(b);
		return null;
	}
	
	/**
	 * 加载所有的用户
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
	 * 注册功能
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
	 * 退出功能
	 * @param req
	 * @param resp
	 * @return
	 */
	public String exit(HttpServletRequest req, HttpServletResponse resp){
		req.getSession().invalidate();
		return "r:/jsps/user/login.jsp";
	}
	
}

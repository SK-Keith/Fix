package cn.BX.user.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
	
import cn.BX.base.BaseServlet;
import cn.BX.tool.CommonUtils;
import cn.BX.user.domain.User;
import cn.BX.user.service.UserService;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

	public String login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/*
		 * 1.封装表单数据到User 
		 * 2.校验表单数据 
		 * 3.使用service查询，得到User 
		 * 4.查看用户是否存在，如果不存在 *
		 * 保存错误信息，用户名或密码错误 * 保存用户数据，为了回显 * 转发到login.jsp 
		 * 5.如果存在，查看状态，如果状态为false:
		 * * 保存错误信息，您没有激活 
		 * * 保存用户数据，为了回显 
		 * * 保存新闻信息，显示在已登录界面 
		 * * 保存公告信息，显示在已登录界面 * 
		 * 	  转发到login.jsp 
		 * 6.登录成功 * 保存当前查询出的user到session中 
		 * *
		 * 保存当前用户的名称到cookie中，注意中文需要编码处理
		 */
		/*
		 * 1.封装表单数据到user
		 */
		User formUser = CommonUtils.toBean(req.getParameterMap(), User.class);
		/*
		 * 2.校验
		 */
		Map<String, String> errors = validateLogin(formUser, req.getSession());
		if (errors.size() > 0) {
			req.setAttribute("form", formUser);
			req.setAttribute("errors", errors);
			return "f:/jsps/user/login.jsp";
		}
		/*
		 * 3.调用userService#login()方法
		 */
		User user = userService.login(formUser);
		/*
		 * 4.开始判断
		 */
		if (user == null) {
			req.setAttribute("msg", "用户名或密码错误！");
			req.setAttribute("user", formUser);
			return "f:/jsps/user/login.jsp";
		} else {
				// 保存用户到session
				req.setAttribute("username", user.getLoginname());
				req.getSession().setAttribute("sessionUser", user);

				//把报修类型和报修范围提交到首页
				
				
				// 获取用户名保存到cookie中
				String loginname = user.getLoginname();
				loginname = URLEncoder.encode(loginname, "utf-8");
				Cookie cookie = new Cookie("loginname", loginname);
				cookie.setMaxAge(60 * 60 * 24 * 10);// 保存10天
				resp.addCookie(cookie);
				return "f:/jsps/student/main.jsp";
			
		}
	}

	private Map<String, String> validateLogin(User formUser, HttpSession session) {
		Map<String, String> errors = new HashMap<String, String>();
		String verifyCode = formUser.getVerifyCode();
		String vcode = (String) session.getAttribute("vCode");
		if (verifyCode == null || verifyCode.trim().isEmpty()) {
			errors.put("verifyCode", "验证码不能为空");
		} else if (!verifyCode.equalsIgnoreCase(vcode)) {
			errors.put("verifyCode", "验证码错误");
		}
		return errors;
	}

}

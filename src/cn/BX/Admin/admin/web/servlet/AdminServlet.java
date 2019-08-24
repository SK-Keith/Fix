package cn.BX.Admin.admin.web.servlet;

import cn.BX.Admin.admin.domain.Admin;
import cn.BX.Admin.admin.service.AdminService;
import cn.BX.Users.floor.domain.Floor;
import cn.BX.Users.range.domain.Range;
import cn.BX.Users.range.service.RangeService;
import cn.BX.base.BaseServlet;
import cn.BX.tool.CommonUtils;
import cn.BX.user.domain.User;
import cn.BX.user.web.servlet.UserServlet;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private AdminService adminService = new AdminService();
	private UserServlet userServlet = new UserServlet();
	
	/**
	 * ��¼����
	 * @param req
	 * @param resp
	 * @return
	 */
	public String login(HttpServletRequest req,HttpServletResponse resp){
		Admin formAdmin = CommonUtils.toBean(req.getParameterMap(), Admin.class);
		
		Admin admin = adminService.login(formAdmin);
		if(admin == null){
			req.setAttribute("msg", "�ʺ��������");
			return "f:/adminjsps/login.jsp";
		}else{
			String identity = admin.getIdentity();
			req.setAttribute("admin", admin);
			req.getSession().setAttribute("SessionAid", admin.getAid());
			req.getSession().setAttribute("SessionName", admin.getName());
			if(identity.equals("1")){
				return "f:/adminjsps/admin/index1.jsp";
			}else if(identity.equals("2")){
				return "f:/adminjsps/worker/index1.jsp";
			}
			return "f:/adminjsps/error.jsp";
		}
	}
	
	/**
	 * ����aid��ѯָ���Ĺ���Ա������Ϣ
	 * @param req
	 * @param resp
	 * @return
	 */
	public String findAdmin(HttpServletRequest req,HttpServletResponse resp){
		String aid = (String) req.getParameter("aid");
		Admin admin = adminService.findAdmin(aid);
		req.setAttribute("admin", admin);
		return "f:/adminjsps/admin/change.jsp";
	}
	
	/**
	 * ����aid��ѯָ����ά��Ա��������Ϣ
	 * @param req
	 * @param resp
	 * @return
	 */
	public String findAdmin2(HttpServletRequest req,HttpServletResponse resp){
		String aid = (String) req.getParameter("aid");
		Admin admin = adminService.findAdmin2(aid);
		req.setAttribute("admin", admin);
		return "f:/adminjsps/worker/change.jsp";
	}
	
	/**
	 * �޸Ĺ���Ա����
	 * ������
	 * 1.Map map = req.getParameterMap()
	 * 2.������תΪ���Ӧ��ʵ����
	 * 3.ִ�����
	 * @param req
	 * @param resp
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String updateAdmin(HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		Map map = req.getParameterMap();
		Admin admin = CommonUtils.toBean(map, Admin.class);
		adminService.updateAdmin(admin);
		
		req.getSession().setAttribute("SessionName", admin.getName());
//		String aid = req.getParameter("aid");
		req.setAttribute("msg", "��Ϣ��ӳɹ�");
		return "f:/adminjsps/admin/msg.jsp";
	}
	
	
	/**
	 * �޸�ά��Ա����
	 * @param req
	 * @param resp
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String updateAdmin2(HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		Map map = req.getParameterMap();
		Admin admin = CommonUtils.toBean(map, Admin.class);
		adminService.updateAdmin(admin);
		req.getSession().setAttribute("SessionName", admin.getName());
		
//		String aid = req.getParameter("aid");
		req.setAttribute("msg", "��Ϣ��ӳɹ�");
		return "f:/adminjsps/worker/msg.jsp";
	}
	
	/**
	 * �˳�����
	 * req.getSession.invalidate()
	 * @param req
	 * @param resp
	 * @return
	 */
	public String quit(HttpServletRequest req,HttpServletResponse resp){
		req.getSession().invalidate();
		return "f:/adminjsps/login.jsp";
	}
	
	/**
	 * ɾ���û�
	 * @param request
	 * @param response
	 * @return
	 */
	public String deleteUser(HttpServletRequest request, HttpServletResponse response){
		String uid = request.getParameter("uid");
		adminService.deleteUser(uid);
		return userServlet.loadAllUser(request,response);
	}
	
	/**
	 * ɾ��ά��Ա��identity = 2
	 * @param request
	 * @param response
	 * @return
	 */
	public String deleteAdmin(HttpServletRequest request, HttpServletResponse response){
		String aid = request.getParameter("aid");
		adminService.deleteAdmin(aid);
		return loadAllAdmin2(request, response);
	}
	
	/**
	 * �������е�ά��ʦ��
	 * ��Ա����ҳ����Ҫ
	 * @param req
	 * @param resp
	 * @return
	 */
	public String loadAllAdmin2(HttpServletRequest req, HttpServletResponse resp){
		List<Admin> adminList = adminService.loadAllAdmin2();
		req.setAttribute("adminList", adminList);
		return "f:/adminjsps/admin/table6.jsp";
	}
	
	
}

package cn.BX.Admin.record.web.servlet;

import cn.BX.Admin.admin.web.servlet.AdminServlet;
import cn.BX.Users.record.domain.Record;
import cn.BX.Users.record.service.RecordService;
import cn.BX.base.BaseServlet;
import cn.BX.user.web.servlet.UserServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RecordServlet1
 */
@WebServlet("/admin/RecordServlet1")
public class RecordServlet1 extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private RecordService recordService = new RecordService();
	
	/**
	 * 查询状态为1的表单信息
	 * 管理员查询所有未修复的信息
	 *  r.rid, addfname, details,image,date1,u.phone, u.uname,a.name,a.phone
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByAdminStatus1(HttpServletRequest request, HttpServletResponse response) {
		List<Record> record = recordService.findByStatus1();
		request.setAttribute("recordList", record);
		return "f:/adminjsps/admin/table1.jsp";
	}
	
	/**
	 * 查询状态为2的表单信息
	 * 管理员查询所有已修复的信息
	 * rid addfname details image date1  phone uname  name phone  evaluation  date4
	 * @param request
	 * @param response
	 * @return
	 */
//	public String findByStatus2(HttpServletRequest request, HttpServletResponse response) {
//		List<Record> record = recordService.findByStatus2();
//		request.setAttribute("recordList", record);
//		return "f:/adminjsps/admin/table2.jsp";
//	}
	
	/**
	 *  查询状态为2的表单信息
	 * 管理员查询所有已修复的信息
	 * rid addfname details image date1  phone uname  name phone  evaluation  date4 date6
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByAdminStatus2(HttpServletRequest request, HttpServletResponse response) {
		List<Record> record = recordService.findByAdminStatus2();
		request.setAttribute("recordList", record);
		return "f:/adminjsps/admin/table2.jsp";
	}
	
	/**
	 * 管理员查询暂缓修复的表单
	 * r.rid,concat(f.fname,u.address) addfname, details,image,date1,u.phone, u.uname,a.name,a.phone
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByAdminStatus3(HttpServletRequest request, HttpServletResponse response) {
		List<Record> record = recordService.findByStatus3();
		request.setAttribute("recordList", record);
		return "f:/adminjsps/admin/table3.jsp";
	}
	
	/**
	 * 管理员查看超时表单的数据
	 * SELECT r.rid,concat(f.fname,u.address) addfname, details,image,date1,u.phone, u.uname,a.name,a.phone FROM b_record r, b_admin a,b_user u,b_floor f where f.fid = a.fid and u.uid = r.uid and r.aid = a.aid and status = '4' order by date1 desc
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByAdminStatus4(HttpServletRequest request, HttpServletResponse response) {
		List<Record> record = recordService.findByStatus4();
		request.setAttribute("recordList", record);
		return "f:/adminjsps/admin/table4.jsp";
	}
	
	/**
	 * 暂时不用
	 * 返回几条消息的基本信息
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
//	public String ajaxfindByStatus4(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		List<Record> record = recordService.findByStatus4();
//		String json = toJson(record);
//		response.getWriter().print(json);
//		return null;
//	}

	/**
	 * 删除表单某一条未修复报修数据
	 * 可以做成ajax模式，就不用调用这么多方法，只是为了在不同页面去显示
	 * 返回到未修复页面
	 * @param request
	 * @param response
	 * @return
	 */
	public String deleteTable1(HttpServletRequest request, HttpServletResponse response){
		String rid = request.getParameter("rid");
		recordService.deleteTable(rid);
		return findByAdminStatus1(request,response);
	}
	
	/**
	 * 删除表单某一条已修复的表单数据
	 * 返回到已修复页面
	 * @param request
	 * @param response
	 * @return
	 */
	public String deleteTable2(HttpServletRequest request, HttpServletResponse response){
		String rid = request.getParameter("rid");
		recordService.deleteTable(rid);
		return findByAdminStatus2(request,response);
	}
	
	/**
	 * 删除表单某一条超时的表单数据
	 * @param request
	 * @param response
	 * @return
	 */
	public String deleteTable4(HttpServletRequest request, HttpServletResponse response){
		String rid = request.getParameter("rid");
		recordService.deleteTable(rid);
		return findByAdminStatus4(request,response);
	}
	
	/**
	 * 管理员页面加载超时表单的数量
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public String ajaxonload1(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Number num1 = recordService.findNum1();
		String num = num1 + "";
		response.getWriter().print(num);
		return null;
	}
	
	/**
	 * 管理员页面加载未修复的表单数量
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public String ajaxonload2(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Number num2 = recordService.findNum2();
		String num = num2 + "";
		response.getWriter().print(num);
		return null;
	}

	/**
	 * 维修员页面加载超时的表单数量
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public String ajaxonload1Aid(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String aid = request.getParameter("aid");
		Number num1 = recordService.findNum1Aid(aid);
		String num = num1 + "";
		response.getWriter().print(num);
		return null;
	}

	/**
	 * 维修员页面加载未修复的表单数量
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	public String ajaxonload2Aid(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String aid = request.getParameter("aid");
		Number num1 = recordService.findNum2Aid(aid);
		String num = num1 + "";
		response.getWriter().print(num);
		return null;
	}
	
	/**
	 * json格式1
	 * @param record
	 * @return
	 */
	private String toJson(Record record) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"detail\"").append(":").append("\"").append(record.getDetails()).append("\"");
		sb.append(",");
		sb.append("\"date1\"").append(":").append("\"").append(record.getDate1()).append("\"");
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * json格式2
	 * @param recordList
	 * @return
	 */
	private String toJson(List<Record> recordList) {
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0; i < recordList.size(); i++) {
			sb.append(toJson(recordList.get(i)));
			if(i < recordList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	/**
	 * ajax返回页面用户留言，即评价墙
	 * 不用
	 * 并且返回时间
	 */
//	public String findEvaluation(HttpServletRequest request, HttpServletResponse response){
//		String aid = request.getParameter("aid");
//		request.getSession().setAttribute("SessionAid", aid);
//		List<Record> record = recordService.findByAdminStatus2();
//		request.setAttribute("recordList", record);
//		return "f:/adminjsps/admin/index.jsp";
//	}
//	
	/**
	 * 返回页面用户留言，即评价墙,状态为2，评价内容非空
	 * 管理员和维修员都是调用这个
	 * 并且返回时间
	 */
	public String findByEvaStatus2(HttpServletRequest request, HttpServletResponse response){
		String aid = request.getParameter("aid");
		request.getSession().setAttribute("SessionAid", aid);
		List<Record> record = recordService.findByEvaStatus2();
		request.setAttribute("recordList", record);
		return "f:/adminjsps/admin/index.jsp";
	}
	
	/**
	 * 在三种情况下使用该方法
	 * 1.管理员登录进入首页页面，自动执行,ajax
	 * 2.维修员登录进入未修复页面，自动执行ajax
	 * 3.用户点击查看表单信息时，自动执行ajax
	 * ajax自动加载判断是否超时，进行判断
	 * @throws IOException 
	 */
	public String ajaxJudgeTime(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		recordService.updateStatus4();
		resp.getWriter().print("");
		return null;
	}
	
}

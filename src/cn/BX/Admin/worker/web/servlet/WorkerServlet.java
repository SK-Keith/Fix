package cn.BX.Admin.worker.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import cn.BX.Admin.admin.domain.Admin;
import cn.BX.Admin.admin.service.AdminService;
import cn.BX.Users.floor.domain.Floor;
import cn.BX.Users.floor.service.FloorService;
import cn.BX.Users.range.domain.Range;
import cn.BX.Users.range.service.RangeService;
import cn.BX.Users.record.domain.Record;
import cn.BX.Users.record.service.RecordService;
import cn.BX.base.BaseServlet;
import cn.BX.tool.CommonUtils;

/**
 * Servlet implementation class WorkerServlet
 */
@WebServlet("/admin/WorkerServlet")
public class WorkerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private RecordService recordService = new RecordService();
	private RangeService rangeService = new RangeService();
	private AdminService adminService = new AdminService();
	private FloorService floorService = new FloorService();
	
	/**
	 * 维修员看到所有评价
	 * @param request
	 * @param response
	 * @return
	 */
	public String findEvaluation(HttpServletRequest request, HttpServletResponse response){
		String aid = request.getParameter("aid");
		request.getSession().setAttribute("SessionAid", aid);
		List<Record> record = recordService.findByEvaStatus2();
		request.setAttribute("recordList", record);
		return "f:/adminjsps/worker/index.jsp";
	}
	
	
	
	/**
	 * 根据维修员id和未修复的查询
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByAidStatus1(HttpServletRequest request, HttpServletResponse response) {
		String aid = request.getParameter("aid");
		List<Record> recordList = recordService.findByAidStatus1(aid);
		request.getSession().setAttribute("SessionAid", aid);
		request.setAttribute("recordList", recordList);
		return "f:/adminjsps/worker/table1.jsp";
	}
	
	/**
	 * 返回维修员首页评价时间
	 * 不用
	 * @param request
	 * @param response
	 * @return
	 */
//	public String findByEvaAidStatus1(HttpServletRequest request, HttpServletResponse response) {
//		String aid = request.getParameter("aid");
//		List<Record> recordList = recordService.findByEvaAidStatus1(aid);
//		request.getSession().setAttribute("SessionAid", aid);
//		request.setAttribute("recordList", recordList);
//		return "f:/adminjsps/worker/table1.jsp";
//	}
	
	/**
	 * 根据维修员id和已修复的查询
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByAidStatus2(HttpServletRequest request, HttpServletResponse response) {
		String aid = request.getParameter("aid");
		List<Record> record = recordService.findByAidStatus2(aid);
		request.setAttribute("recordList", record);
		return "f:/adminjsps/worker/table2.jsp";
	}
	
	/**
	 * 根据维修员id和暂缓修复的查询
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByAidStatus3(HttpServletRequest request, HttpServletResponse response) {
		String aid = request.getParameter("aid");
		List<Record> record = recordService.findByAidStatus3(aid);
		request.setAttribute("recordList", record);
		return "f:/adminjsps/worker/table3.jsp";
	}
	
	/**
	 * 根据维修员id和超时修复的查询
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByAidStatus4(HttpServletRequest request, HttpServletResponse response) {
		String aid = request.getParameter("aid");
		List<Record> record = recordService.findByAidStatus4(aid);
		request.setAttribute("recordList", record);
		return "f:/adminjsps/worker/table4.jsp";
	}
	
	/**
	 * 维修员查看别人对自己的评价
	 * 只看自己，暂时不用
	 * findEvaluation
	 */
//	public String findEvaluation(HttpServletRequest request, HttpServletResponse response){
//		String aid = request.getParameter("aid");
//		List<Record> record = recordService.findByAidStatus2(aid);
//		request.setAttribute("recordList", record);
//		return "f:/adminjsps/worker/index.jsp";
//	}

	/**
	 * 更新未修复的表单致已修复，同时记录完成时间
	 * @param request
	 * @param response
	 * @return
	 */
	public String updateStatus(HttpServletRequest request, HttpServletResponse response){
		String rid = request.getParameter("rid");
		String aid = request.getParameter("aid");
		request.getSession().setAttribute("aid", aid);
		String date4 = String.format("%tF %<tT", new Date());
		recordService.updateStatus(rid,date4); 
		return findByAidStatus1(request,response);
	}
	/**
	 * 更新超时修复的表单致已修复，同时记录完成时间
	 * @param request
	 * @param response
	 * @return
	 */
	public String updateStatus2From4(HttpServletRequest request, HttpServletResponse response){
		String rid = request.getParameter("rid");
		String aid = request.getParameter("aid");
		request.getSession().setAttribute("aid", aid);
		String date4 = String.format("%tF %<tT", new Date());
		recordService.updateStatus(rid,date4); 
		return findByAidStatus4(request,response);
	}
	
	/**
	 * 维修员将已完成的表单返回到未修复状态，防止师傅错误提交
	 * @param request
	 * @param response
	 * @return
	 */
	public String updateAidStaus1(HttpServletRequest request, HttpServletResponse response){
		String rid = request.getParameter("rid");
		String aid = request.getParameter("aid");
		request.getSession().setAttribute("aid", aid);
		recordService.updateAidStaus1(rid); 
		return findByAidStatus2(request,response);
	}
	
	/**
	 * 从状态3点击完成到状态2
	 * 返回查询状态3页面
	 * @param request
	 * @param response
	 * @return
	 */
	public String updateStatus1From3(HttpServletRequest request, HttpServletResponse response){
		String rid = request.getParameter("rid");
		String aid = request.getParameter("aid");
		request.getSession().setAttribute("aid", aid);
		String date4 = String.format("%tF %<tT", new Date());
		recordService.updateStatus(rid,date4);
		return findByAidStatus3(request,response);
	}
	
	/**
	 * 不能报修的准备工作
	 * 向故障描述，报修人，报修时间
	 * @param request
	 * @param response
	 * @return
	 */
	public String updateStatus3Pre(HttpServletRequest request, HttpServletResponse response){
		String rid = request.getParameter("rid");
		request.getSession().setAttribute("SessionRid", rid);
		Record record = recordService.findRecord3(rid);
		request.setAttribute("record", record);
		return "f:/adminjsps/worker/unFix.jsp";
	}
	
	/**
	 * 修改状态致不能报修
	 */
	public String updateStatus3(HttpServletRequest request, HttpServletResponse response){
//		Map map = request.getParameterMap();
//		Record record = CommonUtils.toBean(map, Record.class);
//		
		String reason = request.getParameter("reason");
		String rid = request.getParameter("rid");
		String aid = request.getParameter("aid");
		request.getSession().setAttribute("aid", aid);
		recordService.updateStatus3(rid,reason);
		request.setAttribute("msg", "报修单已经划分到不能报修！");
		return "f:/adminjsps/worker/msg1.jsp";
	}
	
	/**
	 * 添加维修员
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException 
	 */
	public String addAdmin2(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		Map map = req.getParameterMap();
		Admin admin = CommonUtils.toBean(map, Admin.class);
		Floor floor = CommonUtils.toBean(map, Floor.class);
		Range range = CommonUtils.toBean(map, Range.class);
		Floor floor2 = floorService.findRid(floor.getFname());
		Range range2 = rangeService.findTid(range.getTname(),range.getTrange());
		/*
		 * 获取通过rname,tname,trange来获得rid,和tid
		 */
		admin.setFloor(floor2);
		admin.setRange(range2);
		admin.setIdentity("2");
		String aid = CommonUtils.uuid();
		admin.setAid(aid);
		adminService.addAdmin2(admin);
		req.setAttribute("msg", "成功增加维修员！");
		req.setAttribute("msg1", "登录帐号是："+admin.getEmail());
		req.setAttribute("msg2", "密码是:" + admin.getLoginpass());
		return "f:/adminjsps/admin/msg.jsp";
	}
	
	/**
	 * 添加维修员准备工作
	 * @param req
	 * @param resp
	 * @return
	 */
	public String addAdmin2Pre(HttpServletRequest req, HttpServletResponse resp){
		List<Range> range = rangeService.findZhonglei();
		req.setAttribute("range", range);
		return "f:/adminjsps/admin/addAdmin.jsp";
	}
	
	/**
	 * 增加管理员页面
	 * 页面调教一个数据（如行政区），则返回1，
	 * 用1去遍历所有1的数据，即行政楼，图书馆等等
	 * 可返回对应的地址
	 * 通过给b_type表设置一个ttid参数
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	public String ajaxFindChildren(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/*
		 * 1.获取pid 2.通过pid查询出所有2级分类 3.把List<Category>转换成Json，输出给客户端
		 */
		String trange = req.getParameter("trange");
		String a = 0 + "";
		switch (trange) {
		case "行政区":
			a = 1 + "";
			break;
		case "宿舍区":
			a = 2 + "";
			break;
		case "教学区":
			a = 3 + "";
			break;
		}
		List<Floor> floor = floorService.findChildren(a);
		String json = toJson(floor);
		resp.getWriter().print(json);
		return null;

	}
	
	/*
	 * json格式
	 */
	private String toJson(List<Floor> floorList){
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < floorList.size(); i++) {
			sb.append(toJson(floorList.get(i)));
			if (i < floorList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	/*
	 * json格式
	 */
	private Object toJson(Floor floor) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"fid\"").append(":").append("\"").append(floor.getFid()).append("\"");
		sb.append(",");
		sb.append("\"fname\"").append(":").append("\"").append(floor.getFname()).append("\"");
		sb.append("}");
		return sb.toString();
	}
}

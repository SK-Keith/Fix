package cn.BX.Users.record.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.BX.base.BaseServlet;
import cn.BX.pager.PageBean;
import cn.BX.Admin.admin.domain.Admin;
import cn.BX.Users.floor.domain.Floor;
import cn.BX.Users.record.domain.Record;
import cn.BX.Users.record.service.RecordService;

/**
 * Servlet implementation class RecordAddServlet
 */
public class RecordAddServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private RecordService recordService = new RecordService();
       
	/**
	 * 按uid查询
	 * @param req
	 * @param resp
	 * @return
	 */
    public String loadTable(HttpServletRequest req, HttpServletResponse resp){
    	/*
		 * 1. 得到pc：如果页面传递，使用页面的，如果没传，pc=1
		 */
		int pc = getPc(req);
		/*
		 * 2. 得到url：...
		 */
		String url = getUrl(req);
		
		String uid = req.getParameter("uid");
		
		PageBean<Record> pb = recordService.loadTable(uid, pc);
		
    	pb.setUrl(url);
    	req.setAttribute("pb", pb);
    	return "f:/jsps/student/main_baodan.jsp";
    }
    
   /*
    * 返回写的url地址
    */
	private String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();
		/*
		 * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
		 */
		int index = url.lastIndexOf("&pc=");
		if(index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}

	/*
	 * 获取当前页码 
	 */
	private int getPc(HttpServletRequest req) {
		int pc = 1;
		String param = req.getParameter("pc");
		if(param != null && !param.trim().isEmpty()){
			try{
				pc = Integer.parseInt(param);
			}catch(RuntimeException e){}
		}
		return pc;
	}
	
	/**
	 * 加载rid，返回到evaluation.jsp
	 * @param req
	 * @param resp
	 * @return
	 */
	public String loadRid(HttpServletRequest req, HttpServletResponse resp){
		String rid = req.getParameter("rid");
		req.setAttribute("rid", rid);
		return "f:/jsps/student/evaluation.jsp";
	}
	
	/**
	 * 获取uid，返回到main_msg.jsp
	 * @param req
	 * @param resp
	 * @return
	 */
	public String findAdmin(HttpServletRequest req, HttpServletResponse resp){
		String rid = req.getParameter("rid");
		Admin admin = recordService.findAdmin(rid);
		req.getSession().setAttribute("sessionRid", rid);
		req.setAttribute("rid", rid);
		req.setAttribute("admin", admin);
		return "f:/jsps/student/main_msg.jsp";
	}
	
	/**
	 * 页面自动获取该表单id的所有信息
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	public String ajaxfindRecord(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		String rid = req.getParameter("rid");
		List<Record> record = recordService.findRecord(rid);
		String json = toJson(record);
		resp.getWriter().print(json);
		return null;
	}
	
	/*
	 * json格式1
	 */
	private String toJson(List<Record> recordList){
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < recordList.size(); i++) {
			sb.append(toJson(recordList.get(i)));
			if (i < recordList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}


	/*
	 * json格式2
	 */
	private Object toJson(Record record) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"rid\"").append(":").append("\"").append(record.getRid()).append("\"");
		sb.append(",");
		sb.append("\"address\"").append(":").append("\"").append(record.getAddress()).append("\"");
		sb.append(",");
		sb.append("\"details\"").append(":").append("\"").append(record.getDetails()).append("\"");
		sb.append(",");
		sb.append("\"date1\"").append(":").append("\"").append(record.getDate1()).append("\"");
		sb.append(",");
		sb.append("\"status\"").append(":").append("\"").append(record.getStatus()).append("\"");
		sb.append("}");
		return sb.toString();
	}
	
	/**
	 * 更新评价
	 * @param req
	 * @param resp
	 * @return
	 */
	public String updateEvaluation(HttpServletRequest req, HttpServletResponse resp){
		String rid = req.getParameter("rid");
		String evaluation = req.getParameter("evaluation");
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date6 = formatter.format(currentTime);//时间转为字符串
//		req.getSession().setAttribute("SessionEvaluation", evaluation);传评价不能用session，页面ajax传评价吧
		recordService.updateEvaluation(evaluation,rid,date6);
		return loadTable(req, resp);
	}
	
	/*
	 * 取消报修
	 */
	public String cancel(HttpServletRequest req, HttpServletResponse resp){
		String rid = req.getParameter("rid");
		String uid = (String) req.getSession().getAttribute("sessionUid");
		req.getSession().setAttribute("uid", uid);
		recordService.deleteRecord(rid);
		return loadTable(req,resp);
	}
	
	/**
	 * 页面自动加载，判断是否超时，若超时，则修改状态致超时
	 * 跟下面方法重复了
	 * @param req
	 * @param resp
	 * @return
	 */
//	public String ajaxUpdateStatus4(HttpServletRequest req, HttpServletResponse resp){
//		String rid = req.getParameter("rid");
//		recordService.updateStatus4();
//		return null;
//	}
	
	/**
	 * 用户催修，则立即将报单划分为超时表单
	 * @param req
	 * @param resp
	 * @return
	 */
	public String ajaxUpdateStatus4Cui(HttpServletRequest req, HttpServletResponse resp){
		String rid = req.getParameter("rid");
//		Date date = new Date();SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String date6 = formatter.format(date);
		recordService.updateStatus4Cui(rid);
		return null;
	}
	
	/**
	 * ajax自动加载判断是否超时，进行判断
	 * @throws IOException 
	 */
	public String ajaxJudgeTime(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		recordService.updateStatus4();
		resp.getWriter().print(" ");
		return null;
	}
}

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
	 * ��uid��ѯ
	 * @param req
	 * @param resp
	 * @return
	 */
    public String loadTable(HttpServletRequest req, HttpServletResponse resp){
    	/*
		 * 1. �õ�pc�����ҳ�洫�ݣ�ʹ��ҳ��ģ����û����pc=1
		 */
		int pc = getPc(req);
		/*
		 * 2. �õ�url��...
		 */
		String url = getUrl(req);
		
		String uid = req.getParameter("uid");
		
		PageBean<Record> pb = recordService.loadTable(uid, pc);
		
    	pb.setUrl(url);
    	req.setAttribute("pb", pb);
    	return "f:/jsps/student/main_baodan.jsp";
    }
    
   /*
    * ����д��url��ַ
    */
	private String getUrl(HttpServletRequest req) {
		String url = req.getRequestURI() + "?" + req.getQueryString();
		/*
		 * ���url�д���pc��������ȡ��������������ǾͲ��ý�ȡ��
		 */
		int index = url.lastIndexOf("&pc=");
		if(index != -1) {
			url = url.substring(0, index);
		}
		return url;
	}

	/*
	 * ��ȡ��ǰҳ�� 
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
	 * ����rid�����ص�evaluation.jsp
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
	 * ��ȡuid�����ص�main_msg.jsp
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
	 * ҳ���Զ���ȡ�ñ�id��������Ϣ
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
	 * json��ʽ1
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
	 * json��ʽ2
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
	 * ��������
	 * @param req
	 * @param resp
	 * @return
	 */
	public String updateEvaluation(HttpServletRequest req, HttpServletResponse resp){
		String rid = req.getParameter("rid");
		String evaluation = req.getParameter("evaluation");
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date6 = formatter.format(currentTime);//ʱ��תΪ�ַ���
//		req.getSession().setAttribute("SessionEvaluation", evaluation);�����۲�����session��ҳ��ajax�����۰�
		recordService.updateEvaluation(evaluation,rid,date6);
		return loadTable(req, resp);
	}
	
	/*
	 * ȡ������
	 */
	public String cancel(HttpServletRequest req, HttpServletResponse resp){
		String rid = req.getParameter("rid");
		String uid = (String) req.getSession().getAttribute("sessionUid");
		req.getSession().setAttribute("uid", uid);
		recordService.deleteRecord(rid);
		return loadTable(req,resp);
	}
	
	/**
	 * ҳ���Զ����أ��ж��Ƿ�ʱ������ʱ�����޸�״̬�³�ʱ
	 * �����淽���ظ���
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
	 * �û����ޣ�����������������Ϊ��ʱ��
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
	 * ajax�Զ������ж��Ƿ�ʱ�������ж�
	 * @throws IOException 
	 */
	public String ajaxJudgeTime(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		recordService.updateStatus4();
		resp.getWriter().print(" ");
		return null;
	}
}

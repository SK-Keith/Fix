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
	 * ά��Ա������������
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
	 * ����ά��Աid��δ�޸��Ĳ�ѯ
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
	 * ����ά��Ա��ҳ����ʱ��
	 * ����
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
	 * ����ά��Աid�����޸��Ĳ�ѯ
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
	 * ����ά��Աid���ݻ��޸��Ĳ�ѯ
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
	 * ����ά��Աid�ͳ�ʱ�޸��Ĳ�ѯ
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
	 * ά��Ա�鿴���˶��Լ�������
	 * ֻ���Լ�����ʱ����
	 * findEvaluation
	 */
//	public String findEvaluation(HttpServletRequest request, HttpServletResponse response){
//		String aid = request.getParameter("aid");
//		List<Record> record = recordService.findByAidStatus2(aid);
//		request.setAttribute("recordList", record);
//		return "f:/adminjsps/worker/index.jsp";
//	}

	/**
	 * ����δ�޸��ı������޸���ͬʱ��¼���ʱ��
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
	 * ���³�ʱ�޸��ı������޸���ͬʱ��¼���ʱ��
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
	 * ά��Ա������ɵı����ص�δ�޸�״̬����ֹʦ�������ύ
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
	 * ��״̬3�����ɵ�״̬2
	 * ���ز�ѯ״̬3ҳ��
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
	 * ���ܱ��޵�׼������
	 * ����������������ˣ�����ʱ��
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
	 * �޸�״̬�²��ܱ���
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
		request.setAttribute("msg", "���޵��Ѿ����ֵ����ܱ��ޣ�");
		return "f:/adminjsps/worker/msg1.jsp";
	}
	
	/**
	 * ���ά��Ա
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
		 * ��ȡͨ��rname,tname,trange�����rid,��tid
		 */
		admin.setFloor(floor2);
		admin.setRange(range2);
		admin.setIdentity("2");
		String aid = CommonUtils.uuid();
		admin.setAid(aid);
		adminService.addAdmin2(admin);
		req.setAttribute("msg", "�ɹ�����ά��Ա��");
		req.setAttribute("msg1", "��¼�ʺ��ǣ�"+admin.getEmail());
		req.setAttribute("msg2", "������:" + admin.getLoginpass());
		return "f:/adminjsps/admin/msg.jsp";
	}
	
	/**
	 * ���ά��Ա׼������
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
	 * ���ӹ���Աҳ��
	 * ҳ�����һ�����ݣ��������������򷵻�1��
	 * ��1ȥ��������1�����ݣ�������¥��ͼ��ݵȵ�
	 * �ɷ��ض�Ӧ�ĵ�ַ
	 * ͨ����b_type������һ��ttid����
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	public String ajaxFindChildren(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		/*
		 * 1.��ȡpid 2.ͨ��pid��ѯ������2������ 3.��List<Category>ת����Json��������ͻ���
		 */
		String trange = req.getParameter("trange");
		String a = 0 + "";
		switch (trange) {
		case "������":
			a = 1 + "";
			break;
		case "������":
			a = 2 + "";
			break;
		case "��ѧ��":
			a = 3 + "";
			break;
		}
		List<Floor> floor = floorService.findChildren(a);
		String json = toJson(floor);
		resp.getWriter().print(json);
		return null;

	}
	
	/*
	 * json��ʽ
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
	 * json��ʽ
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

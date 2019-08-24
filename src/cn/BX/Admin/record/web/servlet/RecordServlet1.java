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
	 * ��ѯ״̬Ϊ1�ı���Ϣ
	 * ����Ա��ѯ����δ�޸�����Ϣ
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
	 * ��ѯ״̬Ϊ2�ı���Ϣ
	 * ����Ա��ѯ�������޸�����Ϣ
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
	 *  ��ѯ״̬Ϊ2�ı���Ϣ
	 * ����Ա��ѯ�������޸�����Ϣ
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
	 * ����Ա��ѯ�ݻ��޸��ı�
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
	 * ����Ա�鿴��ʱ��������
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
	 * ��ʱ����
	 * ���ؼ�����Ϣ�Ļ�����Ϣ
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
	 * ɾ����ĳһ��δ�޸���������
	 * ��������ajaxģʽ���Ͳ��õ�����ô�෽����ֻ��Ϊ���ڲ�ͬҳ��ȥ��ʾ
	 * ���ص�δ�޸�ҳ��
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
	 * ɾ����ĳһ�����޸��ı�����
	 * ���ص����޸�ҳ��
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
	 * ɾ����ĳһ����ʱ�ı�����
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
	 * ����Աҳ����س�ʱ��������
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
	 * ����Աҳ�����δ�޸��ı�����
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
	 * ά��Աҳ����س�ʱ�ı�����
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
	 * ά��Աҳ�����δ�޸��ı�����
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
	 * json��ʽ1
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
	 * json��ʽ2
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
	 * ajax����ҳ���û����ԣ�������ǽ
	 * ����
	 * ���ҷ���ʱ��
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
	 * ����ҳ���û����ԣ�������ǽ,״̬Ϊ2���������ݷǿ�
	 * ����Ա��ά��Ա���ǵ������
	 * ���ҷ���ʱ��
	 */
	public String findByEvaStatus2(HttpServletRequest request, HttpServletResponse response){
		String aid = request.getParameter("aid");
		request.getSession().setAttribute("SessionAid", aid);
		List<Record> record = recordService.findByEvaStatus2();
		request.setAttribute("recordList", record);
		return "f:/adminjsps/admin/index.jsp";
	}
	
	/**
	 * �����������ʹ�ø÷���
	 * 1.����Ա��¼������ҳҳ�棬�Զ�ִ��,ajax
	 * 2.ά��Ա��¼����δ�޸�ҳ�棬�Զ�ִ��ajax
	 * 3.�û�����鿴����Ϣʱ���Զ�ִ��ajax
	 * ajax�Զ������ж��Ƿ�ʱ�������ж�
	 * @throws IOException 
	 */
	public String ajaxJudgeTime(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		recordService.updateStatus4();
		resp.getWriter().print("");
		return null;
	}
	
}

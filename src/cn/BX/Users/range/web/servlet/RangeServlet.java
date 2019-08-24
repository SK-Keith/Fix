package cn.BX.Users.range.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.BX.base.BaseServlet;
import cn.BX.Users.floor.domain.Floor;
import cn.BX.Users.floor.service.FloorService;
import cn.BX.Users.range.domain.Range;
import cn.BX.Users.range.service.RangeService;

/**
 * Servlet implementation class RangeServlet
 */
public class RangeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	RangeService rangeService = new RangeService();
	FloorService floorService = new FloorService();

	/**
	 * ������������ˮ����Щ��Ϣ
	 * @param req
	 * @param resp
	 * @return
	 */
	public String findRange(HttpServletRequest req, HttpServletResponse resp) {
		List<Range> range = rangeService.findZhonglei();
		req.setAttribute("range", range);
		return "f:/jsps/student/main.jsp";
	}

	/**
	 * ��������¥��
	 * @param req
	 * @param resp
	 * @return
	 */
	public String findName(HttpServletRequest req, HttpServletResponse resp) {
		List<Floor> floor = floorService.findName();
		req.setAttribute("floor", floor);
		return findRange(req, resp);
	}

	/**
	 * �û�ҳ��
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
	 * json��ʽ1
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
	 * json��ʽ2
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

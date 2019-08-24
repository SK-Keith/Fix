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
	 * 返回行政区，水电这些信息
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
	 * 返回宿舍楼号
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
	 * 用户页面
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
	 * json格式1
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
	 * json格式2
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

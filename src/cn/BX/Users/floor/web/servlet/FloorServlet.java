package cn.BX.Users.floor.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.BX.base.BaseServlet;
import cn.BX.Users.floor.domain.Floor;
import cn.BX.Users.floor.service.FloorService;

/**
 * Servlet implementation class FloorServlet
 */
public class FloorServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private FloorService floorService = new FloorService();
	
	/**
	 * 返回各个楼栋的名称
	 * 
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	public String findName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		List<Floor> fname = floorService.findName();
		req.setAttribute("fname", fname);
		return "f:/jsps/student/main_xiugai.jsp";
	}

}

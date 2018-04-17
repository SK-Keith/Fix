package cn.BX.range.web.servlet;

import cn.BX.base.BaseServlet;
import cn.BX.range.domain.Range;
import cn.BX.range.service.RangeService;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class RangeServlet
 */

public class RangeServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	RangeService rangeService = new RangeService();
	/**
	 * 页面使用ajax返回父分类对应的子分类
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
	public String ajaxFindZhonglei(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.println("ajaxFindZhonglei");
		List<Range> zhonglei = rangeService.findZhonglei();
		String json = toJson(zhonglei);
		resp.getWriter().print(json);
		return null;
	}

	// [{"wid":"fdsafdsa", "wtype":"fdsafdas"}, {"wid":"fdsafdsa", "wtype":"fdsafdas"}]
	private String toJson(List<Range> rangeList) {
		StringBuilder sb = new StringBuilder("[");
		for(int i =0; i < rangeList.size(); i++){
			sb.append(toJson(rangeList.get(i)));
			if(i < rangeList.size() - 1){
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	// {"wid":"fdsafdsa", "wtype":"fdsafdas"}
	private String toJson(Range range) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"wid\"").append(":").append("\"").append(range.getWid())
		.append("\"");
		sb.append(",");
		sb.append("\"wtype\"").append(":").append("\"").append(range.getWtype())
		.append("\"");
		sb.append("}");
		return sb.toString();
	}

}

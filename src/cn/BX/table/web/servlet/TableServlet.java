package cn.BX.table.web.servlet;

import cn.BX.base.BaseServlet;
import cn.BX.table.domain.Table;
import cn.BX.table.service.TableService;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TableServlet
 */
@WebServlet("/TableServlet")
public class TableServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	TableService tableService = new TableService();

	
}

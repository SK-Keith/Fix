package cn.BX.table.web.servlet;

import cn.BX.base.BaseServlet;
import cn.BX.floor.domain.Floor;
import cn.BX.project.domain.Project;
import cn.BX.table.domain.Table;
import cn.BX.tool.CommonUtils;
import cn.BX.user.domain.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class TableServlet
 */
@WebServlet("/TableServlet")
public class AddTableServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		//1.创建工具
		FileItemFactory factory = new DiskFileItemFactory();
		//2.创建解析器对象
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(100*1024*1024);
		//3.解析request得到List<FileItem>
		List<FileItem> fileItemList = null;
		try {
			sfu.parseRequest(req);
		} catch (FileUploadException e) {
			error("上传文件超过100KB", req, resp);
			return ;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		for(FileItem fileItem : fileItemList){
			if(fileItem.isFormField()){
				map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
			}
		}
		Table table = CommonUtils.toBean(map, Table.class);
		User user = CommonUtils.toBean(map, User.class);
		Project project = CommonUtils.toBean(map, Project.class);
		Floor floor = CommonUtils.toBean(map, Floor.class);
		
		table.setUser(user);
		table.setProject(project);
		table.setFloor(floor);
		
		
		
	}


	private void error(String msg, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("/jsps/student/main_baodan.jsp").forward(req, resp);
	}

}

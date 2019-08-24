package cn.BX.Users.record.web.servlet;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.BX.Admin.admin.domain.Admin;
import cn.BX.Users.JudgeUid.judgeUid;
import cn.BX.Users.floor.domain.Floor;
import cn.BX.Users.range.domain.Range;
import cn.BX.Users.range.service.RangeService;
import cn.BX.Users.record.domain.Record;
import cn.BX.Users.record.service.RecordService;
import cn.BX.time.getNextDay;
import cn.BX.tool.CommonUtils;
import cn.BX.user.domain.User;

/**
 * Servlet implementation class RecordServlet
 */
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RangeService rangeService = new RangeService();
	private judgeUid j = new judgeUid();
  
	/**
	 * 上传
	 * 提交表单
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 1.commons-fileupload的上传三部
		 */
		// 创建工具
		FileItemFactory factory = new DiskFileItemFactory();
		/*
		 * 2.创建解析器对象
		 */
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(10 * 1024 * 1024);// 设置单个上传的文件上限为80KB
		/*
		 * 3.解析request得到List<FileItem>
		 */
		List<FileItem> fileItemList = null;
		try {
			fileItemList = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			// 如果出现这个异步，则说明是单个文件的大小超出了80KB
			error("上传文件超出了5M", request, response);
			return ;
		}

		/*
		 * 4.把List<FileItem> 封装到Book对象中 
		 * 4.1  首先把“普通表单字段”放到一个Map中，再把Map转换成Book和Category对象，再建立两者的关系
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		for (FileItem fileItem : fileItemList) {
			if (fileItem.isFormField()) {// 如果是普通字段
				map.put(fileItem.getFieldName(), fileItem.getString("UTF-8"));
			}
		}
		
		Record record = CommonUtils.toBean(map, Record.class);
		Range range = CommonUtils.toBean(map, Range.class);
		User user = CommonUtils.toBean(map, User.class);
		Floor floor = CommonUtils.toBean(map, Floor.class);
		range.setTid(CommonUtils.uuid());
		record.setRange(range);
		record.setUser(user);
		record.setFloor(floor);
		record.setStatus(1);
		record.setDate1(String.format("%tF %<tT", new Date()));
		record.setNumber(CommonUtils.generateShortUuid());
		record.setRid(CommonUtils.uuid());
		
		/*
		 * 给date2赋值，存放的是第二天的时间，即如果过了这个时间，表单自动进入超时表单
		 */
		getNextDay gn = new getNextDay();
		String date1 = gn.getNextDay(new Date());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date date2 = formatter.parse(date1, pos);
		record.setDate5(date2);
//		System.out.println("date2 = " +date2);
		
		Admin admin = j.getResult(record);
		record.setAdmin(admin);
//		record.getRange().getTname(), record.getRange().getTrange(), record.getFloor().getFname()
//		record.setAdmin(record.getRange().getTname());
		/*
		 * 4.2把上传的图片保存起来 > 获取文件名，截取之 > 给文件添加前缀，使用uuid前缀，避免文件同名现象 >
		 * 校验文件的扩展名，只能是jpg > 校验图片的尺寸 > 指定图片的保存路径，需要用ServletContext#getRealPath()
		 * > 保存之 > 把图片的路径设置给Book对象
		 */
		// 获取文件名
		FileItem fileItem = fileItemList.get(8);// 获取大图
		String filename = fileItem.getName();
		if(filename.length() != 0){
			// 截取文件名，因为部分浏览器上传的是绝对路径
			int index = filename.lastIndexOf("\\");
			if (index != -1) {
				filename = filename.substring(index + 1);
			}
			// 给文件名添加uuid前缀，避免文件同名现象
//			Date date = new Date();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String dateString = formatter.format(date);//时间转为字符串
//			filename = CommonUtils.uuid() + "_" + dateString;
			filename = CommonUtils.uuid() + "_" + filename;
			// 校验文件名称的扩展名
			if ((!filename.toLowerCase().endsWith(".jpg"))&&(!filename.toLowerCase().endsWith(".png"))) {
				error("上传的图片扩展名必须是JPG或PNG", request, response);
	//			request.setAttribute("record", record);
				return ;
			}else{
				/*
				 * 保存图片 1.获取真实路径
				 */
				String savepath = this.getServletContext().getRealPath("/img");
//				System.out.println("savapath:" + savepath);
				/*
				 * 2.创建目标文件
				 */
				File destFile = new File(savepath, filename);
				/*
				 * 3.保存文件
				 */
				try {
					fileItem.write(destFile);// 它会把临时文件重定向到指定的路径，再删除临时文件
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				record.setImage("img/" + filename);
				System.out.println("filename"+filename);
			}
		}
		RecordService recordService = new RecordService();
		recordService.add(record);
		
		request.setAttribute("msg", "报修成功");
		request.setAttribute("num", record.getNumber());
		request.getRequestDispatcher("/jsps/student/msg.jsp").forward(request, response);
	}

	/**
	 * 保存错误信息
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	private void error(String msg, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("msg", msg);
		List<Range> range = rangeService.findZhonglei();
		req.setAttribute("range", range);
		req.getRequestDispatcher("/jsps/student/main.jsp").forward(req, resp);
	}
	
	
}

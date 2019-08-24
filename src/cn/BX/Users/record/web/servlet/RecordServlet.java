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
	 * �ϴ�
	 * �ύ��
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 1.commons-fileupload���ϴ�����
		 */
		// ��������
		FileItemFactory factory = new DiskFileItemFactory();
		/*
		 * 2.��������������
		 */
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setFileSizeMax(10 * 1024 * 1024);// ���õ����ϴ����ļ�����Ϊ80KB
		/*
		 * 3.����request�õ�List<FileItem>
		 */
		List<FileItem> fileItemList = null;
		try {
			fileItemList = sfu.parseRequest(request);
		} catch (FileUploadException e) {
			// �����������첽����˵���ǵ����ļ��Ĵ�С������80KB
			error("�ϴ��ļ�������5M", request, response);
			return ;
		}

		/*
		 * 4.��List<FileItem> ��װ��Book������ 
		 * 4.1  ���Ȱѡ���ͨ���ֶΡ��ŵ�һ��Map�У��ٰ�Mapת����Book��Category�����ٽ������ߵĹ�ϵ
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		for (FileItem fileItem : fileItemList) {
			if (fileItem.isFormField()) {// �������ͨ�ֶ�
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
		 * ��date2��ֵ����ŵ��ǵڶ����ʱ�䣬������������ʱ�䣬���Զ����볬ʱ��
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
		 * 4.2���ϴ���ͼƬ�������� > ��ȡ�ļ�������ȡ֮ > ���ļ����ǰ׺��ʹ��uuidǰ׺�������ļ�ͬ������ >
		 * У���ļ�����չ����ֻ����jpg > У��ͼƬ�ĳߴ� > ָ��ͼƬ�ı���·������Ҫ��ServletContext#getRealPath()
		 * > ����֮ > ��ͼƬ��·�����ø�Book����
		 */
		// ��ȡ�ļ���
		FileItem fileItem = fileItemList.get(8);// ��ȡ��ͼ
		String filename = fileItem.getName();
		if(filename.length() != 0){
			// ��ȡ�ļ�������Ϊ����������ϴ����Ǿ���·��
			int index = filename.lastIndexOf("\\");
			if (index != -1) {
				filename = filename.substring(index + 1);
			}
			// ���ļ������uuidǰ׺�������ļ�ͬ������
//			Date date = new Date();
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String dateString = formatter.format(date);//ʱ��תΪ�ַ���
//			filename = CommonUtils.uuid() + "_" + dateString;
			filename = CommonUtils.uuid() + "_" + filename;
			// У���ļ����Ƶ���չ��
			if ((!filename.toLowerCase().endsWith(".jpg"))&&(!filename.toLowerCase().endsWith(".png"))) {
				error("�ϴ���ͼƬ��չ��������JPG��PNG", request, response);
	//			request.setAttribute("record", record);
				return ;
			}else{
				/*
				 * ����ͼƬ 1.��ȡ��ʵ·��
				 */
				String savepath = this.getServletContext().getRealPath("/img");
//				System.out.println("savapath:" + savepath);
				/*
				 * 2.����Ŀ���ļ�
				 */
				File destFile = new File(savepath, filename);
				/*
				 * 3.�����ļ�
				 */
				try {
					fileItem.write(destFile);// �������ʱ�ļ��ض���ָ����·������ɾ����ʱ�ļ�
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				record.setImage("img/" + filename);
				System.out.println("filename"+filename);
			}
		}
		RecordService recordService = new RecordService();
		recordService.add(record);
		
		request.setAttribute("msg", "���޳ɹ�");
		request.setAttribute("num", record.getNumber());
		request.getRequestDispatcher("/jsps/student/msg.jsp").forward(request, response);
	}

	/**
	 * ���������Ϣ
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

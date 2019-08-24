package cn.BX.Admin.chat.web.servlet;

import cn.BX.Admin.admin.domain.Admin;
import cn.BX.Admin.admin.service.AdminService;
import cn.BX.Admin.chat.domain.Chat;
import cn.BX.Admin.chat.service.ChatService;
import cn.BX.Admin.record.web.servlet.RecordServlet1;
import cn.BX.Users.record.domain.Record;
import cn.BX.base.BaseServlet;
import cn.BX.time.getTimeFormatText;
import cn.BX.tool.CommonUtils;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class chatServlet
 */
@WebServlet("/admin/ChatServlet")
public class ChatServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private ChatService chatService = new ChatService();
	private AdminService adminService = new AdminService();
	private RecordServlet1 recordServlet1 = new RecordServlet1();
	private getTimeFormatText g = new getTimeFormatText();
	
	/**
	 * 1.����������ݵȻ�����Ϣ
	 * 2.��ajax����ҳ����ʾ���е���������
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
    public String ChatServlet(HttpServletRequest req,HttpServletResponse resp) throws IOException{
    	/*
    	 * 1.��ȡҳ�����Ϣ
    	 */
    	String content = req.getParameter("content");
    	String aid = req.getParameter("aid");
    	Admin admin = adminService.findAdmin(aid);
    	
    	/*
    	 * 2.��ֵ
    	 */
    	Map map = req.getParameterMap();
    	Chat chat = CommonUtils.toBean(map, Chat.class);
    	chat.setCid(CommonUtils.uuid());
    	chat.setContent(content);
    	chat.setAdmin(admin);
    	
    	/*
    	 * 3.��ȡ��ǰʱ��
    	 * ��date��ֵ�������淢����Ϣ��ʱ��
    	 */
    	Date date = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);//ʱ��תΪ�ַ���
    	chat.setDate(dateString);
    	
    	/*
    	 * 4.�����Լ�д��һ������
    	 * ���ص�ǰʱ���뷢����Ϣ��ʱ���
    	 * �Լ���ǰ������Сʱǰ����������ʽ����
    	 */
    	String date5 = g.getTimeFormatText(date);
    	
    	/*
    	 * 5.ִ����ӷ�����Ϣ�����
    	 * ����id,�����ˣ����ݣ�������Ϣ��ʱ��
    	 */
    	chatService.addTable(chat);
    	
    	/*
    	 * 6.�������б����������Ϣ������json��ʽ����
    	 */
    	List<Chat> chatList = chatService.findAllChat();
    	String json = toJson(chatList);
    	resp.getWriter().print(json);
		return null;
    }
    
    /**
     * ��дһ��������һ���ķ�����������������ǹ�.���������Ϣ���������й���Ա����.+ά.����Ϣ
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    public String ChatServlet2(HttpServletRequest req,HttpServletResponse resp) throws IOException{
    	
    	String content = req.getParameter("content");
    	String aid = req.getParameter("aid");
    	Admin admin = adminService.findAdmin2(aid);//���õ��ǲ�ѯά��Ա��������Ϣ
    	
    	Map map = req.getParameterMap();
    	Chat chat = CommonUtils.toBean(map, Chat.class);
    	chat.setCid(CommonUtils.uuid());
    	chat.setContent(content);
    	chat.setAdmin(admin);
    	
    	Date date = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);//ʱ��תΪ�ַ���
    	chat.setDate(dateString);
    	String date5 = g.getTimeFormatText(date);
    	
    	chatService.addTable(chat);
    	
    	List<Chat> chatList = chatService.findAllChat();
    	String json = toJson(chatList);
    	resp.getWriter().print(json);
		return null;
    }
    
    /**
     * ҳ���Զ��������е������¼
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    public String ajaxfindAllChat(HttpServletRequest req,HttpServletResponse resp) throws IOException{
    	List<Chat> chatList = chatService.findAllChat();
    	String json = toJson(chatList);
    	resp.getWriter().print(json);
		return null;
    }
    
    /*
     * ҳ�淵��json��ʽ��string��������
     * ����1������
     */
    private String toJson(Chat chat) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"cid\"").append(":").append("\"").append(chat.getCid()).append("\"");
		sb.append(",");
		sb.append("\"name\"").append(":").append("\"").append(chat.getName()).append("\"");
		sb.append(",");
		sb.append("\"content\"").append(":").append("\"").append(chat.getContent()).append("\"");
		sb.append(",");
		sb.append("\"date5\"").append(":").append("\"").append(chat.getDate5()).append("\"");
		sb.append("}");
		return sb.toString();
	}
	
    /*
     * ҳ�淵��json��ʽ��string��������
     * ����2�����������õ���
     */
	private String toJson(List<Chat> chatList) {
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0; i < chatList.size(); i++) {
			sb.append(toJson(chatList.get(i)));
			if(i < chatList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}

}

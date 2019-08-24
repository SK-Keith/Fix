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
	 * 1.添加留言内容等基本信息
	 * 2.用ajax返回页面显示所有的聊天内容
	 * @param req
	 * @param resp
	 * @return
	 * @throws IOException
	 */
    public String ChatServlet(HttpServletRequest req,HttpServletResponse resp) throws IOException{
    	/*
    	 * 1.获取页面的信息
    	 */
    	String content = req.getParameter("content");
    	String aid = req.getParameter("aid");
    	Admin admin = adminService.findAdmin(aid);
    	
    	/*
    	 * 2.赋值
    	 */
    	Map map = req.getParameterMap();
    	Chat chat = CommonUtils.toBean(map, Chat.class);
    	chat.setCid(CommonUtils.uuid());
    	chat.setContent(content);
    	chat.setAdmin(admin);
    	
    	/*
    	 * 3.获取当前时间
    	 * 给date赋值，即保存发送信息的时间
    	 */
    	Date date = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);//时间转为字符串
    	chat.setDate(dateString);
    	
    	/*
    	 * 4.调用自己写的一个方法
    	 * 返回当前时间与发送信息的时间差
    	 * 以几天前，几个小时前。。。等形式返回
    	 */
    	String date5 = g.getTimeFormatText(date);
    	
    	/*
    	 * 5.执行添加发送信息的语句
    	 * 保存id,发送人，内容，发送信息的时间
    	 */
    	chatService.addTable(chat);
    	
    	/*
    	 * 6.查找所有保存的聊天信息，并以json格式返回
    	 */
    	List<Chat> chatList = chatService.findAllChat();
    	String json = toJson(chatList);
    	resp.getWriter().print(json);
		return null;
    }
    
    /**
     * 再写一个跟上面一样的方法，差别在于上面是管.进行添加信息，返回所有管理员（管.+维.）信息
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    public String ChatServlet2(HttpServletRequest req,HttpServletResponse resp) throws IOException{
    	
    	String content = req.getParameter("content");
    	String aid = req.getParameter("aid");
    	Admin admin = adminService.findAdmin2(aid);//调用的是查询维修员的所有信息
    	
    	Map map = req.getParameterMap();
    	Chat chat = CommonUtils.toBean(map, Chat.class);
    	chat.setCid(CommonUtils.uuid());
    	chat.setContent(content);
    	chat.setAdmin(admin);
    	
    	Date date = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);//时间转为字符串
    	chat.setDate(dateString);
    	String date5 = g.getTimeFormatText(date);
    	
    	chatService.addTable(chat);
    	
    	List<Chat> chatList = chatService.findAllChat();
    	String json = toJson(chatList);
    	resp.getWriter().print(json);
		return null;
    }
    
    /**
     * 页面自动加载所有的聊天记录
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
     * 页面返回json格式的string类型数据
     * 过程1，单条
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
     * 页面返回json格式的string类型数据
     * 过程2，多条，调用单条
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

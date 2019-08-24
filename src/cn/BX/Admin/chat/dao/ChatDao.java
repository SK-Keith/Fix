package cn.BX.Admin.chat.dao;

import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.BX.Admin.admin.domain.Admin;
import cn.BX.Admin.chat.domain.Chat;
import cn.BX.jdbc.TxQueryRunner;
import cn.BX.time.getTimeFormatText;
import cn.BX.tool.CommonUtils;

public class ChatDao {
	private QueryRunner qr = new TxQueryRunner();
	
	/*
	 * 添加留言信息
	 */
	public void addTable(Chat chat) throws SQLException {
		String sql = "insert into b_chat(cid,name,content,date) values(?,?,?,?)";
		Object params[] = {chat.getCid(),chat.getAdmin().getName(),chat.getContent(),chat.getDate()};
		qr.update(sql,params);	
	}

	/*
	 * 加载相关的聊天信息，根据发送信息的时间排序
	 * 界面是上面的信息的时间比较靠前（如10天前），下面的信息的时间比较靠后（如9天前）
	 */
	public List<Chat> findAllChat() throws SQLException {
		String sql = "select cid,name,content,date from b_chat order by date ";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
		return toChatList(mapList);
	}

	/*
	 * 返回所有每条的对比后的时间的整个chat
	 */
	private List<Chat> toChatList(List<Map<String, Object>> mapList) {
		List<Chat> ChatList = new ArrayList<Chat>();
		for(Map<String,Object> map: mapList){
			Chat chat = toChat(map);
			ChatList.add(chat);
		}
		return ChatList;
	}

	/*
	 * 返回每条的对比后的时间的整个chat
	 */
	private Chat toChat(Map<String, Object> map) {
		if(map == null || map.size() == 0) return null;
		
		Chat chat = CommonUtils.toBean(map, Chat.class);
		Admin admin = CommonUtils.toBean(map, Admin.class);//只有发送人的名字信息
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		/*
		 * 拿聊天的发送时间同当下时间比较返回几天前等信息
		 * 说明一下，返回的date5每次都和系统时间进行比较，所以date5会不断更新
		 */
		Date strtodate = formatter.parse(chat.getDate(),pos);
		String date5 = getTimeFormatText.getTimeFormatText(strtodate);
		chat.setDate5(date5);
		chat.setAdmin(admin);
		return chat;
	}
}

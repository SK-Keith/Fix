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
	 * ���������Ϣ
	 */
	public void addTable(Chat chat) throws SQLException {
		String sql = "insert into b_chat(cid,name,content,date) values(?,?,?,?)";
		Object params[] = {chat.getCid(),chat.getAdmin().getName(),chat.getContent(),chat.getDate()};
		qr.update(sql,params);	
	}

	/*
	 * ������ص�������Ϣ�����ݷ�����Ϣ��ʱ������
	 * �������������Ϣ��ʱ��ȽϿ�ǰ����10��ǰ�����������Ϣ��ʱ��ȽϿ�����9��ǰ��
	 */
	public List<Chat> findAllChat() throws SQLException {
		String sql = "select cid,name,content,date from b_chat order by date ";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
		return toChatList(mapList);
	}

	/*
	 * ��������ÿ���ĶԱȺ��ʱ�������chat
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
	 * ����ÿ���ĶԱȺ��ʱ�������chat
	 */
	private Chat toChat(Map<String, Object> map) {
		if(map == null || map.size() == 0) return null;
		
		Chat chat = CommonUtils.toBean(map, Chat.class);
		Admin admin = CommonUtils.toBean(map, Admin.class);//ֻ�з����˵�������Ϣ
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		/*
		 * ������ķ���ʱ��ͬ����ʱ��ȽϷ��ؼ���ǰ����Ϣ
		 * ˵��һ�£����ص�date5ÿ�ζ���ϵͳʱ����бȽϣ�����date5�᲻�ϸ���
		 */
		Date strtodate = formatter.parse(chat.getDate(),pos);
		String date5 = getTimeFormatText.getTimeFormatText(strtodate);
		chat.setDate5(date5);
		chat.setAdmin(admin);
		return chat;
	}
}

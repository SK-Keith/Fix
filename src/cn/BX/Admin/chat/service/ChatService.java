package cn.BX.Admin.chat.service;

import java.sql.SQLException;
import java.util.List;

import cn.BX.Admin.chat.dao.ChatDao;
import cn.BX.Admin.chat.domain.Chat;

public class ChatService {
	private ChatDao chatDao = new ChatDao();

	public void addTable(Chat chat) {
		try {
			chatDao.addTable(chat);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Chat> findAllChat() {
		try {
			return chatDao.findAllChat();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

//	public int findNum() {
//		try {
//			return chatDao.findNum();
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}

}

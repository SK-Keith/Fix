package cn.BX.user.service;

import java.sql.SQLException;
import java.util.List;

import cn.BX.user.dao.UserDao;
import cn.BX.user.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();

	/**
	 * ��¼����
	 * @param user
	 * @return
	 */
	public User login(User user){
		try {
			return userDao.findByLoginnameAndLoginpass(user.getLoginname(), user.getLoginpass());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �༭�û���Ϣ
	 * @param user
	 */
	public void edit(User user){
		try {
			userDao.edit(user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �û���ע��У��
	 */
	public boolean ajaxValidateLoginname(String uname) {
		try {
			return userDao.ajaxValidateLoginname(uname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ����У��
	 * @param email
	 * @return
	 */
	public boolean ajaxValidateEmail(String email){
		try {
			return userDao.ajaxValidateEmail(email);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * �ֻ�����У��
	 * @return
	 */
	public boolean ajaxValidatePhone(String phone){
		try {
			return userDao.ajaxValidateEmail(phone);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<User> loadAllUser() {
		try {
			return userDao.loadAllUser();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean findByUsername(String username) {
		try {
			User u = userDao.findByUsername(username);
			if(u == null) {
				return true;
			}else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void createUser(User u) {
		try {
			userDao.createUser(u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

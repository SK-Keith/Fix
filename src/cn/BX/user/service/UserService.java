package cn.BX.user.service;

import java.sql.SQLException;
import java.util.List;

import cn.BX.user.dao.UserDao;
import cn.BX.user.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();

	/**
	 * 登录功能
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
	 * 编辑用户信息
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
	 * 用户名注册校验
	 */
	public boolean ajaxValidateLoginname(String uname) {
		try {
			return userDao.ajaxValidateLoginname(uname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 邮箱校验
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
	 * 手机号码校验
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

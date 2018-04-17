package cn.BX.user.service;

import java.sql.SQLException;

import cn.BX.tool.CommonUtils;
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
	
	
}

package cn.BX.user.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.BX.jdbc.TxQueryRunner;
import cn.BX.tool.CommonUtils;
import cn.BX.user.domain.User;

public class UserDao {
	private QueryRunner qr = new TxQueryRunner();

	 
//	public boolean findBySidAndPassword(String sid, String password) throws SQLException {
//		String sql = "select count(1) from b_user where sid = ? and loginpass = ?";
//		Number number = (Number) qr.query(sql, new ScalarHandler(), sid, password);
//		return number.intValue()>0;
//	} 
	 
	/**
	 * 用户登录校验
	 * @param loginname
	 * @param loginpass
	 * @return
	 * @throws SQLException
	 */
	public User findByLoginnameAndLoginpass(String loginname,String loginpass) throws SQLException{
		String sql = "select * from b_user where loginname = ? and loginpass = ?"; 
		Map<String, Object> map = qr.query(sql, new MapHandler(),loginname,loginpass);
		User user =  CommonUtils.toBean(map, User.class);
		return user;
	}
	
	/**
	 * 编辑用户信息
	 * @param user
	 * @throws SQLException
	 */
	public void edit(User user) throws SQLException{
		String sql = "update b_user set uname=?, sex=?,loginpass=?,fname=?,address=?,"
				+ "phone=?,email=? where uid =?";
		Object[] params ={user.getUname(),user.getSex(),user.getLoginpass(),
				user.getFloor().getFname(),user.getAddress(), user.getPhone(),user.getEmail(),user.getUid()};
		qr.update(sql,params);
	}

	/*
	 * 以ajax开头命名表示支持ajax的校验方法
	 * 检验用户名是否注册
	 */
	public boolean ajaxValidateLoginname(String uname) throws SQLException{
		String sql = "select count(1) from b_user where uname=?";
		Number number = (Number) qr.query(sql, new ScalarHandler(),uname);
		return number.intValue() == 0;
	}
	
	/*
	 * 校验email是否注册
	 */
	public boolean ajaxValidateEmail(String email) throws SQLException{
		String sql = "select count(1) from b_user where phone=?";
		Number number = (Number) qr.query(sql, new ScalarHandler(),email);
		return number.intValue() == 0;
	}
	
	/*
	 * 校验email是否注册
	 */
	public boolean ajaxValidatePhone(String phone) throws SQLException{
		String sql = "select count(1) from b_user where phone=?";
		Number number = (Number) qr.query(sql, new ScalarHandler(),phone);
		return number.intValue() == 0;
	}

	public List<User> loadAllUser() throws SQLException {
		String sql = "select uid,uname,phone,concat(fname,address) addfname,email from b_user";
		List<User> userList = qr.query(sql, new BeanListHandler<User>(User.class));
		return userList;
	}

	public User findByUsername(String username) throws SQLException {
		String sql = "select * from b_user where loginname = ?";
		User u = qr.query(sql, new BeanHandler<User>(User.class),username);
		return u;
	}

	public void createUser(User user) throws SQLException {
		String sql = "insert into b_user(uid,loginname,loginpass) values(?,?,?)";
		Object[] params = {user.getUid(),user.getLoginname(),user.getLoginpass()};
		qr.update(sql,params);
	}
}

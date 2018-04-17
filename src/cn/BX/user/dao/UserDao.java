package cn.BX.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.BX.jdbc.TxQueryRunner;
import cn.BX.user.domain.User;

public class UserDao {
	 private QueryRunner qr = new TxQueryRunner();

	 
//	public boolean findBySidAndPassword(String sid, String password) throws SQLException {
//		String sql = "select count(1) from b_user where sid = ? and loginpass = ?";
//		Number number = (Number) qr.query(sql, new ScalarHandler(), sid, password);
//		return number.intValue()>0;
//	} 
	 
	 
	public User findByLoginnameAndLoginpass(String loginname,String loginpass) throws SQLException{
		String sql = "select * from student where loginname = ? and loginpass = ?"; 
		return qr.query(sql, new BeanHandler<User>(User.class),loginname,loginpass);
	}
}

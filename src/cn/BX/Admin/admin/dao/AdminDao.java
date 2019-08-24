package cn.BX.Admin.admin.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.BX.Admin.admin.domain.Admin;
import cn.BX.jdbc.TxQueryRunner;

public class AdminDao {
	private QueryRunner qr = new TxQueryRunner();

	/*
	 * 根据用户名和密码返回相应的一个用户
	 */
	public Admin findByLoginnameAndLoginpass(String loginname, String loginpass) throws SQLException {
		String sql = "select * from b_admin where email=? and loginpass=?";
		Admin admin = qr.query(sql, new BeanHandler<Admin>(Admin.class),loginname,loginpass);
		return admin;
	}
	
	/*
	 * 根据指定的楼栋名(fname)，tname（水电类,网络类，门窗类等）,trange（宿舍区，行政区，教学区等）
	 */
	public Admin findAdmin(String fname,String tname, String trange) throws SQLException{
		String sql = "select * from b_admin a, b_floor f, b_type t where a.fid = f.fid and a.tid = t.tid and f.fname = ? and t.tname =? and t.trange = ?";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class),fname, tname, trange);
	}

	/*
	 * 查找管理员
	 */
	public Admin findAdmin(String aid) throws SQLException {
		String sql = "select * from b_admin where aid = ? and identity='1' ";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class),aid);
	}
	
	/*
	 * 查找维修员
	 */
	public Admin findAdmin2(String aid) throws SQLException {
		String sql = "select * from b_admin where aid = ? and identity='2' ";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class),aid);
	}

	/*
	 * 修改资料
	 * 更新管理员信息
	 */
	public void updateAdmin(Admin admin) throws SQLException {
		String sql = "update b_admin set name = ?, phone = ? where aid = ?";
		Object[] params ={admin.getName(),admin.getPhone(),admin.getAid()};
		qr.update(sql,params);
	}

	
	/*
	 * 加载所有的维修员信息
	 */
	public List<Admin> loadAllAdmin2() throws SQLException {
		String sql = "select aid,name, phone,fname,tname from b_admin a,b_type t,b_floor f where identity = '2' and a.fid = f.fid and a.tid = t.tid ";
		return qr.query(sql, new BeanListHandler<Admin>(Admin.class));
	}

	/*
	 * 添加维修员信息
	 */
	public void addAdmin2(Admin admin) throws SQLException {
		String sql = "insert into b_admin(aid,email,loginpass,name,phone,identity,fid,tid) values(?,?,?,?,?,?,?,?)";
		Object[] params = {admin.getAid(),admin.getEmail(),admin.getLoginpass(),admin.getName(),admin.getPhone(),admin.getIdentity(),admin.getFloor().getFid(),admin.getRange().getTid()};
		qr.update(sql,params);
	}

	/*
	 * 根据楼栋id，范围id
	 * 查找维修员
	 */
	public Admin findAdmin(String fid, String tid) throws SQLException {
		String sql = "select * from b_admin where fid = ? AND tid=?";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class),fid,tid);
	}
	
	/*
	 * 删除用户
	 */
	public void deleteUser(String uid) throws SQLException {
		String sql = "delete from b_user where uid = ? ";
		qr.update(sql, uid);
	}
	
	/*
	 * 删除管理员
	 */
	public void deleteAdmin(String aid) throws SQLException {
		String sql = "delete from b_admin where aid = ? and identity = '2'";
		qr.update(sql, aid);
	}
	
}
 
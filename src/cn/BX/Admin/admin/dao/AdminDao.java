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
	 * �����û��������뷵����Ӧ��һ���û�
	 */
	public Admin findByLoginnameAndLoginpass(String loginname, String loginpass) throws SQLException {
		String sql = "select * from b_admin where email=? and loginpass=?";
		Admin admin = qr.query(sql, new BeanHandler<Admin>(Admin.class),loginname,loginpass);
		return admin;
	}
	
	/*
	 * ����ָ����¥����(fname)��tname��ˮ����,�����࣬�Ŵ���ȣ�,trange��������������������ѧ���ȣ�
	 */
	public Admin findAdmin(String fname,String tname, String trange) throws SQLException{
		String sql = "select * from b_admin a, b_floor f, b_type t where a.fid = f.fid and a.tid = t.tid and f.fname = ? and t.tname =? and t.trange = ?";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class),fname, tname, trange);
	}

	/*
	 * ���ҹ���Ա
	 */
	public Admin findAdmin(String aid) throws SQLException {
		String sql = "select * from b_admin where aid = ? and identity='1' ";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class),aid);
	}
	
	/*
	 * ����ά��Ա
	 */
	public Admin findAdmin2(String aid) throws SQLException {
		String sql = "select * from b_admin where aid = ? and identity='2' ";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class),aid);
	}

	/*
	 * �޸�����
	 * ���¹���Ա��Ϣ
	 */
	public void updateAdmin(Admin admin) throws SQLException {
		String sql = "update b_admin set name = ?, phone = ? where aid = ?";
		Object[] params ={admin.getName(),admin.getPhone(),admin.getAid()};
		qr.update(sql,params);
	}

	
	/*
	 * �������е�ά��Ա��Ϣ
	 */
	public List<Admin> loadAllAdmin2() throws SQLException {
		String sql = "select aid,name, phone,fname,tname from b_admin a,b_type t,b_floor f where identity = '2' and a.fid = f.fid and a.tid = t.tid ";
		return qr.query(sql, new BeanListHandler<Admin>(Admin.class));
	}

	/*
	 * ���ά��Ա��Ϣ
	 */
	public void addAdmin2(Admin admin) throws SQLException {
		String sql = "insert into b_admin(aid,email,loginpass,name,phone,identity,fid,tid) values(?,?,?,?,?,?,?,?)";
		Object[] params = {admin.getAid(),admin.getEmail(),admin.getLoginpass(),admin.getName(),admin.getPhone(),admin.getIdentity(),admin.getFloor().getFid(),admin.getRange().getTid()};
		qr.update(sql,params);
	}

	/*
	 * ����¥��id����Χid
	 * ����ά��Ա
	 */
	public Admin findAdmin(String fid, String tid) throws SQLException {
		String sql = "select * from b_admin where fid = ? AND tid=?";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class),fid,tid);
	}
	
	/*
	 * ɾ���û�
	 */
	public void deleteUser(String uid) throws SQLException {
		String sql = "delete from b_user where uid = ? ";
		qr.update(sql, uid);
	}
	
	/*
	 * ɾ������Ա
	 */
	public void deleteAdmin(String aid) throws SQLException {
		String sql = "delete from b_admin where aid = ? and identity = '2'";
		qr.update(sql, aid);
	}
	
}
 
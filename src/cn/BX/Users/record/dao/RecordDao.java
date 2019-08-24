package cn.BX.Users.record.dao;

import java.sql.SQLException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.BX.jdbc.TxQueryRunner;
import cn.BX.pager.Expression;
import cn.BX.pager.PageBean;
import cn.BX.pager.PageConstants;
import cn.BX.time.getTimeFormatText;
import cn.BX.tool.CommonUtils;
import cn.BX.user.domain.User;
import cn.BX.Admin.admin.domain.Admin;
import cn.BX.Users.record.domain.Record;

public class RecordDao {
	private QueryRunner qr = new TxQueryRunner();

	/**
	 * ��uid��ѯ
	 * @param uid
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	public PageBean<Record> findByUid(String uid, int pc) throws SQLException {
		List<Expression> exprList = new ArrayList<Expression>();
		exprList.add(new Expression("uid", "=", uid));
		return findByCriteria(exprList, pc);
	}
	
	/**
	 * ͨ�õĲ�ѯ��������ҳЧ��
	 * @param exprList
	 * @param pc
	 * @return
	 * @throws SQLException
	 */
	private PageBean<Record> findByCriteria(List<Expression> exprList, int pc) throws SQLException {
		/*
		 * 1. �õ�ps 2. �õ�tr 3. �õ�beanList 4. ����PageBean������
		 */
		/*
		 * 1. �õ�ps
		 */
		int ps = PageConstants.RECORD_PAGE_SIZE;// ÿҳ��¼��12
		/*
		 * 2. ͨ��exprList������where�Ӿ�
		 */
		StringBuilder whereSql = new StringBuilder(" where 1=1 and u.uid = r.uid ");
		List<Object> params = new ArrayList<Object>();// SQL�����ʺţ����Ƕ�Ӧ�ʺŵ�ֵ
		for (Expression expr : exprList) {
			/*
			 * ���һ�������ϣ� 1) ��and��ͷ 2) ���������� 3) �������������������=��!=��>��< ... is null��is
			 * nullû��ֵ 4) �����������is null����׷���ʺţ�Ȼ������params�����һ���ʺŶ�Ӧ��ֵ
			 */
			whereSql.append(" and r.").append(expr.getName()).append(" ").append(expr.getOperator()).append(" ");
			// where 1=1 and bid = ?
			if (!expr.getOperator().equals("is null")) {
				whereSql.append("?");
				params.add(expr.getValue());
			}
		}
		/*
		 * 3. �ܼ�¼��
		 */
		String sql = "select count(*) from b_record r, b_user u" + whereSql;
		Number number = (Number) qr.query(sql, new ScalarHandler(), params.toArray());
		int tr = number.intValue();// �õ����ܼ�¼��
		/*
		 * 4. �õ�beanList������ǰҳ��¼
		 */
		sql = "select * from b_record r,b_user u" + whereSql + " order by orderBy DESC limit ?,?";
		params.add((pc - 1) * ps);// ��ǰҳ���м�¼���±�
		params.add(ps);// һ����ѯ���У�����ÿҳ��¼��

		List<Record> beanList = qr.query(sql, new BeanListHandler<Record>(Record.class), params.toArray());

		/*
		 * 5. ����PageBean�����ò���
		 */
		PageBean<Record> pb = new PageBean<Record>();
		/*
		 * ����PageBeanû��url�����������Servlet���
		 */
		pb.setBeanList(beanList);
		pb.setPc(pc);
		pb.setPs(ps);
		pb.setTr(tr);
		return pb;
	}

	/**
	 * ��Ӽ�¼
	 * @param record
	 * @throws SQLException
	 */
	public void add(Record record) throws SQLException {
		if(record.getImage()!= null){
		String sql = "insert into b_record(rid,uid,tid,details,status,address,image,date1,date5,aid)"
				+ " values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { record.getRid(), record.getUser().getUid(), record.getRange().getTid(), record.getDetails(),
				record.getStatus(), record.getAddress(), record.getImage(), record.getDate1(),record.getDate5(), record.getAdmin().getAid()};
		qr.update(sql, params);
		}else{
			String sql = "insert into b_record(rid,uid,tid,details,status,address,date1,date5,aid)"
					+ " values(?,?,?,?,?,?,?,?,?)";
			Object[] params = { record.getRid(), record.getUser().getUid(), record.getRange().getTid(), record.getDetails(),
					record.getStatus(), record.getAddress(), record.getDate1(), record.getDate5(),record.getAdmin().getAid()};
			qr.update(sql, params);
		}
		
	}

	/**
	 * ��ȡָ���û��ı��ޱ�����
	 * 
	 * @param uid
	 * @return
	 * @throws SQLException
	 */
	public List<Record> findByUidTest(String uid) throws SQLException {
		String sql = "SELECT * FROM b_record r, b_user u WHERE r.uid = ?";
		return qr.query(sql, new BeanListHandler<Record>(Record.class), uid);
	}
	
	/**
	 * ��ѯδά�޵ı�
	 * r.rid, addfname, details,image,date1,u.phone, u.uname,a.name,a.phone
	 * @throws SQLException 
	 */
	public List<Record> findByStatus1() throws SQLException{
		String sql = "SELECT r.rid,concat(f.fname,u.address) addfname, details,image,date1,u.phone, u.uname,a.name,a.phone FROM b_record r, b_admin a,b_user u,b_floor f where f.fid = a.fid and u.uid = r.uid and r.aid = a.aid and status = '1' order by date1 desc";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
		return toRecordList(mapList);
	}

	/**
	 * ��ѯ���޸��ı�
	 * �������findByAdminStatus2�ظ�
	 * rid addfname details image date1  phone uname  name phone  evaluation  date4             
	 * @return
	 * @throws SQLException
	 */
//	public List<Record> findByStatus2() throws SQLException {
//		String sql = "SELECT r.rid,concat(f.fname,u.address) addfname, details,image,date1,u.phone, u.uname,a.name,a.phone,evaluation,date4 FROM b_record r, b_admin a,b_user u,b_floor f where f.fid = a.fid and u.uid = r.uid and r.aid = a.aid and status = '2' order by date6 desc";
//		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
////		List<Record> record = CommonUtils.toBean(mapList, Record.class);
////		List<Admin> admin = CommonUtils.toBean(mapList, Admin.class);
////		record.setAdmin(admin);
//		return toRecordList(mapList);
//	}
	
	/**
	 * ��ѯ���޸��ı�
	 * @return
	 * @throws SQLException
	 */
	public List<Record> findByEvaStatus2() throws SQLException {
		String sql = "SELECT r.rid,concat(f.fname,u.address) addfname, details,image,date1,u.phone, u.uname,a.name,a.phone,evaluation,date4,date6 FROM b_record r, b_admin a,b_user u,b_floor f where f.fid = a.fid and u.uid = r.uid and r.aid = a.aid and status = '2' and evaluation is not null order by date6 desc;";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
//		List<Record> record = CommonUtils.toBean(mapList, Record.class);
//		List<Admin> admin = CommonUtils.toBean(mapList, Admin.class);
//		record.setAdmin(admin);
		return toRecordList(mapList);
	}
	
	/**
	 * ��ѯ���޸��ı�
	 * date4����ʱ��
	 * @return
	 * @throws SQLException
	 */
//	public List<Record> findByEvaStatus2() throws SQLException {
//		String sql = "SELECT r.rid,concat(f.fname,u.address) addfname, details,image,date1,u.phone, u.uname,a.name,a.phone,evaluation,date4,date6 FROM b_record r, b_admin a,b_user u,b_floor f where f.fid = a.fid and u.uid = r.uid and r.aid = a.aid and status = '2' and evaluation is not null order by date6 desc";
//		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
////		List<Record> record = CommonUtils.toBean(mapList, Record.class);
////		List<Admin> admin = CommonUtils.toBean(mapList, Admin.class);
////		record.setAdmin(admin);
//		return toRecordList(mapList);
//	}
	
	/**
	 * ��ѯ�������޸��ı�
	 * @return
	 * @throws SQLException
	 */
	public List<Record> findByAdminStatus2() throws SQLException {
		String sql = "SELECT r.rid,concat(f.fname,u.address) addfname, details,image,date1,u.phone, u.uname,a.name,a.phone,date6,date4,evaluation FROM b_record r, b_admin a,b_user u,b_floor f where f.fid = a.fid and u.uid = r.uid and r.aid = a.aid and status = '2' order by date6 desc";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
//		List<Record> record = CommonUtils.toBean(mapList, Record.class);
//		List<Admin> admin = CommonUtils.toBean(mapList, Admin.class);
//		record.setAdmin(admin);
		return toRecordList(mapList);
	}
	
	
	/**
	 * ��ѯ�ݻ��޸��ı�
	 * @return
	 * @throws SQLException
	 */
	public List<Record> findByStatus3() throws SQLException {
		String sql = "SELECT r.rid,concat(f.fname,u.address) addfname, details,image,date1,u.phone, u.uname,a.name,a.phone FROM b_record r, b_admin a,b_user u,b_floor f where f.fid = a.fid and u.uid = r.uid and r.aid = a.aid and status = '3' order by date1 desc";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
		return toRecordList(mapList);
	}
	
	/**
	 * ����Ա�鿴��ʱ��
	 * @return
	 * @throws SQLException 
	 */
	public List<Record> findByStatus4() throws SQLException {
		String sql = "SELECT r.rid,concat(f.fname,u.address) addfname, details,image,date1,u.phone, u.uname,a.name,a.phone FROM b_record r, b_admin a,b_user u,b_floor f where f.fid = a.fid and u.uid = r.uid and r.aid = a.aid and status = '4' order by date1 desc";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
		return toRecordList(mapList);
	}

	/**
	 * �Ѷ��Map(List<Map>)ӳ��ɶ��RecordList(List(RecordList))
	 * @param mapList
	 * @return
	 */
	private List<Record> toRecordList(List<Map<String, Object>> mapList) {
		List<Record> recordList = new ArrayList<Record>();
		for(Map<String,Object> map : mapList){
			Record record = toRecord(map);
			recordList.add(record);
		}
		return recordList;
	}

	/**
	 * �ѵ���Mapӳ���һ��Record
	 * @param map
	 * @return
	 */
	private Record toRecord(Map<String, Object> map) {
		if(map == null || map.size() == 0) return null;
		Record record = CommonUtils.toBean(map, Record.class);
		Admin admin = CommonUtils.toBean(map, Admin.class);
		User user = CommonUtils.toBean(map, User.class);
		/*
		 * �����к�������ʱ��ʱ����������ʱ��͵���ʱ��ıȽϣ����ؼ���ǰ����ʽ�����ݣ�����date3
		 */
		if(record.getDate6() != null && record.getDate6().length() != 0){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			Date strtodate = formatter.parse(record.getDate6(), pos);
			String date3 = getTimeFormatText.getTimeFormatText(strtodate);
			record.setDate3(date3);
		}
		record.setAdmin(admin);
		record.setUser(user);	
		return record;
	}

	public void deleteTable(String rid) throws SQLException {
		String sql = "delete from b_record where rid = ?";
		qr.update(sql, rid);
	}
	
	/**
	 * ��ѯ��ʱ�޸���������������ҳ��ʾ
	 * @return
	 * @throws SQLException
	 */
	public int findNum1() throws SQLException {
		String sql = "select count(*) from b_record r where r.status = '4'";
		Number num = (Number) qr.query(sql, new ScalarHandler());
		return num.intValue();
	}

	/**
	 * �鿴δ�޸��ı���������������ҳ��ʾ
	 * @return
	 * @throws SQLException
	 */
	public Number findNum2() throws SQLException {
		String sql = "select count(*) from b_record r where r.status = '1'";
		Number num = (Number) qr.query(sql, new ScalarHandler());
		return num.intValue(); 
	}

	/**
	 * ��ѯĳ��ʦ���µĳ�ʱ��Ϣ������
	 * @param aid
	 * @return
	 * @throws SQLException
	 */
	public int findNum1Aid(String aid) throws SQLException {
		String sql = "select count(*) from b_record r where r.status = '4' AND aid =?";
		Number num = (Number) qr.query(sql, new ScalarHandler(),aid);
		return num.intValue();
	}

	/**
	 * ��ѯĳ��ʦ���µ�δ�޸���������
	 * @param aid
	 * @return
	 * @throws SQLException
	 */
	public Number findNum2Aid(String aid) throws SQLException {
		String sql = "select count(*) from b_record r where r.status = '1' AND aid=?";
		Number num = (Number) qr.query(sql, new ScalarHandler(),aid);
		return num.intValue(); 
	}
	
	/**
	 * �������ۣ��������ۣ�date6Ϊ���۵�ʱ��
	 * @param evaluation
	 * @param rid
	 * @param date6
	 * @throws SQLException
	 */
	public void updateEvaluation(String evaluation, String rid, String date6) throws SQLException {
		String sql = "update b_record set evaluation = ?,date6 = ? where rid =?";
		qr.update(sql,evaluation,date6,rid);
	}

	/**
	 * 
	 * @param rid
	 * @return
	 * @throws SQLException
	 */
	public Admin findAdmin(String rid) throws SQLException {
		String sql = "select * from b_record where rid = ?";
		Record record = qr.query(sql, new BeanHandler<Record>(Record.class),rid);
		String aid = record.getAid();
		String sql2 = "select * from b_admin where aid = ?";
		Admin admin = qr.query(sql2, new BeanHandler<Admin>(Admin.class),aid);
		return admin;
	}

	public List<Record> findRecord(String rid) throws SQLException {
		String sql = "select * from b_record where rid = ?";
		List<Record> recordList = qr.query(sql, new BeanListHandler<Record>(Record.class),rid);
		return recordList;
	}
	
	public Record findRecord3(String rid) throws SQLException {
		String sql = "select * from b_record where rid = ?";
		Record record = qr.query(sql, new BeanHandler<Record>(Record.class),rid);
		String uid = record.getUid();
		String sql2 = "select uname,phone from b_user where uid = ?";
		User user = qr.query(sql2, new BeanHandler<User>(User.class),uid);
		record.setUser(user);
		return record;
	}

	public List<Record> findByAidStatus1(String aid) throws SQLException {
		String sql = "SELECT r.rid,concat(f.fname,u.address) addfname, r.details,r.image,r.date1,u.phone, u.uname,date6,date4 FROM b_record r, b_admin a,b_user u,b_floor f where f.fid = a.fid and u.uid = r.uid and r.aid = a.aid and status = '1' and r.aid = ?  order by date1 desc";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),aid);
		return toRecordList(mapList);
	}

	/**
	 * ��ѯδ���۵�ĳʦ������Ϣ
	 * ����
	 * @param aid
	 * @return
	 * @throws SQLException
	 */
//	public List<Record> findByEvaAidStatus1(String aid) throws SQLException {
//		String sql = "SELECT r.rid,concat(f.fname,u.address) addfname, r.details,r.image,r.date1,u.phone, u.uname,date6,date4 FROM b_record r, b_admin a,b_user u,b_floor f where f.fid = a.fid and u.uid = r.uid and r.aid = a.aid and status = '1' and r.aid = ? and evaluation is not null order by date6 desc";
//		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),aid);
//		return toRecordList(mapList);
//	}
	
	public List<Record> findByAidStatus2(String aid) throws SQLException {
		String sql = "SELECT r.rid,concat(f.fname,u.address) addfname, details,image,date1,u.phone, u.uname,date4 FROM b_record r, b_admin a,b_user u,b_floor f where f.fid = a.fid and u.uid = r.uid and r.aid = a.aid and status = '2' and r.aid = ?  order by date1 desc";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),aid);
		return toRecordList(mapList);
	}
	
	public List<Record> findByAidStatus3(String aid) throws SQLException {
		String sql = "SELECT r.rid,concat(f.fname,u.address) addfname, details,image,date1,u.phone, u.uname,r.reason FROM b_record r, b_admin a,b_user u,b_floor f where f.fid = a.fid and u.uid = r.uid and r.aid = a.aid and status = '3' and r.aid = ?  order by date1 desc";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),aid);
		return toRecordList(mapList);
	}
	
	public List<Record> findByAidStatus4(String aid) throws SQLException {
		String sql = "SELECT r.rid,concat(f.fname,u.address) addfname, details,image,date1,u.phone, u.uname,r.reason FROM b_record r, b_admin a,b_user u,b_floor f where f.fid = a.fid and u.uid = r.uid and r.aid = a.aid and status = '4' and r.aid = ?  order by date1 desc";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler(),aid);
		return toRecordList(mapList);
	}

	public void updateStatus(String rid, String date4) throws SQLException {
		String sql = "UPDATE b_record r set status = '2',date4 = ? where r.rid = ?";
		qr.update(sql,date4,rid);
 	}

	public void deleteRecord(String rid) throws SQLException {
		String sql = "delete from b_record where rid = ?";
		qr.update(sql,rid);
	}

	public void updateStatus3(String rid, String reason) throws SQLException {
		String sql = "update b_record r set status = '3',reason=? where r.rid = ?";
		qr.update(sql,reason,rid);
	}

	public void updateStatus4() throws SQLException {
		String sql = "update b_record set status = '4' where date5 < sysdate()";
		qr.update(sql);
	}
	
	public void updateStatus4Cui(String rid) throws SQLException {
		String sql = "update b_record set status = '4' where rid = ?";
		qr.update(sql,rid);
	}

	public Record findDate2(String rid) throws SQLException {
		String sql = "select date2 from b_record r where r.rid =?";
		Record record = qr.query(sql, new BeanHandler<Record>(Record.class),rid);
		return  record;
	}

	public void updateAidStaus1(String rid) throws SQLException {
		String sql = "update b_record set status = '1' where rid = ?";
		qr.update(sql,rid);
	}

	
}
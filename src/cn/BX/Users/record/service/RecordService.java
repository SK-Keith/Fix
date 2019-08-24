package cn.BX.Users.record.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.BX.pager.PageBean;
import cn.BX.Admin.admin.domain.Admin;
import cn.BX.Users.record.dao.RecordDao;
import cn.BX.Users.record.domain.Record;

public class RecordService {
	private RecordDao recordDao = new RecordDao();

	public void add(Record record) {
		try {
			recordDao.add(record);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public PageBean<Record> loadTable(String uid, int pc) {
		try {
			return recordDao.findByUid(uid, pc);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Record> findByStatus1(){
		try {
			return recordDao.findByStatus1();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

//	public List<Record> findByStatus2() {
//		try {
//			return recordDao.findByStatus2();
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	public List<Record> findByEvaStatus2() {
		try {
			return recordDao.findByEvaStatus2();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 返回所有有评论信息的已修复表单
	 * @return
	 */
	public List<Record> findByAdminStatus2() {
		try {
			return recordDao.findByAdminStatus2();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Record> findByStatus3() {
		try {
			return recordDao.findByStatus3();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Record> findByStatus4() {
		try {
			return recordDao.findByStatus4();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteTable(String uid) {
		try {
			recordDao.deleteTable(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public Number findNum1() {
		try {
			return recordDao.findNum1();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Number findNum2() {
		try {
			return recordDao.findNum2();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Number findNum1Aid(String aid) {
		try {
			return recordDao.findNum1Aid(aid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Number findNum2Aid(String aid) {
		try {
			return recordDao.findNum2Aid(aid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateEvaluation(String evaluation, String rid, String date6) {
		try {
			recordDao.updateEvaluation(evaluation, rid,date6);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Admin findAdmin(String rid) {
		try {
			return recordDao.findAdmin(rid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Record> findRecord(String rid) {
		try {
			return recordDao.findRecord(rid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Record findRecord3(String rid) {
		try {
			return recordDao.findRecord3(rid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Record> findByAidStatus1(String aid) {
		try {
			return recordDao.findByAidStatus1(aid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
//	public List<Record> findByEvaAidStatus1(String aid) {
//		try {
//			return recordDao.findByEvaAidStatus1(aid);
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}

	public List<Record> findByAidStatus2(String aid) {
		try {
			return recordDao.findByAidStatus2(aid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Record> findByAidStatus3(String aid) {
		try {
			return recordDao.findByAidStatus3(aid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Record> findByAidStatus4(String aid) {
		try {
			return recordDao.findByAidStatus4(aid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateStatus(String rid, String date4) {
		try {
			recordDao.updateStatus(rid,date4);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteRecord(String rid) {
		try {
			recordDao.deleteRecord(rid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		};
	}

	public void updateStatus3(String rid, String reason) {
		try {
			recordDao.updateStatus3(rid,reason);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void updateStatus4() {
		try {
			recordDao.updateStatus4();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateStatus4Cui(String rid) {
		try {
			recordDao.updateStatus4Cui(rid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Record findDate2(String rid) {
		try {
			return recordDao.findDate2(rid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateAidStaus1(String rid) {
		try {
			recordDao.updateAidStaus1(rid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


}

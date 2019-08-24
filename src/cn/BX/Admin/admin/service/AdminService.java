package cn.BX.Admin.admin.service;

import java.sql.SQLException;
import java.util.List;

import cn.BX.Admin.admin.dao.AdminDao;
import cn.BX.Admin.admin.domain.Admin;
import cn.BX.user.domain.User;

public class AdminService {
	private AdminDao adminDao = new AdminDao();

	public Admin login(Admin admin) {
		try {
			return adminDao.findByLoginnameAndLoginpass(admin.getEmail(), admin.getLoginpass());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * ¸ù¾Ýaid²éadmin
	 * @return
	 */
	public Admin findAdmin(String fname, String tname, String trange) {
		try {
			return adminDao.findAdmin(fname,tname,trange);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Admin findAdmin(String aid) {
		try {
			return adminDao.findAdmin(aid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Admin findAdmin2(String aid) {
		try {
			return adminDao.findAdmin2(aid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateAdmin(Admin admin) {
		try {
			adminDao.updateAdmin(admin);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Admin> loadAllAdmin2() {
		try {
			return adminDao.loadAllAdmin2();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void addAdmin2(Admin admin) {
		try {
			adminDao.addAdmin2(admin);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Admin findAdmin(String fid, String tid) {
		try {
			return adminDao.findAdmin(fid,tid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteUser(String uid) {
		try {
			adminDao.deleteUser(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void deleteAdmin(String aid) {
		try {
			adminDao.deleteAdmin(aid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

package cn.BX.Users.floor.service;

import java.sql.SQLException;
import java.util.List;

import cn.BX.Users.floor.dao.FloorDao;
import cn.BX.Users.floor.domain.Floor;

public class FloorService {
	private FloorDao floorDao = new FloorDao();

	public List<Floor> findName() {
		try {
			return floorDao.findName();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Floor> findChildren(String a) {
		try {
			return floorDao.findChildren(a);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Floor findRid(String fname) {
		try {
			return floorDao.findRid(fname);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

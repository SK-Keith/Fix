package cn.BX.Users.range.service;

import java.sql.SQLException;
import java.util.List;

import cn.BX.Users.range.dao.RangeDao;
import cn.BX.Users.range.domain.Range;

public class RangeService {
	private RangeDao rangeDao = new RangeDao();

	public List<Range> findZhonglei() {
		try {
			return rangeDao.findZhonglei();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Range findTid(String tname, String trange) {
		try {
			return rangeDao.findTid(tname,trange);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

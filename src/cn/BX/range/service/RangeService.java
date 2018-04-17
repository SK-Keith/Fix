package cn.BX.range.service;

import java.sql.SQLException;
import java.util.List;

import cn.BX.range.dao.RangeDao;
import cn.BX.range.domain.Range;
import cn.BX.table.domain.Table;

public class RangeService {
	private RangeDao rangeDao = new RangeDao();

	public List<Range> findZhonglei() {
		try {
			return rangeDao.findZhonglei();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	

}

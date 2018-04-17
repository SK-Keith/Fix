package cn.BX.table.service;

import java.sql.SQLException;
import java.util.List;

import cn.BX.table.dao.TableDao;
import cn.BX.table.domain.Table;

public class TableService {

	private TableDao tableDao = new TableDao();
	
	public List<Table> findZhonglei() {
		try {
			return tableDao.findZhonglei();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}

package cn.BX.table.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.BX.jdbc.TxQueryRunner;
import cn.BX.table.domain.Table;
import cn.BX.tool.CommonUtils;

public class TableDao {
 
	private QueryRunner qr = new TxQueryRunner();
	
	public List<Table> findZhonglei() throws SQLException{
		/*
		 * 1.查询出所有一级分类
		 */ 
		String sql = "select * from type";
		List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
		
		return toTableList(mapList);
	}

	private List<Table> toTableList(List<Map<String, Object>> mapList) {
		List<Table> tableList = new ArrayList<Table>();
		for(Map<String,Object> map : mapList){
			Table t = toTable(map);
			tableList.add(t);
		}
		return tableList;
	}

	private Table toTable(Map<String, Object> map) {
		Table table = CommonUtils.toBean(map, Table.class);
		return table;
	}

}

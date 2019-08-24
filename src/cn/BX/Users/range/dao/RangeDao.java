package cn.BX.Users.range.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.BX.jdbc.TxQueryRunner;
import cn.BX.Users.range.domain.Range;

public class RangeDao {

	private QueryRunner qr = new TxQueryRunner();

	/**
	 * 返回所有种类
	 * @return
	 * @throws SQLException
	 */
	
	public List<Range> findZhonglei() throws SQLException{
		/*
		 * 1.查询出所有一级分类
		 */   
		String sql = "select tid,tname,trange from b_type b where tid in (select min(tid) from b_type t where b.tname = t.tname) order by tid";
//		List<Range> mapList =  qr.query(sql, new BeanListHandler<Range>(Range.class));
//		List<Range> list = qr.query(sql, new BeanListHandler<Range>(Range.class));
//		List<Map<String, Object>> mapList = qr.query(sql, new MapListHandler());
//		List<Range> range = toRangeList(mapList);
		List<Range> range = qr.query(sql, new BeanListHandler<Range>(Range.class));
//		sql1 = "select distinct tname from b_type";
//		List<Range> range = qr.query(sql1, new BeanListHandler<Range>(Range.class));
		return range;
	}

	public Range findTid(String tname, String trange) throws SQLException {
		String sql = "select tid from b_type where tname = ? and trange = ?";
		Range range = qr.query(sql, new BeanHandler<Range>(Range.class),tname,trange);
		return range;
	}
	
	

//	private List<Range> toRangeList(List<Map<String, Object>> mapList) {
//		List<Range> rangeList = new ArrayList<Range>();
//		for(Map<String,Object> map : mapList){
//			Range r = toRange(map);
//			rangeList.add(r);
//		}
//		return rangeList;
//	}
//
//	private Range toRange(Map<String, Object> map) {
//		Range range = CommonUtils.toBean(map, Range.class);
//		return range;
//	}

//	private List<Table> toTableList(List<Map<String, Object>> mapList) {
//		List<Table> tableList = new ArrayList<Table>();
//		for(Map<String,Object> map : mapList){
//			Table t = toTable(map);
//			tableList.add(t);
//		}
//		return tableList;
//	}
//
//	private Table toTable(Map<String, Object> map) {
//		Table table = CommonUtils.toBean(map, Table.class);
//		return table;
//	}

}

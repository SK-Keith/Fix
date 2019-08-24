package cn.BX.Users.floor.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.BX.Users.floor.domain.Floor;
import cn.BX.jdbc.TxQueryRunner;

public class FloorDao {
	private QueryRunner qr = new TxQueryRunner();

	public List<Floor> findName() throws SQLException {
		String sql = "select * from b_floor order by orderBy";
		List<Floor> floor = qr.query(sql, new BeanListHandler<Floor>(Floor.class));
		return floor;
	}

	public List<Floor> findChildren(String a) throws SQLException {
		String sql = "select * from b_floor f where ttid =? order by orderBy";
		List<Floor> floor = qr.query(sql, new BeanListHandler<Floor>(Floor.class),a);
		return floor;
	}

	public Floor findRid(String fname) throws SQLException {
		String sql = "select fid from b_floor where fname = ?";
		Floor floor = qr.query(sql, new BeanHandler<Floor>(Floor.class),fname);
		return floor;
	}
	
	
	
}

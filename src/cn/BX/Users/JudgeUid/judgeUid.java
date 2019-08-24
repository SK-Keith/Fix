package cn.BX.Users.JudgeUid;

import cn.BX.Admin.admin.domain.Admin;
import cn.BX.Admin.admin.service.AdminService;
import cn.BX.Users.floor.domain.Floor;
import cn.BX.Users.floor.service.FloorService;
import cn.BX.Users.range.domain.Range;
import cn.BX.Users.range.service.RangeService;
import cn.BX.Users.record.domain.Record;

public class judgeUid {
	private AdminService adminService = new AdminService();
	private FloorService floorService = new FloorService();
	private RangeService rangeService = new RangeService();
	
	public Admin getResult(Record record){
		String tname = record.getRange().getTname();
		String trange = record.getRange().getTrange();
		String fname = record.getFloor().getFname();
		Floor floor = floorService.findRid(fname);
		Range range = rangeService.findTid(tname, trange);
		Admin admin = adminService.findAdmin(floor.getFid(),range.getTid());
		return admin;
	}
}

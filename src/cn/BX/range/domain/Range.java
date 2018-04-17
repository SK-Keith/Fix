package cn.BX.range.domain;

import cn.BX.floor.domain.Floor;

public class Range {
	private String wid;// 范围id
	private String wtype;// 范围划分，有分宿舍区域，教学区域，道路区域等
	private Floor floor;// 外键，连接楼宇表

	public String getWid() {
		return wid;
	}

	public void setWid(String wid) {
		this.wid = wid;
	}

	public String getWtype() {
		return wtype;
	}

	public void setWtype(String wtype) {
		this.wtype = wtype;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		return "Range [wid=" + wid + ", wtype=" + wtype + ", floor=" + floor + "]";
	}

}

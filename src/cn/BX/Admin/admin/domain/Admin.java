package cn.BX.Admin.admin.domain;

import cn.BX.Users.floor.domain.Floor;
import cn.BX.Users.range.domain.Range;

public class Admin {
	private String aid;//id
	private String email;//登录名
	private String loginpass;//密码
	private String name;//用户名
	private String phone;//电话号码
	private String identity;//身份识别，1是超级管理员 2是维修员
	private Floor floor;//楼栋表
	private Range range;//范围表
	private String fname;//负责楼栋
	private String trange;//负责模块
	private String tname;//负责区域

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getTrange() {
		return trange;
	}

	public void setTrange(String trange) {
		this.trange = trange;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	@Override
	public String toString() {
		return "Admin [aid=" + aid + ", email=" + email + ", loginpass=" + loginpass + ", name=" + name + ", phone="
				+ phone + ", identity=" + identity + ", floor=" + floor + ", range=" + range + "]";
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginpass() {
		return loginpass;
	}

	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

}

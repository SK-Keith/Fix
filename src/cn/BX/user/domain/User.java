package cn.BX.user.domain;

import cn.BX.Users.floor.domain.Floor;

public class User {
	// 对应数据库的表
	private String uid;
	private String loginname;
	private String loginpass;
	private String uname;// 用户姓名
	private String sex;// 性别
	private String fname;// 楼栋
	private String number;// 报修数量
	private Floor floor;// 所在楼宇
	private String address;
	private String addfname;//楼栋+具体地址，如22栋a425
	private String phone;// 电话
	private String email;// 邮箱
	private String verifyCode;// 验证码

	public String getAddfname() {
		return addfname;
	}

	public void setAddfname(String addfname) {
		this.addfname = addfname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginpass() {
		return loginpass;
	}

	public void setLoginpass(String loginpass) {
		this.loginpass = loginpass;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", loginname=" + loginname + ", loginpass=" + loginpass + ", uname=" + uname
				+ ", sex=" + sex + ", fname=" + fname + ", number=" + number + ", floor=" + floor + ", address="
				+ address + ", phone=" + phone + ", email=" + email + ", verifyCode=" + verifyCode + "]";
	}

}

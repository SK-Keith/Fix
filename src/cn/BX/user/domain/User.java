package cn.BX.user.domain;

import cn.BX.Users.floor.domain.Floor;

public class User {
	// ��Ӧ���ݿ�ı�
	private String uid;
	private String loginname;
	private String loginpass;
	private String uname;// �û�����
	private String sex;// �Ա�
	private String fname;// ¥��
	private String number;// ��������
	private Floor floor;// ����¥��
	private String address;
	private String addfname;//¥��+�����ַ����22��a425
	private String phone;// �绰
	private String email;// ����
	private String verifyCode;// ��֤��

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

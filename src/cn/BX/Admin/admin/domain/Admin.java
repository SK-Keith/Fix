package cn.BX.Admin.admin.domain;

import cn.BX.Users.floor.domain.Floor;
import cn.BX.Users.range.domain.Range;

public class Admin {
	private String aid;//id
	private String email;//��¼��
	private String loginpass;//����
	private String name;//�û���
	private String phone;//�绰����
	private String identity;//���ʶ��1�ǳ�������Ա 2��ά��Ա
	private Floor floor;//¥����
	private Range range;//��Χ��
	private String fname;//����¥��
	private String trange;//����ģ��
	private String tname;//��������

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

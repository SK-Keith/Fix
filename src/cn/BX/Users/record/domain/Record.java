package cn.BX.Users.record.domain;

import java.util.Date;

import cn.BX.Admin.admin.domain.Admin;
import cn.BX.Users.floor.domain.Floor;
import cn.BX.Users.range.domain.Range;
import cn.BX.time.getTimeFormatText;
import cn.BX.user.domain.User;

public class Record {
	private String rid;
	private String details;
	private int status;
	private String address;
	private String image;
	private String date1;// ����ʱ��
	private String date3;// ���㱨��ʱ�������ʱ���
	private String date4;// ��¼ʦ�����ʱ��
	private String date6;// ��¼����ʱ��
	private Date date5;// ����ʱ��
	private String evaluation;// �û�����
	private String message;// ʦ������
	private String number;
	private User user;
	private String aid; // ����Ա���
	private String uid;// ����Ա���
	private Admin admin;// ����ʦ��
	private Range range;// ���޵ķ�Χ���������������
	private Floor floor;// ���޵�¥��
	private int orderBy;

	public Date getDate5() {
		return date5;
	}

	public void setDate5(Date date5) {
		this.date5 = date5;
	}


	public String getDate6() {
		return date6;
	}

	public void setDate6(String date6) {
		this.date6 = date6;
	}

	public String getDate4() {
		return date4;
	}

	public void setDate4(String date4) {
		this.date4 = date4;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	private String reason;// ���ܱ���ԭ��

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	
	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public String getDate3() {
		return date3;
	}

	public void setDate3(String date3) {
		this.date3 = date3;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Record [rid=" + rid + ", details=" + details + ", status=" + status + ", address=" + address
				+ ", image=" + image + ", date1=" + date1 + ", date3=" + date3 + ", date4=" + date4 + ", date6=" + date6
				+ ", date5=" + date5 + ", evaluation=" + evaluation + ", message=" + message + ", number=" + number
				+ ", user=" + user + ", aid=" + aid + ", uid=" + uid + ", admin=" + admin + ", range=" + range
				+ ", floor=" + floor + ", orderBy=" + orderBy + ", reason=" + reason + "]";
	}

}

package cn.BX.table.domain;

import cn.BX.floor.domain.Floor;
import cn.BX.project.domain.Project;
import cn.BX.user.domain.User;

public class Table {
	private String rid;
	private int status;// 维修状态
	private String date1;// 报修时间
	private String date2;// 维修时间
	private String evaluation;// 用户评价
	private String message;// 师傅留言
	private String number;// 报修单号
	private User user;
	private Floor floor;
	private Project project;

	public String getRid() {
		return rid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDate1() {
		return date1;
	}

	public void setDate1(String date1) {
		this.date1 = date1;
	}

	public String getDate2() {
		return date2;
	}

	public void setDate2(String date2) {
		this.date2 = date2;
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
		return "Table [rid=" + rid + ", status=" + status + ", date1=" + date1 + ", date2=" + date2 + ", evaluation="
				+ evaluation + ", message=" + message + ", number=" + number + ", user=" + user + ", floor=" + floor
				+ ", project=" + project + "]";
	}

}

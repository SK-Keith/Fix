package cn.BX.Admin.chat.domain;

import cn.BX.Admin.admin.domain.Admin;

/*
 * 聊天实体类
 */
public class Chat {
	private String cid;// 信息id
	private String date5;// 发送时间与目前时间相比较
	private String date;// 发送时间
	private String name;// 作者
	private String content;// 内容
	private Admin admin;// 管理员，即管理员和维修员

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getDate5() {
		return date5;
	}

	public void setDate5(String date5) {
		this.date5 = date5;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Chat [cid=" + cid + ", date5=" + date5 + ", date=" + date + ", name=" + name + ", content=" + content
				+ ", admin=" + admin + "]";
	}
}

package cn.BX.Admin.chat.domain;

import cn.BX.Admin.admin.domain.Admin;

/*
 * ����ʵ����
 */
public class Chat {
	private String cid;// ��Ϣid
	private String date5;// ����ʱ����Ŀǰʱ����Ƚ�
	private String date;// ����ʱ��
	private String name;// ����
	private String content;// ����
	private Admin admin;// ����Ա��������Ա��ά��Ա

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

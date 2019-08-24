package cn.BX.Users.range.domain;

public class Range {
	private String tid;
	private String tname;
	private String trange;
	private String ttid;

	public String getTtid() {
		return ttid;
	}

	public void setTtid(String ttid) {
		this.ttid = ttid;
	}

	@Override
	public String toString() {
		return "Range [tid=" + tid + ", tname=" + tname + ", trange=" + trange + ", ttid=" + ttid + "]";
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTrange() {
		return trange;
	}

	public void setTrange(String trange) {
		this.trange = trange;
	}

}

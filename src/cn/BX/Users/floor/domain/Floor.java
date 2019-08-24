package cn.BX.Users.floor.domain;

public class Floor {
	private String fid;
	private String fname;
	private String ttid;

	public String getTtid() {
		return ttid;
	}

	public void setTtid(String ttid) {
		this.ttid = ttid;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	@Override
	public String toString() {
		return "Floor [fid=" + fid + ", fname=" + fname + ", ttid=" + ttid + "]";
	}

}

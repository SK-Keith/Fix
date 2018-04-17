package cn.BX.project.domain;

public class Project {
	private String pid;//物件Id
	private String pname;//物品名称
	private String sort;//物品类别
	private String image;//相关图片
	private String details;//故障描述
	private String address;//具体地址

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Project [pid=" + pid + ", pname=" + pname + ", sort=" + sort + ", image=" + image + ", details="
				+ details + ", address=" + address + "]";
	}

}

package com.wb.admin.vo;

import com.wb.admin.bo.User;

public class UserVO extends User {

	private Long nodeid;
    private String nodename;
    private String groupname;
    public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	private Long groupid;
    
	public Long getNodeid() {
		return nodeid;
	}

	public void setNodeid(Long nodeid) {
		this.nodeid = nodeid;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}




}

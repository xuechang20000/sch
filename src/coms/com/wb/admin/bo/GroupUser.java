package com.wb.admin.bo;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Sequence;
import com.wb.jdbcutils.annos.Table;

@Table(name = "app_group_user")
public class GroupUser {
	private Long groupuserid;// number(16) 关系id
	private Long userid;// number(16) y 用户id
	private Long groupid;// number(16) y 用户组id
	private String status;// varchar2(1) y 状态

	@Column(name = "groupuserid", id = true)
	@Sequence(name = "INCREMENT")
	public Long getGroupuserid() {
		return groupuserid;
	}

	public void setGroupuserid(Long groupuserid) {
		this.groupuserid = groupuserid;
	}

	@Column(name = "userid")
	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	@Column(name = "groupid")
	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	@Column(name = "status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

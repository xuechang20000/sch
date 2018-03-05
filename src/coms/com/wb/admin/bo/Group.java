package com.wb.admin.bo;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Sequence;
import com.wb.jdbcutils.annos.Table;

@Table(name="app_group")
public class Group {

	private Long groupid;//	number(16)			用户组id
	private String groupname;//	varchar2(50)	y		用户组名称
	private String grouptype;//	varchar2(2)	y		用户组类别
	private String status;//	varchar2(1)	y		用户组状态
	private String removed;//	varchar2(1)	y		用户组是否移除
	@Column(name="groupid",id=true)
   @Sequence(name="INCREMENT")
	public Long getGroupid() {
		return groupid;
	}
	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}
	@Column(name="groupname")
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	@Column(name="grouptype")
	public String getGrouptype() {
		return grouptype;
	}
	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="removed")
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	

}

package com.wb.admin.bo;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Table;

@Table(name="app_organ_user")
public class OrganUser {
	private Long nodeid;//	number(16)	y		组织机构id
	private Long userid;//	number(16)	y		用户id
	private String enable;//	varchar2(1)	y		是否有效
	@Column(name="nodeid")
	public Long getNodeid() {
		return nodeid;
	}
	public void setNodeid(Long nodeid) {
		this.nodeid = nodeid;
	}
	@Column(name="userid")
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	@Column(name="enable")
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}

}

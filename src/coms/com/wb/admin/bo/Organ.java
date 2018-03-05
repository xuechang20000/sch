package com.wb.admin.bo;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Sequence;
import com.wb.jdbcutils.annos.Table;

@Table(name = "app_organ")
public class Organ {
	private Long nodeid;// number(16) 节点标识
	private Long parentnodeid;// number(16) y 父节点
	private String nodetype;// varchar2(2) y 节点类型
	private String address;// varchar2(200) y 地址
	private String principal;// varchar2(50) y 负责人
	private String phone;// varchar2(20) y 电话
	private String removed;// varchar2(1) y 删除标志
	private String nodename;// varchar2(50) y 节点名称

	@Column(name = "nodeid", id = true)
	@Sequence(name = "INCREMENT")
	public Long getNodeid() {
		return nodeid;
	}

	public void setNodeid(Long nodeid) {
		this.nodeid = nodeid;
	}

	@Column(name = "parentnodeid")
	public Long getParentnodeid() {
		return parentnodeid;
	}

	public void setParentnodeid(Long parentnodeid) {
		this.parentnodeid = parentnodeid;
	}

	@Column(name = "nodetype")
	public String getNodetype() {
		return nodetype;
	}

	public void setNodetype(String nodetype) {
		this.nodetype = nodetype;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "principal")
	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "removed")
	public String getRemoved() {
		return removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "nodename")
	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

}

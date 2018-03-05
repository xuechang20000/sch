package com.wb.admin.bo;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Sequence;
import com.wb.jdbcutils.annos.Table;

@Table(name="app_group_type")
public class GroupType {
	private Long grouptypeid;//	number(16)			用户组类别代码
	private String grouptypename;//	varchar2(50)	y		用户组类别名称
	private String status;//	varchar2(1)	y		用户组类别状态
	private String grouptypeclass;//	varchar2(2)	y		用户组类别分类
	@Column(name="grouptypeid",id=true)
	@Sequence(name="INCREMENT")
	public Long getGrouptypeid() {
		return grouptypeid;
	}
	public void setGrouptypeid(Long grouptypeid) {
		this.grouptypeid = grouptypeid;
	}
	@Column(name="grouptypename")
	public String getGrouptypename() {
		return grouptypename;
	}
	public void setGrouptypename(String grouptypename) {
		this.grouptypename = grouptypename;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="grouptypeclass")
	public String getGrouptypeclass() {
		return grouptypeclass;
	}
	public void setGrouptypeclass(String grouptypeclass) {
		this.grouptypeclass = grouptypeclass;
	}

}

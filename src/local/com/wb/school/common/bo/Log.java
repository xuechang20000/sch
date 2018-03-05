package com.wb.school.common.bo;

import java.util.Date;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Sequence;
import com.wb.jdbcutils.annos.Table;

@Table(name="t_log")
public class Log {
	private Long busilogid ;//           number(16)           not null,
	private Long  userid   ;//            number(16),
	private String  username  ;//           varchar2(50),
	   private Long   stuid  ;//              number(16),
	   private String  name  ;//               varchar2(16),
	   private String  processcode ;//           number(16),
	   private String   stepcode;//              number(16),
	 private Date  ctime    ;//            date,
	 
	 @Column(name="busilogid",id=true)
	@Sequence(name="INCREMENT")
	public Long getBusilogid() {
		return busilogid;
	}
	public void setBusilogid(Long busilogid) {
		this.busilogid = busilogid;
	}
	@Column(name="userid")
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	@Column(name="username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name="stuid")
	public Long getStuid() {
		return stuid;
	}
	public void setStuid(Long stuid) {
		this.stuid = stuid;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="processcode")
	public String getProcesscode() {
		return processcode;
	}
	public void setProcesscode(String processcode) {
		this.processcode = processcode;
	}
	@Column(name="stepcode")
	public String getStepcode() {
		return stepcode;
	}
	public void setStepcode(String stepcode) {
		this.stepcode = stepcode;
	}
	@Column(name="ctime")
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	 
	 
}

package com.wb.login.model;

import java.util.Date;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Sequence;
import com.wb.jdbcutils.annos.Table;
@Table(name="app_log_login")
public class LogLoginBO {

	private Long id;//ID	NUMBER(16)
	private Long userid;//USERID	NUMBER(16)
	private String loginname;//LOGINNAME	NUMBER(16)
	private String password;//PASSWORD	VARCHAR2(32)
	private String pid;//PID	NUMBER(16)
	private String issuccess;//ISSUCCESS	VARCHAR2(1)
	private String message;//MESSAGE	VARCHAR2(200)
	private Date cdate;//CDATE	DATE
	private String ip;//ip address
	@Sequence(name="INCREMENT")
	@Column(id=true,name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name="userid")
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	@Column(name="loginname")
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="pid")
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	@Column(name="issuccess")
	public String getIssuccess() {
		return issuccess;
	}
	public void setIssuccess(String issuccess) {
		this.issuccess = issuccess;
	}
	@Column(name="message")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Column(name="cdate")
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	@Column(name="ip")
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}

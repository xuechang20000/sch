package com.wb.login.model;

import java.util.Date;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Sequence;
import com.wb.jdbcutils.annos.Table;

@Table(name = "app_log_url")
public class LogUrlBO {

	private Long id;// ID NUMBER(16)
	private Long userid;// USERID NUMBER(16)
	private Long groupid;// GROUPID NUMBER(16)
	private String loginname;// LOGINNAME VARCHAR2(20)
	private String url;// URL VARCHAR2(200)
	private String params;// PARAMS VARCHAR2(2000)
	private String headers;// HADERS VARCHAR2(2000)
	private Date cdate;// CDATE DATE
	private Long usetime;// USETIME NUMBER
	private String ip;// ip address

	@Sequence(name = "INCREMENT")
	@Column(id = true, name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Column(name = "loginname")
	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	@Column(name = "url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "params")
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	@Column(name = "headers")
	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	@Column(name = "cdate")
	public Date getCdate() {
		return cdate;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	@Column(name = "usetime")
	public Long getUsetime() {
		return usetime;
	}

	public void setUsetime(Long usetime) {
		this.usetime = usetime;
	}

	@Column(name = "ip")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}

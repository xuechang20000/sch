package com.wb.login.model;

import com.wb.jdbcutils.annos.Callable;
import com.wb.jdbcutils.annos.In;
import com.wb.jdbcutils.annos.Out;

@Callable(name="SP_WSBS_LOGIN")
public class LoginSpDTO {
@In(order=1)
private String loginname;
@In(order=2)
private String password;
@In(order=3)
private String pid;
@Out(order=4)
private String userid;
@Out(order=5)
private String viewname;
@Out(order=6)
private Integer retcode;
@Out(order=7)
private String retmsg;
public String getLoginname() {
	return loginname;
}
public void setLoginname(String loginname) {
	this.loginname = loginname;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getPid() {
	return pid;
}
public void setPid(String pid) {
	this.pid = pid;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getViewname() {
	return viewname;
}
public void setViewname(String viewname) {
	this.viewname = viewname;
}
public Integer getRetcode() {
	return retcode;
}
public void setRetcode(Integer retcode) {
	this.retcode = retcode;
}
public String getRetmsg() {
	return retmsg;
}
public void setRetmsg(String retmsg) {
	this.retmsg = retmsg;
}

}

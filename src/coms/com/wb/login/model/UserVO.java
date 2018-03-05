package com.wb.login.model;

import java.util.Date;

public class UserVO {
	 private Long userid;//undefined
     private String loginname;//undefined
     private String password;//undefined
     private String verifytype;//undefined
     private String name;//undefined
     private String sex;//undefined
     private String stauts;//undefined
     private String removed;//undefined
     private Date ctime;//undefined
     private String ext1;//undefined
     private String ext2;//undefined
     private String ext3;//undefined
     private Long groupid;
     private String groupname;
     private Long grouptypeid;
     private String grouptypename;
     private String grouptypeclass;
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
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
	public String getVerifytype() {
		return verifytype;
	}
	public void setVerifytype(String verifytype) {
		this.verifytype = verifytype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getStauts() {
		return stauts;
	}
	public void setStauts(String stauts) {
		this.stauts = stauts;
	}
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
	public Long getGroupid() {
		return groupid;
	}
	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public Long getGrouptypeid() {
		return grouptypeid;
	}
	public void setGrouptypeid(Long grouptypeid) {
		this.grouptypeid = grouptypeid;
	}
	public String getGrouptypename() {
		return grouptypename;
	}
	public void setGrouptypename(String grouptypename) {
		this.grouptypename = grouptypename;
	}
	public String getGrouptypeclass() {
		return grouptypeclass;
	}
	public void setGrouptypeclass(String grouptypeclass) {
		this.grouptypeclass = grouptypeclass;
	}
     
}

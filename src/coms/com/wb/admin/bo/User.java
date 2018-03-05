package com.wb.admin.bo;

import java.util.Date;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Sequence;
import com.wb.jdbcutils.annos.Table;

@Table(name="app_user")
public class User {
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
    @Column(name="userid",id=true)
   @Sequence(name="INCREMENT")
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
	@Column(name="verifytype")
	public String getVerifytype() {
		return verifytype;
	}
	public void setVerifytype(String verifytype) {
		this.verifytype = verifytype;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="sex")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name="stauts")
	public String getStauts() {
		return stauts;
	}
	public void setStauts(String stauts) {
		this.stauts = stauts;
	}
	@Column(name="removed")
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	@Column(name="ctime")
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	@Column(name="ext1")
	public String getExt1() {
		return ext1;
	}
	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}
	@Column(name="ext2")
	public String getExt2() {
		return ext2;
	}
	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}
	@Column(name="ext3")
	public String getExt3() {
		return ext3;
	}
	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}
     
}

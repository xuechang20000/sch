package com.wb.school.common.bo;

public class Step {
	private long stepid;// STEPID NUMBER(16) 业务步骤ID
	private String processcode;// PROCESSCODE VARCHAR2(2) Y 业务流程ID
	private String stepname;// STEPNAME VARCHAR2(50) Y 业务步骤名称
	private String stepcode;// STEPCODE VARCHAR2(2) Y
	private Long groupid;// GROUPID NUMBER(16) Y 用户组ID
	private Long xh;// XH NUMBER(2) Y 序号

	public long getStepid() {
		return stepid;
	}

	public void setStepid(long stepid) {
		this.stepid = stepid;
	}

	public String getProcesscode() {
		return processcode;
	}

	public void setProcesscode(String processcode) {
		this.processcode = processcode;
	}

	public String getStepname() {
		return stepname;
	}

	public void setStepname(String stepname) {
		this.stepname = stepname;
	}

	public String getStepcode() {
		return stepcode;
	}

	public void setStepcode(String stepcode) {
		this.stepcode = stepcode;
	}

	public Long getGroupid() {
		return groupid;
	}

	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

	public Long getXh() {
		return xh;
	}

	public void setXh(Long xh) {
		this.xh = xh;
	}

}

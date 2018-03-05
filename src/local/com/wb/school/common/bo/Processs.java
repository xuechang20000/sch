package com.wb.school.common.bo;

public class Processs {
	private long processid;//PROCESSID	NUMBER(16)			业务流程Id
	private String processname;//PROCESSNAME	VARCHAR2(100)	Y		业务流程名称
	private String processcode;//PROCESSCODE	VARCHAR2(2)	Y		
	private Integer xh;//XH	NUMBER(2)	Y		序号
	private String enable;//ENABLE	VARCHAR2(1)	Y		有效标志
	public long getProcessid() {
		return processid;
	}
	public void setProcessid(long processid) {
		this.processid = processid;
	}
	public String getProcessname() {
		return processname;
	}
	public void setProcessname(String processname) {
		this.processname = processname;
	}
	public String getProcesscode() {
		return processcode;
	}
	public void setProcesscode(String processcode) {
		this.processcode = processcode;
	}
	public Integer getXh() {
		return xh;
	}
	public void setXh(Integer xh) {
		this.xh = xh;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	
}

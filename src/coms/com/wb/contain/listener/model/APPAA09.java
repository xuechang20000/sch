package com.wb.contain.listener.model;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Sequence;
import com.wb.jdbcutils.annos.Table;

@Table(name="APP_AA09")
public class APPAA09 implements java.io.Serializable{
	private Long aaa001;
	private String aaa100;
	private String aaa101;
	private String aaa104;
	private String aae100;
	
	@Sequence(name="SEQUENCE",sequencename="SEQ_APP_AA09")
	@Column(id=true,name="AAA001")
	public Long getAaa001() {
		return aaa001;
	}
	public void setAaa001(Long aaa001) {
		this.aaa001 = aaa001;
	}
	
	@Column(name="AAA100", length=20)
	public String getAaa100() {
		return aaa100;
	}
	public void setAaa100(String aaa100) {
		this.aaa100 = aaa100;
	}
	
	@Column(name="AAA101", length=50)
	public String getAaa101() {
		return aaa101;
	}
	public void setAaa101(String aaa101) {
		this.aaa101 = aaa101;
	}
	
	@Column(name="AAA104", length=1)
	public String getAaa104() {
		return aaa104;
	}
	public void setAaa104(String aaa104) {
		this.aaa104 = aaa104;
	}
	
	@Column(name="AAE100", length=1)
	public String getAae100() {
		return aae100;
	}
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}
	
	
	
}

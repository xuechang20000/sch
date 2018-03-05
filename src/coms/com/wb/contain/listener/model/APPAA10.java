package com.wb.contain.listener.model;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Sequence;
import com.wb.jdbcutils.annos.Table;

@Table(name="APP_AA10")
public class APPAA10 implements java.io.Serializable{
	private Long aaa002;
	private String aaa100;
	private String aaa102;
	private String aaa103;
	private String aae013;
	private String aae100;
	private int aae030;
	private int aae031;
	private int cae008;
	
	
	@Sequence(name="SEQUENCE",sequencename="SEQ_APP_AA09")
	@Column(id=true,name="AAA002")
	public Long getAaa002() {
		return aaa002;
	}
	public void setAaa002(Long aaa002) {
		this.aaa002 = aaa002;
	}
	
	@Column(name="AAA100", length=20)
	public String getAaa100() {
		return aaa100;
	}
	public void setAaa100(String aaa100) {
		this.aaa100 = aaa100;
	}
	
	@Column(name="AAA102", length=8)
	public String getAaa102() {
		return aaa102;
	}
	public void setAaa102(String aaa102) {
		this.aaa102 = aaa102;
	}
	
	@Column(name="AAA103", length=300)
	public String getAaa103() {
		return aaa103;
	}
	public void setAaa103(String aaa103) {
		this.aaa103 = aaa103;
	}
	
	@Column(name="AAE013", length=100)
	public String getAae013() {
		return aae013;
	}
	public void setAae013(String aae013) {
		this.aae013 = aae013;
	}
	
	@Column(name="AAE100", length=1)
	public String getAae100() {
		return aae100;
	}
	public void setAae100(String aae100) {
		this.aae100 = aae100;
	}
	
	@Column(name="AAE030")
	public int getAae030() {
		return aae030;
	}
	public void setAae030(int aae030) {
		this.aae030 = aae030;
	}
	
	@Column(name="AAE031")
	public int getAae031() {
		return aae031;
	}
	public void setAae031(int aae031) {
		this.aae031 = aae031;
	}
	
	@Column(name="CAE008")
	public int getCae008() {
		return cae008;
	}
	public void setCae008(int cae008) {
		this.cae008 = cae008;
	}
	

	
	
	
}

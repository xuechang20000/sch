package com.wb.school.common.bo;

import java.util.Date;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Sequence;
import com.wb.jdbcutils.annos.Table;

@Table(name = "t_student_pay")
public class StudentPay {

	private Long payid;// number(16) 16 缴费ID
	private Long stuid;// number(16) 16 学生id
	private Integer year;// number(4) 4 年度
	private Integer seqnumber;// number(1) 1 第几年
	private double payable;// number(6,2) 6 2 学费应缴
	private double paidin;// number(6,2) 6 2 学费实缴
	private Date paydate;// TIMESTAMP 学费实缴日期
	private double bookable;// number(6,2) 6 2 书费应缴
	private double bookin;// number(6,2) 6 2 书费实缴
	private Date bookdate;// TIMESTAMP 书费实缴日期
	private String ispay;// varchar2(1) 1 是否缴费
	private Date sdate;// timestamp 开始缴费期限
	private Date edate;// timestamp 结束缴费期限
	private String comments;// 备注

	@Column(name = "payid", id = true)
	@Sequence(name = "INCREMENT")
	public Long getPayid() {
		return payid;
	}

	public void setPayid(Long payid) {
		this.payid = payid;
	}

	@Column(name = "stuid")
	public Long getStuid() {
		return stuid;
	}

	public void setStuid(Long stuid) {
		this.stuid = stuid;
	}

	@Column(name = "year")
	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Column(name = "seqnumber")
	public Integer getSeqnumber() {
		return seqnumber;
	}

	public void setSeqnumber(Integer seqnumber) {
		this.seqnumber = seqnumber;
	}

	@Column(name = "payable")
	public double getPayable() {
		return payable;
	}

	public void setPayable(double payable) {
		this.payable = payable;
	}

	@Column(name = "paidin")
	public double getPaidin() {
		return paidin;
	}

	public void setPaidin(double paidin) {
		this.paidin = paidin;
	}

	@Column(name = "paydate")
	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	@Column(name = "bookable")
	public double getBookable() {
		return bookable;
	}

	public void setBookable(double bookable) {
		this.bookable = bookable;
	}

	@Column(name = "bookin")
	public double getBookin() {
		return bookin;
	}

	public void setBookin(double bookin) {
		this.bookin = bookin;
	}

	@Column(name = "bookdate")
	public Date getBookdate() {
		return bookdate;
	}

	public void setBookdate(Date bookdate) {
		this.bookdate = bookdate;
	}

	@Column(name = "ispay")
	public String getIspay() {
		return ispay;
	}

	public void setIspay(String ispay) {
		this.ispay = ispay;
	}

	@Column(name = "sdate")
	public Date getSdate() {
		return sdate;
	}

	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}

	@Column(name = "edate")
	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "comments")
	public String getComments() {
		return comments;
	}

}

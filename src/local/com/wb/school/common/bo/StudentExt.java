package com.wb.school.common.bo;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Table;

@Table(name="t_student_ext")
public class StudentExt {
	private Long stuid;//	number(16)	16	学生id
	private String isenrollensure;//	varchar2(1)	1	招办信息确认是否完成
	private String isenroll;//	varchar2(1)	1	网上报名信息录入
	private String enrollnumber;//	varchar2(50)	50	准考证领取卡报名序号
	private String isnetpay;//	varchar2(1)	1	网上银行缴费确认
	private String examnumber;//	varchar2(50)	50	准考证号
	private String isexamcard;//	varchar2(1)	1	准考证是否发放
	private String examaddress;//	varchar2(50)	50	考点名称
	private double initscore;//	number(4,2)	4	原始成绩
	private double plusscore;//	number(4,2)	4	加分
	private double finalscore;//	number(4,2)	4	最终成绩
	private String isenterline;//	varchar2(1)	1	是否过录取线
	private String ischeat;//是否作弊
	private String isenter;//	varchar2(1)	1	是否录取
	private String isstudent;//	varchar2(1)	1	是否生成学籍
	private String studentaddress;//	varchar2(50)	50	学籍注册处
	private String finalenter;//	varchar2(10)	10	最终录取结果
	private String examlevel;//	varchar2(20)	20	最终录取层次
	private String examclass;//	varchar2(20)	20	最终录取科类
	private String firstwishschool;//	varchar2(20)	20	最终录取学校
	private String firstwishspecialty;//	varchar2(20)	20	最终录取专业
	private String learningform;//	varchar2(20)	20	最终学习形式
	private String firstwishlength;//	varchar2(20)	20	学制
	private double tuition;//	number(6,2)	6	学费标准
	private double tuitionfirst;//	number(6,2)	6	第一年学费
	private double bookfee;//	number(6,2)	6	书费预收标准
	private String inschooldate;//	varchar2(8)	8	入学时间
	private String outschooldate;//	varchar2(8)	8	预计毕业时间
	private String isgraduate;//	varchar2(1)	1	毕业证书是否发放
	private String comments;//	varchar2(2000)	2000	备注
	@Column(name="stuid",id=true)
	public Long getStuid() {
		return stuid;
	}
	public void setStuid(Long stuid) {
		this.stuid = stuid;
	}
	@Column(name="isenrollensure")
	public String getIsenrollensure() {
		return isenrollensure;
	}
	public void setIsenrollensure(String isenrollensure) {
		this.isenrollensure = isenrollensure;
	}
	@Column(name="isenroll")
	public String getIsenroll() {
		return isenroll;
	}
	public void setIsenroll(String isenroll) {
		this.isenroll = isenroll;
	}
	@Column(name="enrollnumber")
	public String getEnrollnumber() {
		return enrollnumber;
	}
	public void setEnrollnumber(String enrollnumber) {
		this.enrollnumber = enrollnumber;
	}
	@Column(name="isnetpay")
	public String getIsnetpay() {
		return isnetpay;
	}
	public void setIsnetpay(String isnetpay) {
		this.isnetpay = isnetpay;
	}
	@Column(name="examnumber")
	public String getExamnumber() {
		return examnumber;
	}
	public void setExamnumber(String examnumber) {
		this.examnumber = examnumber;
	}
	@Column(name="isexamcard")
	public String getIsexamcard() {
		return isexamcard;
	}
	public void setIsexamcard(String isexamcard) {
		this.isexamcard = isexamcard;
	}
	@Column(name="examaddress")
	public String getExamaddress() {
		return examaddress;
	}
	public void setExamaddress(String examaddress) {
		this.examaddress = examaddress;
	}
	@Column(name="initscore")
	public double getInitscore() {
		return initscore;
	}
	public void setInitscore(double initscore) {
		this.initscore = initscore;
	}
	@Column(name="plusscore")
	public double getPlusscore() {
		return plusscore;
	}
	public void setPlusscore(double plusscore) {
		this.plusscore = plusscore;
	}
	@Column(name="finalscore")
	public double getFinalscore() {
		return finalscore;
	}
	public void setFinalscore(double finalscore) {
		this.finalscore = finalscore;
	}
	@Column(name="isenterline")
	public String getIsenterline() {
		return isenterline;
	}
	public void setIsenterline(String isenterline) {
		this.isenterline = isenterline;
	}
	@Column(name="isenter")
	public String getIsenter() {
		return isenter;
	}
	public void setIsenter(String isenter) {
		this.isenter = isenter;
	}
	@Column(name="isstudent")
	public String getIsstudent() {
		return isstudent;
	}
	public void setIsstudent(String isstudent) {
		this.isstudent = isstudent;
	}
	@Column(name="studentaddress")
	public String getStudentaddress() {
		return studentaddress;
	}
	public void setStudentaddress(String studentaddress) {
		this.studentaddress = studentaddress;
	}
	@Column(name="finalenter")
	public String getFinalenter() {
		return finalenter;
	}
	public void setFinalenter(String finalenter) {
		this.finalenter = finalenter;
	}
	@Column(name="examlevel")
	public String getExamlevel() {
		return examlevel;
	}
	public void setExamlevel(String examlevel) {
		this.examlevel = examlevel;
	}
	@Column(name="examclass")
	public String getExamclass() {
		return examclass;
	}
	public void setExamclass(String examclass) {
		this.examclass = examclass;
	}
	@Column(name="firstwishschool")
	public String getFirstwishschool() {
		return firstwishschool;
	}
	public void setFirstwishschool(String firstwishschool) {
		this.firstwishschool = firstwishschool;
	}
	@Column(name="firstwishspecialty")
	public String getFirstwishspecialty() {
		return firstwishspecialty;
	}
	public void setFirstwishspecialty(String firstwishspecialty) {
		this.firstwishspecialty = firstwishspecialty;
	}
	@Column(name="learningform")
	public String getLearningform() {
		return learningform;
	}
	public void setLearningform(String learningform) {
		this.learningform = learningform;
	}
	@Column(name="firstwishlength")
	public String getFirstwishlength() {
		return firstwishlength;
	}
	public void setFirstwishlength(String firstwishlength) {
		this.firstwishlength = firstwishlength;
	}
	@Column(name="tuition")
	public double getTuition() {
		return tuition;
	}
	public void setTuition(double tuition) {
		this.tuition = tuition;
	}
	@Column(name="tuitionfirst")
	public double getTuitionfirst() {
		return tuitionfirst;
	}
	public void setTuitionfirst(double tuitionfirst) {
		this.tuitionfirst = tuitionfirst;
	}
	@Column(name="bookfee")
	public double getBookfee() {
		return bookfee;
	}
	public void setBookfee(double bookfee) {
		this.bookfee = bookfee;
	}
	@Column(name="inschooldate")
	public String getInschooldate() {
		return inschooldate;
	}
	public void setInschooldate(String inschooldate) {
		this.inschooldate = inschooldate;
	}
	@Column(name="outschooldate")
	public String getOutschooldate() {
		return outschooldate;
	}
	public void setOutschooldate(String outschooldate) {
		this.outschooldate = outschooldate;
	}
	@Column(name="isgraduate")
	public String getIsgraduate() {
		return isgraduate;
	}
	public void setIsgraduate(String isgraduate) {
		this.isgraduate = isgraduate;
	}
	@Column(name="comments")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setIscheat(String ischeat) {
		this.ischeat = ischeat;
	}
	@Column(name="ischeat")
	public String getIscheat() {
		return ischeat;
	}
				

}

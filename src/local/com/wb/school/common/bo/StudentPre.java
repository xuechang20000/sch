package com.wb.school.common.bo;

import java.util.Date;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Sequence;
import com.wb.jdbcutils.annos.Table;

@Table(name="t_pre_student")
public class StudentPre {

	private Long stuid;//	number(16)			学生id
	private String educationtype;//	varchar2(2)	y		学历类别
	private String stu_level;//	varchar2(2)	y		学生级别
	private String stu_name;//	varchar2(50)	y		姓名
	private String sex;//	varchar2(2)	y		性别
	private String nation;//	varchar2(2)	y		民族
	private String politicalstatus;//	varchar2(2)	y		政治面貌
	private String cardid;//	varchar2(18)	y		身份证号
	private String cellphone;//	varchar2(11)	y		手机
	private String phone;//	varchar2(20)	y		联系电话
	private String qq;//	varchar2(20)	y		qq
	private String otherphone;//	varchar2(50)	y		其它联系方式
	private String email;//	varchar2(100)	y		email
	private String company;//	varchar2(100)	y		工作单位
	private String clientclass;//	varchar2(2)	y		客户群
	private String address;//	varchar2(200)	y		家庭住址
	private String blongto;//	varchar2(200)	y		学员信息采集归属地
	private String nearby;//	varchar2(200)	y		考前辅导、事项办理就近区域
	private String oldeducationlevel;//	varchar2(2)	y		原学历层次
	private String examlevel;//	varchar2(2)	y		报考层次
	private String examclass;//	varchar2(2)	y		考试科类
	private String firstwishschool;//	varchar2(2)	y		第一志愿院校
	private String firstwishspecialty;//	varchar2(2)	y		第一志愿专业
	private String learningform;//	varchar2(2)	y		学习形式
	private String firstwishlength;//	varchar2(2)	y		第一志愿学制
	private String collectwishschool;//	varchar2(2)	y		征集志愿院校
	private String collectwishspecialty;//	varchar2(2)	y		征集志愿专业
	private String manualschool;//	varchar2(200)	y		手输院校
	private String manualspecialty;//	varchar2(200)	y		手输专业
	private String manualtype;//	varchar2(200)	y		手输学习形式
	private String manuallength;//	varchar2(200)	y		手输学制
	private String schoolresource;//	varchar2(2)	y		获知学校来源
	private String blongrelation;//	varchar2(2)	y		隶属关系
	private String introducer;//	varchar2(50)	y		介绍人
	private String introducerphone;//	varchar2(50)	y		介绍人电话
	private String netservice;//	varchar2(2)	y		网络客服
	private String performancer;//	varchar2(50)	y		业绩人
	private String oldbelong;//	varchar2(2)	y		学员原归属人
	private Long follow;//	number(16)	y		跟进服务人
	private Long recorder;//	number(16)	y		录入人
	private String entryfee;//	varchar2(2)	y		信息采集报名费
	private String memberlevel;//	varchar2(2)	y		会员级别
	private String istextbook;//	varchar2(2)	y		教材押金缴纳
	private String isdisk;//	varchar2(2)	y		光盘押金缴纳
	private String istextbookcat;//	varchar2(2)	y		辅导教材领取
	private String isgift;//	varchar2(2)	y		礼品领取
	private String iseduserach;//	varchar2(2)	y		专科学历真实可查
	private String isphoto;//	varchar2(2)	y		非当地户口开照相证明
	private String iscreatenormal;//	varchar2(2)	y		生成正式报名信息
	private String comments;//	varchar2(2000)	y		备注
	private Long photoid;//	number(16)	y		照片id
	private String processcode;//	number(16)	y		业务流程id
	private String stepcode;//	number(16)	y		业务步骤id
	private String enabled;//	varchar2(2)	y		有效标志
	private Date ctime;//	date	y		生成时间
	private String isfall;//是否掉队
	private String financenumber;//财务审批票据号
	@Column(name="stuid",id=true)
	@Sequence(name="INCREMENT")
	public Long getStuid() {
		return stuid;
	}
	public void setStuid(Long stuid) {
		this.stuid = stuid;
	}
	 @Column(name="educationtype")
	public String getEducationtype() {
		return educationtype;
	}
	public void setEducationtype(String educationtype) {
		this.educationtype = educationtype;
	}
	 @Column(name="stu_level")
	public String getStu_level() {
		return stu_level;
	}
	public void setStu_level(String stu_level) {
		this.stu_level = stu_level;
	}
	 @Column(name="stu_name")
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	 @Column(name="sex")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	 @Column(name="nation")
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	 @Column(name="politicalstatus")
	public String getPoliticalstatus() {
		return politicalstatus;
	}
	public void setPoliticalstatus(String politicalstatus) {
		this.politicalstatus = politicalstatus;
	}
	 @Column(name="cardid")
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	 @Column(name="cellphone")
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	 @Column(name="phone")
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	 @Column(name="qq")
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	 @Column(name="otherphone")
	public String getOtherphone() {
		return otherphone;
	}
	public void setOtherphone(String otherphone) {
		this.otherphone = otherphone;
	}
	 @Column(name="email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	 @Column(name="company")
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	 @Column(name="clientclass")
	public String getClientclass() {
		return clientclass;
	}
	public void setClientclass(String clientclass) {
		this.clientclass = clientclass;
	}
	 @Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	 @Column(name="blongto")
	public String getBlongto() {
		return blongto;
	}
	public void setBlongto(String blongto) {
		this.blongto = blongto;
	}
	 @Column(name="nearby")
	public String getNearby() {
		return nearby;
	}
	public void setNearby(String nearby) {
		this.nearby = nearby;
	}
	 @Column(name="oldeducationlevel")
	public String getOldeducationlevel() {
		return oldeducationlevel;
	}
	public void setOldeducationlevel(String oldeducationlevel) {
		this.oldeducationlevel = oldeducationlevel;
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
	 @Column(name="collectwishschool")
	public String getCollectwishschool() {
		return collectwishschool;
	}
	public void setCollectwishschool(String collectwishschool) {
		this.collectwishschool = collectwishschool;
	}
	 @Column(name="collectwishspecialty")
	public String getCollectwishspecialty() {
		return collectwishspecialty;
	}
	public void setCollectwishspecialty(String collectwishspecialty) {
		this.collectwishspecialty = collectwishspecialty;
	}
	 @Column(name="manualschool")
	public String getManualschool() {
		return manualschool;
	}
	public void setManualschool(String manualschool) {
		this.manualschool = manualschool;
	}
	 @Column(name="manualspecialty")
	public String getManualspecialty() {
		return manualspecialty;
	}
	public void setManualspecialty(String manualspecialty) {
		this.manualspecialty = manualspecialty;
	}
	 @Column(name="manualtype")
	public String getManualtype() {
		return manualtype;
	}
	public void setManualtype(String manualtype) {
		this.manualtype = manualtype;
	}
	 @Column(name="manuallength")
	public String getManuallength() {
		return manuallength;
	}
	public void setManuallength(String manuallength) {
		this.manuallength = manuallength;
	}
	 @Column(name="schoolresource")
	public String getSchoolresource() {
		return schoolresource;
	}
	public void setSchoolresource(String schoolresource) {
		this.schoolresource = schoolresource;
	}
	 @Column(name="blongrelation")
	public String getBlongrelation() {
		return blongrelation;
	}
	public void setBlongrelation(String blongrelation) {
		this.blongrelation = blongrelation;
	}
	 @Column(name="introducer")
	public String getIntroducer() {
		return introducer;
	}
	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}
	 @Column(name="introducerphone")
	public String getIntroducerphone() {
		return introducerphone;
	}
	public void setIntroducerphone(String introducerphone) {
		this.introducerphone = introducerphone;
	}
	 @Column(name="netservice")
	public String getNetservice() {
		return netservice;
	}
	public void setNetservice(String netservice) {
		this.netservice = netservice;
	}
	 @Column(name="performancer")
	public String getPerformancer() {
		return performancer;
	}
	public void setPerformancer(String performancer) {
		this.performancer = performancer;
	}
	 @Column(name="oldbelong")
	public String getOldbelong() {
		return oldbelong;
	}
	public void setOldbelong(String oldbelong) {
		this.oldbelong = oldbelong;
	}
	 @Column(name="follow")
	public Long getFollow() {
		return follow;
	}
	public void setFollow(Long follow) {
		this.follow = follow;
	}
	 @Column(name="recorder")
	public Long getRecorder() {
		return recorder;
	}
	public void setRecorder(Long recorder) {
		this.recorder = recorder;
	}
	 @Column(name="entryfee")
	public String getEntryfee() {
		return entryfee;
	}
	public void setEntryfee(String entryfee) {
		this.entryfee = entryfee;
	}
	 @Column(name="memberlevel")
	public String getMemberlevel() {
		return memberlevel;
	}
	public void setMemberlevel(String memberlevel) {
		this.memberlevel = memberlevel;
	}
	 @Column(name="istextbook")
	public String getIstextbook() {
		return istextbook;
	}
	public void setIstextbook(String istextbook) {
		this.istextbook = istextbook;
	}
	 @Column(name="isdisk")
	public String getIsdisk() {
		return isdisk;
	}
	public void setIsdisk(String isdisk) {
		this.isdisk = isdisk;
	}
	 @Column(name="istextbookcat")
	public String getIstextbookcat() {
		return istextbookcat;
	}
	public void setIstextbookcat(String istextbookcat) {
		this.istextbookcat = istextbookcat;
	}
	 @Column(name="isgift")
	public String getIsgift() {
		return isgift;
	}
	public void setIsgift(String isgift) {
		this.isgift = isgift;
	}
	 @Column(name="iseduserach")
	public String getIseduserach() {
		return iseduserach;
	}
	public void setIseduserach(String iseduserach) {
		this.iseduserach = iseduserach;
	}
	 @Column(name="isphoto")
	public String getIsphoto() {
		return isphoto;
	}
	public void setIsphoto(String isphoto) {
		this.isphoto = isphoto;
	}
	 @Column(name="iscreatenormal")
	public String getIscreatenormal() {
		return iscreatenormal;
	}
	public void setIscreatenormal(String iscreatenormal) {
		this.iscreatenormal = iscreatenormal;
	}
	 @Column(name="comments")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	 @Column(name="photoid")
	public Long getPhotoid() {
		return photoid;
	}
	public void setPhotoid(Long photoid) {
		this.photoid = photoid;
	}
	 @Column(name="processcode")
	public String getProcesscode() {
		return processcode;
	}
	public void setProcesscode(String processcode) {
		this.processcode = processcode;
	}
	 @Column(name="stepcode")
	public String getStepcode() {
		return stepcode;
	}
	public void setStepcode(String stepcode) {
		this.stepcode = stepcode;
	}
	 @Column(name="enabled")
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	 @Column(name="ctime")
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	@Column(name="isfall")
	public String getIsfall() {
		return isfall;
	}
	public void setIsfall(String isfall) {
		this.isfall = isfall;
	}
	public void setFinancenumber(String financenumber) {
		this.financenumber = financenumber;
	}
	@Column(name="financenumber")
	public String getFinancenumber() {
		return financenumber;
	}

}

package com.wb.school.common.bo;

import java.util.Date;

import com.wb.jdbcutils.annos.Column;
import com.wb.jdbcutils.annos.Sequence;
import com.wb.jdbcutils.annos.Table;

@Table(name = "t_student_remote")
public class StudentRemote {

	private Long stuid;// number(16) 学生id
	private String educationtype2;// varchar2(2) 学历类别
	private String stu_level2;// varchar2(2) 学生级别
	private String stu_name;// varchar2(50) 姓名
	private String sex;// varchar2(2) 性别
	private String nation;// varchar2(2) 民族
	private String politicalstatus;// varchar2(2) 政治面貌
	private String cardid;// varchar2(18) 身份证号
	private String cellphone;// varchar2(11) 手机
	private String phone;// varchar2(20) 联系电话
	private String phone1;// varchar2(20) 电话修改1
	private String phone2;// varchar2(20) 电话修改2
	private String qq;// varchar2(20) qq
	private String otherphone;// varchar2(50) 其它联系方式
	private String email;// varchar2(100) 微信号
	private String company;// varchar2(100) 工作单位
	private String clientclass;// varchar2(2) 客户群
	private String blongto;// varchar2(200) 学员信息采集归属地
	private String address;// varchar2(200) 家庭住址
	private String examlevel2;// varchar2(20) 层次
	private String examclass2;// varchar2(20) 科类
	private String firstwishschool2;// varchar2(20) 院校
	private String firstwishspecialty2;// varchar2(20) 专业
	private String firstwishlength2;// varchar2(20) 学制
	private String schoolresource;// varchar2(2) 获知学校来源
	private String blongrelation;// varchar2(2) 隶属关系
	private String introducer;// varchar2(50) 介绍人
	private String introducerphone;// varchar2(50) 介绍人电话
	private String netservice2;// varchar2(2) 辅导员(客服代表)
	private Long follow;// number(16) 班主任（学习顾问）
	private Long recorder;// number(16) 录入人
	private String performancer;// varchar2(50) 业绩人
	private String entryfee2;// varchar2(2) 报名费
	private String intestfee;// number(10,2) 入学测试费
	private String stunumber;// varchar2(50) 学号
	private String stupassword;// varchar2(50) 密码
	private String examnumber;// varchar2(50) 统考账号
	private String exampassword;// varchar2(50) 统考密码
	private String pcis;// varchar2(2) 计算机证书是否有
	private String pcdealis;// varchar2(2) 计算机证书是否代办
	private String pcdealtime;// TIMESTAMP 计算机证书代办时间
	private String pcdealmoney;// number(10,2) 计算机证书代办金额
	private String pcouttime;// TIMESTAMP 计算机证书出证时间
	private String pcexamis;// varchar2(2) 计算机证书是否申请免考
	private String engis;// varchar2(2) 英语等级证书是否有
	private String engdealis;// varchar2(2) 英语等级证书是否代办
	private String engdealtime;// TIMESTAMP 英语等级证书代办时间
	private String engdealmoney;// number(10,2) 英语等级证书代办金额
	private String engouttime;// TIMESTAMP 英语等级证书出证时间
	private String engexamis;// varchar2(2) 英语等级证书是否申请免考
	private String pctkpassis;// varchar2(2) 计算机统考是否通过
	private String pctkreplaceis;// varchar2(2) 计算机统考是否替考
	private String pctkexamaddress;// varchar2(100) 计算机统考考试地点
	private String pctkpasstime;// TIMESTAMP 计算机统考通过时间
	private String pctkreplacemoney;// number(10,2) 计算机统考替考金额
	private String engtkpassis;// varchar2(2) 英语统考是否通过
	private String engtkreplaceis;// varchar2(2) 英语统考是否替考
	private String engtkexamaddress;// varchar2(100) 英语统考考试地点
	private String engtkpasstime;// TIMESTAMP 英语统考通过时间
	private String engtkreplacemoney;// number(10,2) 英语统考替考金额
	private String tk1is;// varchar2(2) 第一次统考是否
	private String tk1money;// number(10,2) 第一次统考金额
	private String tk1passis;// varchar2(2) 第一次统考是否考过
	private String tk2is;// varchar2(2) 第二次统考是否
	private String tk2money;// number(10,2) 第二次统考金额
	private String tk2passis;// varchar2(2) 第二次统考是否考过
	private String tk3is;// varchar2(2) 第三次统考是否
	private String tk3money;// number(10,2) 第三次统考金额
	private String tk3passis;// varchar2(2) 第三次统考是否考过
	private String tk4is;// varchar2(2) 第四次统考是否
	private String tk4money;// number(10,2) 第四次统考金额
	private String tk4passis;// varchar2(2) 第四次统考是否考过
	private String tk5is;// varchar2(2) 第五次统考是否
	private String tk5money;// number(10,2) 第五次统考金额
	private String tk5passis;// varchar2(2) 第五次统考是否考过
	private String tkallis;// varchar2(2) 大包是否
	private String tkallmoney;// number(10,2) 大包金额
	private String tkallpassis;// varchar2(2) 大包是否通过
	private String workcompleateis;// varchar2(2) 作业是否完成
	private String degreeis;// varchar2(2) 学位证是否报考
	private String degreenotice1time;// TIMESTAMP 学位证第一次通知时间
	private String degreenotice2time;// TIMESTAMP 学位证第二次通知时间
	private String degreepassis;// varchar2(2) 学位证是否通过
	private String degreepasstime;// TIMESTAMP 学位证通过时间
	private String stufeetype;// varchar2(2) 学费标准
	private String stufeemoney;// number(10,2) 学费金额
	private String inschooltime;// TIMESTAMP 入学时间
	private String bookfeetype;// varchar2(2) 书费预收标准
	private String nearbyaddress;// varchar2(100) 就近面授、考试地点
	private String stufee1should;// number(10,2) 第一年缴费应缴
	private String stufee1actual;// number(10,2) 第一年缴费实缴
	private String stufee1is;// varchar2(2) 第一年是否缴费
	private String stufee2should;// number(10,2) 第二年缴费应缴
	private String stufee2actual;// number(10,2) 第二年缴费实缴
	private String stufee2is;// varchar2(2) 第二年是否缴费
	private String bookfeeshould;// number(10,2) 书费应缴
	private String bookfeeactual;// varchar2(2) 书费实缴
	private String bookfeeis;// varchar2(2) 书费是否缴费
	private String sturegisteraddress;// varchar2(2) 学藉注册处
	private String graduatetimeabout;// TIMESTAMP 预计毕业时间
	private String graduatetime;// TIMESTAMP 毕业时间
	private String graduatenumber;// varchar2(50) 毕业证号
	private String graduatecardis;// varchar2(2) 毕业证书是否发放
	private String nowstatus;// varchar2(2) 状态
	private String photoid;// number(16) 照片ID
	private String comments;// varchar2(2000) 备注
	private String enabled;// varchar2(2) 有效标志
	private Date ctime;// TIMESTAMP 生成时间

	@Column(name = "stuid", id = true)
	@Sequence(name = "INCREMENT")
	public Long getStuid() {
		return stuid;
	}

	public void setStuid(Long stuid) {
		this.stuid = stuid;
	}

	@Column(name = "educationtype2")
	public String getEducationtype2() {
		return educationtype2;
	}

	public void setEducationtype2(String educationtype2) {
		this.educationtype2 = educationtype2;
	}

	@Column(name = "stu_level2")
	public String getStu_level2() {
		return stu_level2;
	}

	public void setStu_level2(String stuLevel2) {
		stu_level2 = stuLevel2;
	}

	@Column(name = "stu_name")
	public String getStu_name() {
		return stu_name;
	}

	public void setStu_name(String stuName) {
		stu_name = stuName;
	}

	@Column(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "nation")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	@Column(name = "politicalstatus")
	public String getPoliticalstatus() {
		return politicalstatus;
	}

	public void setPoliticalstatus(String politicalstatus) {
		this.politicalstatus = politicalstatus;
	}

	@Column(name = "cardid")
	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	@Column(name = "cellphone")
	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "phone1")
	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	@Column(name = "phone2")
	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	@Column(name = "qq")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "otherphone")
	public String getOtherphone() {
		return otherphone;
	}

	public void setOtherphone(String otherphone) {
		this.otherphone = otherphone;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "company")
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "clientclass")
	public String getClientclass() {
		return clientclass;
	}

	public void setClientclass(String clientclass) {
		this.clientclass = clientclass;
	}

	@Column(name = "blongto")
	public String getBlongto() {
		return blongto;
	}

	public void setBlongto(String blongto) {
		this.blongto = blongto;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "examlevel2")
	public String getExamlevel2() {
		return examlevel2;
	}

	public void setExamlevel2(String examlevel2) {
		this.examlevel2 = examlevel2;
	}

	@Column(name = "examclass2")
	public String getExamclass2() {
		return examclass2;
	}

	public void setExamclass2(String examclass2) {
		this.examclass2 = examclass2;
	}

	@Column(name = "firstwishschool2")
	public String getFirstwishschool2() {
		return firstwishschool2;
	}

	public void setFirstwishschool2(String firstwishschool2) {
		this.firstwishschool2 = firstwishschool2;
	}

	@Column(name = "firstwishspecialty2")
	public String getFirstwishspecialty2() {
		return firstwishspecialty2;
	}

	public void setFirstwishspecialty2(String firstwishspecialty2) {
		this.firstwishspecialty2 = firstwishspecialty2;
	}

	@Column(name = "firstwishlength2")
	public String getFirstwishlength2() {
		return firstwishlength2;
	}

	public void setFirstwishlength2(String firstwishlength2) {
		this.firstwishlength2 = firstwishlength2;
	}

	@Column(name = "schoolresource")
	public String getSchoolresource() {
		return schoolresource;
	}

	public void setSchoolresource(String schoolresource) {
		this.schoolresource = schoolresource;
	}

	@Column(name = "blongrelation")
	public String getBlongrelation() {
		return blongrelation;
	}

	public void setBlongrelation(String blongrelation) {
		this.blongrelation = blongrelation;
	}

	@Column(name = "introducer")
	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

	@Column(name = "introducerphone")
	public String getIntroducerphone() {
		return introducerphone;
	}

	public void setIntroducerphone(String introducerphone) {
		this.introducerphone = introducerphone;
	}

	@Column(name = "netservice2")
	public String getNetservice2() {
		return netservice2;
	}

	public void setNetservice2(String netservice2) {
		this.netservice2 = netservice2;
	}

	@Column(name = "follow")
	public Long getFollow() {
		return follow;
	}

	public void setFollow(Long follow) {
		this.follow = follow;
	}

	@Column(name = "recorder")
	public Long getRecorder() {
		return recorder;
	}

	public void setRecorder(Long recorder) {
		this.recorder = recorder;
	}

	@Column(name = "performancer")
	public String getPerformancer() {
		return performancer;
	}

	public void setPerformancer(String performancer) {
		this.performancer = performancer;
	}

	@Column(name = "entryfee2")
	public String getEntryfee2() {
		return entryfee2;
	}

	public void setEntryfee2(String entryfee2) {
		this.entryfee2 = entryfee2;
	}

	@Column(name = "intestfee")
	public String getIntestfee() {
		return intestfee;
	}

	public void setIntestfee(String intestfee) {
		this.intestfee = intestfee;
	}

	@Column(name = "stunumber")
	public String getStunumber() {
		return stunumber;
	}

	public void setStunumber(String stunumber) {
		this.stunumber = stunumber;
	}

	@Column(name = "stupassword")
	public String getStupassword() {
		return stupassword;
	}

	public void setStupassword(String stupassword) {
		this.stupassword = stupassword;
	}

	@Column(name = "examnumber")
	public String getExamnumber() {
		return examnumber;
	}

	public void setExamnumber(String examnumber) {
		this.examnumber = examnumber;
	}

	@Column(name = "exampassword")
	public String getExampassword() {
		return exampassword;
	}

	public void setExampassword(String exampassword) {
		this.exampassword = exampassword;
	}

	@Column(name = "pcdealis")
	public String getPcdealis() {
		return pcdealis;
	}

	public void setPcdealis(String pcdealis) {
		this.pcdealis = pcdealis;
	}

	@Column(name = "pcdealtime")
	public String getPcdealtime() {
		return pcdealtime;
	}

	public void setPcdealtime(String pcdealtime) {
		this.pcdealtime = pcdealtime;
	}

	@Column(name = "pcdealmoney")
	public String getPcdealmoney() {
		return pcdealmoney;
	}

	public void setPcdealmoney(String pcdealmoney) {
		this.pcdealmoney = pcdealmoney;
	}

	@Column(name = "pcouttime")
	public String getPcouttime() {
		return pcouttime;
	}

	public void setPcouttime(String pcouttime) {
		this.pcouttime = pcouttime;
	}

	@Column(name = "pcexamis")
	public String getPcexamis() {
		return pcexamis;
	}

	public void setPcexamis(String pcexamis) {
		this.pcexamis = pcexamis;
	}

	@Column(name = "engdealis")
	public String getEngdealis() {
		return engdealis;
	}

	public void setEngdealis(String engdealis) {
		this.engdealis = engdealis;
	}

	@Column(name = "engdealtime")
	public String getEngdealtime() {
		return engdealtime;
	}

	public void setEngdealtime(String engdealtime) {
		this.engdealtime = engdealtime;
	}

	@Column(name = "engdealmoney")
	public String getEngdealmoney() {
		return engdealmoney;
	}

	public void setEngdealmoney(String engdealmoney) {
		this.engdealmoney = engdealmoney;
	}

	@Column(name = "engouttime")
	public String getEngouttime() {
		return engouttime;
	}

	public void setEngouttime(String engouttime) {
		this.engouttime = engouttime;
	}

	@Column(name = "engexamis")
	public String getEngexamis() {
		return engexamis;
	}

	public void setEngexamis(String engexamis) {
		this.engexamis = engexamis;
	}

	@Column(name = "pctkpassis")
	public String getPctkpassis() {
		return pctkpassis;
	}

	public void setPctkpassis(String pctkpassis) {
		this.pctkpassis = pctkpassis;
	}

	@Column(name = "pctkreplaceis")
	public String getPctkreplaceis() {
		return pctkreplaceis;
	}

	public void setPctkreplaceis(String pctkreplaceis) {
		this.pctkreplaceis = pctkreplaceis;
	}

	@Column(name = "pctkexamaddress")
	public String getPctkexamaddress() {
		return pctkexamaddress;
	}

	public void setPctkexamaddress(String pctkexamaddress) {
		this.pctkexamaddress = pctkexamaddress;
	}

	@Column(name = "pctkpasstime")
	public String getPctkpasstime() {
		return pctkpasstime;
	}

	public void setPctkpasstime(String pctkpasstime) {
		this.pctkpasstime = pctkpasstime;
	}

	@Column(name = "pctkreplacemoney")
	public String getPctkreplacemoney() {
		return pctkreplacemoney;
	}

	public void setPctkreplacemoney(String pctkreplacemoney) {
		this.pctkreplacemoney = pctkreplacemoney;
	}

	@Column(name = "engtkpassis")
	public String getEngtkpassis() {
		return engtkpassis;
	}

	public void setEngtkpassis(String engtkpassis) {
		this.engtkpassis = engtkpassis;
	}

	@Column(name = "engtkreplaceis")
	public String getEngtkreplaceis() {
		return engtkreplaceis;
	}

	public void setEngtkreplaceis(String engtkreplaceis) {
		this.engtkreplaceis = engtkreplaceis;
	}

	@Column(name = "engtkexamaddress")
	public String getEngtkexamaddress() {
		return engtkexamaddress;
	}

	public void setEngtkexamaddress(String engtkexamaddress) {
		this.engtkexamaddress = engtkexamaddress;
	}

	@Column(name = "engtkpasstime")
	public String getEngtkpasstime() {
		return engtkpasstime;
	}

	public void setEngtkpasstime(String engtkpasstime) {
		this.engtkpasstime = engtkpasstime;
	}

	@Column(name = "engtkreplacemoney")
	public String getEngtkreplacemoney() {
		return engtkreplacemoney;
	}

	public void setEngtkreplacemoney(String engtkreplacemoney) {
		this.engtkreplacemoney = engtkreplacemoney;
	}

	@Column(name = "tk1is")
	public String getTk1is() {
		return tk1is;
	}

	public void setTk1is(String tk1is) {
		this.tk1is = tk1is;
	}

	@Column(name = "tk1money")
	public String getTk1money() {
		return tk1money;
	}

	public void setTk1money(String tk1money) {
		this.tk1money = tk1money;
	}

	@Column(name = "tk1passis")
	public String getTk1passis() {
		return tk1passis;
	}

	public void setTk1passis(String tk1passis) {
		this.tk1passis = tk1passis;
	}

	@Column(name = "tk2is")
	public String getTk2is() {
		return tk2is;
	}

	public void setTk2is(String tk2is) {
		this.tk2is = tk2is;
	}

	@Column(name = "tk2money")
	public String getTk2money() {
		return tk2money;
	}

	public void setTk2money(String tk2money) {
		this.tk2money = tk2money;
	}

	@Column(name = "tk2passis")
	public String getTk2passis() {
		return tk2passis;
	}

	public void setTk2passis(String tk2passis) {
		this.tk2passis = tk2passis;
	}

	@Column(name = "tk3is")
	public String getTk3is() {
		return tk3is;
	}

	public void setTk3is(String tk3is) {
		this.tk3is = tk3is;
	}

	@Column(name = "tk3money")
	public String getTk3money() {
		return tk3money;
	}

	public void setTk3money(String tk3money) {
		this.tk3money = tk3money;
	}

	@Column(name = "tk3passis")
	public String getTk3passis() {
		return tk3passis;
	}

	public void setTk3passis(String tk3passis) {
		this.tk3passis = tk3passis;
	}

	@Column(name = "tk4is")
	public String getTk4is() {
		return tk4is;
	}

	public void setTk4is(String tk4is) {
		this.tk4is = tk4is;
	}

	@Column(name = "tk4money")
	public String getTk4money() {
		return tk4money;
	}

	public void setTk4money(String tk4money) {
		this.tk4money = tk4money;
	}

	@Column(name = "tk4passis")
	public String getTk4passis() {
		return tk4passis;
	}

	public void setTk4passis(String tk4passis) {
		this.tk4passis = tk4passis;
	}

	@Column(name = "tk5is")
	public String getTk5is() {
		return tk5is;
	}

	public void setTk5is(String tk5is) {
		this.tk5is = tk5is;
	}

	@Column(name = "tk5money")
	public String getTk5money() {
		return tk5money;
	}

	public void setTk5money(String tk5money) {
		this.tk5money = tk5money;
	}

	@Column(name = "tk5passis")
	public String getTk5passis() {
		return tk5passis;
	}

	public void setTk5passis(String tk5passis) {
		this.tk5passis = tk5passis;
	}

	@Column(name = "tkallis")
	public String getTkallis() {
		return tkallis;
	}

	public void setTkallis(String tkallis) {
		this.tkallis = tkallis;
	}

	@Column(name = "tkallmoney")
	public String getTkallmoney() {
		return tkallmoney;
	}

	public void setTkallmoney(String tkallmoney) {
		this.tkallmoney = tkallmoney;
	}

	@Column(name = "tkallpassis")
	public String getTkallpassis() {
		return tkallpassis;
	}

	public void setTkallpassis(String tkallpassis) {
		this.tkallpassis = tkallpassis;
	}

	@Column(name = "workcompleateis")
	public String getWorkcompleateis() {
		return workcompleateis;
	}

	public void setWorkcompleateis(String workcompleateis) {
		this.workcompleateis = workcompleateis;
	}

	@Column(name = "degreeis")
	public String getDegreeis() {
		return degreeis;
	}

	public void setDegreeis(String degreeis) {
		this.degreeis = degreeis;
	}

	@Column(name = "degreenotice1time")
	public String getDegreenotice1time() {
		return degreenotice1time;
	}

	public void setDegreenotice1time(String degreenotice1time) {
		this.degreenotice1time = degreenotice1time;
	}

	@Column(name = "degreenotice2time")
	public String getDegreenotice2time() {
		return degreenotice2time;
	}

	public void setDegreenotice2time(String degreenotice2time) {
		this.degreenotice2time = degreenotice2time;
	}

	@Column(name = "degreepassis")
	public String getDegreepassis() {
		return degreepassis;
	}

	public void setDegreepassis(String degreepassis) {
		this.degreepassis = degreepassis;
	}

	@Column(name = "degreepasstime")
	public String getDegreepasstime() {
		return degreepasstime;
	}

	public void setDegreepasstime(String degreepasstime) {
		this.degreepasstime = degreepasstime;
	}

	@Column(name = "stufeetype")
	public String getStufeetype() {
		return stufeetype;
	}

	public void setStufeetype(String stufeetype) {
		this.stufeetype = stufeetype;
	}

	@Column(name = "stufeemoney")
	public String getStufeemoney() {
		return stufeemoney;
	}

	public void setStufeemoney(String stufeemoney) {
		this.stufeemoney = stufeemoney;
	}

	@Column(name = "inschooltime")
	public String getInschooltime() {
		return inschooltime;
	}

	public void setInschooltime(String inschooltime) {
		this.inschooltime = inschooltime;
	}

	@Column(name = "bookfeetype")
	public String getBookfeetype() {
		return bookfeetype;
	}

	public void setBookfeetype(String bookfeetype) {
		this.bookfeetype = bookfeetype;
	}

	@Column(name = "nearbyaddress")
	public String getNearbyaddress() {
		return nearbyaddress;
	}

	public void setNearbyaddress(String nearbyaddress) {
		this.nearbyaddress = nearbyaddress;
	}

	@Column(name = "stufee1should")
	public String getStufee1should() {
		return stufee1should;
	}

	public void setStufee1should(String stufee1should) {
		this.stufee1should = stufee1should;
	}

	@Column(name = "stufee1actual")
	public String getStufee1actual() {
		return stufee1actual;
	}

	public void setStufee1actual(String stufee1actual) {
		this.stufee1actual = stufee1actual;
	}

	@Column(name = "stufee1is")
	public String getStufee1is() {
		return stufee1is;
	}

	public void setStufee1is(String stufee1is) {
		this.stufee1is = stufee1is;
	}

	@Column(name = "stufee2should")
	public String getStufee2should() {
		return stufee2should;
	}

	public void setStufee2should(String stufee2should) {
		this.stufee2should = stufee2should;
	}

	@Column(name = "stufee2actual")
	public String getStufee2actual() {
		return stufee2actual;
	}

	public void setStufee2actual(String stufee2actual) {
		this.stufee2actual = stufee2actual;
	}

	@Column(name = "stufee2is")
	public String getStufee2is() {
		return stufee2is;
	}

	public void setStufee2is(String stufee2is) {
		this.stufee2is = stufee2is;
	}

	@Column(name = "bookfeeshould")
	public String getBookfeeshould() {
		return bookfeeshould;
	}

	public void setBookfeeshould(String bookfeeshould) {
		this.bookfeeshould = bookfeeshould;
	}

	@Column(name = "bookfeeactual")
	public String getBookfeeactual() {
		return bookfeeactual;
	}

	public void setBookfeeactual(String bookfeeactual) {
		this.bookfeeactual = bookfeeactual;
	}

	@Column(name = "bookfeeis")
	public String getBookfeeis() {
		return bookfeeis;
	}

	public void setBookfeeis(String bookfeeis) {
		this.bookfeeis = bookfeeis;
	}

	@Column(name = "sturegisteraddress")
	public String getSturegisteraddress() {
		return sturegisteraddress;
	}

	public void setSturegisteraddress(String sturegisteraddress) {
		this.sturegisteraddress = sturegisteraddress;
	}

	@Column(name = "graduatetimeabout")
	public String getGraduatetimeabout() {
		return graduatetimeabout;
	}

	public void setGraduatetimeabout(String graduatetimeabout) {
		this.graduatetimeabout = graduatetimeabout;
	}

	@Column(name = "graduatetime")
	public String getGraduatetime() {
		return graduatetime;
	}

	public void setGraduatetime(String graduatetime) {
		this.graduatetime = graduatetime;
	}

	@Column(name = "graduatenumber")
	public String getGraduatenumber() {
		return graduatenumber;
	}

	public void setGraduatenumber(String graduatenumber) {
		this.graduatenumber = graduatenumber;
	}

	@Column(name = "graduatecardis")
	public String getGraduatecardis() {
		return graduatecardis;
	}

	public void setGraduatecardis(String graduatecardis) {
		this.graduatecardis = graduatecardis;
	}

	@Column(name = "nowstatus")
	public String getNowstatus() {
		return nowstatus;
	}

	public void setNowstatus(String nowstatus) {
		this.nowstatus = nowstatus;
	}

	@Column(name = "photoid")
	public String getPhotoid() {
		return photoid;
	}

	public void setPhotoid(String photoid) {
		this.photoid = photoid;
	}

	@Column(name = "comments")
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Column(name = "enabled")
	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	@Column(name = "ctime")
	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public void setPcis(String pcis) {
		this.pcis = pcis;
	}

	@Column(name = "pcis")
	public String getPcis() {
		return pcis;
	}

	public void setEngis(String engis) {
		this.engis = engis;
	}

	@Column(name = "engis")
	public String getEngis() {
		return engis;
	}

}

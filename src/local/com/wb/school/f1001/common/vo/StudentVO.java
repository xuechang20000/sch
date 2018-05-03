package com.wb.school.f1001.common.vo;

import com.wb.school.common.bo.Student;

public class StudentVO extends Student {

	private String groupname;
	private String grouptypeclass;
	private String recorderor;//RECORDEROR;
	private String followor;//FOLLOWOR;
	private String examlevelor;
	private String examclassor;//EXAMCLASSor;
	private String firstwishschoolor;//FIRSTWISHSCHOOLor;
	private String firstwishspecialtyor;//FIRSTWISHSPECIALTYor;
	private String learningformor;//LEARNINGFORMor;
	private String s_date;
	private String e_date;
	private String processname;
	private String stepname;
	private String isstudent;
	private String proce_stepname;
	private Long vcount;
	private String notNext;//是否跳到下一步
	private String remind;//是否已经生日提醒
	private String birthday;
	public String getProcessname() {
		return processname;
	}
	public void setProcessname(String processname) {
		this.processname = processname;
	}
	public String getStepname() {
		return stepname;
	}
	public void setStepname(String stepname) {
		this.stepname = stepname;
	}
	public String getS_date() {
		return s_date;
	}
	public void setS_date(String s_date) {
		this.s_date = s_date;
	}
	public String getE_date() {
		return e_date;
	}
	public void setE_date(String e_date) {
		this.e_date = e_date;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getRecorderor() {
		return recorderor;
	}
	public void setRecorderor(String recorderor) {
		this.recorderor = recorderor;
	}
	public String getFollowor() {
		return followor;
	}
	public void setFollowor(String followor) {
		this.followor = followor;
	}
	public String getExamlevelor() {
		return examlevelor;
	}
	public void setExamlevelor(String examlevelor) {
		this.examlevelor = examlevelor;
	}
	public String getExamclassor() {
		return examclassor;
	}
	public void setExamclassor(String examclassor) {
		this.examclassor = examclassor;
	}
	public String getFirstwishschoolor() {
		return firstwishschoolor;
	}
	public void setFirstwishschoolor(String firstwishschoolor) {
		this.firstwishschoolor = firstwishschoolor;
	}
	public String getFirstwishspecialtyor() {
		return firstwishspecialtyor;
	}
	public void setFirstwishspecialtyor(String firstwishspecialtyor) {
		this.firstwishspecialtyor = firstwishspecialtyor;
	}
	public String getLearningformor() {
		return learningformor;
	}
	public void setLearningformor(String learningformor) {
		this.learningformor = learningformor;
	}
	public String getGrouptypeclass() {
		return grouptypeclass;
	}
	public void setGrouptypeclass(String grouptypeclass) {
		this.grouptypeclass = grouptypeclass;
	}
	public Long getVcount() {
		return vcount;
	}
	public void setVcount(Long vcount) {
		this.vcount = vcount;
	}
	public void setProce_stepname(String proce_stepname) {
		this.proce_stepname = proce_stepname;
	}
	public String getProce_stepname() {
		return proce_stepname;
	}
	public void setIsstudent(String isstudent) {
		this.isstudent = isstudent;
	}
	public String getIsstudent() {
		return isstudent;
	}
	public void setNotNext(String notNext) {
		this.notNext = notNext;
	}
	public String getNotNext() {
		return notNext;
	}
	public void setRemind(String remind) {
		this.remind = remind;
	}
	public String getRemind() {
		return remind;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getBirthday() {
		return birthday;
	}
	
	
}

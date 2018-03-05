package com.wb.school.f1001.common.vo;

import com.wb.school.common.bo.StudentExt;

public class StudentExtVO extends StudentExt {

	private String stu_name;
	private String oldeducationlevel;
	private String stu_cardid;

	public String getStu_cardid() {
		return stu_cardid;
	}

	public void setStu_cardid(String stuCardid) {
		stu_cardid = stuCardid;
	}

	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}

	public String getStu_name() {
		return stu_name;
	}

	public void setOldeducationlevel(String oldeducationlevel) {
		this.oldeducationlevel = oldeducationlevel;
	}

	public String getOldeducationlevel() {
		return oldeducationlevel;
	}
}

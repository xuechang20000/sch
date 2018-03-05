package com.wb.school.f1002.service;

import com.wb.jdbcutils.Page;
import com.wb.school.common.bo.StudentRemote;
import com.wb.school.f1002.vo.StudentRemoteVO;





/**
 * 职工信息查询公用Service
 * @author xue
 * 2015-9-25
 */
public interface RemoteService {

	public StudentRemote execStudentRemoteAdd(StudentRemote stu);
	public StudentRemote execStudentRemoteUpdate(StudentRemoteVO stuVO);
	public void queryStuListByCurentUserPub(Page page,StudentRemoteVO vo,String seq_year);
	public StudentRemoteVO queryStudentRemoteBystuid(Long stuid);
	public void deleteRemoteStudentByStuid(String stuid);
}

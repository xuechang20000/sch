package com.wb.school.f1001.common.service;

import java.util.List;

import com.wb.admin.bo.User;
import com.wb.jdbcutils.CommonJdbcUtils;
import com.wb.jdbcutils.Page;
import com.wb.school.common.bo.Student;
import com.wb.school.common.bo.StudentExt;
import com.wb.school.common.bo.StudentPay;
import com.wb.school.f1001.common.vo.ExportSetVO;
import com.wb.school.f1001.common.vo.ProStepParmVO;
import com.wb.school.f1001.common.vo.ProcessDTO;
import com.wb.school.f1001.common.vo.StudentExtVO;
import com.wb.school.f1001.common.vo.StudentVO;




/**
 * 职工信息查询公用Service
 * @author xue
 * 2015-9-25
 */
public interface EmpService {
	public List<User> loadAllUser();
	public void addStudent(Student stu);
	public void addStudentExt(Student stu);
	public StudentExtVO queryStuExtByid(Long stuid);
	public StudentExt updateStuExt(StudentExtVO vo);
	public void addStudentPay(Student stu);
	public List<StudentPay> createPayList(String length,String date);
	public void gotoNextStep(ProcessDTO dto);
	public void saveLog(ProcessDTO dto,Long stuid,String stuName);
	public void queryStuListByCurentUser(Page page,StudentVO vo);
	public void queryStuListByCurentUserPub(Page page,StudentVO vo);
	public void queryExport(Page page,StudentVO vo);
	public List<ExportSetVO> queryExportSets(String iscall,String isstudent);
	public List<ExportSetVO> queryExportSets(String arrays);
	public void queryStuListByCurentUser(Page page);
	public StudentVO queryStudnetByStuid(String stuid);
	public Student updateStudent(StudentVO vo);
	public List<StudentPay> queryStudentPayByStuid(Long stuid);
	public void queryStudentPayByStuid(Page page,Long stuid);
	public StudentPay queryStudentPayByPayid(Long payid);
	public void updateStudentPay(StudentPay pay);
	public Student execNextStep(StudentVO vo);
	public void execPatchNextStep(String [] stuidArray);
	public Student execFall(Long stuid,String flag);
	public void deleteStudent(String stuid);
	public List<ProStepParmVO> queryProcessStepParams(String param);
	public void saveBirthdayRemind();
	void queryStuListByBirthdayRemind(Page page, StudentVO vo);
	public void updateBirthdayRemind(Long stuid);
}

package com.wb.school.common.service;

import java.util.List;

import com.wb.admin.bo.User;
import com.wb.school.common.bo.School;

/**
 * 个人查询工程共用Service
 * @author xue
 * 2015-9-27
 */
public interface CommonService {
	public void updateUser(User user);
	public User loadUser(Long userid);
	public void updatePassword(Long userid,String newPassword);
	public List<School> querySubSchoolsById(Long parentid);
	public void addSchool(School school);
	public void deleteSchool(Long id);
	public School querySchoolByid(Long id);
}

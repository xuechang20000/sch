package com.wb.school.common.service;

import java.util.List;

import com.wb.admin.bo.User;
import com.wb.jdbcutils.CommonJdbcUtils;
import com.wb.school.common.bo.School;

/**
 * 个人查询工程共用Service
 * @author xue
 * 2015-9-27
 */
public class CommonServiceImpl implements CommonService{

	@Override
	public void updateUser(User user){
		CommonJdbcUtils.update(user);
	}
	@Override
	public User loadUser(Long userid){
		return CommonJdbcUtils.queryFirst("select * from app_user where userid=? and removed='0' ", User.class, userid);
	}
	@Override
	public void updatePassword(Long userid,String newPassword){
		CommonJdbcUtils.execute("update app_user set password=? where userid=?",newPassword,userid);
	}
	@Override
	public List<School> querySubSchoolsById(Long parentid){
		if(parentid!=null&&parentid>0){
			String sql="SELECT * FROM t_school where parentid=? and removed='0'";
			return CommonJdbcUtils.query(sql, School.class,parentid);
		}
		String sql="SELECT * FROM t_school where parentid is null and removed='0'";
		return CommonJdbcUtils.query(sql, School.class);
	}
	@Override
	public void addSchool(School school){
		String sql="insert into t_school(id,parentid,name,type,ext,removed) " +
				"values (null,?,?,?,?,'0')";
		CommonJdbcUtils.execute(sql,school.getParentid(),school.getName(),school.getType(),school.getExt());
		
	}
	@Override
	public void deleteSchool(Long id){
		String sql="update t_school set removed='1' where id=?";
		CommonJdbcUtils.execute(sql,id);
	}
	@Override
	public School querySchoolByid(Long id){
		String sql="select * from t_school where id=?";
		return CommonJdbcUtils.queryFirst(sql, School.class, id);
	}
}

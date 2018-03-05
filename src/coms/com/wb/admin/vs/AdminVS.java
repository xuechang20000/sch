package com.wb.admin.vs;

import java.util.List;

import javax.servlet.ServletContext;

import com.wb.admin.bo.Group;
import com.wb.admin.bo.GroupType;
import com.wb.admin.bo.Organ;
import com.wb.admin.bo.User;
import com.wb.admin.vo.TreeDTO;
import com.wb.admin.vo.UserVO;
import com.wb.contain.listener.model.APPAA09;
import com.wb.contain.listener.model.APPAA10;
import com.wb.jdbcutils.Page;

public interface AdminVS {
	public List<TreeDTO> queryTree();
	public List<TreeDTO> querySubTreesByNodeid(Long nodeid);
	public void queryUsersByNodeid(Page page,Long nodeid);
	public void queryUsersByKeyWords(Page page,String key);
	public void addOrgan(Organ organ);
	public void updateOrgan(Organ organ);
	public void deleteOrgan(Long nodeid);
	public Organ queryOrganByNodeid(Long nodeid);
	public void addUser(UserVO vo,List<Group> groups);
	public void deleteUser(Long userid);
	public User updateUser(UserVO vo,List<Group> groups);
	public void initUserPassword(Long userid);
	public User queryUserById(Long userid);
	public void queryAllGroups(Page page,String groupname);
	public List<Group> queryGroupByUserid(Long userid);
	public List<GroupType> queryAllGroupType();
	public void saveGroup(String groupName,String groupTypeId);
	public void updateGroup(Group group);
	public void deleteGroup(Group group);
	public Group queryGroupByGroupid(Long groupid);
	public List<APPAA09> queryAa09list();
	public List<APPAA10> queryAa10list(String aaa100);
	public void addAa10(String aaa100,String aaa103);
	public void deleteAa10(String aaa100,String aaa102);
	public void refreashContext(ServletContext context,String aaa100 );
}

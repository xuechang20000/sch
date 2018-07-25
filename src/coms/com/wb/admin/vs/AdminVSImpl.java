package com.wb.admin.vs;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;

import com.wb.admin.bo.Group;
import com.wb.admin.bo.GroupType;
import com.wb.admin.bo.GroupUser;
import com.wb.admin.bo.Organ;
import com.wb.admin.bo.OrganUser;
import com.wb.admin.bo.User;
import com.wb.admin.vo.TreeDTO;
import com.wb.admin.vo.UserVO;
import com.wb.contain.listener.model.APPAA09;
import com.wb.contain.listener.model.APPAA10;
import com.wb.exceptions.BusinessException;
import com.wb.jdbcutils.CommonJdbcUtils;
import com.wb.jdbcutils.Page;
import com.wb.utils.web.Md5Utils;

public class AdminVSImpl implements AdminVS {

	/**
	 * 查询组织架构列表
	 * 
	 * @return
	 */
	@Override
	public List<TreeDTO> queryTree() {
		String sql = "SELECT nodeid ID,nodename text,Parentnodeid pid FROM app_organ WHERE Removed='0'";
		return CommonJdbcUtils.query(sql, TreeDTO.class);
	}

	@Override
	public List<TreeDTO> querySubTreesByNodeid(Long nodeid) {
		String sql = "SELECT nodeid ID,nodename text,Parentnodeid pid FROM app_organ WHERE Removed='0' and parentnodeid=?";
		return CommonJdbcUtils.query(sql, TreeDTO.class, nodeid);
	}

	/**
	 * 查询组织下人员列表
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public void queryUsersByNodeid(Page page, Long nodeid) {
		String sql = "SELECT a.nodeid,c.nodename,e.groupname,b.* FROM app_organ_user a,app_user b,app_organ c,app_group_user d,app_group e "
				+ "WHERE a.userid=b.userid AND a.nodeid=c.nodeid AND a.userid=d.userid "
				+ "AND d.groupid=e.groupid  AND c.removed='0' AND d.status='1' "
				+ "AND e.removed='0' AND e.status='1' AND  a.nodeid=? AND a.enable='1' and b.removed='0'";
		CommonJdbcUtils.queryPage(page, sql, UserVO.class, nodeid);
	}

	/**
	 * 查询组织下人员列表
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public void queryUsersByKeyWords(Page page, String key) {
		if (key != null && key.length() > 20)
			return;
		String sql = "SELECT a.nodeid,c.nodename,e.groupname,b.* FROM app_organ_user a,app_user b,app_organ c,app_group_user d,app_group e "
				+ "WHERE a.userid=b.userid AND a.nodeid=c.nodeid AND a.userid=d.userid "
				+ "AND d.groupid=e.groupid  AND c.removed='0' AND d.status='1' "
				+ "AND e.removed='0' AND e.status='1'  AND a.enable='1' and b.removed='0'";
		if (key != null && key.length() > 0) {
			sql = sql + " AND ( b.name like '%" + key + "%' or  b.loginname like '%" + key + "%' )";
		}
		CommonJdbcUtils.queryPage(page, sql, UserVO.class);
	}

	/**
	 * 添加组织
	 * 
	 * @param organ
	 */
	@Override
	public void addOrgan(Organ organ) {
		if (organ.getNodeid() != null) {
			Organ org = queryOrganByNodeid(organ.getNodeid());
			org.setNodename(organ.getNodename());
			CommonJdbcUtils.update(org);
		} else {
			CommonJdbcUtils.save(organ);
		}

	}

	/**
	 * 更新组织
	 * 
	 * @param organ
	 */
	@Override
	public void updateOrgan(Organ organ) {
		CommonJdbcUtils.update(organ);
	}

	/**
	 * 删除组织
	 * 
	 * @param organ
	 */
	@Override
	public void deleteOrgan(Long nodeid) {
		List<TreeDTO> dtos = querySubTreesByNodeid(nodeid);
		if (dtos != null && dtos.size() > 0) {
			throw new BusinessException("此节点下存在子节点，不允许删除！");
		}
		CommonJdbcUtils.execute("update app_organ set removed='1' where nodeid=?", nodeid);
	}

	/**
	 * 根据ID查询组织架构
	 * 
	 * @return
	 */
	@Override
	public Organ queryOrganByNodeid(Long nodeid) {
		String sql = "SELECT * FROM app_organ WHERE Removed='0' and nodeid=?";
		return CommonJdbcUtils.queryFirst(sql, Organ.class, nodeid);
	}

	/***
	 * 添加用户
	 * 
	 * @param vo
	 */
	@Override
	public void addUser(UserVO vo, List<Group> groups) {

		if (queryUserByLoginname(vo.getLoginname()) != null) {
			throw new BusinessException("此账号已经存在！");
		}
		;
		// 保存用户
		User user = new User();
		BeanUtils.copyProperties(vo, user);
		user.setStauts("1");
		user.setRemoved("0");
		user.setCtime(new Date());
		user.setVerifytype("1");
		String password = Md5Utils.MD5Code(user.getLoginname());
		user.setPassword(password);
		CommonJdbcUtils.save(user);

		// 保存机构用户
		OrganUser ou = new OrganUser();
		ou.setNodeid(vo.getNodeid());
		ou.setUserid(user.getUserid());
		ou.setEnable("1");
		CommonJdbcUtils.save(ou);
		// 保存组织用户
		GroupUser gu;
		for (Group group : groups) {
			gu = new GroupUser();
			gu.setGroupid(group.getGroupid());
			gu.setUserid(ou.getUserid());
			gu.setStatus("1");
			CommonJdbcUtils.save(gu);
		}

	}

	@Override
	public void deleteUser(Long userid,String recorder) {
		String sql = "update app_user set removed='1' where userid=?";
		CommonJdbcUtils.execute(sql, userid);
		sql="update t_student set RECORDER=?,FOLLOW=? where RECORDER=?";
		CommonJdbcUtils.execute(sql,recorder,recorder,userid);
	}

	@Override
	public User updateUser(UserVO vo, List<Group> groups) {
		// 更新用户
		User user = queryUserById(vo.getUserid());
		user.setName(vo.getName());
		user.setSex(vo.getSex());
		CommonJdbcUtils.update(user);

		// 更新机构
		String sql = "select * from app_organ_user where userid=?";
		OrganUser ou = CommonJdbcUtils.queryFirst(sql, OrganUser.class, vo.getUserid());
		ou.setNodeid(vo.getNodeid());
		if (vo.getNodeid() != null)
			CommonJdbcUtils.save(ou);

		sql = "DELETE from APP_GROUP_USER WHERE USERID=" + vo.getUserid();
		CommonJdbcUtils.execute(sql);
		// 保存组织用户
		GroupUser gu;
		for (Group group : groups) {
			gu = new GroupUser();
			gu.setGroupid(group.getGroupid());
			gu.setUserid(vo.getUserid());
			gu.setStatus("1");
			CommonJdbcUtils.save(gu);
		}

		return user;
	}

	/**
	 * 初始化密码
	 * 
	 * @param userid
	 */
	@Override
	public void initUserPassword(Long userid) {
		User user = queryUserById(userid);
		String password = Md5Utils.MD5Code(user.getLoginname());
		user.setPassword(password);
		CommonJdbcUtils.update(user);
	}

	@Override
	public User queryUserById(Long userid) {
		return CommonJdbcUtils.queryFirst("select * from app_user where userid=?", User.class, userid);
	}

	public UserVO queryUserByLoginname(String loginname) {
		return CommonJdbcUtils.queryFirst("select * from app_user where loginname=? and removed<>'1' ", UserVO.class,
				loginname);
	}

	@Override
	public void queryAllGroups(Page page, String groupname) {
		String sql = "select a.groupid,a.groupname,b.grouptypename Grouptype,a.status,a.removed from app_group a,app_group_type b WHERE a.grouptype=b.grouptypeid AND a.status='1' and a.removed='0' ";
		if (groupname != null && groupname.trim().length() > 0)
			sql = "select a.groupid,a.groupname,b.grouptypename Grouptype,a.status,a.removed from app_group a,app_group_type b WHERE a.grouptype=b.grouptypeid AND a.status='1' and a.removed='0' and a.groupname like %"
					+ groupname + "% ";
		CommonJdbcUtils.queryPage(page, sql, Group.class);

	}

	@Override
	public List<Group> queryGroupByUserid(Long userid) {
		String sql = "SELECT b.* FROM app_group_user a,app_group b WHERE a.groupid=b.groupid AND userid=? ";
		return CommonJdbcUtils.query(sql, Group.class, userid);

	}

	@Override
	public List<GroupType> queryAllGroupType() {
		return CommonJdbcUtils.query("SELECT * FROM app_group_type WHERE status='1'", GroupType.class);
	}

	@Override
	public void saveGroup(String groupName, String groupTypeId) {
		Group group = new Group();
		group.setGroupname(groupName);
		group.setGrouptype(groupTypeId);
		group.setRemoved("0");
		group.setStatus("1");
		CommonJdbcUtils.save(group);
		/**
		 * 添加组权限
		 */
		String sql = "INSERT INTO app_permission" + "  SELECT null," + group.getGroupid()
				+ ",resourceid,'1' FROM app_resource WHERE status='1'";
		CommonJdbcUtils.execute(sql);
	}

	@Override
	public void updateGroup(Group group) {
		Group g = queryGroupByGroupid(group.getGroupid());
		g.setGroupname(group.getGroupname());
		g.setGrouptype(group.getGrouptype());
		CommonJdbcUtils.update(g);
	}

	@Override
	public void deleteGroup(Group group) {
		CommonJdbcUtils.execute("update app_group set removed='1' where groupid=?", group.getGroupid());
	}

	@Override
	public Group queryGroupByGroupid(Long groupid) {
		String sql = "select * from app_group where groupid=?";
		return CommonJdbcUtils.queryFirst(sql, Group.class, groupid);
	}

	@Override
	public List<APPAA09> queryAa09list() {
		List<APPAA09> aa09s = CommonJdbcUtils.query("select * from app_aa09 where aae100='1'", APPAA09.class);
		return aa09s;
	}

	@Override
	public List<APPAA10> queryAa10list(String aaa100) {
		List<APPAA10> aa10s = CommonJdbcUtils.query("select * from app_aa10 where aaa100=?  order by cae008 asc",
				APPAA10.class, aaa100);
		return aa10s;
	}

	@Override
	public void addAa10(String aaa100, String aaa103) {
		String sql = "select max(cast(AAA102 as unsigned int)) from app_aa10 where aaa100=?";
		int seq_number = CommonJdbcUtils.queryObject(sql, Integer.class, aaa100);
		sql = "INSERT INTO app_aa10 VALUES (null, ?, ?, ?, 19000101, 99991230, 1, NULL, '1')";
		CommonJdbcUtils.execute(sql, aaa100, seq_number + 1, aaa103);
	}

	@Override
	public void deleteAa10(String aaa100, String aaa102) {
		String sql = "update app_aa10  set aae100='0' where aaa100=? and aaa102=?";
		CommonJdbcUtils.execute(sql, aaa100, aaa102);
	}

	@Override
	public void refreashContext(ServletContext context, String aaa100) {
		List<APPAA09> aa09s = CommonJdbcUtils.query("select * from app_aa09 where aae100='1' and aaa100=?",
				APPAA09.class, aaa100);
		if (aa09s != null) {
			String aa10sql = "select * from app_aa10 where aaa100=? and aae100='1' order by cae008 asc";
			for (int i = 0; i < aa09s.size(); i++) {
				List<APPAA10> aa10s = CommonJdbcUtils.query(aa10sql, APPAA10.class, aa09s.get(i).getAaa100());
				if (aa10s != null && aa10s.size() > 0) {
					if (aa09s.get(i).getAaa100() != null)
						context.setAttribute(aa09s.get(i).getAaa100().toUpperCase(), aa10s);
				}
			}
		}
	}
}
package com.wb.login.service;

import java.util.ArrayList;
import java.util.List;

import com.wb.admin.bo.User;
import com.wb.exceptions.BusinessException;
import com.wb.jdbcutils.CommonJdbcUtils;
import com.wb.login.model.LogLoginBO;
import com.wb.login.model.LoginSpDTO;
import com.wb.login.model.Resourcevo;
import com.wb.login.model.UserVO;

public class LoginServiceImpl implements LoginService {
	@Override
	public UserVO getUser(String domain, String Loginname, String password) {
		String sql = "SELECT a.*,c.groupid,c.groupname,d.grouptypeid,d.grouptypename,d.grouptypeclass FROM app_user a,app_group_user b ,app_group c,app_group_type d "
				+ "WHERE a.userid=b.userid AND b.groupid=c.groupid AND c.grouptype=d.grouptypeid "
				+ "AND a.stauts='1' AND a.removed='0' AND b.status='1' AND c.status='1' AND d.status='1' "
				+ "AND a.loginname=? AND a.password=? ";
		return CommonJdbcUtils.queryFirst(sql, UserVO.class, Loginname, password);
	}

	@Override
	public UserVO getUserByUserId(String Userid) {
		String sql = "SELECT a.*,c.groupid,c.groupname,d.grouptypeid,d.grouptypename,d.grouptypeclass FROM app_user a,app_group_user b ,app_group c,app_group_type d "
				+ "WHERE a.userid=b.userid AND b.groupid=c.groupid AND c.grouptype=d.grouptypeid "
				+ "AND a.stauts='1' AND a.removed='0' AND b.status='1' AND c.status='1' AND d.status='1' "
				+ "AND a.userid=? ";
		return CommonJdbcUtils.queryFirst(sql, UserVO.class, Userid);
	}

	/**
	 * 查询顶级菜单列表
	 */
	@Override
	public List<Resourcevo> getParentResourceByUserid(Long userid, String application) {
		String sql = "SELECT a.RESOURCEID as ID,a.RESOURCEID,a.PARENTRESOURCEID as PID,a.PARENTRESOURCEID,a.NAME as TEXT,a.name,a.ICON1 as ICONCLS,a.icon1, a.URL as URL FROM app_resource a,app_permission b,app_group_user c,app_user d,app_group e,app_group_type f "
				+ "WHERE a.resourceid=b.resourceid AND b.groupid=c.groupid AND c.groupid=e.groupid AND c.userid=d.userid AND e.grouptype=f.grouptypeid "
				+ "AND a.status='1' AND b.status='1' AND c.status='1' AND d.stauts='1' AND d.removed='0' AND e.status='1' AND f.status='1' "
				+ "AND a.parentresourceid IS NULL and a.appid=? AND d.userid=? ";
		return CommonJdbcUtils.query(sql, Resourcevo.class, application, userid);

	}

	@Override
	public List<Resourcevo> getPubParentResourceByUserid(String application, String resourcetype) {
		return new ArrayList<Resourcevo>();
	}

	@Override
	public List<Resourcevo> getResourceByResourceidAndResourceType(Long resourceid, String application,
			String resourcetype) {
		return new ArrayList<Resourcevo>();
	}

	/**
	 * 查询左侧菜单
	 */
	@Override
	public List<Resourcevo> getResourceByResourceid(Long resourceid, String application, Long userid) {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DISTINCT A.RESOURCEID       ID,                ");
		sb.append("                A.RESOURCEID,                         ");
		sb.append("                A.PARENTRESOURCEID PID,               ");
		sb.append("                A.PARENTRESOURCEID,                   ");
		sb.append("                A.NAME AS TEXT,      ");
		sb.append("                A.NAME,              ");
		sb.append("                A.ICON1            ICONCLS,           ");
		sb.append("                A.ICON1,                              ");
		sb.append("                A.URL              URL                ");
		sb.append("  FROM (SELECT *                                      ");
		sb.append("          FROM APP_RESOURCE                           ");
		sb.append("         WHERE PARENTRESOURCEID = ?                ");
		sb.append("        UNION                                         ");
		sb.append("        SELECT *                                      ");
		sb.append("          FROM APP_RESOURCE                           ");
		sb.append("         WHERE PARENTRESOURCEID IN                    ");
		sb.append("               (SELECT RESOURCEID                     ");
		sb.append("                  FROM APP_RESOURCE                   ");
		sb.append("                 WHERE PARENTRESOURCEID = ?)) A,   ");
		sb.append("       APP_PERMISSION B,                              ");
		sb.append("       APP_GROUP_USER C,                              ");
		sb.append("       APP_USER D,                                    ");
		sb.append("       APP_GROUP E,                                   ");
		sb.append("       APP_GROUP_TYPE F                               ");
		sb.append(" WHERE A.RESOURCEID = B.RESOURCEID                    ");
		sb.append("   AND B.GROUPID = C.GROUPID                          ");
		sb.append("   AND C.GROUPID = E.GROUPID                          ");
		sb.append("   AND C.USERID = D.USERID                            ");
		sb.append("   AND E.GROUPTYPE = F.GROUPTYPEID                    ");
		sb.append("   AND A. STATUS = '1'                                ");
		sb.append("   AND B. STATUS = '1'                                ");
		sb.append("   AND C. STATUS = '1'                                ");
		sb.append("   AND D.STAUTS = '1'                                 ");
		sb.append("   AND D.REMOVED = '0'                                ");
		sb.append("   AND E.STATUS = '1'                                 ");
		sb.append("   AND F.STATUS = '1'                                 ");
		sb.append("   AND A.APPID = ?                                   ");
		sb.append("   AND D.USERID = ?                        ");
		return CommonJdbcUtils.query(sb.toString(), Resourcevo.class, resourceid, resourceid, application, userid);
	}

	@Override
	public Resourcevo getUrlByResourceid(Long resourceid, String application) {
		String sql = "select url,name from app_resource where resourceid=? and appid=? and status=1";
		return CommonJdbcUtils.queryObject(sql, Resourcevo.class, resourceid, application);
	}

	@Override
	public Resourcevo getResourceByResourceid(Long resourceid) {
		String sql = "select * from app_resource where resourceid=?";
		return CommonJdbcUtils.queryObject(sql, Resourcevo.class, resourceid);
	}

	/**
	 * 调用登陆后台
	 * 
	 * @param dto
	 */
	@Override
	public void callLoginSP(LoginSpDTO dto) {
		// CommonJdbcUtils.call(dto);
		User user = getUserByLoginnameAndPassword(dto);
		if (user == null) {
			dto.setRetcode(-1);
			dto.setRetmsg("用户名密码不正确！");
		} else {
			dto.setRetcode(0);
			dto.setUserid(user.getUserid().toString());
			dto.setViewname("index");
		}
	}

	public User getUserByLoginnameAndPassword(LoginSpDTO dto) {
		if (null == dto.getLoginname())
			throw new BusinessException("用户名不能为空！");
		if (null == dto.getPassword())
			throw new BusinessException("密码不能为空！");
		String sql = "select * from app_user where LOGINNAME=? and password=? and removed='0' ";
		return CommonJdbcUtils.queryFirst(sql, User.class, dto.getLoginname(), dto.getPassword());
	}

	/**
	 * 记录登陆日志
	 * 
	 * @param bo
	 */
	@Override
	public void saveLoginLog(LogLoginBO bo) {
		CommonJdbcUtils.save(bo);
	}
}

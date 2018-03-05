package com.wb.school.f1002.service;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.wb.admin.bo.OrganUser;
import com.wb.jdbcutils.CommonJdbcUtils;
import com.wb.jdbcutils.Page;
import com.wb.login.model.UserVO;
import com.wb.school.common.bo.Log;
import com.wb.school.common.bo.StudentRemote;
import com.wb.school.common.service.CommonService;
import com.wb.school.f1001.common.vo.ProcessDTO;
import com.wb.school.f1002.vo.StudentRemoteVO;
import com.wb.user.utils.BusinessContextUtils;

/**
 * 职工信息查询公用Service
 * 
 * @author xue 2015-9-25
 */
public class RemoteServiceImpl implements RemoteService {

	private CommonService commonService;

	@Override
	public StudentRemote execStudentRemoteAdd(StudentRemote stu) {
		stu.setEnabled("1");
		stu.setCtime(new Date());
		CommonJdbcUtils.save(stu);
		// 写日志
		ProcessDTO pdto = new ProcessDTO();
		saveLog(pdto, stu.getStuid(), stu.getStu_name());
		return stu;
	}

	@Override
	public StudentRemote execStudentRemoteUpdate(StudentRemoteVO stuVO) {
		StudentRemoteVO vo = queryStudentRemoteBystuid(stuVO.getStuid());
		StudentRemote stu = new StudentRemote();
		BeanUtils.copyProperties(stuVO, stu, new String[] { "enabled", "ctime" });
		stu.setEnabled(vo.getEnabled());
		stu.setCtime(vo.getCtime());
		CommonJdbcUtils.update(stu);
		return stu;
	}

	@Override
	public StudentRemoteVO queryStudentRemoteBystuid(Long stuid) {
		String sql = "select (SELECT GROUPNAME FROM APP_GROUP, APP_GROUP_USER  "
				+ "WHERE APP_GROUP_USER.GROUPID = APP_GROUP.GROUPID   "
				+ "AND APP_GROUP_USER.USERID = A.RECORDER) GROUPNAME,a.* " + "from t_student_remote a where a.stuid=?";
		return CommonJdbcUtils.queryFirst(sql, StudentRemoteVO.class, stuid);
	}

	/**
	 * 查询待操作列表 seq_year:one 第一年，two 第二年
	 */
	@Override
	public void queryStuListByCurentUserPub(Page page, StudentRemoteVO vo, String seq_year) {
		StringBuffer sb = new StringBuffer();
		UserVO user = BusinessContextUtils.getUserContext();
		OrganUser ou = CommonJdbcUtils.queryFirst("select * from app_organ_user where userid=?", OrganUser.class,
				user.getUserid());
		if (ou == null)
			return;
		// Long groupid=user.getGrouptypeid();
		sb.append("SELECT D.GROUPNAME GROUPNAME,                                                                  ");
		sb.append("       A.STUID,                                                                                ");
		sb.append("       A.STU_NAME,                                                                             ");
		sb.append("       A.CELLPHONE,                                                                            ");
		sb.append("       A.STU_LEVEL2,                                                                           ");
		sb.append("       A.EDUCATIONTYPE2,                                                                       ");
		sb.append("       A.NETSERVICE2,                                                                          ");
		sb.append("       A.PERFORMANCER,                                                                         ");
		sb.append("       A.STUFEETYPE,                                                                           ");
		sb.append("       A.STUREGISTERADDRESS,                                                                   ");
		sb.append("       A.NOWSTATUS,                                                                            ");
		sb.append("       A.CTIME,                                                                                ");
		sb.append("       A.RECORDER,                                                                             ");
		sb.append("       (SELECT NAME FROM APP_USER WHERE USERID = A.RECORDER) RECORDEROR,                       ");
		sb.append("       A.FOLLOW,                                                                               ");
		sb.append("       (SELECT NAME FROM APP_USER WHERE USERID = A.FOLLOW) FOLLOWOR,                           ");
		sb.append("       A.EXAMLEVEL2,                                                                           ");
		sb.append("       A.FIRSTWISHSCHOOL2,                                                                     ");
		sb.append("       A.FIRSTWISHSPECIALTY2,                                                                  ");
		sb.append("       A.BLONGRELATION                                                                         ");
		sb.append("  FROM T_STUDENT_REMOTE A, V_APP_USER D                                                        ");
		sb.append(" WHERE A.RECORDER = D.USERID                                                                   ");
		sb.append("   AND (A.ENABLED <> 2 OR A.ENABLED IS NULL)                                                   ");
		sb.append("   AND D.NODEID IN                                                                             ");
		sb.append("       (SELECT NODEID                                                                          ");
		sb.append("          FROM APP_ORGAN                                                                       ");
		sb.append("         WHERE FIND_IN_SET(NODEID, SF_GETSUBORGAN(?)))                                         ");
		if (vo.getStu_name() != null && vo.getStu_name().length() > 0 && vo.getStu_name().length() < 20) {
			sb.append(" and a.stu_name LIKE '%" + vo.getStu_name() + "%'   ");
		}
		if (seq_year != null && "one".equals(seq_year)) {
			sb.append(" and (a.stufee1is ='' or a.stufee1is='2')   ");
		}
		if (seq_year != null && "two".equals(seq_year)) {
			sb.append(" and (a.stufee2is ='' or a.stufee2is='2')   ");
		}
		if (vo.getStu_level2() != null && vo.getStu_level2().length() > 0 && vo.getStu_level2().length() < 10) {
			sb.append(" and a.stu_level2=" + vo.getStu_level2() + "  ");
		}
		if (vo.getS_date() != null && vo.getS_date().length() > 0) {
			sb.append(" AND a.ctime>=to_date('" + vo.getS_date() + "','yyyymmdd')  ");
		}
		if (vo.getE_date() != null && vo.getE_date().length() > 0) {
			sb.append(" AND a.ctime<=to_date('" + vo.getE_date() + "','yyyymmdd')  ");
		}
		if (vo.getNowstatus() != null && vo.getNowstatus().length() > 0) {
			sb.append(" AND a.nowstatus='" + vo.getNowstatus() + "'  ");
		}
		if (vo.getGroupname() != null && vo.getGroupname().length() > 0) {
			sb.append(" AND d.groupname like '%" + vo.getGroupname() + "%'  ");
		}
		if (vo.getFollowor() != null && vo.getFollowor().length() > 0) {
			sb.append(" AND a.follow =" + vo.getFollowor() + " ");
		}
		CommonJdbcUtils.queryPage(page, sb.toString(), StudentRemoteVO.class, ou.getNodeid());
	}

	@Override
	public void deleteRemoteStudentByStuid(String stuid) {
		String sql = "update t_student_remote set enabled='2' where stuid=?";
		CommonJdbcUtils.execute(sql, stuid);
	}

	public void saveLog(ProcessDTO dto, Long stuid, String stuName) {
		UserVO user = BusinessContextUtils.getUserContext();
		Log log = new Log();
		if (user != null) {
			log.setUserid(user.getUserid());
			log.setUsername(user.getName());
		}
		log.setStuid(stuid);
		log.setName(stuName);
		log.setProcesscode(dto.getNext_processcode());
		log.setStepcode(dto.getNext_stepcode());
		log.setCtime(new Date());
		CommonJdbcUtils.save(log);
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public CommonService getCommonService() {
		return commonService;
	}

}

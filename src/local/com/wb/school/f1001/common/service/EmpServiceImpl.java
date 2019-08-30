package com.wb.school.f1001.common.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wb.school.common.bo.*;
import com.wb.school.f1001.common.vo.*;
import com.wb.utils.web.common.StringTools;
import org.springframework.beans.BeanUtils;

import com.wb.admin.bo.OrganUser;
import com.wb.admin.bo.User;
import com.wb.exceptions.BusinessException;
import com.wb.jdbcutils.CommonJdbcUtils;
import com.wb.jdbcutils.Page;
import com.wb.login.model.UserVO;
import com.wb.school.common.service.CommonService;
import com.wb.user.utils.BusinessContextUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * 职工信息查询公用Service
 * 
 * @author xue 2015-9-25
 */
public class EmpServiceImpl implements EmpService {

	private CommonService commonService;

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	@Override
	public List<User> loadAllUser() {
		return CommonJdbcUtils.query("select * from app_user where removed='0' and stauts='1'", User.class);
	}

	@Override
	public void addStudent(Student stu,String pre) {
		//String sql = "select * from t_student where cardid=? and (enabled is null or enabled='1') and CTIME > DATE_SUB(CURDATE(),INTERVAL dayofyear(now())-1 DAY) ";
		String sql = "select * from t_student where cardid=? and (enabled is null or enabled='1') ";
		Student student = CommonJdbcUtils.queryFirst(sql, Student.class, stu.getCardid());
		if (student != null) {
			throw new BusinessException("该身份证号人员已经存在！");
		}
		if(!"1".equals(pre)){//已经预报名，不允正式报名
			sql="select * from t_pre_student where (cardid=? or CELLPHONE=? or EMAIL=?)  and (enabled is null or enabled='1') and CTIME > DATE_SUB(CURDATE(),INTERVAL dayofyear(now())-1 DAY)";
			StudentPre studentPre=CommonJdbcUtils.queryFirst(sql,StudentPre.class,stu.getCardid(),stu.getCellphone(),stu.getEmail());
			if (studentPre != null) {
				throw new BusinessException("该身份证号人员已经预报名！");
			}
		}
		stu.setEnabled("1");
		stu.setCtime(new Date());
		stu.setProcesscode("B");
		stu.setStepcode("B1");
		stu.setIsfall("0");
		CommonJdbcUtils.save(stu);
		// 写扩展表
		addStudentExt(stu);
		// 写日志
		ProcessDTO pdto = new ProcessDTO();
		pdto.setNext_processcode("A");
		pdto.setNext_stepcode("A1");
		saveLog(pdto, stu.getStuid(), stu.getStu_name());
	}
	/**
	 * 预报名登记
	 */
	@Override
	public void addStudentPre(StudentPre stu) {
		String sql = "select * from t_pre_student where cardid=? and (enabled is null or enabled='1') and CTIME > DATE_SUB(CURDATE(),INTERVAL dayofyear(now())-1 DAY) ";
		Student student = CommonJdbcUtils.queryFirst(sql, Student.class, stu.getCardid());
		if (student != null&& stu.getStuid()==null) {
			throw new BusinessException("该身份证号人员已经存在！");
		}
		stu.setEnabled("1");
		stu.setCtime(new Date());
		stu.setProcesscode("z");
		stu.setStepcode("z1");
		stu.setIsfall("0");
		if (stu.getStuid()!=null)
		CommonJdbcUtils.update(stu);
		else
			CommonJdbcUtils.save(stu);
		// 写日志
		ProcessDTO pdto = new ProcessDTO();
		pdto.setNext_processcode("z");
		pdto.setNext_stepcode("z1");
		saveLog(pdto, stu.getStuid(), stu.getStu_name());
	}
	@Transactional
	public void updateStudentPreToAlready(StudentPre stu) {
		String sql = "update t_pre_student set enabled='0' where stuid=? ";
		CommonJdbcUtils.execute(sql, stu.getStuid());
		CommonJdbcUtils.execute("delete from t_pre_student_dis where stuid=?",stu.getStuid());
	}
	@Override
	public void addStudentExt(Student stu) {
		StudentExt se = new StudentExt();
		se.setStuid(stu.getStuid());
		CommonJdbcUtils.save(se);
	}

	@Override
	public StudentExtVO queryStuExtByid(Long stuid) {
		String sql = "select a.*,b.oldeducationlevel,b.stu_name from t_student_ext a,t_student b where a.stuid=b.stuid and  (b.ENABLED<>2 or b.ENABLED is null) and a.stuid=?";
		return CommonJdbcUtils.queryFirst(sql, StudentExtVO.class, stuid);
	}

	@Override
	public StudentExt updateStuExt(StudentExtVO vo) {
		StudentExt stuext = new StudentExt();
		BeanUtils.copyProperties(vo, stuext, new String[] {});
		CommonJdbcUtils.update(stuext);
		return stuext;
	}

	@Override
	public void addStudentPay(Student stu) {
		StudentExtVO extVO = queryStuExtByid(stu.getStuid());
		if (extVO == null)
			return;
		if (extVO.getFirstwishlength() == null || "".equals(extVO.getFirstwishlength()))
			throw new BusinessException("最终学制为空！");
		if (extVO.getInschooldate() == null || "".equals(extVO.getInschooldate()))
			throw new BusinessException("入学时间为空！");
		List<StudentPay> list = createPayList(extVO.getFirstwishlength(), extVO.getInschooldate());
		for (StudentPay pay : list) {
			pay.setStuid(stu.getStuid());
			CommonJdbcUtils.save(pay);
		}

	}

	@Override
	public List<StudentPay> createPayList(String length, String date) {
		List<StudentPay> list = new ArrayList<StudentPay>();
		if (length == null || "".equals(length) || date == null || "".equals(date))
			return list;

		double len = Math.ceil((Double.valueOf(length)));
		int year = Integer.valueOf(date.substring(0, 4));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		StudentPay pay = null;
		for (int i = 0; i < len; i++) {
			pay = new StudentPay();
			pay.setYear(year + i);
			pay.setSeqnumber(i + 1);
			pay.setIspay("0");
			try {
				pay.setSdate(sdf.parse(date));
				pay.setEdate(sdf.parse(year + i + "1231"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			list.add(pay);
		}
		return list;
	}

	@Override
	public void gotoNextStep(ProcessDTO dto) {
		if (dto.getProcesscode() == null || dto.getStepcode() == null)
			return;
		// 获取当前步骤
		String sql = "select * from t_step where stepcode=?";
		Step step_now = CommonJdbcUtils.queryFirst(sql, Step.class, dto.getStepcode());
		// 获取下一步骤
		sql = "select * from t_step where processcode=? and xh=?";
		Step step_next = CommonJdbcUtils.queryFirst(sql, Step.class, step_now.getProcesscode(), step_now.getXh() + 1);
		if (step_next != null) {
			dto.setNext_processcode(step_next.getProcesscode());
			dto.setNext_stepcode(step_next.getStepcode());
			return;
		}
		// 获取下一流程第一步骤
		String sql2 = "SELECT * FROM t_process WHERE xh=(SELECT xh+1 FROM t_process WHERE processcode=?)";
		Processs processs = CommonJdbcUtils.queryFirst(sql2, Processs.class, step_now.getProcesscode());
		if (processs == null)
			return;
		step_next = CommonJdbcUtils.queryFirst(sql, Step.class, processs.getProcesscode(), 1);
		dto.setNext_processcode(step_next.getProcesscode());
		dto.setNext_stepcode(step_next.getStepcode());
	}

	/**
	 * 业务操作日志
	 * 
	 * @param dto
	 * @param stuid
	 * @param stuName
	 */
	@Override
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

	/**
	 * 查询待操作列表
	 */
	@Override
	public void queryStuListByCurentUser(Page page, StudentVO vo) {
		StringBuffer sb = new StringBuffer();
		UserVO user = BusinessContextUtils.getUserContext();
		OrganUser ou = CommonJdbcUtils.queryFirst("select * from app_organ_user where userid=?", OrganUser.class,
				user.getUserid());
		if (ou == null)
			return;
		Long groupid = user.getGrouptypeid();
		sb.append("SELECT (SELECT GROUPNAME                                                                      ");
		sb.append("          FROM APP_GROUP, APP_GROUP_USER                                                      ");
		sb.append("         WHERE APP_GROUP_USER.GROUPID = APP_GROUP.GROUPID                                     ");
		sb.append("           AND APP_GROUP_USER.USERID = A.RECORDER) GROUPNAME,                                 ");
		sb.append("       A.STUID,                                                                               ");
		sb.append("       A.STU_NAME,                                                                            ");
		sb.append("       A.CEllPHONE,                                                                               ");
		sb.append("       A.STU_LEVEL,                                                                           ");
		sb.append("       A.RECORDER,                                                                            ");
		sb.append("       (SELECT NAME FROM app_user WHERE userid=a.recorder) RECORDEROR,                        ");
		sb.append("       A.FOLLOW,                                                                              ");
		sb.append("       (SELECT NAME FROM app_user WHERE userid=a.FOLLOW) FOLLOWOR,                            ");
		sb.append("       A.EXAMLEVEL,                                                                           ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.examlevel) examlevelor,                          ");
		sb.append("       A.EXAMCLASS,                                                                           ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.EXAMCLASS) EXAMCLASSor,                          ");
		sb.append("       A.FIRSTWISHSCHOOL,                                                                     ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.FIRSTWISHSCHOOL) FIRSTWISHSCHOOLor,              ");
		sb.append("       A.FIRSTWISHSPECIALTY,                                                                  ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.FIRSTWISHSPECIALTY) FIRSTWISHSPECIALTYor,        ");
		sb.append("       A.LEARNINGFORM,                                                                        ");
		sb.append("        (SELECT NAME FROM t_school WHERE ID=a.LEARNINGFORM) LEARNINGFORMor,                   ");
		sb.append("       A.MANUALSCHOOL,                                                                        ");
		sb.append("       A.MANUALSPECIALTY,                                                                     ");
		sb.append(
				"       A.BLONGRELATION,a.ctime,a.stepcode                                                                     ");
		sb.append("  FROM T_STUDENT A, APP_ORGAN_USER B                                                        ");
		sb.append(
				" WHERE A.RECORDER = B.USERID   AND a.ISCREATENORMAL='1'" +
                        " and a.processcode=? " +
                        " and (a.ENABLED<>2 or a.ENABLED is null) " +
                        " and b.NODEID in (select nodeid from app_organ where REMOVED='0')"
						+ " AND a.STEPCODE in (select STEPCODE from t_step where FIND_IN_SET(" + groupid
						+ ",GROUPID))                  ");
		if(!"02".equals(user.getGrouptypeclass())){
			sb.append("   AND B.NODEID IN                                                                        ");
			sb.append("       (SELECT nodeid FROM app_organ WHERE FIND_IN_SET(nodeid,sf_getsuborgan("+ou.getNodeid()+")))    ");
		}
		if (vo.getStu_name() != null && vo.getStu_name().length() > 0 && vo.getStu_name().length() < 20) {
			sb.append(" and a.stu_name LIKE '%" + vo.getStu_name().trim() + "%'   ");
		}
		if (vo.getStu_level() != null && vo.getStu_level().length() > 0 && vo.getStu_level().length() < 10) {
			sb.append(" and a.stu_level=" + vo.getStu_level() + "  ");
		}
		if (vo.getS_date() != null && vo.getS_date().length() > 0) {
			sb.append(" AND a.ctime>=to_date('" + vo.getS_date() + "','yyyymmdd')  ");
		}
		if (vo.getE_date() != null && vo.getE_date().length() > 0) {
			sb.append(" AND a.ctime<=to_date('" + vo.getE_date() + "','yyyymmdd')  ");
		}
		CommonJdbcUtils.queryPage(page, sb.toString(), StudentVO.class, vo.getProcesscode());
	}
	/**
	 * 查询预报名待操作列表
	 */
	@Override
	public void queryStuListByCurentUserPre(Page page, StudentVO vo) {
		StringBuffer sb = new StringBuffer();
		UserVO user = BusinessContextUtils.getUserContext();
		OrganUser ou = CommonJdbcUtils.queryFirst("select * from app_organ_user where userid=?", OrganUser.class,
				user.getUserid());
		if (ou == null)
			return;
		Long groupid = user.getGrouptypeid();
		sb.append("SELECT (SELECT GROUPNAME                                                                      ");
		sb.append("          FROM APP_GROUP, APP_GROUP_USER                                                      ");
		sb.append("         WHERE APP_GROUP_USER.GROUPID = APP_GROUP.GROUPID                                     ");
		sb.append("           AND APP_GROUP_USER.USERID = A.RECORDER) GROUPNAME,                                 ");
		sb.append("       A.STUID,                                                                               ");
		sb.append("       A.STU_NAME,                                                                            ");
		sb.append("       A.CEllPHONE,                                                                               ");
		sb.append("       A.STU_LEVEL,                                                                           ");
		sb.append("       A.RECORDER,                                                                            ");
		sb.append("       (SELECT NAME FROM app_user WHERE userid=a.recorder) RECORDEROR,                        ");
		sb.append("       A.FOLLOW,                                                                              ");
		sb.append("       (SELECT NAME FROM app_user WHERE userid=a.FOLLOW) FOLLOWOR,                            ");
		sb.append("       A.EXAMLEVEL,                                                                           ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.examlevel) examlevelor,                          ");
		sb.append("       A.EXAMCLASS,                                                                           ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.EXAMCLASS) EXAMCLASSor,                          ");
		sb.append("       A.FIRSTWISHSCHOOL,                                                                     ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.FIRSTWISHSCHOOL) FIRSTWISHSCHOOLor,              ");
		sb.append("       A.FIRSTWISHSPECIALTY,                                                                  ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.FIRSTWISHSPECIALTY) FIRSTWISHSPECIALTYor,        ");
		sb.append("       A.LEARNINGFORM,                                                                        ");
		sb.append("        (SELECT NAME FROM t_school WHERE ID=a.LEARNINGFORM) LEARNINGFORMor,                   ");
		sb.append("       A.MANUALSCHOOL,                                                                        ");
		sb.append("       A.MANUALSPECIALTY,                                                                     ");
		sb.append(
				"       A.BLONGRELATION,a.ctime,a.stepcode,a.enabled                                                                     ");
		sb.append("  FROM t_pre_student A, APP_ORGAN_USER B                                                        ");
		sb.append(
				" WHERE A.RECORDER = B.USERID   and a.processcode=?  "
						+ "  ");
		sb.append("   AND B.NODEID IN                                                                        ");
		sb.append("       (SELECT nodeid FROM app_organ WHERE FIND_IN_SET(nodeid,sf_getsuborgan(?)))    ");
		//超过30天进入待分配池
		sb.append(" and (a.CTIME >= DATE_SUB(CURDATE(),INTERVAL 30 DAY)  " +
				" or a.stuid in (select stuid from t_pre_student_dis where record="+user.getUserid()+")) ");
		if (vo.getStu_name() != null && vo.getStu_name().length() > 0 && vo.getStu_name().length() < 20) {
			sb.append(" and a.stu_name LIKE '%" + vo.getStu_name() + "%'   ");
		}
		if (vo.getStu_level() != null && vo.getStu_level().length() > 0 && vo.getStu_level().length() < 10) {
			sb.append(" and a.stu_level=" + vo.getStu_level() + "  ");
		}
		if (vo.getCellphone() != null && vo.getCellphone().length() > 0 ) {
			sb.append(" and a.cellphone=" + vo.getCellphone() + "  ");
		}
		if (vo.getS_date() != null && vo.getS_date().length() > 0) {
			sb.append(" AND a.ctime>=to_date('" + vo.getS_date() + "','yyyymmdd')  ");
		}
		if (vo.getE_date() != null && vo.getE_date().length() > 0) {
			sb.append(" AND a.ctime<=to_date('" + vo.getE_date() + "','yyyymmdd')  ");
		}
		CommonJdbcUtils.queryPage(page, sb.toString(), StudentVO.class, "z", ou.getNodeid());
	}
	/**
	 * 查询预报名待操作列表
	 */
	@Override
	public Integer queryStuListByCurentUserPreCount(StudentVO vo) {
		StringBuffer sb = new StringBuffer();
		UserVO user = BusinessContextUtils.getUserContext();
		OrganUser ou = CommonJdbcUtils.queryFirst("select * from app_organ_user where userid=?", OrganUser.class,
				user.getUserid());
		if (ou == null)
			return 0;
		Long groupid = user.getGrouptypeid();
		sb.append("SELECT count(a.STUID) vcount                                                                     ");
		sb.append("  FROM t_pre_student A, APP_ORGAN_USER B                                                        ");
		sb.append(
				" WHERE A.RECORDER = B.USERID   and a.processcode=?  "
						+ "  ");
		sb.append("   AND B.NODEID IN                                                                        ");
		sb.append("       (SELECT nodeid FROM app_organ WHERE FIND_IN_SET(nodeid,sf_getsuborgan(?)))    ");
		//超过30天进入待分配池
		sb.append(" and (a.CTIME >= DATE_SUB(CURDATE(),INTERVAL 30 DAY)  " +
				" or a.stuid in (select stuid from t_pre_student_dis where record="+user.getUserid()+")) ");
		if (vo.getStu_name() != null && vo.getStu_name().length() > 0 && vo.getStu_name().length() < 20) {
			sb.append(" and a.stu_name LIKE '%" + vo.getStu_name() + "%'   ");
		}
		if (vo.getStu_level() != null && vo.getStu_level().length() > 0 && vo.getStu_level().length() < 10) {
			sb.append(" and a.stu_level=" + vo.getStu_level() + "  ");
		}
		if (vo.getS_date() != null && vo.getS_date().length() > 0) {
			sb.append(" AND a.ctime>=to_date('" + vo.getS_date() + "','yyyymmdd')  ");
		}
		if (vo.getE_date() != null && vo.getE_date().length() > 0) {
			sb.append(" AND a.ctime<=to_date('" + vo.getE_date() + "','yyyymmdd')  ");
		}
		return CommonJdbcUtils.queryObject(sb.toString(), Integer.class, "z", ou.getNodeid());
	}
	/**
	 * 查询预报名待分配列表
	 */
	@Override
	public void queryStuListByCurentUserPreDis(Page page, StudentVO vo) {
		StringBuffer sb = new StringBuffer();
		UserVO user = BusinessContextUtils.getUserContext();
		OrganUser ou = CommonJdbcUtils.queryFirst("select * from app_organ_user where userid=?", OrganUser.class,
				user.getUserid());
		if (ou == null)
			return;
		Long groupid = user.getGrouptypeid();
		sb.append("SELECT (SELECT GROUPNAME                                                                      ");
		sb.append("          FROM APP_GROUP, APP_GROUP_USER                                                      ");
		sb.append("         WHERE APP_GROUP_USER.GROUPID = APP_GROUP.GROUPID                                     ");
		sb.append("           AND APP_GROUP_USER.USERID = A.RECORDER) GROUPNAME,                                 ");
		sb.append("       A.STUID,                                                                               ");
		sb.append("       A.STU_NAME,                                                                            ");
		sb.append("       A.CEllPHONE,                                                                               ");
		sb.append("       A.STU_LEVEL,                                                                           ");
		sb.append("       A.RECORDER,                                                                            ");
		sb.append("       (SELECT NAME FROM app_user WHERE userid=a.recorder) RECORDEROR,                        ");
		sb.append("       A.FOLLOW,                                                                              ");
		sb.append("       (SELECT NAME FROM app_user WHERE userid=a.FOLLOW) FOLLOWOR,                            ");
		sb.append("       A.EXAMLEVEL,                                                                           ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.examlevel) examlevelor,                          ");
		sb.append("       A.EXAMCLASS,                                                                           ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.EXAMCLASS) EXAMCLASSor,                          ");
		sb.append("       A.FIRSTWISHSCHOOL,                                                                     ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.FIRSTWISHSCHOOL) FIRSTWISHSCHOOLor,              ");
		sb.append("       A.FIRSTWISHSPECIALTY,                                                                  ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.FIRSTWISHSPECIALTY) FIRSTWISHSPECIALTYor,        ");
		sb.append("       A.LEARNINGFORM,                                                                        ");
		sb.append("        (SELECT NAME FROM t_school WHERE ID=a.LEARNINGFORM) LEARNINGFORMor,                   ");
		sb.append("       A.MANUALSCHOOL,                                                                        ");
		sb.append("       A.MANUALSPECIALTY,                                                                     ");
		sb.append(
				"       A.BLONGRELATION,a.ctime,a.stepcode,a.enabled,a.comments                                                                     ");
		sb.append("  FROM t_pre_student A, APP_ORGAN_USER B                                                        ");
		sb.append(
				" WHERE A.RECORDER = B.USERID   and a.processcode=?  "
						+ "  ");
//		sb.append("   AND B.NODEID IN                                                                        ");
//		sb.append("       (SELECT nodeid FROM app_organ WHERE FIND_IN_SET(nodeid,sf_getsuborgan(?)))    ");
		//超过30天进入待分配池
		sb.append(" and a.CTIME < DATE_SUB(CURDATE(),INTERVAL 30 DAY)  " +
				"and a.stuid not in (select stuid from t_pre_student_dis) " +
				"and a.enabled='1' " );
		if (vo.getStu_name() != null && vo.getStu_name().length() > 0 && vo.getStu_name().length() < 20) {
			sb.append(" and a.stu_name LIKE '%" + vo.getStu_name() + "%'   ");
		}
		if (vo.getStu_level() != null && vo.getStu_level().length() > 0 && vo.getStu_level().length() < 10) {
			sb.append(" and a.stu_level=" + vo.getStu_level() + "  ");
		}
		if (vo.getCellphone() != null && vo.getCellphone().length() > 0 ) {
			sb.append(" and a.cellphone=" + vo.getCellphone() + "  ");
		}
		if (vo.getS_date() != null && vo.getS_date().length() > 0) {
			sb.append(" AND a.ctime>=to_date('" + vo.getS_date() + "','yyyymmdd')  ");
		}
		if (vo.getE_date() != null && vo.getE_date().length() > 0) {
			sb.append(" AND a.ctime<=to_date('" + vo.getE_date() + "','yyyymmdd')  ");
		}
		CommonJdbcUtils.queryPage(page, sb.toString(), StudentVO.class, "z");
	}
	/**
	 * 查询待操作列表
	 */
	@Override
	public void queryStuListByCurentUserPub(Page page, StudentVO vo) {
		StringBuffer sb = new StringBuffer();
		UserVO user = BusinessContextUtils.getUserContext();
		OrganUser ou = CommonJdbcUtils.queryFirst("select * from app_organ_user where userid=?", OrganUser.class,
				user.getUserid());
		if (ou == null)
			return;
		// Long groupid=user.getGrouptypeid();
		sb.append("SELECT d.groupname GROUPNAME,                                 ");
		sb.append("       A.STUID,                                                                               ");
		sb.append("       A.STU_NAME,                                                                            ");
		if(!user.getGrouptypeclass().equals("04")&&!user.getGrouptypeclass().equals("06"))
		sb.append("       A.CEllPHONE,                                                                               ");
		sb.append("       A.STU_LEVEL,                                                                           ");
		sb.append("       A.cardid,                                                                           ");
		sb.append("       A.RECORDER,                                                                            ");
		sb.append("       (SELECT NAME FROM app_user WHERE userid=a.recorder) RECORDEROR,                        ");
		sb.append("       A.FOLLOW,                                                                              ");
		sb.append("       (SELECT NAME FROM app_user WHERE userid=a.FOLLOW) FOLLOWOR,                            ");
		sb.append("       A.EXAMLEVEL,                                                                           ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.examlevel) examlevelor,                          ");
		sb.append("       A.EXAMCLASS,                                                                           ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.EXAMCLASS) EXAMCLASSor,                          ");
		sb.append("       A.FIRSTWISHSCHOOL,                                                                     ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.FIRSTWISHSCHOOL) FIRSTWISHSCHOOLor,              ");
		sb.append("       A.FIRSTWISHSPECIALTY,                                                                  ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.FIRSTWISHSPECIALTY) FIRSTWISHSPECIALTYor,        ");
		sb.append("       A.LEARNINGFORM,                                                                        ");
		sb.append("        (SELECT NAME FROM t_school WHERE ID=a.LEARNINGFORM) LEARNINGFORMor,                   ");
		sb.append("       A.MANUALSCHOOL,                                                                        ");
		sb.append("       A.MANUALSPECIALTY,                                                                     ");
		sb.append("       A.BLONGRELATION,                                                                        ");
		sb.append(" 	CONCAT((select processname from t_process where PROCESSCODE=a.PROCESSCODE),"
				+ "'》',(select stepname from t_step where STEPCODE=a.STEPCODE)) as proce_stepname ");
		sb.append("  FROM T_STUDENT A,t_student_ext c,v_app_user d                    ");
		sb.append(
				" WHERE  a.stuid=c.stuid	and a.RECORDER=d.userid	and (a.ENABLED<>2 or a.ENABLED is null)						 ");
		if(!"02".equals(user.getGrouptypeclass())){
			sb.append("   AND d.NODEID IN                                                                          	 ");
			sb.append("       (SELECT nodeid FROM app_organ WHERE FIND_IN_SET(nodeid,sf_getsuborgan("+ou.getNodeid()+")))    ");
		}

		if (vo.getStu_name() != null && vo.getStu_name().length() > 0 && vo.getStu_name().length() < 20) {
			sb.append(" and a.stu_name LIKE '%" + vo.getStu_name() + "%'   ");
		}
		if (vo.getStu_level() != null && vo.getStu_level().length() > 0 && vo.getStu_level().length() < 10) {
			sb.append(" and a.stu_level=" + vo.getStu_level() + "  ");
		}
		if (vo.getS_date() != null && vo.getS_date().length() > 0) {
			sb.append(" AND a.ctime>=to_date('" + vo.getS_date() + "','yyyymmdd')  ");
		}
		if (vo.getE_date() != null && vo.getE_date().length() > 0) {
			sb.append(" AND a.ctime<=to_date('" + vo.getE_date() + "','yyyymmdd')  ");
		}
		if (vo.getOldeducationlevel() != null && vo.getOldeducationlevel().length() > 0) {
			sb.append(" AND a.oldeducationlevel='" + vo.getOldeducationlevel() + "' ");
		}
		if (vo.getExamlevel() != null && vo.getExamlevel().length() > 0) {
			sb.append(" AND a.examlevel='" + vo.getExamlevel() + "' ");
		}
		if (vo.getExamclass() != null && vo.getExamclass().length() > 0) {
			sb.append(" AND a.examclass='" + vo.getExamclass() + "' ");
		}
		if (vo.getFirstwishschool() != null && vo.getFirstwishschool().length() > 0) {
			sb.append(" AND a.firstwishschool='" + vo.getFirstwishschool() + "' ");
		}
		if (vo.getFirstwishspecialty() != null && vo.getFirstwishspecialty().length() > 0) {
			sb.append(" AND a.firstwishspecialty='" + vo.getFirstwishspecialty() + "' ");
		}
		if (vo.getIsstudent() != null && "1".equals(vo.getIsstudent())) {
			sb.append(" AND c.isstudent='1'  ");
		}
		if (vo.getCardid() != null&&vo.getCardid().length()>3) {
			sb.append(" AND a.cardid='"+vo.getCardid()+"'  ");
		}
		if (vo.getGroupname() != null && vo.getGroupname().length() > 0) {
			sb.append(" AND d.groupname like '%" + vo.getGroupname() + "%'  ");
		}
		if (vo.getProcesscode() != null && vo.getProcesscode().length() > 0) {
			sb.append(" AND a.processcode ='" + vo.getProcesscode() + "'  ");
		}
		if (vo.getStepcode() != null && vo.getStepcode().length() > 0) {
			sb.append(" AND SUBSTR(a.stepcode,2,1) ='" + vo.getStepcode().substring(1, 2) + "'  ");
		}
		if (vo.getFollowor() != null && vo.getFollowor().length() > 0) {
			sb.append(" AND a.follow =" + vo.getFollowor() + " ");
		}
		if (vo.getIsstudent() != null && "2".equals(vo.getIsstudent())) {
			sb.append(" AND ( c.isstudent is null or c.isstudent='2' ) ");
		}
		if (vo.getIscreatenormal() != null && "3".equals(vo.getIscreatenormal())) {
			sb.append(" AND a.iscreatenormal='3'  ");
		} else {
			sb.append(" AND a.iscreatenormal='1'  ");
		}
		CommonJdbcUtils.queryPage(page, sb.toString(), StudentVO.class);
	}
	/**
	 * 查询待操作列表
	 * 退费查询功能
	 */
	@Override
	public void queryStuListByCurentUserPubForDelete(Page page, StudentVO vo) {
		StringBuffer sb = new StringBuffer();
		UserVO user = BusinessContextUtils.getUserContext();
		OrganUser ou = CommonJdbcUtils.queryFirst("select * from app_organ_user where userid=?", OrganUser.class,
				user.getUserid());
		if (ou == null)
			return;
		// Long groupid=user.getGrouptypeid();
		sb.append("SELECT d.groupname GROUPNAME,                                 ");
		sb.append("       A.STUID,                                                                               ");
		sb.append("       A.STU_NAME,                                                                            ");
		if(!user.getGrouptypeclass().equals("04")&&!user.getGrouptypeclass().equals("06"))
		sb.append("       A.CEllPHONE,                                                                               ");
		sb.append("       A.STU_LEVEL,                                                                           ");
		sb.append("       A.cardid,                                                                           ");
		sb.append("       A.RECORDER,                                                                            ");
		sb.append("       (SELECT NAME FROM app_user WHERE userid=a.recorder) RECORDEROR,                        ");
		sb.append("       A.FOLLOW,                                                                              ");
		sb.append("       (SELECT NAME FROM app_user WHERE userid=a.FOLLOW) FOLLOWOR,                            ");
		sb.append("       A.EXAMLEVEL,                                                                           ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.examlevel) examlevelor,                          ");
		sb.append("       A.EXAMCLASS,                                                                           ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.EXAMCLASS) EXAMCLASSor,                          ");
		sb.append("       A.FIRSTWISHSCHOOL,                                                                     ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.FIRSTWISHSCHOOL) FIRSTWISHSCHOOLor,              ");
		sb.append("       A.FIRSTWISHSPECIALTY,                                                                  ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.FIRSTWISHSPECIALTY) FIRSTWISHSPECIALTYor,        ");
		sb.append("       A.LEARNINGFORM,                                                                        ");
		sb.append("        (SELECT NAME FROM t_school WHERE ID=a.LEARNINGFORM) LEARNINGFORMor,                   ");
		sb.append("       A.MANUALSCHOOL,                                                                        ");
		sb.append("       A.MANUALSPECIALTY,                                                                     ");
		sb.append("       A.BLONGRELATION,                                                                        ");
		sb.append(" 	CONCAT((select processname from t_process where PROCESSCODE=a.PROCESSCODE),"
				+ "'》',(select stepname from t_step where STEPCODE=a.STEPCODE)) as proce_stepname, ");
		sb.append(" (select 1 from t_student_delete f where a.STUID=f.stuid and enabled='1') as dflag ");
		sb.append("  FROM T_STUDENT A,t_student_ext c,v_app_user d                    ");
		sb.append(
				" WHERE  a.stuid=c.stuid	and a.RECORDER=d.userid	" +
						" AND (IFNULL(a.ENABLED,'1')='1' or EXISTS(select 1 from t_student_delete f where a.STUID=f.stuid and enabled='1'))	 ");
		if (vo.getStu_name() != null && vo.getStu_name().length() > 0 && vo.getStu_name().length() < 20) {
			sb.append(" and a.stu_name LIKE '%" + vo.getStu_name() + "%'   ");
		}
		if (vo.getStu_level() != null && vo.getStu_level().length() > 0 && vo.getStu_level().length() < 10) {
			sb.append(" and a.stu_level=" + vo.getStu_level() + "  ");
		}
		if (vo.getS_date() != null && vo.getS_date().length() > 0) {
			sb.append(" AND a.ctime>=to_date('" + vo.getS_date() + "','yyyymmdd')  ");
		}
		if (vo.getE_date() != null && vo.getE_date().length() > 0) {
			sb.append(" AND a.ctime<=to_date('" + vo.getE_date() + "','yyyymmdd')  ");
		}
		if (vo.getOldeducationlevel() != null && vo.getOldeducationlevel().length() > 0) {
			sb.append(" AND a.oldeducationlevel='" + vo.getOldeducationlevel() + "' ");
		}
		if (vo.getExamlevel() != null && vo.getExamlevel().length() > 0) {
			sb.append(" AND a.examlevel='" + vo.getExamlevel() + "' ");
		}
		if (vo.getExamclass() != null && vo.getExamclass().length() > 0) {
			sb.append(" AND a.examclass='" + vo.getExamclass() + "' ");
		}
		if (vo.getFirstwishschool() != null && vo.getFirstwishschool().length() > 0) {
			sb.append(" AND a.firstwishschool='" + vo.getFirstwishschool() + "' ");
		}
		if (vo.getFirstwishspecialty() != null && vo.getFirstwishspecialty().length() > 0) {
			sb.append(" AND a.firstwishspecialty='" + vo.getFirstwishspecialty() + "' ");
		}
		if (vo.getIsstudent() != null && "1".equals(vo.getIsstudent())) {
			sb.append(" AND c.isstudent='1'  ");
		}
		if (vo.getCardid() != null&&vo.getCardid().length()>3) {
			sb.append(" AND a.cardid='"+vo.getCardid().trim()+"'  ");
		}
		if (vo.getGroupname() != null && vo.getGroupname().length() > 0) {
			sb.append(" AND d.groupname like '%" + vo.getGroupname() + "%'  ");
		}
		if (vo.getProcesscode() != null && vo.getProcesscode().length() > 0) {
			sb.append(" AND a.processcode ='" + vo.getProcesscode() + "'  ");
		}
		if (vo.getStepcode() != null && vo.getStepcode().length() > 0) {
			sb.append(" AND SUBSTR(a.stepcode,2,1) ='" + vo.getStepcode().substring(1, 2) + "'  ");
		}
		if (vo.getFollowor() != null && vo.getFollowor().length() > 0) {
			sb.append(" AND a.follow =" + vo.getFollowor() + " ");
		}
		if (vo.getIsstudent() != null && "2".equals(vo.getIsstudent())) {
			sb.append(" AND ( c.isstudent is null or c.isstudent='2' ) ");
		}
		if (vo.getIscreatenormal() != null && "3".equals(vo.getIscreatenormal())) {
			sb.append(" AND a.iscreatenormal='3'  ");
		} else {
			sb.append(" AND a.iscreatenormal='1'  ");
		}
		CommonJdbcUtils.queryPage(page, sb.toString(), StudentVO.class);
	}
	/**
	 * 查询待操作列表
	 */
	@Override
	public Integer queryStuListByCurentUserPubCount( StudentVO vo) {
		StringBuffer sb = new StringBuffer();
		UserVO user = BusinessContextUtils.getUserContext();
		OrganUser ou = CommonJdbcUtils.queryFirst("select * from app_organ_user where userid=?", OrganUser.class,
				user.getUserid());
		if (ou == null)
			return 0;
		// Long groupid=user.getGrouptypeid();
		sb.append("SELECT count(1)                                 ");
		sb.append("  FROM T_STUDENT A,t_student_ext c,v_app_user d                    ");
		sb.append(
				" WHERE  a.stuid=c.stuid	and a.RECORDER=d.userid	and (a.ENABLED<>2 or a.ENABLED is null)						 ");
		if(!"02".equals(user.getGrouptypeclass())){
			sb.append("   AND d.NODEID IN                                                                          	 ");
			sb.append("       (SELECT nodeid FROM app_organ WHERE FIND_IN_SET(nodeid,sf_getsuborgan("+ou.getNodeid()+")))    ");
		}
		if (vo.getStu_name() != null && vo.getStu_name().length() > 0 && vo.getStu_name().length() < 20) {
			sb.append(" and a.stu_name LIKE '%" + vo.getStu_name() + "%'   ");
		}
		if (vo.getStu_level() != null && vo.getStu_level().length() > 0 && vo.getStu_level().length() < 10) {
			sb.append(" and a.stu_level=" + vo.getStu_level() + "  ");
		}
		if (vo.getS_date() != null && vo.getS_date().length() > 0) {
			sb.append(" AND a.ctime>=to_date('" + vo.getS_date() + "','yyyymmdd')  ");
		}
		if (vo.getE_date() != null && vo.getE_date().length() > 0) {
			sb.append(" AND a.ctime<=to_date('" + vo.getE_date() + "','yyyymmdd')  ");
		}
		if (vo.getIsstudent() != null && "1".equals(vo.getIsstudent())) {
			sb.append(" AND c.isstudent='1'  ");
		}
		if (vo.getGroupname() != null && vo.getGroupname().length() > 0) {
			sb.append(" AND d.groupname like '%" + vo.getGroupname() + "%'  ");
		}
		if (vo.getProcesscode() != null && vo.getProcesscode().length() > 0) {
			sb.append(" AND a.processcode ='" + vo.getProcesscode() + "'  ");
		}
		if (vo.getStepcode() != null && vo.getStepcode().length() > 0) {
			sb.append(" AND SUBSTR(a.stepcode,2,1) ='" + vo.getStepcode().substring(1, 2) + "'  ");
		}
		if (vo.getFollowor() != null && vo.getFollowor().length() > 0) {
			sb.append(" AND a.follow =" + vo.getFollowor() + " ");
		}
		if (vo.getIsstudent() != null && "2".equals(vo.getIsstudent())) {
			sb.append(" AND ( c.isstudent is null or c.isstudent='2' ) ");
		}
		if (vo.getIscreatenormal() != null && "3".equals(vo.getIscreatenormal())) {
			sb.append(" AND a.iscreatenormal='3'  ");
		} else {
			sb.append(" AND a.iscreatenormal='1'  ");
		}
		return  CommonJdbcUtils.queryObject(sb.toString(), Integer.class);
	}

	/**
	 * 查询待操作列表
	 */
	@Override
	public void queryExport(Page page, StudentVO vo) {
		StringBuffer sb = new StringBuffer();
		UserVO user = BusinessContextUtils.getUserContext();
		// String deptname=user.getGroupname();
		OrganUser ou = CommonJdbcUtils.queryFirst("select * from app_organ_user where userid=?", OrganUser.class,
				user.getUserid());
		if (ou == null)
			return;
		// Long groupid=user.getGrouptypeid();
		sb.append(
				"SELECT (SELECT E.GROUPNAME																																																														");
		sb.append(
				"          FROM APP_GROUP E, APP_GROUP_USER F                                                                                                         ");
		sb.append(
				"         WHERE E.GROUPID = F.GROUPID                                                                                                                 ");
		sb.append(
				"           AND F.USERID = A.RECORDER) DEPTNAME,                                                                                                      ");
		sb.append(
				"       A.STUID,                                                                                                                                      ");
		sb.append(
				"       A.EDUCATIONTYPE,                                                                                                                              ");
		sb.append(
				"       A.STU_LEVEL,                                                                                                                                  ");
		sb.append(
				"       A.STU_NAME,                                                                                                                                   ");
		sb.append(
				"       A.SEX,                                                                                                                                        ");
		sb.append(
				"       A.NATION,                                                                                                                                     ");
		sb.append(
				"       A.POLITICALSTATUS,                                                                                                                            ");
		sb.append(
				"       A.CARDID,                                                                                                                                     ");
		if(!user.getGrouptypeclass().equals("04")&&!user.getGrouptypeclass().equals("06"))
		{
		sb.append(
				"       A.CELLPHONE,                                                                                                                                  ");
		sb.append(
				"       A.PHONE,                                                                                                                                      ");
		sb.append(
				"       A.QQ,                                                                                                                                         ");
		sb.append(
				"       A.OTHERPHONE,                                                                                                                                 ");
		sb.append(
				"       A.EMAIL,                                                                                                                                      ");
		sb.append(
				"       A.COMPANY,                                                                                                                                    ");
		}
		sb.append(
				"       A.CLIENTCLASS,                                                                                                                                ");
		sb.append(
				"       A.ADDRESS,                                                                                                                                    ");
		sb.append(
				"       A.BLONGTO,                                                                                                                                    ");
		sb.append(
				"       A.NEARBY,                                                                                                                                     ");
		sb.append(
				"       A.OLDEDUCATIONLEVEL,                                                                                                                          ");
		sb.append(
				"       A.EXAMLEVEL,                                                                                                                                  ");
		sb.append(
				"       A.EXAMCLASS,                                                                                                                                  ");
		sb.append(
				"       A.FIRSTWISHSCHOOL,                                                                                                                            ");
		sb.append(
				"       A.FIRSTWISHSPECIALTY,                                                                                                                         ");
		sb.append(
				"       A.LEARNINGFORM,                                                                                                                               ");
		sb.append(
				"       A.FIRSTWISHLENGTH,                                                                                                                            ");
		sb.append(
				"       A.COLLECTWISHSCHOOL,                                                                                                                          ");
		sb.append(
				"       A.COLLECTWISHSPECIALTY,                                                                                                                       ");
		sb.append(
				"       A.MANUALSCHOOL,                                                                                                                               ");
		sb.append(
				"       A.MANUALSPECIALTY,                                                                                                                            ");
		sb.append(
				"       A.MANUALTYPE,                                                                                                                                 ");
		sb.append(
				"       A.MANUALLENGTH,                                                                                                                               ");
		sb.append(
				"       A.SCHOOLRESOURCE,                                                                                                                             ");
		sb.append(
				"       A.BLONGRELATION,                                                                                                                              ");
		sb.append(
				"       A.INTRODUCER,                                                                                                                                 ");
		sb.append(
				"       A.INTRODUCERPHONE,                                                                                                                            ");
		sb.append(
				"       A.NETSERVICE,                                                                                                                                 ");
		sb.append(
				"       A.PERFORMANCER,                                                                                                                               ");
		sb.append(
				"       A.FINANCENUMBER,                                                                                                                               ");
		sb.append(
				"       (select name from app_user where userid=A.OLDBELONG) OLDBELONG,                                                                               ");
		sb.append(
				"       (select name from app_user where userid=A.FOLLOW) FOLLOW,                                                                                     ");
		sb.append(
				"       (select name from app_user where userid=A.RECORDER) RECORDER,                                                                                 ");
		sb.append(
				"       A.ENTRYFEE,                                                                                                                                   ");
		sb.append(
				"       A.MEMBERLEVEL,                                                                                                                                ");
		sb.append(
				"       case A.ISTEXTBOOK when '1' then '是' ELSE '否' end as ISTEXTBOOK,                                                                             ");
		sb.append(
				"       case A.ISDISK when '1' then '是' ELSE '否' end as ISDISK,                                                                                     ");
		sb.append(
				"       case A.ISTEXTBOOKCAT when '1' then '是' ELSE '否' end as ISTEXTBOOKCAT,                                                                       ");
		sb.append(
				"       case A.ISGIFT when '1' then '是' ELSE '否' end as ISGIFT,                                                                                     ");
		sb.append(
				"       case A.ISEDUSERACH when '1' then '是' ELSE '否' end as ISEDUSERACH,                                                                           ");
		sb.append(
				"       case A.ISPHOTO when '1' then '是' ELSE '否' end as ISPHOTO,                                                                                   ");
		sb.append(
				"       A.ISCREATENORMAL,                                                                                                                             ");
		sb.append(
				"       case A.ISFALL when '1' then '是' ELSE '否' end as ISFALL,                                                                                     ");
		sb.append(
				"       A.COMMENTS,                                                                                                                                   ");
		sb.append(
				"       A.PHOTOID,                                                                                                                                    ");
		sb.append(
				"       (select processname from t_process where PROCESSCODE=A.PROCESSCODE) PROCESSCODE ,                                                             ");
		sb.append(
				"       (select stepname from t_step where STEPCODE=A.STEPCODE) STEPCODE,                                                                             ");
		sb.append(
				"       A.ENABLED,                                                                                                                                    ");
		sb.append(
				"       A.CTIME,                                                                                                                                      ");
		sb.append(
				"       (SELECT NAME FROM T_SCHOOL WHERE ID = A.OLDEDUCATIONLEVEL) OLDEDUCATIONLEVEL_O,                                                               ");
		sb.append(
				"       (SELECT NAME FROM T_SCHOOL WHERE ID = A.EXAMLEVEL) EXAMLEVEL_O,                                                                               ");
		sb.append(
				"       (SELECT NAME FROM T_SCHOOL WHERE ID = A.EXAMCLASS) EXAMCLASS_O,                                                                               ");
		sb.append(
				"       (SELECT NAME FROM T_SCHOOL WHERE ID = A.FIRSTWISHSCHOOL) FIRSTWISHSCHOOL_O,                                                                   ");
		sb.append(
				"       (SELECT NAME FROM T_SCHOOL WHERE ID = A.FIRSTWISHSPECIALTY) FIRSTWISHSPECIALTY_O,                                                             ");
		sb.append(
				"       (SELECT NAME FROM T_SCHOOL WHERE ID = A.LEARNINGFORM) LEARNINGFORM_O,                                                                         ");
		sb.append(
				"       A.FIRSTWISHLENGTH FIRSTWISHLENGTH_O,                                                                                                          ");
		sb.append(
				"       (SELECT NAME FROM T_SCHOOL WHERE ID = A.COLLECTWISHSCHOOL) COLLECTWISHSCHOOL_O,                                                               ");
		sb.append(
				"       (SELECT NAME FROM T_SCHOOL WHERE ID = A.COLLECTWISHSPECIALTY) COLLECTWISHSPECIALTY_O,                                                         ");
		sb.append(
				"       B.ISENROLLENSURE,                                                                                                                             ");
		sb.append(
				"       CASE B.ISENROLL when '1' then '是' ELSE '否' end as ISENROLL,                                                                                 ");
		sb.append(
				"       B.ENROLLNUMBER,                                                                                                                               ");
		sb.append(
				"       B.ISNETPAY,                                                                                                                                   ");
		sb.append(
				"       B.EXAMNUMBER,                                                                                                                                 ");
		sb.append(
				"       CASE B.ISEXAMCARD when '1' then '是' ELSE '否' end as ISEXAMCARD,                                                                             ");
		sb.append(
				"       B.EXAMADDRESS,                                                                                                                                ");
		sb.append(
				"       B.INITSCORE,                                                                                                                                  ");
		sb.append(
				"       B.PLUSSCORE,                                                                                                                                  ");
		sb.append(
				"       CASE B.ISCHEAT when '1' then '是' ELSE '否' end as ISCHEAT,                                                                                   ");
		sb.append(
				"       B.FINALSCORE,                                                                                                                                 ");
		sb.append(
				"       CASE B.ISENTERLINE when '1' then '是' ELSE '否' end as ISENTERLINE,                                                                           ");
		sb.append(
				"       B.ISENTER,                                                                                                                                    ");
		sb.append(
				"       CASE B.ISSTUDENT when '1' then '是' ELSE '否' end as ISSTUDENT,                                                                               ");
		sb.append(
				"       B.STUDENTADDRESS,                                                                                                                             ");
		sb.append(
				"       B.FINALENTER,                                                                                                                                 ");
		sb.append(
				"       (SELECT NAME FROM T_SCHOOL WHERE ID = B.EXAMLEVEL) EXAMLEVEL_F,                                                                               ");
		sb.append(
				"       (SELECT NAME FROM T_SCHOOL WHERE ID = B.EXAMCLASS) EXAMCLASS_F,                                                                               ");
		sb.append(
				"       (SELECT NAME FROM T_SCHOOL WHERE ID = B.FIRSTWISHSCHOOL) FIRSTWISHSCHOOL_F,                                                                   ");
		sb.append(
				"       (SELECT NAME FROM T_SCHOOL WHERE ID = B.FIRSTWISHSPECIALTY) FIRSTWISHSPECIALTY_F,                                                             ");
		sb.append(
				"       (SELECT NAME FROM T_SCHOOL WHERE ID = B.LEARNINGFORM) LEARNINGFORM_F,                                                                         ");
		sb.append(
				"       B.FIRSTWISHLENGTH FIRSTWISHLENGTH_F,                                                                                                          ");
		sb.append(
				"       B.TUITION,                                                                                                                                    ");
		sb.append(
				"       (select aaa103 from app_aa10 where aaa100='TUITION' and aaa102=B.TUITIONFIRST) TUITIONFIRST,                                                  ");
		sb.append(
				"       B.BOOKFEE,                                                                                                                                    ");
		sb.append(
				"       B.INSCHOOLDATE,                                                                                                                               ");
		sb.append(
				"       B.OUTSCHOOLDATE,                                                                                                                              ");
		sb.append(
				"       CASE B.ISGRADUATE  when '1' then '是' ELSE '否' end as ISGRADUATE,                                                                            ");
		sb.append(
				"(select concat('学费：应缴',PAYABLE,',实缴',paidin,'。书费：应缴',BOOKABLE,',实缴',BOOKIN,'。是否已缴：',case ISPAY when 1 then '是' else '否' END)  ");
		sb.append(
				"as yearfee1 from t_student_pay where stuid=A.STUID and SEQNUMBER=1) yearfee1,                                                                             ");
		sb.append(
				"(select concat('学费：应缴',PAYABLE,',实缴',paidin,'。书费：应缴',BOOKABLE,',实缴',BOOKIN,'。是否已缴：',case ISPAY when 1 then '是' else '否' END)  ");
		sb.append(
				"as yearfee2 from t_student_pay where stuid=A.STUID and SEQNUMBER=2) yearfee2,                                                                             ");
		sb.append(
				"(select concat('学费：应缴',PAYABLE,',实缴',paidin,'。书费：应缴',BOOKABLE,',实缴',BOOKIN,'。是否已缴：',case ISPAY when 1 then '是' else '否' END)  ");
		sb.append(
				"as yearfee3 from t_student_pay where stuid=A.STUID and SEQNUMBER=3) yearfee3,                                                                             ");
		sb.append(
				"(select concat('学费：应缴',PAYABLE,',实缴',paidin,'。书费：应缴',BOOKABLE,',实缴',BOOKIN,'。是否已缴：',case ISPAY when 1 then '是' else '否' END)  ");
		sb.append(
				"as yearfee4 from t_student_pay where stuid=A.STUID and SEQNUMBER=4) yearfee4,                                                                             ");
		sb.append(
				"(select concat('学费：应缴',PAYABLE,',实缴',paidin,'。书费：应缴',BOOKABLE,',实缴',BOOKIN,'。是否已缴：',case ISPAY when 1 then '是' else '否' END)  ");
		sb.append(
				"as yearfee5 from t_student_pay where stuid=A.STUID and SEQNUMBER=5) yearfee5                                                                              ");
		sb.append(
				"  FROM T_STUDENT A, T_STUDENT_EXT B, v_app_user C                                                                                                ");
		sb.append(
				" WHERE A.STUID = B.STUID                                                                                                                             ");
		sb.append(
				"   AND A.RECORDER = C.USERID   and (a.ENABLED<>2 or a.ENABLED is null)                                                                                                                      ");
		sb.append(
				"   AND C.NODEID IN                                                                                                                                   ");
		sb.append(
				"       (SELECT NODEID                                                                                                                                ");
		sb.append(
				"          FROM APP_ORGAN                                                                                                                             ");
		sb.append(
				"         WHERE FIND_IN_SET(NODEID, SF_GETSUBORGAN(?)))                                                                                               ");

		if (vo.getStu_name() != null && vo.getStu_name().length() > 0 && vo.getStu_name().length() < 20) {
			sb.append(" and a.stu_name LIKE '%" + vo.getStu_name() + "%'   ");
		}
		if (vo.getStu_level() != null && vo.getStu_level().length() > 0 && vo.getStu_level().length() < 10) {
			sb.append(" and a.stu_level=" + vo.getStu_level() + "  ");
		}
		if (vo.getS_date() != null && vo.getS_date().length() > 0) {
			sb.append(" AND a.ctime>=str_to_date('" + vo.getS_date() + "','%Y%m%d')  ");
		}
		if (vo.getE_date() != null && vo.getE_date().length() > 0) {
			sb.append(" AND a.ctime<=str_to_date('" + vo.getE_date() + "','%Y%m%d')  ");
		}
		if (vo.getIscreatenormal() != null && "3".equals(vo.getIscreatenormal())) {
			sb.append(" AND a.iscreatenormal='3'  ");
		} else {
			sb.append(" AND a.iscreatenormal='1'  ");
		}
		if (vo.getIsstudent() != null && "1".equals(vo.getIsstudent())) {
			sb.append(" AND b.isstudent='1'  ");
		}
		if (vo.getGroupname() != null && vo.getGroupname().length() > 0) {
			sb.append(" AND c.groupname like '%" + vo.getGroupname() + "%'  ");
		}
		if (vo.getProcesscode() != null && vo.getProcesscode().length() > 0) {
			sb.append(" AND a.processcode ='" + vo.getProcesscode() + "'  ");
		}
		if (vo.getStepcode() != null && vo.getStepcode().length() > 0) {
			sb.append(" AND SUBSTR(a.stepcode,2,1) ='" + vo.getStepcode().substring(1, 2) + "'  ");
		}
		if (vo.getFollowor() != null && vo.getFollowor().length() > 0) {
			sb.append(" AND a.follow =" + vo.getFollowor() + " ");
		}
		if (vo.getIsstudent() != null && "2".equals(vo.getIsstudent())) {
			sb.append(" AND ( b.isstudent is null or b.isstudent='2' ) ");
		}
		CommonJdbcUtils.queryPage(page, sb.toString(), ExportVO.class, ou.getNodeid());
	}

	@Override
	public List<ExportSetVO> queryExportSets(String iscall, String isstudent) {
		String sql = "select * from t_export_set where 1=1 ";
		if (iscall != null) {
			sql = sql + " and iscall=" + iscall;
		}
		if (isstudent != null) {
			sql = sql + " and isstudent=" + isstudent;
		}
		return CommonJdbcUtils.query(sql, ExportSetVO.class);
	}

	@Override
	public List<ExportSetVO> queryExportSets(String arrays) {
		String[] arrs = arrays.split(",");
		StringBuffer sb = new StringBuffer("select * from t_export_set where 1=1 and (");
		for (int i = 0; i < arrs.length; i++) {
			if (i == arrs.length - 1)
				sb.append(" columnname ='" + arrs[i] + "' ");
			else
				sb.append(" columnname ='" + arrs[i] + "' or ");
		}
		sb.append(")");
		return CommonJdbcUtils.query(sb.toString(), ExportSetVO.class);
	}

	/**
	 * 查询待操作列表
	 */
	@Override
	public void queryStuListByCurentUser(Page page) {
		StringBuffer sb = new StringBuffer();
		UserVO user = BusinessContextUtils.getUserContext();
		OrganUser ou = CommonJdbcUtils.queryFirst("select * from app_organ_user where userid=?", OrganUser.class,
				user.getUserid());
		if (ou == null)
			return;
		Long groupid = user.getGrouptypeid();
		sb.append("select e.PROCESSCODE,e.PROCESSNAME,d.VCOUNT from (SELECT A.PROCESSCODE,                    ");
		sb.append("       (SELECT PROCESSNAME FROM T_PROCESS WHERE PROCESSCODE = A.PROCESSCODE) PROCESSNAME,  ");
		sb.append("       COUNT(DISTINCT A.STUID) VCOUNT                                                      ");
		sb.append("  FROM T_STUDENT A, APP_ORGAN_USER B                                          ");
		sb.append(" WHERE A.RECORDER = B.USERID                                                               ");
		sb.append(
				"   AND A.ISCREATENORMAL = '1'     and (a.ENABLED<>2 or a.ENABLED is null)                                                         ");
		sb.append(
				"   AND a.STEPCODE in (select STEPCODE from t_step where FIND_IN_SET(?,GROUPID))                  ");
		if(!"02".equals(user.getGrouptypeclass())){
			sb.append("   AND B.NODEID IN                                                                         ");
			sb.append("       (SELECT NODEID                                                                      ");
			sb.append("          FROM APP_ORGAN                                                                   ");
			sb.append("         WHERE FIND_IN_SET(NODEID, SF_GETSUBORGAN("+ou.getNodeid()+")))                                      ");
		}

		sb.append(" GROUP BY A.PROCESSCODE) d RIGHT  JOIN t_process e on d.PROCESSCODE=e.PROCESSCODE          ");
		CommonJdbcUtils.queryPage(page, sb.toString(), StudentVO.class, groupid);
	}

	@Override
	public StudentVO queryStudnetByStuid(String stuid) {
		String sql = "SELECT "
				+ "(SELECT groupname FROM app_group,app_group_user WHERE app_group_user.groupid=app_group.groupid AND app_group_user.userid=a.recorder) groupname,"
				+ "(SELECT processname FROM t_process WHERE processcode=a.processcode) processname,"
				+ "(SELECT stepname FROM t_step WHERE stepcode=a.stepcode) stepname,a.ctime s_date,a.* "
				+ "FROM t_student a WHERE a.stuid=?";
		return CommonJdbcUtils.queryFirst(sql, StudentVO.class, stuid);
	}
	@Override
	public StudentVO queryStudnetByStuidPre(String stuid) {
		String sql = "SELECT "
				+ "(SELECT groupname FROM app_group,app_group_user WHERE app_group_user.groupid=app_group.groupid AND app_group_user.userid=a.recorder) groupname,"
				+ "(SELECT processname FROM t_process WHERE processcode=a.processcode) processname,"
				+ "(SELECT stepname FROM t_step WHERE stepcode=a.stepcode) stepname,a.ctime s_date,a.* "
				+ "FROM t_pre_student a WHERE a.stuid=?";
		return CommonJdbcUtils.queryFirst(sql, StudentVO.class, stuid);
	}

	@Override
	public Student updateStudent(StudentVO vo) {
		Student student = CommonJdbcUtils.queryFirst("select * from t_student where stuid=?", Student.class,
				vo.getStuid());
		BeanUtils.copyProperties(vo, student,
				new String[] { "stuid", "processcode", "stepcode", "enabled", "ctime", "isfall" });
		CommonJdbcUtils.update(student);
		return student;
	}

	/**
	 * 更改人员信息并进入下步操作
	 */
	@Override
	public Student execNextStep(StudentVO vo) {
		Student student = updateStudent(vo);
		ProcessDTO dto = null;
		if ("3".equals(vo.getIscreatenormal()) || "1".equals(vo.getNotNext())) {// 掉队操作不执行下一步
			dto = new ProcessDTO(student.getProcesscode(), student.getStepcode());
		} else {
			dto = new ProcessDTO(student.getProcesscode(), student.getStepcode());
			gotoNextStep(dto);
			payRemain(vo.getStuid(), dto);
			student.setProcesscode(dto.getNext_processcode());
			student.setStepcode(dto.getNext_stepcode());
			CommonJdbcUtils.update(student);
			// 生成学籍后生成缴费信息
			if (!"K".equals(dto.getNext_processcode()) && "K".equals(dto.getProcesscode())) {
				addStudentPay(vo);
			}

		}

		saveLog(dto, vo.getStuid(), vo.getStu_name());
		return student;
	}

	/**
	 * 批量人员信息并进入下步操作
	 */
	@Override
	public void execPatchNextStep(String[] stuidArray) {
		StudentVO studentVO = null;
		for (String stuid : stuidArray) {
			studentVO = CommonJdbcUtils.queryFirst("select * from t_student where stuid=?", StudentVO.class, stuid);
			execNextStep(studentVO);
		}
	}

	// 缴费提醒
	public void payRemain(Long stuid, ProcessDTO dto) {
		List<StudentPay> payList = queryStudentPayByStuid(stuid);
		int i = payList.size();
		if (!dto.getProcesscode().equals(dto.getNext_processcode()) && i > 0) {

			if ((i == 1 && "L".equals(dto.getProcesscode())) || (i == 2 && "M".equals(dto.getProcesscode()))
					|| (i == 3 && "N".equals(dto.getProcesscode())) || (i == 4 && "O".equals(dto.getProcesscode()))
					|| (i == 5 && "P".equals(dto.getProcesscode()))) {
				dto.setProcesscode("T");
				dto.setStepcode("T1");
			}
		}
	}

	@Override
	public List<StudentPay> queryStudentPayByStuid(Long stuid) {
		String sql = "select * from t_student_pay where stuid=?";
		return CommonJdbcUtils.query(sql, StudentPay.class, stuid);
	}

	@Override
	public void queryStudentPayByStuid(Page page, Long stuid) {
		String sql = "select * from t_student_pay where stuid=?";
		CommonJdbcUtils.queryPage(page, sql, StudentPay.class, stuid);
	}

	@Override
	public StudentPay queryStudentPayByPayid(Long payid) {
		String sql = "select * from t_student_pay where payid=?";
		return CommonJdbcUtils.queryFirst(sql, StudentPay.class, payid);
	}

	@Override
	public void updateStudentPay(StudentPay pay) {
		String sql = "select * from t_student_pay where payid=?";
		StudentPay sp = CommonJdbcUtils.queryFirst(sql, StudentPay.class, pay.getPayid());
		sp.setPayable(pay.getPayable());
		sp.setPaidin(pay.getPaidin());
		sp.setPaydate(new Date());
		sp.setBookable(pay.getBookable());
		sp.setBookin(pay.getBookin());
		sp.setBookdate(new Date());
		sp.setIspay(pay.getIspay());
		sp.setComments(pay.getComments());
		sp.setTicketnumber(pay.getTicketnumber());

		CommonJdbcUtils.update(sp);
	}

	/**
	 * 掉队操作
	 * 
	 * @param stuid
	 * @param flag
	 *            1误操作恢复，2老生转报名
	 * @return
	 */
	@Override
	public Student execFall(Long stuid, String flag) {
		Student stu = CommonJdbcUtils.queryFirst("select * from t_student where stuid=?", Student.class, stuid);
		if ("1".equals(flag)) {
			stu.setIscreatenormal("1");
			CommonJdbcUtils.update(stu);
		} else if ("2".equals(flag)) {
			stu.setIscreatenormal("1");
			stu.setProcesscode("B");
			stu.setStepcode("B1");
			stu.setIsfall("0");
			CommonJdbcUtils.update(stu);
		}

		return stu;
	}

	@Override
	public void deleteStudent(String stuid) {
		String sql = "update t_student set enabled='2' where stuid=?";
		CommonJdbcUtils.execute(sql, stuid);

	}

	@Override
	public List<ProStepParmVO> queryProcessStepParams(String param) {
		String sql = "";
		if ("process".equals(param))
			sql = "select processcode id,processname name from t_process where enable='1' ";
		if ("step".equals(param))
			sql = "select stepcode id,stepname name from t_step where PROCESSCODE='C'";
		return CommonJdbcUtils.query(sql, ProStepParmVO.class);
	}
	/**
	 * 生成生日提醒记录
	 */
	public void saveBirthdayRemind(){
		String sql="insert into t_birthday(stuid,year,birthday,remind) " +
				" select stuid,year(CURDATE()),SUBSTR(cardid FROM 11 FOR 4),'0' from t_student where SUBSTR(cardid FROM 11 FOR 4) = SUBSTR(CURDATE()+0 FROM 5 FOR 4)";
		CommonJdbcUtils.execute(sql);
	}
	
	/**
	 * 查询待操作列表
	 */
	@Override
	public void queryStuListByBirthdayRemind(Page page, StudentVO vo) {
		StringBuffer sb = new StringBuffer();
		UserVO user = BusinessContextUtils.getUserContext();
		OrganUser ou = CommonJdbcUtils.queryFirst("select * from app_organ_user where userid=?", OrganUser.class,
				user.getUserid());
		if (ou == null)
			return;
		// Long groupid=user.getGrouptypeid();
		sb.append("SELECT d.groupname GROUPNAME,                                 ");
		sb.append("       A.STUID,                                                                               ");
		sb.append("       A.STU_NAME,                                                                            ");
		if(!user.getGrouptypeclass().equals("04")&&!user.getGrouptypeclass().equals("06"))
		sb.append("       A.CEllPHONE,                                                                               ");
		sb.append("       A.STU_LEVEL,                                                                           ");
		sb.append("       A.RECORDER,                                                                            ");
		sb.append("       (SELECT NAME FROM app_user WHERE userid=a.recorder) RECORDEROR,                        ");
		sb.append("       A.FOLLOW,                                                                              ");
		sb.append("       (SELECT NAME FROM app_user WHERE userid=a.FOLLOW) FOLLOWOR,                            ");
		sb.append("       A.EXAMLEVEL,                                                                           ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.examlevel) examlevelor,                          ");
		sb.append("       A.EXAMCLASS,                                                                           ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.EXAMCLASS) EXAMCLASSor,                          ");
		sb.append("       A.FIRSTWISHSCHOOL,                                                                     ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.FIRSTWISHSCHOOL) FIRSTWISHSCHOOLor,              ");
		sb.append("       A.FIRSTWISHSPECIALTY,                                                                  ");
		sb.append("       (SELECT NAME FROM t_school WHERE ID=a.FIRSTWISHSPECIALTY) FIRSTWISHSPECIALTYor,        ");
		sb.append("       A.LEARNINGFORM,                                                                        ");
		sb.append("        (SELECT NAME FROM t_school WHERE ID=a.LEARNINGFORM) LEARNINGFORMor,                   ");
		sb.append("       A.MANUALSCHOOL,                                                                        ");
		sb.append("       A.MANUALSPECIALTY,                                                                     ");
		sb.append("       A.BLONGRELATION,c.remind,c.birthday,                                                                      ");
		sb.append(" 	CONCAT((select processname from t_process where PROCESSCODE=a.PROCESSCODE),"
				+ "'》',(select stepname from t_step where STEPCODE=a.STEPCODE)) as proce_stepname ");
		sb.append("  FROM T_STUDENT A,v_app_user d,t_birthday c ");
		sb.append(
				" WHERE a.stuid=c.stuid and a.RECORDER=d.userid	and (a.ENABLED<>2 or a.ENABLED is null)						 ");
		sb.append("   AND d.NODEID IN                                                                          	 ");
		sb.append("       (SELECT nodeid FROM app_organ WHERE FIND_IN_SET(nodeid,sf_getsuborgan(?)))    ");
		if (vo.getStu_name() != null && vo.getStu_name().length() > 0 && vo.getStu_name().length() < 20) {
			sb.append(" and a.stu_name LIKE '%" + vo.getStu_name() + "%'   ");
		}
		if (vo.getStu_level() != null && vo.getStu_level().length() > 0 && vo.getStu_level().length() < 10) {
			sb.append(" and a.stu_level=" + vo.getStu_level() + "  ");
		}
		if (vo.getS_date() != null && vo.getS_date().length() > 0) {
			sb.append(" AND a.ctime>=to_date('" + vo.getS_date() + "','yyyymmdd')  ");
		}
		if (vo.getE_date() != null && vo.getE_date().length() > 0) {
			sb.append(" AND a.ctime<=to_date('" + vo.getE_date() + "','yyyymmdd')  ");
		}
		if (vo.getGroupname() != null && vo.getGroupname().length() > 0) {
			sb.append(" AND d.groupname like '%" + vo.getGroupname() + "%'  ");
		}
		if (vo.getFollowor() != null && vo.getFollowor().length() > 0) {
			sb.append(" AND a.follow =" + vo.getFollowor() + " ");
		}
		if (vo.getIscreatenormal() != null && "3".equals(vo.getIscreatenormal())) {
			sb.append(" AND a.iscreatenormal='3'  ");
		} else {
			sb.append(" AND a.iscreatenormal='1'  ");
		}
		if (vo.getRemind() != null && vo.getRemind().length() > 0) {
			sb.append(" AND c.remind =" + vo.getRemind() + " ");
		}
		CommonJdbcUtils.queryPage(page, sb.toString(), StudentVO.class, ou.getNodeid());
	}
	
	public void updateBirthdayRemind(Long stuid){
		String sql="update t_birthday set remind='1' where stuid=? ";
		CommonJdbcUtils.execute(sql,stuid);
	}

	/**
	 * 重新分配 人员
	 */
	@Transactional
	public void updateDistributePreStudent(Long stuid,Long userid){
		List<StudentVO> studentVOS=CommonJdbcUtils.query("select * from t_pre_student_dis where record=?",StudentVO.class,userid);
		if (studentVOS.size()>=5){
			throw new BusinessException("该老师已经分配"+studentVOS.size()+"人，不能继续分配！");
		}
		String sql="update t_pre_student set RECORDER=? where stuid=? ";
		CommonJdbcUtils.execute(sql,userid,stuid);
		CommonJdbcUtils.execute("insert into t_pre_student_dis values(?,?,'1')",stuid,userid);
	}
	/**
	 * 退费,删除人员
	 */
	@Transactional
	public void deleteRefund(StudentDeleteVO vo){
		UserVO user = BusinessContextUtils.getUserContext();
		String sql="update t_student set enabled='2' where stuid=?";
		CommonJdbcUtils.execute(sql,vo.getStuid());
		StudentDelete studentDelete=new StudentDelete();
		BeanUtils.copyProperties(vo,studentDelete);
		studentDelete.setUserid(user.getUserid());
		studentDelete.setEnabled("1");
		studentDelete.setCtime(new SimpleDateFormat("yyyyMMdd HH:mm:ss").format(new Date()));
		CommonJdbcUtils.save(studentDelete);
	}
	/**
	 * 退费删除人员
	 */
	@Transactional
	public void updateRefund(StudentDeleteVO vo){
		String sql="update t_student set enabled='1' where stuid=?";
		CommonJdbcUtils.execute(sql,vo.getStuid());
		sql="update t_student_delete set enabled='2' where stuid=?";
		CommonJdbcUtils.execute(sql,vo.getStuid());
	}
}

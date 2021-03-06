package com.wb.school.f1001.f100101;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.jdbcutils.Page;
import com.wb.login.SessionUtils;
import com.wb.login.model.UserVO;
import com.wb.school.common.bo.Student;
import com.wb.school.common.bo.StudentPre;
import com.wb.school.f1001.common.service.EmpService;
import com.wb.school.f1001.common.vo.StudentVO;
import com.wb.user.utils.BusinessContextUtils;
import com.wb.utils.web.JsonUtils;
import com.wb.utils.web.RequestUtils;

/**
 * 人员基本信息
 * @author xue
 * 2015-9-25
 */
@Controller
@RequestMapping(value="/work/f10010113/")
public class F10010113Action {

	@Autowired
	private EmpService empService;
	
	@RequestMapping("index.action")
	public String index(HttpServletRequest request){
		HttpSession session=request.getSession();
		UserVO uservo=(UserVO)session.getAttribute(SessionUtils.USER);
		request.setAttribute("user", uservo);
		request.setAttribute("groupname", uservo.getGroupname());
		request.setAttribute("userid", uservo.getUserid());
		return "/f10/f1001/f100101/f10010113/index";
	}
	
	@RequestMapping("preAddStudent.action")
	@ResponseBody
	public String addStudent(StudentPre student, HttpServletRequest request) {
		empService.addStudentPre(student);
		return JsonUtils.getJsonData(student);
	}
	@RequestMapping("updateStudentPreToAlready.action")
	@ResponseBody
	public String updateStudentPreToAlready(StudentPre student, HttpServletRequest request) {
		empService.updateStudentPreToAlready(student);
		return JsonUtils.getJsonData(student);
	}
	@RequestMapping(value="queryStuListByCurentUserPre.action")
	@ResponseBody
	public String queryUsersByNodeid(StudentVO vo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int pageSize = RequestUtils.getParameter(request, "pageSize", 0);
		int pageIndex = RequestUtils.getParameter(request, "pageIndex", 0);
		Page page = new Page();
		page.setPageno(pageIndex+1);
		page.setPagesize(pageSize);
		this.empService.queryStuListByCurentUserPre(page,vo);
		return JsonUtils.getJsonByPage(page);
	}
	@RequestMapping(value="queryStuListByCurentUserPreCount.action")
	@ResponseBody
	public String queryStuListByCurentUserPreCount(StudentVO vo,HttpServletRequest request,HttpServletResponse response) throws Exception{

		return  this.empService.queryStuListByCurentUserPreCount(vo).toString();
	}
	@RequestMapping(value="queryStuListByCurentUserPreDis.action")
	@ResponseBody
	public String queryStuListByCurentUserPreDis(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int pageSize = RequestUtils.getParameter(request, "pageSize", 0);
		int pageIndex = RequestUtils.getParameter(request, "pageIndex", 0);
		StudentVO vo = new StudentVO();
		String stu_name = request.getParameter("stu_name");
		String stu_level = request.getParameter("stu_level");
		String cellphone = request.getParameter("cellphone");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		vo.setStu_name(stu_name);
		vo.setStu_level(stu_level);
		vo.setS_date(s_date);
		vo.setE_date(e_date);
		vo.setCellphone(cellphone);
		Page page = new Page();
		page.setPageno(pageIndex+1);
		page.setPagesize(pageSize);
		this.empService.queryStuListByCurentUserPreDis(page,vo);
		return JsonUtils.getJsonByPage(page);
	}
	@RequestMapping("queryStudnetByStuidPre.action")
	@ResponseBody
	public String queryStudnetByStuid(HttpServletRequest request,String stuid) {
	     StudentVO vo=empService.queryStudnetByStuidPre(stuid);
	     vo.setGrouptypeclass(BusinessContextUtils.getUserContext().getGrouptypeclass());
		return JsonUtils.getJsonData(vo);
	}
	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}
	public EmpService getEmpService() {
		return empService;
	}

}

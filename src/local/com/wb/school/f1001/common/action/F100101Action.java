package com.wb.school.f1001.common.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.wb.admin.bo.User;
import com.wb.jdbcutils.Page;
import com.wb.login.model.UserVO;
import com.wb.school.common.bo.Student;
import com.wb.school.common.bo.StudentExt;
import com.wb.school.f1001.common.service.EmpService;
import com.wb.school.f1001.common.vo.ExportSetVO;
import com.wb.school.f1001.common.vo.StudentExtVO;
import com.wb.school.f1001.common.vo.StudentVO;
import com.wb.user.utils.BusinessContextUtils;
import com.wb.utils.web.JsonUtils;
import com.wb.utils.web.RequestUtils;


/**
 * 
 * @author xue
 *
 */
@Controller
@RequestMapping(value="/work/f100101/")
public class F100101Action {

	@Autowired
	private EmpService empService;
	
	@RequestMapping("loadAllUser.action")
	@ResponseBody
	public String loadUserInfo(HttpServletRequest request) {
	     List<User> users=empService.loadAllUser();
		return JsonUtils.getJsonDataFromCollection(users);
	}
	@RequestMapping("index.action")
	public String index(HttpServletRequest request) {
		UserVO user=BusinessContextUtils.getUserContext();
		String id=request.getParameter("id");
		request.setAttribute("user", user);
		request.setAttribute("id", id);
		return "/f10/f1001/f100101/preList";
	}
	@RequestMapping(value="queryStuListByCurentUser.action")
	@ResponseBody
	public String queryUsersByNodeid(StudentVO vo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int pageSize = RequestUtils.getParameter(request, "pageSize", 0);
		int pageIndex = RequestUtils.getParameter(request, "pageIndex", 0);
		Page page = new Page();
		page.setPageno(pageIndex+1);
		page.setPagesize(pageSize);
		this.empService.queryStuListByCurentUser(page,vo);
		return JsonUtils.getJsonByPage(page);
	}
	@RequestMapping(value="queryStuListByCurentUserPub.action")
	@ResponseBody
	public String queryStuListByCurentUserPub(StudentVO vo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int pageSize = RequestUtils.getParameter(request, "pageSize", 0);
		int pageIndex = RequestUtils.getParameter(request, "pageIndex", 0);
		Page page = new Page();
		page.setPageno(pageIndex+1);
		page.setPagesize(pageSize);
		this.empService.queryStuListByCurentUserPub(page,vo);
		return JsonUtils.getJsonByPage(page);
	}
	@RequestMapping(value="queryExport.action")
	@ResponseBody
	public String queryExport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int pageSize = RequestUtils.getParameter(request, "pageSize", 0);
		int pageIndex = RequestUtils.getParameter(request, "pageIndex", 0);
		Page page = new Page();
		page.setPageno(pageIndex+1);
		page.setPagesize(pageSize);
		StudentVO vo=new StudentVO();
		String stu_name=request.getParameter("stu_name");
		String stu_level=request.getParameter("stu_level");
		String s_date=request.getParameter("s_date");
		String e_date=request.getParameter("e_date");
		String iscreatenormal=request.getParameter("iscreatenormal");
		String isstudent=request.getParameter("isstudent");
		String groupname=request.getParameter("groupname");
		String followor=request.getParameter("followor");
		String process=request.getParameter("process");
		String step=request.getParameter("step");
		vo.setStu_name(stu_name);
		vo.setStu_level(stu_level);
		vo.setS_date(s_date);
		vo.setE_date(e_date);
		vo.setIscreatenormal(iscreatenormal);
		vo.setIsstudent(isstudent);
		vo.setGroupname(groupname);
		vo.setFollowor(followor);
		vo.setProcesscode(process);
		vo.setStepcode(step);
		this.empService.queryExport(page,vo);
		return JsonUtils.getJsonByPage(page);
	}
	@RequestMapping(value="queryExportSets.action")
	@ResponseBody
	public String queryExportSets(ExportSetVO vo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<ExportSetVO> vos=this.empService.queryExportSets(vo.getIscall(), vo.getIsstudent());
		return JsonUtils.getJsonDataFromCollection(vos);
	}
	@RequestMapping(value="queryExportSetsByarrays.action")
	@ResponseBody
	public String queryExportSetsByarrays(String arrays,HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<ExportSetVO> vos=this.empService.queryExportSets(arrays);
		return JsonUtils.getJsonDataFromCollection(vos);
	}
	@RequestMapping(value="queryStuCountsByCurentUser.action")
	@ResponseBody
	public String queryStuCountsByCurentUser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int pageSize = RequestUtils.getParameter(request, "pageSize", 0);
		int pageIndex = RequestUtils.getParameter(request, "pageIndex", 0);
		Page page = new Page();
		page.setPageno(pageIndex+1);
		page.setPagesize(pageSize);
		this.empService.queryStuListByCurentUser(page);
		return JsonUtils.getJsonByPage(page);
	}
	@RequestMapping("queryStudnetByStuid.action")
	@ResponseBody
	public String queryStudnetByStuid(HttpServletRequest request,String stuid) {
	     StudentVO vo=empService.queryStudnetByStuid(stuid);
	     vo.setGrouptypeclass(BusinessContextUtils.getUserContext().getGrouptypeclass());
		return JsonUtils.getJsonData(vo);
	}
	@RequestMapping("nextStep.action")
	@ResponseBody
	public String nextStep(HttpServletRequest request,StudentVO vo) {
	     Student student=empService.execNextStep(vo);
		return JsonUtils.getJsonData(student);
	}
	@RequestMapping("execPatchNextStep.action")
	@ResponseBody
	public String execPatchNextStep(String stuids,HttpServletRequest request,StudentVO vo) {
	    String [] stuidArray=stuids.split(",");
		empService.execPatchNextStep(stuidArray);
		return "";
	}
	@RequestMapping("queryStuExtByid.action")
	@ResponseBody
	public String queryStuExtByid(HttpServletRequest request,String stuid) {
	     StudentExtVO vo=empService.queryStuExtByid(Long.valueOf(stuid));
	     StudentVO svo=empService.queryStudnetByStuid(stuid);
	     vo.setStu_cardid(svo.getCardid());
		return JsonUtils.getJsonData(vo);
	}
	@RequestMapping("updateStuExt.action")
	@ResponseBody
	public String queryStuExtByid(HttpServletRequest request,StudentExtVO vo) {
	     StudentExt se=empService.updateStuExt(vo);
		return JsonUtils.getJsonData(se);
	}
	@RequestMapping(value="deleteStudent.action")
	@ResponseBody
	public String deleteStudent(String stuid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		this.empService.deleteStudent(stuid);
		return "";
	}
	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}
	public EmpService getEmpService() {
		return empService;
	}

}

package com.wb.school.f1001.f100102;

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
import com.wb.school.common.bo.StudentPay;
import com.wb.school.f1001.common.service.EmpService;
import com.wb.utils.web.JsonUtils;
import com.wb.utils.web.RequestUtils;

/**
 * 人员基本信息
 * @author xue
 * 2015-9-25
 */
@Controller
@RequestMapping(value="/work/f10010201/")
public class F10010201Action {

	@Autowired
	private EmpService empService;
	
	@RequestMapping("index.action")
	public String index(HttpServletRequest request){
		HttpSession session=request.getSession();
		String id=request.getParameter("id");
		UserVO uservo=(UserVO)session.getAttribute(SessionUtils.USER);
		request.setAttribute("user", uservo);
		request.setAttribute("groupname", uservo.getGroupname());
		request.setAttribute("userid", uservo.getUserid());
		request.setAttribute("id", id);
		return "/f10/f1001/f100102/f10010201/preList";
	}
	@RequestMapping(value="queryStudentPayByStuid.action")
	@ResponseBody
	public String queryStudentPayByStuid(String stuid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		int pageSize = RequestUtils.getParameter(request, "pageSize", 0);
		int pageIndex = RequestUtils.getParameter(request, "pageIndex", 0);
		Page page = new Page();
		page.setPageno(pageIndex+1);
		page.setPagesize(pageSize);
		this.empService.queryStudentPayByStuid(page,Long.valueOf(stuid));
		return JsonUtils.getJsonByPage(page);
	}
	@RequestMapping(value="queryStudentPayByPayid.action")
	@ResponseBody
	public String queryStudentPayByPayid(String payid,HttpServletRequest request,HttpServletResponse response) throws Exception{
		StudentPay pay=this.empService.queryStudentPayByPayid(Long.valueOf(payid));
		return JsonUtils.getJsonData(pay);
	}
	@RequestMapping(value="updateStudentPay.action")
	@ResponseBody
	public String updateStudentPay(StudentPay pay,HttpServletRequest request,HttpServletResponse response) throws Exception{
		this.empService.updateStudentPay(pay);
		return JsonUtils.getJsonData(pay);
	}
	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}
	public EmpService getEmpService() {
		return empService;
	}

}

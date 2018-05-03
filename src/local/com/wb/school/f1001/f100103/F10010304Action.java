package com.wb.school.f1001.f100103;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.jdbcutils.Page;
import com.wb.login.model.UserVO;
import com.wb.school.f1001.common.service.EmpService;
import com.wb.school.f1001.common.vo.StudentVO;
import com.wb.school.f1002.service.RemoteService;
import com.wb.user.utils.BusinessContextUtils;
import com.wb.utils.web.JsonUtils;
import com.wb.utils.web.RequestUtils;


/**
 * 生日提醒
 * @author xue
 *
 */
@Controller
@RequestMapping(value="/work/f10010304/")
public class F10010304Action {


	@Autowired
	private EmpService empService;
	@RequestMapping("index.action")
	public String index(HttpServletRequest request) {
		UserVO user=BusinessContextUtils.getUserContext();
		request.setAttribute("user", user);
		return "/f10/f1001/f100103/f10010304/preList";
	}

	@Scheduled(cron = "0 0 1 * * ?")
	public void saveBirthdayRecommmend(){
		this.empService.saveBirthdayRemind();
	}
	@RequestMapping(value = "queryStuListByBirthdayRemind.action")
	@ResponseBody
	public String queryStuListByBirthdayRemind(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int pageSize = RequestUtils.getParameter(request, "pageSize", 0);
		int pageIndex = RequestUtils.getParameter(request, "pageIndex", 0);
		Page page = new Page();
		page.setPageno(pageIndex + 1);
		page.setPagesize(pageSize);
		StudentVO vo = new StudentVO();
		String stu_name = request.getParameter("stu_name");
		String stu_level = request.getParameter("stu_level");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String iscreatenormal = request.getParameter("iscreatenormal");
		String isstudent = request.getParameter("isstudent");
		String groupname = request.getParameter("groupname");
		String followor = request.getParameter("followor");
		String process = request.getParameter("process");
		String step = request.getParameter("step");
		String remind = request.getParameter("remind");
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
		vo.setRemind(remind);
		this.empService.queryStuListByBirthdayRemind(page, vo);
		return JsonUtils.getJsonByPage(page);
	}
	@RequestMapping(value = "updateBirthdayRemind.action")
	@ResponseBody
	public String updateBirthdayRemind(Long stuid){
		this.empService.updateBirthdayRemind(stuid);
		return "";
	}
	
	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public EmpService getEmpService() {
		return empService;
	}



}

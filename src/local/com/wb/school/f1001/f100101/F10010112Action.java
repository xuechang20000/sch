package com.wb.school.f1001.f100101;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.login.SessionUtils;
import com.wb.login.model.UserVO;
import com.wb.school.common.bo.Student;
import com.wb.school.f1001.common.service.EmpService;
import com.wb.user.utils.BusinessContextUtils;
import com.wb.utils.web.JsonUtils;

/**
 * 人员基本信息
 * @author xue
 * 2015-9-25
 */
@Controller
@RequestMapping(value="/work/f10010112/")
public class F10010112Action {

	@Autowired
	private EmpService empService;
	
	@RequestMapping("index.action")
	public String index(HttpServletRequest request){
		HttpSession session=request.getSession();
		UserVO uservo=(UserVO)session.getAttribute(SessionUtils.USER);
		request.setAttribute("user", uservo);
		request.setAttribute("groupname", uservo.getGroupname());
		request.setAttribute("userid", uservo.getUserid());
		return "/f10/f1001/f100101/f10010112/preList";
	}
	
	@RequestMapping("execFall.action")
	@ResponseBody
	public String execFall(String stuid,String flag,HttpServletRequest request) {
	   Student student=this.empService.execFall(Long.valueOf(stuid), flag);
		return JsonUtils.getJsonData(student);
	}
	
	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}
	public EmpService getEmpService() {
		return empService;
	}

}

package com.wb.school.f1001.f100103;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wb.login.SessionUtils;
import com.wb.login.model.UserVO;
import com.wb.school.f1001.common.service.EmpService;


@Controller
@RequestMapping(value="/work/f10010302/")
public class F10010302Action {

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
		return "/f10/f1001/f100103/f10010302/preList";
	}
	
	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}
	public EmpService getEmpService() {
		return empService;
	}

}

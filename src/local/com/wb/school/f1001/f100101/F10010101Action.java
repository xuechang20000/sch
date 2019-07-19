package com.wb.school.f1001.f100101;

import java.io.InputStream;

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
import com.wb.utils.web.JsonUtils;

/**
 * 人员基本信息
 * 
 * @author xue 2015-9-25
 */
@Controller
@RequestMapping(value = "/work/f10010101/")
public class F10010101Action {

	@Autowired
	private EmpService empService;

	@RequestMapping("index.action")
	public String index(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO uservo = (UserVO) session.getAttribute(SessionUtils.USER);
		request.setAttribute("user", uservo);
		request.setAttribute("groupname", uservo.getGroupname());
		request.setAttribute("userid", uservo.getUserid());
		return "/f10/f1001/f100101/f10010101/index";
	}

	@RequestMapping("uploadPic.action")
	@ResponseBody
	public String uploadPic(HttpServletRequest request, InputStream is) {
		UserVO vo = new UserVO();
		return JsonUtils.getJsonData(vo);
	}

	@RequestMapping("addStudent.action")
	@ResponseBody
	public String addStudent(Student student,String pre, HttpServletRequest request) {
		empService.addStudent(student,pre);
		return JsonUtils.getJsonData(student);
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public EmpService getEmpService() {
		return empService;
	}

}

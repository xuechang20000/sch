package com.wb.school.f1002.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.login.model.UserVO;
import com.wb.school.common.bo.StudentRemote;
import com.wb.school.f1002.service.RemoteService;
import com.wb.school.f1002.vo.StudentRemoteVO;
import com.wb.user.utils.BusinessContextUtils;
import com.wb.utils.web.JsonUtils;

/**
 * 
 * @author xue
 *
 */
@Controller
@RequestMapping(value = "/work/f10020101/")
public class F10020101Action {

	@Autowired
	private RemoteService remoteService;

	@RequestMapping("index.action")
	public String index(HttpServletRequest request) {
		UserVO user = BusinessContextUtils.getUserContext();
		request.setAttribute("user", user);
		request.setAttribute("groupname", user.getGroupname());

		return "/f10/f1002/f100201/f10020101/index";
	}

	@RequestMapping("studentRemoteAdd.action")
	@ResponseBody
	public String studentRemoteAdd(StudentRemote stu, HttpServletRequest request) {
		this.remoteService.execStudentRemoteAdd(stu);
		return JsonUtils.getJsonData(stu);
	}

	@RequestMapping("studentRemoteUpdate.action")
	@ResponseBody
	public String studentRemoteUpdate(StudentRemoteVO stuVO, HttpServletRequest request) {
		StudentRemote stu = this.remoteService.execStudentRemoteUpdate(stuVO);
		return JsonUtils.getJsonData(stu);
	}

	public void setRemoteService(RemoteService remoteService) {
		this.remoteService = remoteService;
	}

	public RemoteService getRemoteService() {
		return remoteService;
	}

}

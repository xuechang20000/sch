package com.wb.school.f1002.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wb.login.model.UserVO;
import com.wb.school.f1002.service.RemoteService;
import com.wb.user.utils.BusinessContextUtils;

/**
 * 
 * @author xue
 *
 */
@Controller
@RequestMapping(value = "/work/f10020103/")
public class F10020103Action {

	@Autowired
	private RemoteService remoteService;

	@RequestMapping("index.action")
	public String index(HttpServletRequest request) {
		UserVO user = BusinessContextUtils.getUserContext();
		request.setAttribute("user", user);
		return "/f10/f1002/f100201/f10020103/preList";
	}

	public void setRemoteService(RemoteService remoteService) {
		this.remoteService = remoteService;
	}

	public RemoteService getRemoteService() {
		return remoteService;
	}

}

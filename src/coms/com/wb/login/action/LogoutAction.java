package com.wb.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wb.login.SessionUtils;

@Controller
public class LogoutAction {
	@RequestMapping(value = "logout.action")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		// clearSessionContext(session);
		ModelAndView m = new ModelAndView();
		m.setViewName("/index");
		return m;
	}

	private void clearSessionContext(HttpSession session) {
		session.setAttribute(SessionUtils.LOGINTOKEN, null);
		session.setAttribute(SessionUtils.USERID, null);
		session.setAttribute(SessionUtils.LOGINNAME, null);
		session.setAttribute(SessionUtils.APPLICATION, null);
	}
}

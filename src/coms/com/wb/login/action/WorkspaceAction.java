package com.wb.login.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wb.login.ApplicationUtils;
import com.wb.login.SessionUtils;
import com.wb.login.model.Resourcevo;
import com.wb.login.service.LoginService;
import com.wb.utils.web.JsonUtils;
import com.wb.utils.web.RequestUtils;

@Controller
public class WorkspaceAction {
	private LoginService loginService;
	
	/**
	 * 加载头部标题栏
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/workspace/loadTopParentResources.action")
	@ResponseBody
	public String loadTopParentResources(HttpServletRequest request, HttpServletResponse response){
		HttpSession session=request.getSession();
		//获取工程ID
		String appid=session.getServletContext().getInitParameter(ApplicationUtils.APPLICATION);
		//获取人员ID
		Long logintoken = (Long)session.getAttribute(SessionUtils.LOGINTOKEN);
		List<Resourcevo> parentResources ;
		if(logintoken==null){
			parentResources =loginService.getPubParentResourceByUserid(appid,"2");
		}else{
			Long userid=(Long)session.getAttribute(SessionUtils.USERID);
			parentResources=loginService.getParentResourceByUserid(userid, appid);
		}
		return JsonUtils.getJsonDataFromCollection(parentResources);
	}
	/**
	 * 加载 左侧导航
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/workspace/loadLeftResources.action")
	@ResponseBody
	public String loadLeftResources(HttpServletRequest request, HttpServletResponse response){
		String parentId=RequestUtils.getParameter(request,"pid", "0");
		HttpSession session=request.getSession();
		//获取工程ID
		String appid=session.getServletContext().getInitParameter(ApplicationUtils.APPLICATION);
		//获取人员ID
		Long logintoken = (Long)session.getAttribute(SessionUtils.LOGINTOKEN);
		//List<String> groupids = (List<String>)session.getAttribute(SessionUtils.GROUPIDS);
		//判断缓存中是否存在
		@SuppressWarnings("unchecked")
		List<Resourcevo> resources=(List<Resourcevo>)session.getAttribute("CACHE_LEFTRESOURCE_"+logintoken+parentId);
		if(resources!=null)
			return JsonUtils.getJsonDataFromCollection(resources);
		
		if(logintoken==null) {
			//resources=loginService.getResourceByResourceidAndResourceType(Long.valueOf(parentId), appid,"2");
		}else{
			resources=loginService.getResourceByResourceid(Long.valueOf(parentId), appid,logintoken);
		}
		//缓存左侧菜单
		session.setAttribute("CACHE_LEFTRESOURCE_"+logintoken+parentId, resources);
		
		return JsonUtils.getJsonDataFromCollection(resources);
	}
	/**
	 * 加载首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/workspace/loadWelcomPage.action")
	public ModelAndView loadWelcomPage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView m = new ModelAndView();
		/*HttpSession session=request.getSession();
		String appid=(String)session.getServletContext().getInitParameter(ApplicationUtils.APPLICATION);
	  //获取人员ID
		Long logintoken = (Long)session.getAttribute(SessionUtils.LOGINTOKEN);
		if(logintoken==null){
			List<Resourcevo> parentResources =loginService.getPubParentResourceByUserid(appid,"1");
		    m.addObject("parentResources",parentResources);
		}*/
		m.setViewName("/welcome");
		return m;
	}
	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}

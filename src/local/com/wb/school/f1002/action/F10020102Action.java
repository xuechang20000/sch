package com.wb.school.f1002.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.jdbcutils.Page;
import com.wb.login.model.UserVO;
import com.wb.school.f1002.service.RemoteService;
import com.wb.school.f1002.vo.StudentRemoteVO;
import com.wb.user.utils.BusinessContextUtils;
import com.wb.utils.web.JsonUtils;
import com.wb.utils.web.RequestUtils;


/**
 * 
 * @author xue
 *
 */
@Controller
@RequestMapping(value="/work/f10020102/")
public class F10020102Action {

	@Autowired
	private RemoteService remoteService;
	
	@RequestMapping("index.action")
	public String index(HttpServletRequest request) {
		UserVO user=BusinessContextUtils.getUserContext();
		request.setAttribute("user", user);
		return "/f10/f1002/f100201/f10020102/preList";
	}
	@RequestMapping(value="queryStuRemoteListByCurentUserPub.action")
	@ResponseBody
	public String queryStuListByCurentUserPub(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int pageSize = RequestUtils.getParameter(request, "pageSize", 0);
		int pageIndex = RequestUtils.getParameter(request, "pageIndex", 0);
		Page page = new Page();
		page.setPageno(pageIndex+1);
		page.setPagesize(pageSize);
		StudentRemoteVO vo=new StudentRemoteVO();
		String stu_name=request.getParameter("stu_name");
		String stu_level2=request.getParameter("stu_level2");
		String s_date=request.getParameter("s_date");
		String e_date=request.getParameter("e_date");
		String nowstatus=request.getParameter("nowstatus");
		String groupname=request.getParameter("groupname");
		String followor=request.getParameter("followor");
		String seq_year=request.getParameter("seq_year");
		vo.setStu_name(stu_name);
		vo.setStu_level2(stu_level2);
		vo.setS_date(s_date);
		vo.setE_date(e_date);
		vo.setNowstatus(nowstatus);
		vo.setGroupname(groupname);
		vo.setFollowor(followor);
		this.remoteService.queryStuListByCurentUserPub(page,vo,seq_year);
		return JsonUtils.getJsonByPage(page);
	}
	@RequestMapping(value="queryStudentRemoteBystuid.action")
	@ResponseBody
	public String queryStudentRemoteBystuid(HttpServletRequest request,HttpServletResponse response){
		String stuid=request.getParameter("stuid");
		StudentRemoteVO vo=this.remoteService.queryStudentRemoteBystuid(Long.valueOf(stuid));
		return JsonUtils.getJsonData(vo);
	}
	@RequestMapping(value="execStudentRemoteUpdate.action")
	@ResponseBody
	public String execStudentRemoteUpdate(StudentRemoteVO vo,HttpServletRequest request,HttpServletResponse response){
		this.remoteService.execStudentRemoteUpdate(vo);
		return JsonUtils.getJsonData(vo);
	}
	@RequestMapping(value="deleteRemoteStudentByStuid.action")
	@ResponseBody
	public String execStudentRemoteUpdate(String stuid,HttpServletRequest request,HttpServletResponse response){
		this.remoteService.deleteRemoteStudentByStuid(stuid);
		return "";
	}
	public void setRemoteService(RemoteService remoteService) {
		this.remoteService = remoteService;
	}

	public RemoteService getRemoteService() {
		return remoteService;
	}


}

package com.wb.school.f1001.f100102;

import java.util.List;

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
import com.wb.school.f1001.common.service.EmpService;
import com.wb.school.f1001.common.vo.ProStepParmVO;
import com.wb.school.f1001.common.vo.StudentVO;
import com.wb.utils.web.JsonUtils;
import com.wb.utils.web.RequestUtils;

/**
 * 人员基本信息
 * 
 * @author xue 2015-9-25
 */
@Controller
@RequestMapping(value = "/work/f10010202/")
public class F10010202Action {

	@Autowired
	private EmpService empService;

	@RequestMapping("index.action")
	public String index(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserVO uservo = (UserVO) session.getAttribute(SessionUtils.USER);
		request.setAttribute("user", uservo);
		request.setAttribute("groupname", uservo.getGroupname());
		request.setAttribute("userid", uservo.getUserid());
		return "/f10/f1001/f100102/f10010202/preList";
	}

	@RequestMapping(value = "queryStuListByCurentUserPub.action")
	@ResponseBody
	public String queryStuListByCurentUserPub(HttpServletRequest request, HttpServletResponse response)
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
		String      oldeducationlevel=request.getParameter("oldeducationlevel");
		String 		examlevel=request.getParameter("examlevel");
		String 		examclass=request.getParameter("examclass");
		String 		firstwishschool=request.getParameter("firstwishschool");
		String 	firstwishspecialty=request.getParameter("firstwishspecialty");
		String iscreatenormal = request.getParameter("iscreatenormal");
		String isstudent = request.getParameter("isstudent");
		String groupname = request.getParameter("groupname");
		String followor = request.getParameter("followor");
		String process = request.getParameter("process");
		String step = request.getParameter("step");
		String cardid = request.getParameter("cardid");
		vo.setStu_name(stu_name);
		vo.setStu_level(stu_level);
		vo.setS_date(s_date);
		vo.setE_date(e_date);
		vo.setOldeducationlevel(oldeducationlevel);
		vo.setExamlevel(examlevel);
		vo.setExamclass(examclass);
		vo.setFirstwishschool(firstwishschool);
		vo.setFirstwishspecialty(firstwishspecialty);
		vo.setIscreatenormal(iscreatenormal);
		vo.setIsstudent(isstudent);
		vo.setGroupname(groupname);
		vo.setFollowor(followor);
		vo.setProcesscode(process);
		vo.setStepcode(step);
		vo.setCardid(cardid);
		this.empService.queryStuListByCurentUserPub(page, vo);
		return JsonUtils.getJsonByPage(page);
	}

	@RequestMapping(value = "queryProcessStepParams.action")
	@ResponseBody
	public String queryProcessStepParams(String param) {
		List<ProStepParmVO> list = this.empService.queryProcessStepParams(param);
		return JsonUtils.getJsonDataFromCollection(list);
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public EmpService getEmpService() {
		return empService;
	}

}

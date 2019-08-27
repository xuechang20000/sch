package com.wb.school.f1001.f100101;

import com.wb.jdbcutils.Page;
import com.wb.login.SessionUtils;
import com.wb.login.model.UserVO;
import com.wb.school.common.bo.StudentPre;
import com.wb.school.f1001.common.service.EmpService;
import com.wb.school.f1001.common.vo.StudentDeleteVO;
import com.wb.school.f1001.common.vo.StudentVO;
import com.wb.user.utils.BusinessContextUtils;
import com.wb.utils.web.JsonUtils;
import com.wb.utils.web.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 人员基本信息
 * @author xue
 * 2015-9-25
 */
@Controller
@RequestMapping(value="/work/f10010114/")
public class F10010114Action {

	@Autowired
	private EmpService empService;
	
	@RequestMapping("index.action")
	public String index(HttpServletRequest request){
		HttpSession session=request.getSession();
		UserVO uservo=(UserVO)session.getAttribute(SessionUtils.USER);
		request.setAttribute("user", uservo);
		request.setAttribute("groupname", uservo.getGroupname());
		request.setAttribute("userid", uservo.getUserid());
		return "/f10/f1001/f100101/f10010114/index";
	}
	@RequestMapping(value="queryStuListByCurentUserPubForDelete.action")
	@ResponseBody
	public String queryStuListByCurentUserPubForDelete(HttpServletRequest request,HttpServletResponse response) throws Exception{
		int pageSize = RequestUtils.getParameter(request, "pageSize", 0);
		int pageIndex = RequestUtils.getParameter(request, "pageIndex", 0);
		Page page = new Page();
		page.setPageno(pageIndex+1);
		page.setPagesize(pageSize);
		StudentVO vo=new StudentVO();
		String stu_name=request.getParameter("stu_name");
		String stu_level=request.getParameter("stu_level");
		String cardid=request.getParameter("cardid");
		String s_date=request.getParameter("s_date");
		String e_date=request.getParameter("e_date");
		String      oldeducationlevel=request.getParameter("oldeducationlevel");
		String 		examlevel=request.getParameter("examlevel");
		String 		examclass=request.getParameter("examclass");
		String 		firstwishschool=request.getParameter("firstwishschool");
		String 	firstwishspecialty=request.getParameter("firstwishspecialty");
		String iscreatenormal=request.getParameter("iscreatenormal");
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
		vo.setCardid(cardid);
		this.empService.queryStuListByCurentUserPubForDelete(page,vo);
		return JsonUtils.getJsonByPage(page);
	}
	@RequestMapping("deleteRefund.action")
	@ResponseBody
	public String deleteRefund( StudentDeleteVO vo){
	     this.empService.deleteRefund(vo);
		return JsonUtils.getJsonData(vo);
	}
	@RequestMapping("updateRefund.action")
	@ResponseBody
	public String updateRefund( StudentDeleteVO vo){
	     this.empService.updateRefund(vo);
		return JsonUtils.getJsonData(vo);
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}
	public EmpService getEmpService() {
		return empService;
	}

}

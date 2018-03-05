package com.wb.school.common.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.school.common.bo.School;
import com.wb.school.common.service.CommonService;
import com.wb.utils.web.JsonUtils;

	/**
	 * 个人账户管理
	 */
	@Controller
	public class SchoolAction {

		@Autowired
		private CommonService commonService;
		
		@RequestMapping("/admin/querySubSchoolsById.action")
		@ResponseBody
		public String querySubSchoolsById(String parentid,HttpServletRequest request,String oldpassword,String password){
			long p_id=(parentid==null?0:Long.valueOf(parentid));
			List<School> schools=commonService.querySubSchoolsById(p_id);
			return JsonUtils.getJsonDataFromCollection(schools);
		}
		@RequestMapping("/admin/addSchool.action")
		@ResponseBody
		public String addSchool(School school,HttpServletRequest request,String oldpassword,String password){
			commonService.addSchool(school);
			return "";
		}
		@RequestMapping("/admin/deleteSchool.action")
		@ResponseBody
		public String deleteSchool(String id,HttpServletRequest request,String oldpassword,String password){
			commonService.deleteSchool(Long.valueOf(id));
			return "";
		}
		@RequestMapping("/admin/querySchoolByid.action")
		@ResponseBody
		public String querySchoolByid(String id,HttpServletRequest request,String oldpassword,String password){
			School school=commonService.querySchoolByid(Long.valueOf(id));
			return JsonUtils.getJsonData(school);
		}
		public CommonService getCommonService() {
			return commonService;
		}
		public void setCommonService(CommonService commonService) {
			this.commonService = commonService;
		}
	
	}

	


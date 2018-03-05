package com.wb.school.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wb.admin.bo.User;
import com.wb.exceptions.BusinessException;
import com.wb.login.SessionUtils;
import com.wb.school.common.service.CommonService;
import com.wb.utils.web.Md5Utils;

	/**
	 * 个人账户管理
	 */
	@Controller
	public class UserAccountAction {

		@Autowired
		private CommonService commonService;
		
		@RequestMapping("/work/userAccount/updatePassword.action")
		@ResponseBody
		public String updatePassword(HttpServletRequest request,String oldpassword,String password){
			HttpSession session=request.getSession();
			Long userid=(Long)session.getAttribute(SessionUtils.USERID);
			User user=commonService.loadUser(userid);
			String oldpassString=Md5Utils.MD5Code(oldpassword);
			String newPassword=Md5Utils.MD5Code(password);
			if(!oldpassString.equals(user.getPassword())){
				throw new BusinessException("原密码不对！");
			}
			commonService.updatePassword(userid,newPassword);
			return "";
		}
		
		public CommonService getCommonService() {
			return commonService;
		}
		public void setCommonService(CommonService commonService) {
			this.commonService = commonService;
		}
	
	}

	


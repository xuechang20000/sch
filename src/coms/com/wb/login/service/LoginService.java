package com.wb.login.service;

import java.util.List;

import com.wb.login.model.LogLoginBO;
import com.wb.login.model.LoginSpDTO;
import com.wb.login.model.Resourcevo;
import com.wb.login.model.UserVO;


public interface LoginService {
	public UserVO getUser(String domain, String Loginname, String password);
	public UserVO getUserByUserId(String Userid);
	public List<Resourcevo> getParentResourceByUserid(Long userid, String application);
	public List<Resourcevo> getPubParentResourceByUserid(String application,String resourcetype);
	public List<Resourcevo> getResourceByResourceidAndResourceType(Long resourceid, String application,String resourcetype);
	public List<Resourcevo> getResourceByResourceid(Long resourceid, String application,Long userid);
	public Resourcevo getUrlByResourceid(Long resourceid, String application);
	public Resourcevo getResourceByResourceid(Long resourceid);
	/**
	 * 调用登陆后台
	 * @param dto
	 */
	public void callLoginSP(LoginSpDTO dto);
	/**
	 * 记录登陆日志 
	 * @param bo
	 */
	public void saveLoginLog(LogLoginBO bo);
}

package com.wb.user.utils;

import com.wb.login.model.UserVO;

/**
 * 
 * @author xue
 * 2015-9-27
 */
public class BusinessContextUtils {
	private static BusinessUserContexThreadLocal userContext =new BusinessUserContexThreadLocal();
	public static UserVO getUserContext(){
		return userContext.get();
	}
	
	public static void setUserContext(UserVO userVO){
		userContext.set(userVO);
	}
}

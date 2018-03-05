package com.wb.user.utils;

import com.wb.login.model.UserVO;

public class BusinessUserContexThreadLocal extends ThreadLocal<UserVO> {

	@Override
	protected UserVO initialValue() {
		// TODO Auto-generated method stub
		return super.initialValue();
	}
}

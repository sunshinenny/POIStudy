package com.utils;

import com.domain.userInfo;

public class dynamicUserInfoFunction {
	userInfo tempUserInfo;

	public dynamicUserInfoFunction(userInfo tempUserInfo) {
		// TODO Auto-generated constructor stub
		this.tempUserInfo = tempUserInfo;
	}

	public String getValueFromUserInfo(String str) {
		String temp = null;
		if (str.equals("id"))
			temp = tempUserInfo.getId();
		if (str.equals("name"))
			temp = tempUserInfo.getName();
		if (str.equals("age"))
			temp = tempUserInfo.getAge();
		if (str.equals("sex"))
			temp = tempUserInfo.getSex();
		if (str.equals("phoneNumber"))
			temp = tempUserInfo.getPhoneNumber();
		if (str.equals("address"))
			temp = tempUserInfo.getAddress();
		if (str.equals("email"))
			temp = tempUserInfo.getEmail();
		return temp;

	}
}

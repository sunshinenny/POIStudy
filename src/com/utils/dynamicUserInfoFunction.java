package com.utils;

import com.domain.userInfo;

public class dynamicUserInfoFunction {
	userInfo tempUserInfo;

	/**
	 * 构造函数,获取userInfo,已封装查询结果
	 * 
	 * @param tempUserInfo
	 */
	public dynamicUserInfoFunction(userInfo tempUserInfo) {
		this.tempUserInfo = tempUserInfo;
	}

	/**
	 * 动态调用userInfo中的getXxx方法
	 * 
	 * @param str-遍历标准字段对照关系时,得到的key,key一定为标准字段
	 * @return 目标数据
	 */
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

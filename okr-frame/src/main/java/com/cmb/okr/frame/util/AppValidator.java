package com.cmb.okr.frame.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class AppValidator {

	/**
	 * 判断是否为手机号码
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isMobile(String obj) {
		return checkRegex(PatternType.MOBILE, obj);
	}

	public static boolean checkRegex(String regex, String val) {
		if (StringUtils.isEmpty(regex) || StringUtils.isEmpty(val)) {
			return false;
		}
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(val);
		return m.matches();
	}
}

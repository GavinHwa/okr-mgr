package com.cmb.okr.frame.security;

import java.security.MessageDigest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MD5 {
	private static final transient Log logger = LogFactory.getLog(MD5.class);

	public static String encrypt(String str) {
		if (str == null) {
			logger.info("MD5 加密字符串为空");
			return null;
		}
		logger.info("MD5 加密前字符串：" + str);

		return encrypt(str.getBytes());
	}

	public static String encrypt(byte[] src) {
		if (src == null) {
			return null;
		}

		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");

			mdInst.update(src);

			byte[] md = mdInst.digest();

			int j = md.length;
			char[] strs = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				strs[(k++)] = hexDigits[(byte0 >>> 4 & 0xF)];
				strs[(k++)] = hexDigits[(byte0 & 0xF)];
			}
			logger.info("MD5 加密后字符串：" + new String(strs).toLowerCase());

			return new String(strs).toLowerCase();
		} catch (Exception e) {
			logger.error("MD5 异常：" + e.getMessage());
		}
		return null;
	}
}

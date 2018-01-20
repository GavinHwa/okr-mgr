package com.cmb.okr.frame.security;

import java.text.DateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityUtils {
	private static Logger logger = LoggerFactory.getLogger(SecurityUtils.class);
	private static final DateFormat df = DateFormat.getDateTimeInstance(2, 3);
	private static final String ALL_CHARS = "abcdefghjklmnpqrstuvwxyz";
	private static final String ALL_NUMBERS = "0123456789";

	private static final String DEFAULT_SALT = "DEFAULT_SALT";

	/**
	 * 获取盐值
	 * 
	 * @param identity
	 * @return
	 */
	public static String getSalt(String identity) {
		String now = df.format(new Date());
		String identityString = identity == null ? RandomStringUtils.randomAlphanumeric(20) : identity;

		return Base64.encodeBase64String(blend(now.getBytes(), identityString.getBytes()));
	}

	/**
	 * 匹配密码
	 * 
	 * @param passphrase
	 *            数据库中密码
	 * @param salt
	 * @param userPassword
	 *            用户输入的密码
	 * @return
	 */
	public static boolean matchPassphrase(String passphrase, String salt, String userPassword) {
		boolean result = passphrase.equalsIgnoreCase(getPassphrase(salt, userPassword));
		if (!result) {
			logger.debug("Passphrase not matching, expecting {} but having {}", passphrase,
					getPassphrase(salt, userPassword));
		}
		return result;
	}

	/**
	 * 获取密码
	 * 
	 * @param salt
	 * @param userPassword
	 * @return
	 */
	public static String getPassphrase(String salt, String userPassword) {
		return DigestUtils.sha1Hex(blend(salt.getBytes(), userPassword.getBytes()));
	}

	private static byte[] blend(byte[] a, byte[] b) {
		byte[] result = new byte[a.length + b.length];
		int ai = 0;
		int bi = 0;
		for (int i = 0; i < result.length; i++) {
			if ((ai == a.length) || ((bi < ai) && (bi < b.length))) {
				result[i] = b[(bi++)];
			} else if ((bi == b.length) || (ai <= bi)) {
				result[i] = a[(ai++)];
			}
		}

		return result;
	}

	public static String getMD5(String key) {
		return getMD5(key, null);
	}

	public static String getMD5(String key, String salt) {
		if (StringUtils.isEmpty(salt)) {
			salt = DEFAULT_SALT;
		}
		return MD5.encrypt(key.concat(salt));
	}

	public static String random(RandomLevel level, int length) {
		switch (level) {
		case WEEK:
			return RandomStringUtils.randomNumeric(length);
		case MEDIUM:
			return RandomStringUtils.random(length, ALL_CHARS);
		case STRONG:
			return RandomStringUtils.random(length, ALL_CHARS.concat(ALL_NUMBERS));
		default:
			return null;
		}
	}

	public static enum RandomLevel {

		WEEK("弱,纯数字"), MEDIUM("中等.纯字母"), STRONG("强,数字加字母");

		private final String desc;

		private RandomLevel(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

	}
}

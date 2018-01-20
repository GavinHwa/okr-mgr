package com.cmb.okr.frame.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 添加该注解表示需要相应权限
 * 
 * @author hf
 *
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthRequired {

	/**
	 * 权限码集合
	 * 
	 * @return
	 */
	String[] value();
}

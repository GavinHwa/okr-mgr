package com.cmb.okr.dao.domain;

import com.cmb.okr.dao.enums.UserType;
import com.cmb.okr.frame.db.BaseEntity;

/**
 * 该类是映射到表的实体类，负责与表进行一一对应。
 * @author: huang 
 * @date: 2018-01-19 08:22:31
 * @since 1.0.0
 */
public class User extends BaseEntity {
private static final long serialVersionUID = 1L;
	

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 密码
	 */
	private String password;
	
	private String salt;

	/**
	 * 头像
	 */
	private String icon;

	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 移事通号
	 */
	private String ystCode;

	/**
	 * 用户类型
            admin:管理员
            normal:普通用户
	 */
	private UserType userType;


	/**
	 * 姓名
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * 姓名
	 */
	public String getName(){
		return  name;
	}

	/**
	 * 登录名
	 */
	public void setLoginName(String loginName){
		this.loginName = loginName;
	}
	
	/**
	 * 登录名
	 */
	public String getLoginName(){
		return  loginName;
	}

	/**
	 * 密码
	 */
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * 密码
	 */
	public String getPassword(){
		return  password;
	}

	/**
	 * 头像
	 */
	public void setIcon(String icon){
		this.icon = icon;
	}
	
	/**
	 * 头像
	 */
	public String getIcon(){
		return  icon;
	}

	/**
	 * 手机号码
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	/**
	 * 手机号码
	 */
	public String getPhone(){
		return  phone;
	}

	/**
	 * 邮箱
	 */
	public void setEmail(String email){
		this.email = email;
	}
	
	/**
	 * 邮箱
	 */
	public String getEmail(){
		return  email;
	}

	/**
	 * 昵称
	 */
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
	
	/**
	 * 昵称
	 */
	public String getNickName(){
		return  nickName;
	}

	/**
	 * 移事通号
	 */
	public void setYstCode(String ystCode){
		this.ystCode = ystCode;
	}
	
	/**
	 * 移事通号
	 */
	public String getYstCode(){
		return  ystCode;
	}

	/**
	 * 用户类型
            admin:管理员
            normal:普通用户
	 */
	public void setUserType(UserType userType){
		this.userType = userType;
	}
	
	/**
	 * 用户类型
            admin:管理员
            normal:普通用户
	 */
	public UserType getUserType(){
		return  userType;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
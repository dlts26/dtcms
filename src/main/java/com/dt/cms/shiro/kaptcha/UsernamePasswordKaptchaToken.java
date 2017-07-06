package com.dt.cms.shiro.kaptcha;


import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 继承Shiro用户验证类：添加验证
 * @author 岳海亮
 * @date 2017年7月6日
 */
public class UsernamePasswordKaptchaToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public UsernamePasswordKaptchaToken() {
		super();
	}

	public UsernamePasswordKaptchaToken(String username, char[] password,boolean rememberMe, String host, String captcha) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
	}

}
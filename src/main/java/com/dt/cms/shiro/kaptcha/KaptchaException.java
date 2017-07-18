package com.dt.cms.shiro.kaptcha;

import org.apache.shiro.authc.AuthenticationException;

/**
 * 验证码异常类
 * @author 岳海亮
 * @date 2017年7月18日
 */
public class KaptchaException extends AuthenticationException {

	private static final long serialVersionUID = 1L;

	public KaptchaException() {
		super();
	}

	public KaptchaException(String message, Throwable cause) {
		super(message, cause);
	}

	public KaptchaException(String message) {
		super(message);
	}

	public KaptchaException(Throwable cause) {
		super(cause);
	}
}
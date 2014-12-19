package com.charmyin.cmstudio.basic.authorize.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Basic login form with JSR-303 Annotation for validation
 * @author charmyin
 *
 */
public class LoginForm {

	@NotNull(message = "用户名不允许为空")
	@Size(min=4, max=50, message="用户名长度必须大于1小于50")
	private String username;
	
	@NotNull(message = "密码不允许为空")
	@Size(min=4, max=50, message="密码长度必须大于1小于50")
	private String passphrase;

	/*@NotNull(message = "验证码不允许为空")
	@Size(min=4, max=5, message="验证码长度必须大于4小于5")*/
	private String validateCode;
	
	
	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUserName() {
		return username;
	}

	public void setUserName(String userName) {
		this.username = userName;
	}


	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}
	public String getPassword() {
		return passphrase;
	}

	public void setPassword(String password) {
		this.passphrase = password;
	}
}

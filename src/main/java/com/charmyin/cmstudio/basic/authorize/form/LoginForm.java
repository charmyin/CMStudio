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
	@Size(min=4, max=50, message="用户名长度必须大于4小于50")
	private String username;
	
	@NotNull(message = "密码不允许为空")
	@Size(min=8, max=50, message="密码长度必须大于4小于50")
	private String passphrase;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}
}
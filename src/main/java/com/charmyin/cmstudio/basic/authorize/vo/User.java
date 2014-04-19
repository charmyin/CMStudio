package com.charmyin.cmstudio.basic.authorize.vo;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import com.charmyin.cmstudio.basic.pagination.annotation.Paging;
import com.charmyin.cmstudio.basic.pagination.page.Page;

/**
 * User VO
 * @author YinCM
 * @since 2013-9-2 16:57:30
 */
@Paging(field = "pageVO")
public class User {	

	private Page pageVO;

    public Page getPageVO() {
        return pageVO;
    }

    public void setPageVO(Page pageVO) {
        this.pageVO = pageVO;
    }
    
	private Integer id;
	
	@NotNull(message = "登录名不允许为空")
	@Size(max=50, message="登录名长度应小于50")
	private String loginId;
	
	@NotNull(message = "用户昵称不允许为空")
	@Size(max=50, message="用户昵称长度应小于50")
	private String 	name;
 
	@NotNull(message = "所属组织id不允许为空")
	@Min(value=0, message="所属组织id需大于等于0")
	@Max(value=999999, message="所属组织id需小于999999")
	private Integer organizationId;
	
	@NotNull(message = "Email不允许为空")
	@Size(max=100, message="Email长度应小于100")
	@Email(message="邮件格式有误")
	private String email;
 
	@NotNull(message = "密码不允许为空")
	@Size(max=50, message="密码长度应小于50")
	private String passphrase;
 
	private String salt;
 
	private Boolean state;
 
	private Date dateCreated;
 
	@Size(max=200, message="备注长度应小于200")
	private String remark;
	
	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public Integer getId() {
		return id==null?0:id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getOrganizationId() {
		return organizationId==null?0:organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

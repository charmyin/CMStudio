package com.charmyin.cmstudio.basic.authorize.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Role VO
 * @author YinCM
 * @since 2013-9-2 16:57:30
 */
public class Role {	
	
	@NotNull(message = "密码不允许为空")
	@Size(min=1, max=50, message="名称长度应大于1小于50")
	private String name;
	
	@Size(max=200, message="描述长度应小于200")
	private String description;
	
	private Integer organizationId;
	
	private Boolean state;
	
	@Size(max=200, message="备注长度应小于200")
	private String remark;

	@Size(max=1000, message="权限过多或者权限字符串过长")
	private String permission;
	
	@Size(max=1000, message="所选菜单过多或者菜单字符串过长")
	private String menu;

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getOrganizationId() {
		return organizationId==null?0:organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
	
}

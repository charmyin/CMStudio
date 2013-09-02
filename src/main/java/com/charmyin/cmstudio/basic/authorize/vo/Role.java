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
	
	private Integer id;
	
	@Size(max=50, message="名称长度应小于50")
	private String name;
	
	@Size(max=200, message="名称长度应小于200")
	private String description;
	
	@NotNull
	@Min(value=0, message="所属组织id需大于等于0")
	@Max(value=999999, message="所属组织id需小于999999")
	private Integer organizationId;
	
	private Boolean state;
	
	@Size(max=200, message="备注长度应小于200")
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return organizationId;
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

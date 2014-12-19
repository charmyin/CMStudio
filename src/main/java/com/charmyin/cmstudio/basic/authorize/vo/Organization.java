package com.charmyin.cmstudio.basic.authorize.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Organization VO
 * @author YinCM
 * @since 2013-9-2 16:57:30
 */
public class Organization {
	
	private Integer id;
	
	@NotNull
	@Size(max=200, message="备注长度应小于200")
	private String name;
	
	@Min(value=1, message="父节点id需大于等于1")
	@Max(value=999999, message="序号需小于999999")
	private Integer parentId;
	
	@Max(value=999999, message="序号需小于999999")
	private Integer orderNumber;
	
	@Size(max=200, message="备注长度应小于200")
	private String remark;
	
	//机构类型，1公司，0部门
	private Integer organizationType;

	public Integer getOrganizationType() {
		return organizationType;
	}

	public void setOrganizationType(Integer organizationType) {
		this.organizationType = organizationType;
	}

	public Integer getId() {
		return id==null?0:id;
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

	public Integer getParentId() {
		return parentId==null?0:parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}



	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

package com.charmyin.cmstudio.basic.authorize.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * PO class used for users' menus
 * @author charmyin
 *
 */
public class Menu {
	
	//Menu id
	private Integer id;
	
	//Menu name used to show on the tree
	@NotNull(message = "密码不允许为空")
	@Size(min=1, max=20, message="名称长度应大于1小于20")
	private String name;
	
	//Parent id
	@Min(value=1, message="父节点id需大于等于1")
	@Max(value=999999, message="序号需小于999999")
	private Integer parentId;
	
	//The url which is linked to the page
	@Size(max=300, message="URL长度应小于300")
	private String linkUrl;
	
	//The order of the menu in the tree list.Maybe it's better to be "int"
	
	@Max(value=999999, message="序号需小于999999")
	private Integer orderNumber;
	
	//The remark to add description of this menu 
	@Size(max=200, message="备注长度应小于200")
	private String remark;
	
	//Full permission to get this menu can read and write, it's json array
	@Size(max=1000, message="权限过多或者权限字符串过长")
	private String fullPermission;
	
	//Read permission to get this menu just can read
	private String readPermission;
	
	public int getId() {
		return id==null?0:id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId==null?0:parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
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
	public String getFullPermission() {
		return fullPermission;
	}
	public void setFullPermission(String fullPermission) {
		this.fullPermission = fullPermission;
	}
	public String getReadPermission() {
		return readPermission;
	}
	public void setReadPermission(String readPermission) {
		this.readPermission = readPermission;
	}
	
	
}

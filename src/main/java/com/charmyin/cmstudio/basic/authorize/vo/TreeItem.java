package com.charmyin.cmstudio.basic.authorize.vo;

/**
 * 
 * @author YinCM
 * @since 2014-08-01 15:05
 *
 */
public class TreeItem {
	
	//唯一标志
	int id;
	//父节点唯一标志
	int parentId;
	
	//节点类型（冗余字段）
	int type;
	
	//名称
	String name;
	//是否可选
	boolean chkDisabled;
	
	private Integer orderNumber;
	
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isChkDisabled() {
		return chkDisabled;
	}
	public void setChkDisabled(boolean chkDisabled) {
		this.chkDisabled = chkDisabled;
	}
	
	

}

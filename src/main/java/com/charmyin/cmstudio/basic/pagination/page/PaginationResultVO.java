package com.charmyin.cmstudio.basic.pagination.page;

import java.util.List;

/**
 * 
 * @author YinCM
 * @Date 2014-4-20 21:08:45
 * @category Generate {"total":"28","rows":[{"itemid":"EST-28","productid":"K9-RT-01","listprice":"155.29","unitcost":"90.00","status":"P","attr1":"Adult Female"},{"itemid":"EST-3","productid":"FI-SW-02","listprice":"18.50","unitcost":"12.00","status":"P","attr1":"Toothless"},{"itemid":"EST-4","productid":"FI-FW-01","listprice":"18.50","unitcost":"12.00","status":"P","attr1":"Spotted"},{"itemid":"EST-5","productid":"FI-FW-01","listprice":"18.50","unitcost":"12.00","status":"P","attr1":"Spotless"},{"itemid":"EST-6","productid":"K9-BD-01","listprice":"18.50","unitcost":"12.00","status":"P","attr1":"Male Adult"},{"itemid":"EST-7","productid":"K9-BD-01","listprice":"18.50","unitcost":"12.00","status":"P","attr1":"Female Puppy"},{"itemid":"EST-8","productid":"K9-PO-02","listprice":"18.50","unitcost":"12.00","status":"P","attr1":"Male Puppy"},{"itemid":"EST-9","productid":"K9-DL-01","listprice":"18.50","unitcost":"12.00","status":"P","attr1":"Spotless Male Puppy"}]}
 */
public class PaginationResultVO {
	private String total;
	private Object rows;
	private String success;
	private String msg;
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
 
	
	
}

package com.wb.utils.web.common;

/**
 * AjaxObject说明：AjaxObject对象用来封装SpringMVC返回的json对象
 * 
 **/

public class AjaxObject implements java.io.Serializable{
	private boolean success;
	private Object result;
	
	public AjaxObject(){}
	public AjaxObject(boolean s, Object d){
		this.success = s;
		this.result = d;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
}

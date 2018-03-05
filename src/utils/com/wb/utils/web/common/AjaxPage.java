package com.wb.utils.web.common;

import java.util.List;

public class AjaxPage implements java.io.Serializable {
	private Long total;
	private List data;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}
}

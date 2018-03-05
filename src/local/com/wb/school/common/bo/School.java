package com.wb.school.common.bo;

public class School {
	   private Long id;//                   number(16)           not null,
	   private Long parentid ;//            number(16),
	   private String name  ;//               varchar2(50),
	   private String type  ;//               varchar2(2),
	   private String ext   ;//               varchar2(20),
	   private String removed  ;//            varchar2(1),
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentid() {
		return parentid;
	}
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getRemoved() {
		return removed;
	}
	public void setRemoved(String removed) {
		this.removed = removed;
	}
	   
}

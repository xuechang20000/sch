package com.wb.admin.vo;

import java.util.List;

public class TreeDTO {

	private Long id;
	private Long pid;
	private String text;
	private boolean checked;
	private boolean expanded;
	private String _id;
	private String _pid;
	private String _uid;
	private String _level;
	private String _state;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_pid() {
		return _pid;
	}
	public void set_pid(String _pid) {
		this._pid = _pid;
	}
	public String get_uid() {
		return _uid;
	}
	public void set_uid(String _uid) {
		this._uid = _uid;
	}
	public String get_level() {
		return _level;
	}
	public void set_level(String _level) {
		this._level = _level;
	}
	public String get_state() {
		return _state;
	}
	public void set_state(String _state) {
		this._state = _state;
	}
	private List<TreeDTO> children;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<TreeDTO> getChildren() {
		return children;
	}
	public void setChildren(List<TreeDTO> children) {
		this.children = children;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	
}

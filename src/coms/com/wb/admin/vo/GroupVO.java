package com.wb.admin.vo;

import com.wb.admin.bo.Group;

public class GroupVO extends Group {
	private Long _id;
	private Long _uid;

	public Long get_id() {
		return _id;
	}

	public void set_id(Long _id) {
		this._id = _id;
	}

	public Long get_uid() {
		return _uid;
	}

	public void set_uid(Long _uid) {
		this._uid = _uid;
	}

}

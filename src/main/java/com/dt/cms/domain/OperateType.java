package com.dt.cms.domain;

public enum OperateType {
	/**
	 * 添加
	 */
	ADD(1),

	/**
	 * 更新
	 */
	UPDATE(2),

	/**
	 * 删除
	 */
	DELETE(3);
	private int index;

	OperateType(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	/**
	 * 转化为操作类型字符串
	* @param index
	* @return
	 */
	public static String toString(int index) {
		switch (index) {
		case 1:
			return "ADD";
		case 2:
			return "UPDATE";
		case 3:
			return "DELETE";
		default:
			return "UNKOWN";
		}
	}
}
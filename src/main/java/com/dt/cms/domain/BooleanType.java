package com.dt.cms.domain;

public enum BooleanType {
	YES(1), NO(0);
	private int value;

	BooleanType(int i) {
		this.value = i;
	}

	public int value() {
		return this.value;
	}

	public static void main(String[] args) {
		System.out.println(BooleanType.YES.value());
	}
}

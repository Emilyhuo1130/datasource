package com.zhang.javaTest.guavaTest;

import com.google.common.base.Preconditions;

public class PreconditionsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String ss=null;
		Preconditions.checkNotNull(ss,"空的值。。。。。。。。。。。。");
		ss="33";
		Preconditions.checkNotNull(ss,"kongzhi.....");
	}

}

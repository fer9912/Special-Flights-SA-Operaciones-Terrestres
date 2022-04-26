package com.springboot.app.utils;

public class Util {

	public static boolean isEmptyOrNull(String any) {
		return any == null || any.trim().equals("");
	}

}

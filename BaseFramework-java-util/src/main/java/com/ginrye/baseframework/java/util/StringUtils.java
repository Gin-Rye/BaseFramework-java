package com.ginrye.baseframework.java.util;

public class StringUtils {
	public static boolean equals(String str1, String str2) {
		if(str1 == null) {
			if(str2 == null) {
				return true;
			} else {
				return false;
			}
		} else {
			if(str2 == null) {
				return false;
			} else {
				return str1.equals(str2);
			}
		}
	}
}
